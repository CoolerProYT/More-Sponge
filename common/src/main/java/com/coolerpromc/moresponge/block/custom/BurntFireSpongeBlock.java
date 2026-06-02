package com.coolerpromc.moresponge.block.custom;

import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BurntFireSpongeBlock extends Block {
    private final RegistryHandler.Blocks<FireSpongeBlock> dryBlock;

    public BurntFireSpongeBlock(Properties properties, RegistryHandler.Blocks<FireSpongeBlock> dryBlock) {
        super(properties);
        this.dryBlock = dryBlock;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.getBiome(pos).is(BiomeTags.SPAWNS_SNOW_FOXES) || level.getBiome(pos).is(BiomeTags.IS_OCEAN) || isTouchingWater(level, pos)) {
            level.setBlock(pos, dryBlock.get().defaultBlockState(), 3);
            level.levelEvent(2009, pos, 0);
            level.playSound(null, pos, SoundEvents.WET_SPONGE_DRIES, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
    }

    private boolean isTouchingWater(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()){
            if (isWater(level, pos.relative(direction))){
                return true;
            }
        }
        return false;
    }

    private boolean isWater(Level level, BlockPos pos){
        return level.getFluidState(pos).is(FluidTags.WATER);
    }
}
