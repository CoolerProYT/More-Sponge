package com.coolerpromc.moresponge.block.custom;

import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WetWaterSpongeBlock extends Block {
    private final RegistryHandler.Blocks<WaterSpongeBlock> dryBlock;

    public WetWaterSpongeBlock(Properties properties, RegistryHandler.Blocks<WaterSpongeBlock> dryBlock) {
        super(properties);
        this.dryBlock = dryBlock;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
            level.setBlock(pos, dryBlock.get().defaultBlockState(), 3);
            level.levelEvent(2009, pos, 0);
            level.playSound(null, pos, SoundEvents.WET_SPONGE_DRIES, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
    }
}
