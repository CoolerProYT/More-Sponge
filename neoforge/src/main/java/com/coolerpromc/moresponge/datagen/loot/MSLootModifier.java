package com.coolerpromc.moresponge.datagen.loot;

import com.coolerpromc.moresponge.datagen.MSGlobalLootModifierProvider;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class MSLootModifier extends LootModifier {
    public static final MapCodec<MSLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(inst.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(MSLootModifier::item),
                    Codec.INT.fieldOf("min").forGetter(MSLootModifier::min),
                    Codec.INT.fieldOf("max").forGetter(MSLootModifier::max)
            )).apply(inst, MSLootModifier::new)
    );

    private final Item item;
    private final int min;
    private final int max;

    public MSLootModifier(LootItemCondition[] conditionsIn, Item item, int min, int max) {
        super(conditionsIn);
        this.item = item;
        this.min = min;
        this.max = max;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        RandomSource random = context.getRandom();
        int count = random.nextIntBetweenInclusive(min, max);
        generatedLoot.add(new ItemStack(item, count));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return MSGlobalLootModifierProvider.MS_LOOT_MODIFIER.get();
    }

    public Item item() {
        return item;
    }

    public int min(){
        return min;
    }

    public int max(){
        return max;
    }
}
