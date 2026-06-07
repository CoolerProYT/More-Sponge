package com.coolerpromc.moresponge.entity.spawner;

import com.coolerpromc.moresponge.entity.MSEntities;
import com.coolerpromc.moresponge.entity.custom.SpongeTrader;
import com.coolerpromc.moresponge.gamerule.MSGameRules;
import com.coolerpromc.moresponge.saveddata.SpongeTraderData;
import com.coolerpromc.moresponge.tag.MSBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.SavedDataStorage;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class SpongeTraderSpawner implements CustomSpawner {
    private static final int DEFAULT_TICK_DELAY = 1200;
    public static final int DEFAULT_SPAWN_DELAY = 36000;
    public static final int MIN_SPAWN_CHANCE = 20;
    private static final int MAX_SPAWN_CHANCE = 65;
    private static final int SPAWN_CHANCE_INCREASE = 25;
    private static final int SPAWN_ONE_IN_X_CHANCE = 10;
    private static final int NUMBER_OF_SPAWN_ATTEMPTS = 10;
    private final RandomSource random = RandomSource.create();
    private final SavedDataStorage savedDataStorage;
    private int tickDelay;
    private @Nullable SpongeTraderData traderData;

    public SpongeTraderSpawner(SavedDataStorage savedDataStorage) {
        this.savedDataStorage = savedDataStorage;
        this.tickDelay = DEFAULT_TICK_DELAY;
        this.traderData = null;
    }

    @Override
    public void tick(ServerLevel level, boolean b) {
        if (level.getGameRules().get(MSGameRules.SPAWN_SPONGE_TRADERS.get())) {
            if (--this.tickDelay <= 0) {
                this.tickDelay = DEFAULT_TICK_DELAY;
                SpongeTraderData data = this.getTraderData();
                int spawnDelay = data.spawnDelay() - DEFAULT_TICK_DELAY;
                data.setSpawnDelay(spawnDelay);
                if (spawnDelay <= 0) {
                    data.setSpawnDelay(DEFAULT_SPAWN_DELAY);
                    int chanceToSpawn = data.spawnChance();
                    int newSpawnChance = Mth.clamp(chanceToSpawn + MIN_SPAWN_CHANCE, MIN_SPAWN_CHANCE, MAX_SPAWN_CHANCE);
                    data.setSpawnChance(newSpawnChance);
                    if (this.random.nextInt(100) <= chanceToSpawn) {
                        if (this.spawn(level)) {
                            data.setSpawnChance(MIN_SPAWN_CHANCE);
                        }
                    }
                }
            }
        }
    }

    private SpongeTraderData getTraderData() {
        if (this.traderData == null) {
            this.traderData = this.savedDataStorage.computeIfAbsent(SpongeTraderData.TYPE);
        }

        return this.traderData;
    }

    private boolean spawn(ServerLevel level) {
        Player player = level.getRandomPlayer();
        if (player == null) {
            return true;
        }

        if (this.random.nextInt(NUMBER_OF_SPAWN_ATTEMPTS) != 0) {
            return false;
        }

        BlockPos playerPos = player.blockPosition();
        PoiManager poiManager = level.getPoiManager();
        Optional<BlockPos> poiPos = poiManager.find(p -> p.is(PoiTypes.MEETING), p -> true, playerPos, 48, PoiManager.Occupancy.ANY);
        BlockPos referencePos = poiPos.orElse(playerPos);
        BlockPos spawnPosition = this.findSpawnPositionNear(level, referencePos, 48);
        if (spawnPosition != null && this.hasEnoughSpace(level, spawnPosition)) {
            SpongeTrader trader = trySpawn(level, spawnPosition);
            if (trader != null) {
                trader.setDespawnDelay(48000);
                trader.setWanderTarget(referencePos);
                trader.setHomeTo(referencePos, 16);
                return true;
            }
        }

        return false;
    }

    private SpongeTrader trySpawn(ServerLevel level, BlockPos spawnPosition){
        if (level.getBiome(spawnPosition).is(MSBiomeTags.CAN_SPAWN_LAVA_SPONGE_TRADER)){
            return MSEntities.LAVA_SPONGE_TRADER.get().spawn(level, spawnPosition, EntitySpawnReason.EVENT);
        }
        if (level.getBiome(spawnPosition).is(MSBiomeTags.CAN_SPAWN_SNOW_SPONGE_TRADER)){
            return MSEntities.SNOW_SPONGE_TRADER.get().spawn(level, spawnPosition, EntitySpawnReason.EVENT);
        }
        if (level.getBiome(spawnPosition).is(MSBiomeTags.CAN_SPAWN_FIRE_SPONGE_TRADER)){
            return MSEntities.FIRE_SPONGE_TRADER.get().spawn(level, spawnPosition, EntitySpawnReason.EVENT);
        }
        if (level.getBiome(spawnPosition).is(MSBiomeTags.CAN_SPAWN_WATER_SPONGE_TRADER)){
            return MSEntities.WATER_SPONGE_TRADER.get().spawn(level, spawnPosition, EntitySpawnReason.EVENT);
        }
        return null;
    }

    private @Nullable BlockPos findSpawnPositionNear(LevelReader level, BlockPos referencePosition, int radius) {
        BlockPos spawnPosition = null;
        SpawnPlacementType spongeTraderSpawnType = SpawnPlacements.getPlacementType(MSEntities.WATER_SPONGE_TRADER.get());
        boolean hasCeiling = level.dimensionType().hasCeiling();

        for (int i = 0; i < SPAWN_ONE_IN_X_CHANCE; i++) {
            int xPosition = referencePosition.getX() + this.random.nextInt(radius * 2) - radius;
            int zPosition = referencePosition.getZ() + this.random.nextInt(radius * 2) - radius;
            int yPosition = level.getHeight(SpawnPlacements.getHeightmapType(MSEntities.WATER_SPONGE_TRADER.get()), xPosition, zPosition);
            if (hasCeiling){
                yPosition = findGroundY(level, xPosition, zPosition, referencePosition.getY() + 8);
                if (yPosition == Integer.MIN_VALUE) continue;
            }
            BlockPos spawnPos = new BlockPos(xPosition, yPosition, zPosition);
            if (spongeTraderSpawnType.isSpawnPositionOk(level, spawnPos, MSEntities.WATER_SPONGE_TRADER.get())) {
                spawnPosition = spawnPos;
                break;
            }
        }

        return spawnPosition;
    }

    private int findGroundY(LevelReader level, int x, int z, int startY) {
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos(x, startY, z);
        int minY = level.getMinY();

        while (cursor.getY() > minY + 1) {
            BlockState state = level.getBlockState(cursor);
            BlockState below = level.getBlockState(cursor.below());
            if (state.isAir() && below.isFaceSturdy(level, cursor.below(), Direction.UP)) {
                return cursor.getY();
            }
            cursor.move(Direction.DOWN);
        }
        return Integer.MIN_VALUE;
    }

    private boolean hasEnoughSpace(BlockGetter level, BlockPos spawnPos) {
        for (BlockPos pos : BlockPos.betweenClosed(spawnPos, spawnPos.offset(1, 2, 1))) {
            if (!level.getBlockState(pos).getCollisionShape(level, pos).isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
