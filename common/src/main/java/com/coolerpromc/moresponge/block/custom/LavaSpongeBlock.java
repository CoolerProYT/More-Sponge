package com.coolerpromc.moresponge.block.custom;

import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public class LavaSpongeBlock extends Block {
    private static final Direction[] ALL_DIRECTIONS = Direction.values();
    private final int maxDepth;
    private final int maxCount;
    private final Supplier<RegistryHandler.Blocks<HotLavaSpongeBlock>> hotBlock;

    public LavaSpongeBlock(Properties properties, int maxDepth, int maxCount, Supplier<RegistryHandler.Blocks<HotLavaSpongeBlock>> hotBlock) {
        super(properties);
        this.maxDepth = maxDepth;
        this.maxCount = maxCount;
        this.hotBlock = hotBlock;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            this.tryAbsorbLava(level, pos);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        this.tryAbsorbLava(level, pos);
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    protected void tryAbsorbLava(Level level, BlockPos pos) {
        if (this.removeLavaBFS(level, pos)) {
            level.setBlock(pos, hotBlock.get().get().defaultBlockState(), 2);
            level.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    private boolean removeLavaBFS(Level level, BlockPos startPos) {
        return BlockPos.breadthFirstTraversal(startPos, this.maxDepth, this.maxCount,
            (pos, consumer) -> {
                for (Direction direction : ALL_DIRECTIONS) {
                    consumer.accept(pos.relative(direction));
                }
            },
            pos -> {
                if (pos.equals(startPos)) {
                    return BlockPos.TraversalNodeStatus.ACCEPT;
                }
                BlockState state = level.getBlockState(pos);
                FluidState fluidState = level.getFluidState(pos);
                if (!fluidState.is(FluidTags.LAVA)) {
                    return BlockPos.TraversalNodeStatus.SKIP;
                }
                if (state.getBlock() instanceof BucketPickup bucketPickup && !bucketPickup.pickupBlock(null, level, pos, state).isEmpty()) {
                    return BlockPos.TraversalNodeStatus.ACCEPT;
                }
                if (state.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                } else {
                    return BlockPos.TraversalNodeStatus.SKIP;
                }
                return BlockPos.TraversalNodeStatus.ACCEPT;
            }) > 1;
    }
}