package com.coolerpromc.moresponge.block.entity.custom;

import com.coolerpromc.moresponge.MoreSpongeClient;
import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import com.coolerpromc.moresponge.recipe.custom.FreezingRecipe;
import com.coolerpromc.moresponge.screen.custom.FreezerMenu;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class FreezerBlockEntity extends BlockEntity implements MenuProvider, RecipeCraftingHolder {
    private static final Codec<Map<ResourceKey<Recipe<?>>, Integer>> RECIPES_USED_CODEC = Codec.unboundedMap(Recipe.KEY_CODEC, Codec.INT);
    public static final Map<Item, Integer> FUEL_VALUES = Map.of(
            Items.SNOWBALL, 50,
            Items.SNOW, 50,
            Items.SNOW_BLOCK, 200,
            Items.ICE, 200,
            Items.PACKED_ICE, 600,
            Items.BLUE_ICE, 1800,
            Items.POWDER_SNOW_BUCKET, 800
    );
    public static final List<ItemStack> FUELS = FUEL_VALUES.keySet().stream().map(Item::getDefaultInstance).toList();

    protected final SimpleContainer inputSlot = new SimpleContainer(1){
        @Override
        public void setItem(int slot, ItemStack itemStack) {
            ItemStack oldStack = this.getItem(slot);
            boolean same = !itemStack.isEmpty() && ItemStack.isSameItemSameComponents(oldStack, itemStack);
            this.items.set(slot, itemStack);
            itemStack.limitSize(this.getMaxStackSize(itemStack));
            if (slot == 0 && !same && FreezerBlockEntity.this.level instanceof ServerLevel serverLevel) {
                FreezerBlockEntity.this.cookingTotalTime = getTotalCookTime(serverLevel, FreezerBlockEntity.this);
                FreezerBlockEntity.this.cookingTimer = 0;
                this.setChanged();
            }
        }

        @Override
        public boolean canPlaceItem(int slot, @NonNull ItemStack itemStack) {
            return FreezerBlockEntity.this.hasRecipe(itemStack);
        }
    };
    protected final SimpleContainer fuelSlot = new SimpleContainer(1){
        @Override
        public boolean canPlaceItem(int slot, @NonNull ItemStack itemStack) {
            return FreezerBlockEntity.FUEL_VALUES.getOrDefault(itemStack.getItem(), -1) > 0;
        }
    };
    protected final SimpleContainer resultSlot = new SimpleContainer(1){
        @Override
        public boolean canPlaceItem(int slot, @NonNull ItemStack itemStack) {
            return false;
        }
    };
    private int litTimeRemaining;
    private int litTotalTime;
    private int cookingTimer;
    private int cookingTotalTime;
    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int dataId) {
            return switch (dataId) {
                case 0 -> FreezerBlockEntity.this.litTimeRemaining;
                case 1 -> FreezerBlockEntity.this.litTotalTime;
                case 2 -> FreezerBlockEntity.this.cookingTimer;
                case 3 -> FreezerBlockEntity.this.cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int dataId, int value) {
            switch (dataId) {
                case 0:
                    FreezerBlockEntity.this.litTimeRemaining = value;
                    break;
                case 1:
                    FreezerBlockEntity.this.litTotalTime = value;
                    break;
                case 2:
                    FreezerBlockEntity.this.cookingTimer = value;
                    break;
                case 3:
                    FreezerBlockEntity.this.cookingTotalTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<SingleRecipeInput, FreezingRecipe> quickCheck = RecipeManager.createCheck(MSRecipes.FREEZER_TYPE.get());

    public FreezerBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MSBlockEntities.FREEZER.get(), worldPosition, blockState);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        this.cookingTimer = input.getShortOr("cooking_time_spent", (short)0);
        this.cookingTotalTime = input.getShortOr("cooking_total_time", (short)0);
        this.litTimeRemaining = input.getShortOr("lit_time_remaining", (short)0);
        this.litTotalTime = input.getShortOr("lit_total_time", (short)0);
        ContainerHelper.loadAllItems(input.childOrEmpty("input"), this.inputSlot.items);
        ContainerHelper.loadAllItems(input.childOrEmpty("fuel"), this.fuelSlot.items);
        ContainerHelper.loadAllItems(input.childOrEmpty("result"), this.resultSlot.items);
        this.recipesUsed.clear();
        this.recipesUsed.putAll(input.read("RecipesUsed", RECIPES_USED_CODEC).orElse(Map.of()));
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putShort("cooking_time_spent", (short)this.cookingTimer);
        output.putShort("cooking_total_time", (short)this.cookingTotalTime);
        output.putShort("lit_time_remaining", (short)this.litTimeRemaining);
        output.putShort("lit_total_time", (short)this.litTotalTime);
        ContainerHelper.saveAllItems(output.child("input"), this.inputSlot.items);
        ContainerHelper.saveAllItems(output.child("fuel"), this.fuelSlot.items);
        ContainerHelper.saveAllItems(output.child("result"), this.resultSlot.items);
        output.store("RecipesUsed", RECIPES_USED_CODEC, this.recipesUsed);
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, FreezerBlockEntity blockEntity) {
        boolean changed = false;
        boolean isLit;
        boolean wasLit;
        if (blockEntity.litTimeRemaining > 0) {
            wasLit = true;
            blockEntity.litTimeRemaining--;
            isLit = blockEntity.litTimeRemaining > 0;
        } else {
            wasLit = false;
            isLit = false;
        }

        ItemStack fuel = blockEntity.fuelSlot.getItem(0);
        ItemStack ingredient = blockEntity.inputSlot.getItem(0);
        boolean hasIngredient = !ingredient.isEmpty();
        boolean hasFuel = !fuel.isEmpty();
        if (isLit || hasFuel && hasIngredient) {
            if (hasIngredient) {
                SingleRecipeInput input = new SingleRecipeInput(ingredient);
                RecipeHolder<FreezingRecipe> recipe = blockEntity.quickCheck.getRecipeFor(input, level).orElse(null);
                if (recipe != null) {
                    int maxStackSize = blockEntity.inputSlot.getMaxStackSize();
                    ItemStack burnResult = recipe.value().assemble(input);
                    if (!burnResult.isEmpty() && canBurn(blockEntity.resultSlot.getItem(0), maxStackSize, burnResult)) {
                        if (!isLit) {
                            int newLitTime = blockEntity.getBurnDuration(fuel);
                            blockEntity.litTimeRemaining = newLitTime;
                            blockEntity.litTotalTime = newLitTime;
                            if (newLitTime > 0) {
                                consumeFuel(blockEntity.fuelSlot, fuel);
                                isLit = true;
                                changed = true;
                            }
                        }

                        if (isLit) {
                            blockEntity.cookingTimer++;
                            if (blockEntity.cookingTimer == blockEntity.cookingTotalTime) {
                                blockEntity.cookingTimer = 0;
                                blockEntity.cookingTotalTime = recipe.value().cookingTime();
                                burn(blockEntity, ingredient, burnResult);
                                blockEntity.setRecipeUsed(recipe);
                                changed = true;
                            }
                        } else {
                            blockEntity.cookingTimer = 0;
                        }
                    } else {
                        blockEntity.cookingTimer = 0;
                    }
                }
            } else {
                blockEntity.cookingTimer = 0;
            }
        } else if (blockEntity.cookingTimer > 0) {
            blockEntity.cookingTimer = Mth.clamp(blockEntity.cookingTimer - 2, 0, blockEntity.cookingTotalTime);
        }

        if (wasLit != isLit) {
            changed = true;
            state = state.setValue(AbstractFurnaceBlock.LIT, isLit);
            level.setBlock(pos, state, 3);
        }

        if (changed) {
            setChanged(level, pos, state);
        }
    }

    private static void consumeFuel(SimpleContainer fuelSlot, ItemStack fuel) {
        Item fuelItem = fuel.getItem();
        fuel.shrink(1);
        if (fuel.isEmpty()) {
            ItemStackTemplate remainder = fuelItem.getCraftingRemainder();
            fuelSlot.setItem(0, remainder != null ? remainder.create() : ItemStack.EMPTY);
        }
    }

    private static boolean canBurn(ItemStack resultItemStack, int maxStackSize, ItemStack burnResult) {
        if (resultItemStack.isEmpty()) {
            return true;
        } else if (!ItemStack.isSameItemSameComponents(resultItemStack, burnResult)) {
            return false;
        } else {
            int resultCount = resultItemStack.getCount() + burnResult.count();
            int maxResultCount = Math.min(maxStackSize, burnResult.getMaxStackSize());
            return resultCount <= maxResultCount;
        }
    }

    private static void burn(FreezerBlockEntity blockEntity, ItemStack inputItemStack, ItemStack result) {
        ItemStack resultItemStack = blockEntity.resultSlot.getItem(0);
        if (resultItemStack.isEmpty()) {
            blockEntity.resultSlot.setItem(0, result.copy());
        } else {
            resultItemStack.grow(result.getCount());
        }

        inputItemStack.shrink(1);
    }

    protected int getBurnDuration(ItemStack itemStack) {
        return FUEL_VALUES.getOrDefault(itemStack.getItem(), -1);
    }

    private static int getTotalCookTime(ServerLevel level, FreezerBlockEntity entity) {
        SingleRecipeInput input = new SingleRecipeInput(entity.inputSlot.getItem(0));
        return entity.quickCheck.getRecipeFor(input, level).map(recipeHolder -> recipeHolder.value().cookingTime()).orElse(200);
    }

    private boolean hasRecipe(ItemStack stack){
        SingleRecipeInput input = new SingleRecipeInput(stack);
        if (level instanceof ServerLevel serverLevel){
            return this.quickCheck.getRecipeFor(input, serverLevel).isPresent();
        }
        else{
            return MoreSpongeClient.syncedRecipes.getRecipesFor(MSRecipes.FREEZER_TYPE.get(), input, level).findAny().isPresent();
        }
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<RecipeHolder<?>> recipesToAward = this.getRecipesToAwardAndPopExperience(player.level(), player.position());
        player.awardRecipes(recipesToAward);

        for (RecipeHolder<?> recipe : recipesToAward) {
            player.triggerRecipeCrafted(recipe, this.inputSlot.getItems());
        }

        this.recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 position) {
        List<RecipeHolder<?>> recipesToAward = Lists.newArrayList();

        for (Reference2IntMap.Entry<ResourceKey<Recipe<?>>> entry : this.recipesUsed.reference2IntEntrySet()) {
            level.recipeAccess().byKey(entry.getKey()).ifPresent(recipe -> {
                recipesToAward.add(recipe);
                createExperience(level, position, entry.getIntValue(), ((FreezingRecipe)recipe.value()).experience());
            });
        }

        return recipesToAward;
    }

    private static void createExperience(ServerLevel level, Vec3 position, int amount, float value) {
        int xpReward = Mth.floor(amount * value);
        float xpFraction = Mth.frac(amount * value);
        if (xpFraction != 0.0F && level.getRandom().nextFloat() < xpFraction) {
            xpReward++;
        }

        ExperienceOrb.award(level, position, xpReward);
    }

    @Override
    public void preRemoveSideEffects(@NonNull BlockPos pos, @NonNull BlockState state) {
        super.preRemoveSideEffects(pos, state);
        if (this.level instanceof ServerLevel serverLevel) {
            this.getRecipesToAwardAndPopExperience(serverLevel, Vec3.atCenterOf(pos));
        }
    }

    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("block.moresponge.freezer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NonNull Inventory inventory, @NonNull Player player) {
        return new FreezerMenu(containerId, inventory, this, data);
    }

    public ContainerData getData() {
        return data;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipeUsed) {
        if (recipeUsed != null) {
            ResourceKey<Recipe<?>> id = recipeUsed.id();
            this.recipesUsed.addTo(id, 1);
        }
    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    public SimpleContainer getInputSlot() {
        return inputSlot;
    }

    public SimpleContainer getFuelSlot() {
        return fuelSlot;
    }

    public SimpleContainer getResultSlot() {
        return resultSlot;
    }

    public @Nullable SimpleContainer getCapabilityBySide(Direction direction){
        if (direction == null) return null;
        if (direction == Direction.DOWN) return getResultSlot();
        if (direction == Direction.UP) return getInputSlot();
        return getFuelSlot();
    }
}
