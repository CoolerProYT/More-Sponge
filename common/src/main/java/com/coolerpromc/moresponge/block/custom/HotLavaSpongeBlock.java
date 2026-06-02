package com.coolerpromc.moresponge.block.custom;

import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HotLavaSpongeBlock extends Block {
    private final RegistryHandler.Blocks<LavaSpongeBlock> dryBlock;

    public HotLavaSpongeBlock(Properties properties, RegistryHandler.Blocks<LavaSpongeBlock> dryBlock) {
        super(properties);
        this.dryBlock = dryBlock;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.getBiome(pos).is(BiomeTags.SPAWNS_SNOW_FOXES)) {
            level.setBlock(pos, dryBlock.get().defaultBlockState(), 3);
            level.levelEvent(2009, pos, 0);
            level.playSound(null, pos, SoundEvents.WET_SPONGE_DRIES, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
    }

    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().hotFloor(), 1.0F);
        }

        super.stepOn(level, pos, onState, entity);
    }
}
