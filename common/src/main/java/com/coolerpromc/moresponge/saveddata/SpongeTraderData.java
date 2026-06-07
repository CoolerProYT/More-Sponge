package com.coolerpromc.moresponge.saveddata;

import com.coolerpromc.moresponge.Constants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class SpongeTraderData extends SavedData {
    public static final Codec<SpongeTraderData> CODEC = RecordCodecBuilder.create((i) -> i.group(
            Codec.INT.optionalFieldOf("spawn_delay", 36000).forGetter((data) -> data.spawnDelay),
            Codec.INT.optionalFieldOf("spawn_chance", 20).forGetter((data) -> data.spawnChance)
    ).apply(i, SpongeTraderData::new));
    public static final SavedDataType<SpongeTraderData> TYPE = new SavedDataType<>(Constants.id("sponge_trader"), SpongeTraderData::new, CODEC, DataFixTypes.SAVED_DATA_WANDERING_TRADER);
    private int spawnDelay;
    private int spawnChance;

    public SpongeTraderData() {
        this(36000, 20);
    }

    public SpongeTraderData(int spawnDelay, int spawnChance) {
        this.spawnDelay = spawnDelay;
        this.spawnChance = spawnChance;
    }

    public int spawnDelay() {
        return this.spawnDelay;
    }

    public void setSpawnDelay(int spawnDelay) {
        if (this.spawnDelay != spawnDelay) {
            this.spawnDelay = spawnDelay;
            this.setDirty(true);
        }
    }

    public int spawnChance() {
        return this.spawnChance;
    }

    public void setSpawnChance(int spawnChance) {
        if (this.spawnChance != spawnChance) {
            this.spawnChance = spawnChance;
            this.setDirty(true);
        }

    }
}
