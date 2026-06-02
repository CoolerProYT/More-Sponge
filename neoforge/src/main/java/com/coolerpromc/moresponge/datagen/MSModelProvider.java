package com.coolerpromc.moresponge.datagen;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.block.MSBlocks;
import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class MSModelProvider extends ModelProvider {
    private static final ModelTemplate TWO_LAYER_CUBE = new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("block/block")), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1, TextureSlot.PARTICLE);

    private static final Identifier WATER_SPONGE = Constants.id("block/water_sponge");
    private static final Identifier WET_WATER_SPONGE = Constants.id("block/wet_water_sponge");

    private static final Identifier LAVA_SPONGE = Constants.id("block/lava_sponge");
    private static final Identifier HOT_LAVA_SPONGE = Constants.id("block/hot_lava_sponge");

    private static final Identifier SNOW_SPONGE_TEX = Constants.id("block/snow_sponge");
    private static final Identifier FROZEN_SNOW_SPONGE_TEX = Constants.id("block/frozen_snow_sponge");

    private static final Identifier FIRE_SPONGE_TEX = Constants.id("block/fire_sponge");
    private static final Identifier BURNT_FIRE_SPONGE_TEX = Constants.id("block/burnt_fire_sponge");

    private static final Identifier COMPRESSED_1X = Constants.id("block/compressed_1x");
    private static final Identifier COMPRESSED_2X = Constants.id("block/compressed_2x");
    private static final Identifier COMPRESSED_3X = Constants.id("block/compressed_3x");
    private static final Identifier COMPRESSED_4X = Constants.id("block/compressed_4x");
    private static final Identifier COMPRESSED_5X = Constants.id("block/compressed_5x");

    private BlockModelGenerators blockModels;
    private ItemModelGenerators itemModels;

    public MSModelProvider(PackOutput output) {
        super(output, Constants.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        this.blockModels = blockModels;
        this.itemModels = itemModels;

        blockModels.createFurnace(MSBlocks.FREEZER.get(), TexturedModel.ORIENTABLE_ONLY_TOP);

        spongeBlock(MSBlocks.COMPRESSED_SPONGE, WATER_SPONGE, COMPRESSED_1X);
        spongeBlock(MSBlocks.COMPRESSED_SPONGE_2X, WATER_SPONGE, COMPRESSED_2X);
        spongeBlock(MSBlocks.COMPRESSED_SPONGE_3X, WATER_SPONGE, COMPRESSED_3X);
        spongeBlock(MSBlocks.COMPRESSED_SPONGE_4X, WATER_SPONGE, COMPRESSED_4X);
        spongeBlock(MSBlocks.COMPRESSED_SPONGE_5X, WATER_SPONGE, COMPRESSED_5X);

        spongeBlock(MSBlocks.WET_COMPRESSED_SPONGE, WET_WATER_SPONGE, COMPRESSED_1X);
        spongeBlock(MSBlocks.WET_COMPRESSED_SPONGE_2X, WET_WATER_SPONGE, COMPRESSED_2X);
        spongeBlock(MSBlocks.WET_COMPRESSED_SPONGE_3X, WET_WATER_SPONGE, COMPRESSED_3X);
        spongeBlock(MSBlocks.WET_COMPRESSED_SPONGE_4X, WET_WATER_SPONGE, COMPRESSED_4X);
        spongeBlock(MSBlocks.WET_COMPRESSED_SPONGE_5X, WET_WATER_SPONGE, COMPRESSED_5X);

        blockModels.createTrivialCube(MSBlocks.LAVA_SPONGE.get());
        blockModels.createTrivialCube(MSBlocks.HOT_LAVA_SPONGE.get());
        blockModels.createTrivialCube(MSBlocks.SNOW_SPONGE.get());
        blockModels.createTrivialCube(MSBlocks.FROZEN_SNOW_SPONGE.get());
        blockModels.createTrivialCube(MSBlocks.FIRE_SPONGE.get());
        blockModels.createTrivialCube(MSBlocks.BURNT_FIRE_SPONGE.get());

        spongeBlock(MSBlocks.COMPRESSED_LAVA_SPONGE, LAVA_SPONGE, COMPRESSED_1X);
        spongeBlock(MSBlocks.COMPRESSED_LAVA_SPONGE_2X, LAVA_SPONGE, COMPRESSED_2X);
        spongeBlock(MSBlocks.COMPRESSED_LAVA_SPONGE_3X, LAVA_SPONGE, COMPRESSED_3X);
        spongeBlock(MSBlocks.COMPRESSED_LAVA_SPONGE_4X, LAVA_SPONGE, COMPRESSED_4X);
        spongeBlock(MSBlocks.COMPRESSED_LAVA_SPONGE_5X, LAVA_SPONGE, COMPRESSED_5X);

        spongeBlock(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE, HOT_LAVA_SPONGE, COMPRESSED_1X);
        spongeBlock(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_2X, HOT_LAVA_SPONGE, COMPRESSED_2X);
        spongeBlock(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_3X, HOT_LAVA_SPONGE, COMPRESSED_3X);
        spongeBlock(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_4X, HOT_LAVA_SPONGE, COMPRESSED_4X);
        spongeBlock(MSBlocks.HOT_COMPRESSED_LAVA_SPONGE_5X, HOT_LAVA_SPONGE, COMPRESSED_5X);

        spongeBlock(MSBlocks.COMPRESSED_SNOW_SPONGE, SNOW_SPONGE_TEX, COMPRESSED_1X);
        spongeBlock(MSBlocks.COMPRESSED_SNOW_SPONGE_2X, SNOW_SPONGE_TEX, COMPRESSED_2X);
        spongeBlock(MSBlocks.COMPRESSED_SNOW_SPONGE_3X, SNOW_SPONGE_TEX, COMPRESSED_3X);
        spongeBlock(MSBlocks.COMPRESSED_SNOW_SPONGE_4X, SNOW_SPONGE_TEX, COMPRESSED_4X);
        spongeBlock(MSBlocks.COMPRESSED_SNOW_SPONGE_5X, SNOW_SPONGE_TEX, COMPRESSED_5X);

        spongeBlock(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE, FROZEN_SNOW_SPONGE_TEX, COMPRESSED_1X);
        spongeBlock(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_2X, FROZEN_SNOW_SPONGE_TEX, COMPRESSED_2X);
        spongeBlock(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_3X, FROZEN_SNOW_SPONGE_TEX, COMPRESSED_3X);
        spongeBlock(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_4X, FROZEN_SNOW_SPONGE_TEX, COMPRESSED_4X);
        spongeBlock(MSBlocks.FROZEN_COMPRESSED_SNOW_SPONGE_5X, FROZEN_SNOW_SPONGE_TEX, COMPRESSED_5X);

        spongeBlock(MSBlocks.COMPRESSED_FIRE_SPONGE, FIRE_SPONGE_TEX, COMPRESSED_1X);
        spongeBlock(MSBlocks.COMPRESSED_FIRE_SPONGE_2X, FIRE_SPONGE_TEX, COMPRESSED_2X);
        spongeBlock(MSBlocks.COMPRESSED_FIRE_SPONGE_3X, FIRE_SPONGE_TEX, COMPRESSED_3X);
        spongeBlock(MSBlocks.COMPRESSED_FIRE_SPONGE_4X, FIRE_SPONGE_TEX, COMPRESSED_4X);
        spongeBlock(MSBlocks.COMPRESSED_FIRE_SPONGE_5X, FIRE_SPONGE_TEX, COMPRESSED_5X);

        spongeBlock(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE, BURNT_FIRE_SPONGE_TEX, COMPRESSED_1X);
        spongeBlock(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_2X, BURNT_FIRE_SPONGE_TEX, COMPRESSED_2X);
        spongeBlock(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_3X, BURNT_FIRE_SPONGE_TEX, COMPRESSED_3X);
        spongeBlock(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_4X, BURNT_FIRE_SPONGE_TEX, COMPRESSED_4X);
        spongeBlock(MSBlocks.BURNT_COMPRESSED_FIRE_SPONGE_5X, BURNT_FIRE_SPONGE_TEX, COMPRESSED_5X);
    }

    private <T extends Block> void spongeBlock(RegistryHandler.Blocks<T> block, Identifier layer0, Identifier layer1) {
        TextureMapping textureMapping = TextureMapping.layered(new Material(layer0), new Material(layer1)).put(TextureSlot.PARTICLE, new Material(layer0));

        Identifier model = TWO_LAYER_CUBE.extend()
                .element(builder -> builder.cube(TextureSlot.LAYER0).from(0, 0, 0).to(16, 16, 16))
                .element(builder -> builder.cube(TextureSlot.LAYER1).from(0, 0, 0).to(16, 16, 16))
                .build()
                .create(block.get(), textureMapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get(), multiVariant(model)));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(model));
    }

    private MultiVariant multiVariant(Identifier model){
        return new MultiVariant(WeightedList.of(new Variant(model)));
    }
}
