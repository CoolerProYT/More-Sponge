package com.coolerpromc.moresponge.platform;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.platform.services.IRegistryHelper;
import com.coolerpromc.moresponge.platform.util.BlockEntityTypeFactory;
import com.coolerpromc.moresponge.platform.util.CreativeTabOutput;
import com.coolerpromc.moresponge.platform.util.MenuFactory;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.gamerules.GameRule;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.*;

import java.util.Arrays;
import java.util.function.*;

public class NeoForgeRegistryHelper implements IRegistryHelper {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constants.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Constants.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Constants.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Constants.MODID);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Constants.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.createEntities(Constants.MODID);
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, Constants.MODID);
    public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(Registries.GAME_RULE, Constants.MODID);

    @Override
    public <T extends Block> RegistryHandler.Blocks<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, BlockBehaviour.Properties p) {
        DeferredBlock<T> deferredBlock = BLOCKS.registerBlock(name, func, () -> p);

        return new RegistryHandler.Blocks<>() {
            @Override
            public Holder<Block> holder() {
                return deferredBlock;
            }

            @Override
            public T get() {
                return deferredBlock.get();
            }
        };
    }

    @Override
    public <T extends Item> RegistryHandler.Items<T> registerItem(String name, Function<Item.Properties, T> func, Item.Properties p) {
        DeferredItem<T> deferredItem = ITEMS.registerItem(name, func, () -> p);

        return new RegistryHandler.Items<>() {
            @Override
            public Holder<Item> holder() {
                return deferredItem;
            }

            @Override
            public T get() {
                return deferredItem.get();
            }
        };
    }

    @Override
    public <T extends BlockItem> RegistryHandler.Items<T> registerBlockItem(String name, RegistryHandler.Blocks<? extends Block> block, BiFunction<Block, Item.Properties, T> item) {
        return registerItem(name, properties -> item.apply(block.get(), properties));
    }

    @Override
    public RegistryHandler<CreativeModeTab, CreativeModeTab> registerCreativeTab(String name, Supplier<ItemStack> icon, Component title, BiConsumer<CreativeTabOutput, CreativeModeTab.ItemDisplayParameters> entries) {
        DeferredHolder<CreativeModeTab, CreativeModeTab> deferredHolder = CREATIVE_TABS.register(name, () -> CreativeModeTab.builder().icon(icon).title(title).displayItems((params, output) -> entries.accept(output::accept, params)).build());

        return new RegistryHandler<>() {
            @Override
            public Holder<CreativeModeTab> holder() {
                return deferredHolder;
            }

            @Override
            public CreativeModeTab get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T extends BlockEntity> RegistryHandler<BlockEntityType<?>, BlockEntityType<T>> registerBlockEntityType(String name, BlockEntityTypeFactory<T> factory, Supplier<? extends Block>... blocks) {
        DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> deferredHolder = BLOCK_ENTITIES.register(name, () -> new BlockEntityType<>(factory::create, Arrays.stream(blocks).map(Supplier::get).toArray(Block[]::new)));

        return new RegistryHandler<>() {
            @Override
            public Holder<BlockEntityType<?>> holder() {
                return deferredHolder;
            }

            @Override
            public BlockEntityType<T> get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T extends Recipe<?>> RegistryHandler<RecipeType<?>, RecipeType<T>> registerRecipeType(String name) {
        DeferredHolder<RecipeType<?>, RecipeType<T>> deferredHolder = RECIPE_TYPES.register(name, () -> RecipeType.simple(Constants.id(name)));

        return new RegistryHandler<>() {
            @Override
            public Holder<RecipeType<?>> holder() {
                return deferredHolder;
            }

            @Override
            public RecipeType<T> get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T extends Recipe<?>> RegistryHandler<RecipeSerializer<?>, RecipeSerializer<T>> registerRecipeSerializer(String name, RecipeSerializer<T> serializer) {
        DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> deferredHolder = RECIPE_SERIALIZERS.register(name, () -> serializer);

        return new RegistryHandler<>() {
            @Override
            public Holder<RecipeSerializer<?>> holder() {
                return deferredHolder;
            }

            @Override
            public RecipeSerializer<T> get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T extends AbstractContainerMenu, D> RegistryHandler<MenuType<?>, MenuType<T>> registerMenuType(String name, MenuFactory<T, D> factory, StreamCodec<? super RegistryFriendlyByteBuf, D> data) {
        DeferredHolder<MenuType<?>, MenuType<T>> deferredHolder = MENUS.register(name, () -> IMenuTypeExtension.create((id, inv, buf) -> factory.create(id, inv, data.decode(buf))));

        return new RegistryHandler<>() {
            @Override
            public Holder<MenuType<?>> holder() {
                return deferredHolder;
            }

            @Override
            public MenuType<T> get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T extends Entity> RegistryHandler.Entities<T> registerEntity(String name, EntityType.EntityFactory<T> factory, MobCategory category, UnaryOperator<EntityType.Builder<T>> builder) {
        ResourceKey<EntityType<?>> key = IRegistryHelper.entityKey(name);
        DeferredHolder<EntityType<?>, EntityType<T>> deferredHolder = ENTITIES.register(name, () -> builder.apply(EntityType.Builder.of(factory, category)).build(key));
        
        return new RegistryHandler.Entities<>() {
            @Override
            public Holder<EntityType<?>> holder() {
                return deferredHolder;
            }

            @Override
            public EntityType<T> get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T> RegistryHandler<EntityDataSerializer<?>, EntityDataSerializer<T>> registerEntityDataSerializer(String name, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<T>> deferredHolder = ENTITY_SERIALIZERS.register(name, () -> EntityDataSerializer.forValueType(streamCodec));

        return new RegistryHandler<>() {
            @Override
            public Holder<EntityDataSerializer<?>> holder() {
                return deferredHolder;
            }

            @Override
            public EntityDataSerializer<T> get() {
                return deferredHolder.get();
            }
        };
    }

    @Override
    public <T> RegistryHandler<GameRule<?>, GameRule<T>> registerGameRule(String name, GameRule<T> gameRule) {
        DeferredHolder<GameRule<?>, GameRule<T>> deferredHolder = GAME_RULES.register(name, () -> gameRule);

        return new RegistryHandler<>() {
            @Override
            public Holder<GameRule<?>> holder() {
                return deferredHolder;
            }

            @Override
            public GameRule<T> get() {
                return deferredHolder.get();
            }
        };
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        CREATIVE_TABS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
        MENUS.register(eventBus);
        ENTITIES.register(eventBus);
        ENTITY_SERIALIZERS.register(eventBus);
        GAME_RULES.register(eventBus);
    }
}
