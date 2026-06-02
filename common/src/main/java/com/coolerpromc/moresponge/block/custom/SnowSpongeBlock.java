package com.coolerpromc.moresponge.block.custom;

import com.coolerpromc.moresponge.platform.util.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public class SnowSpongeBlock extends Block {
    private static final Direction[] ALL_DIRECTIONS = Direction.values();
    private final int maxDepth;
    private final int maxCount;
    private final Supplier<RegistryHandler.Blocks<FrozenSnowSpongeBlock>> frozenBlock;

    public SnowSpongeBlock(Properties properties, int maxDepth, int maxCount, Supplier<RegistryHandler.Blocks<FrozenSnowSpongeBlock>> frozenBlock) {
        super(properties);
        this.maxDepth = maxDepth;
        this.maxCount = maxCount;
        this.frozenBlock = frozenBlock;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            this.tryAbsorbSnow(level, pos);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        this.tryAbsorbSnow(level, pos);
        super.neighborChanged(state, level, pos, block, orientation, movedByPiston);
    }

    protected void tryAbsorbSnow(Level level, BlockPos pos) {
        if (this.removeSnowBFS(level, pos)) {
            level.setBlock(pos, frozenBlock.get().get().defaultBlockState(), 2);
            level.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    private boolean removeSnowBFS(Level level, BlockPos startPos) {
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
                    BlockState aboveState = level.getBlockState(pos.above());
                    BlockState belowState = level.getBlockState(pos.below());
                    if (!state.is(BlockTags.SNOW) && !aboveState.is(BlockTags.SNOW) && !belowState.is(BlockTags.SNOW)) {
                        return BlockPos.TraversalNodeStatus.SKIP;
                    }
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    return BlockPos.TraversalNodeStatus.ACCEPT;
                }) > 1;
    }
}