package com.coolerpromc.moresponge.entity.custom;

import com.coolerpromc.moresponge.trade.MSTradeSets;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Illusioner;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;
import java.util.Objects;

public class SpongeTrader extends AbstractVillager {
    private @Nullable BlockPos wanderTarget;
    private int despawnDelay = 0;
    private Type traderType;

    public SpongeTrader(EntityType<? extends AbstractVillager> type, Level level, Type traderType) {
        super(type, level);
        this.traderType = traderType;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5));
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(2, new WanderToPositionGoal(this, 2.0, 0.35));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int popXp = 3 + this.random.nextInt(4);
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), popXp));
        }
    }

    @Override
    protected void updateTrades(ServerLevel level) {
        MerchantOffers offers = this.getOffers();
        switch (traderType){
            case WATER -> {
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_WATER_BUYING);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_WATER_COMMON);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_WATER_UNCOMMON);
            }
            case FIRE -> {
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_FIRE_BUYING);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_FIRE_COMMON);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_FIRE_UNCOMMON);
            }
            case LAVA -> {
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_LAVA_BUYING);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_LAVA_COMMON);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_LAVA_UNCOMMON);
            }
            case SNOW -> {
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_SNOW_BUYING);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_SNOW_COMMON);
                this.addOffersFromTradeSet(level, offers, MSTradeSets.SPONGE_TRADER_SNOW_UNCOMMON);
            }
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    public Type getTraderType() {
        return this.traderType;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!itemStack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (hand == InteractionHand.MAIN_HAND) {
                player.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (!this.level().isClientSide()) {
                if (this.getOffers().isEmpty()) {
                    return InteractionResult.CONSUME;
                }

                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public boolean removeWhenFarAway(double distSqr) {
        return false;
    }

    public void setWanderTarget(@Nullable BlockPos pos) {
        this.wanderTarget = pos;
    }

    private @Nullable BlockPos getWanderTarget() {
        return this.wanderTarget;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("DespawnDelay", this.despawnDelay);
        output.storeNullable("wander_target", BlockPos.CODEC, this.wanderTarget);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.despawnDelay = input.getIntOr("DespawnDelay", 0);
        this.wanderTarget = input.read("wander_target", BlockPos.CODEC).orElse(null);
        this.setAge(Math.max(0, this.getAge()));
    }

    public void setDespawnDelay(int despawnDelay) {
        this.despawnDelay = despawnDelay;
    }

    public int getDespawnDelay() {
        return this.despawnDelay;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide()) {
            this.maybeDespawn();
        }
    }

    private void maybeDespawn() {
        if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
            this.discard();
        }
    }

    private class WanderToPositionGoal extends Goal {
        private final SpongeTrader trader;
        private final double stopDistance;
        private final double speedModifier;

        public WanderToPositionGoal(SpongeTrader trader, double stopDistance, double speedModifier) {
            Objects.requireNonNull(SpongeTrader.this);
            super();
            this.trader = trader;
            this.stopDistance = stopDistance;
            this.speedModifier = speedModifier;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public void stop() {
            this.trader.setWanderTarget((BlockPos)null);
            SpongeTrader.this.navigation.stop();
        }

        public boolean canUse() {
            BlockPos wanderPosition = this.trader.getWanderTarget();
            return wanderPosition != null && this.isTooFarAway(wanderPosition, this.stopDistance);
        }

        public void tick() {
            BlockPos wanderPosition = this.trader.getWanderTarget();
            if (wanderPosition != null && SpongeTrader.this.navigation.isDone()) {
                if (this.isTooFarAway(wanderPosition, (double)10.0F)) {
                    Vec3 dir = (new Vec3((double)wanderPosition.getX() - this.trader.getX(), (double)wanderPosition.getY() - this.trader.getY(), (double)wanderPosition.getZ() - this.trader.getZ())).normalize();
                    Vec3 targetPos = dir.scale((double)10.0F).add(this.trader.getX(), this.trader.getY(), this.trader.getZ());
                    SpongeTrader.this.navigation.moveTo(targetPos.x, targetPos.y, targetPos.z, this.speedModifier);
                } else {
                    SpongeTrader.this.navigation.moveTo((double)wanderPosition.getX(), (double)wanderPosition.getY(), (double)wanderPosition.getZ(), this.speedModifier);
                }
            }

        }

        private boolean isTooFarAway(BlockPos pos, double distance) {
            return !pos.closerToCenterThan(this.trader.position(), distance);
        }
    }

    public enum Type implements StringRepresentable {
        WATER("water"),
        LAVA("lava"),
        FIRE("fire"),
        SNOW("snow");
        private final String name;

        Type(String name){
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
