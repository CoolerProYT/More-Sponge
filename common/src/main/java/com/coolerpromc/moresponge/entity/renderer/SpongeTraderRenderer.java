package com.coolerpromc.moresponge.entity.renderer;

import com.coolerpromc.moresponge.Constants;
import com.coolerpromc.moresponge.entity.custom.SpongeTrader;
import com.coolerpromc.moresponge.entity.model.SpongeTraderModel;
import com.coolerpromc.moresponge.entity.renderer.state.SpongeTraderRenderState;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.resources.Identifier;

public class SpongeTraderRenderer extends MobRenderer<SpongeTrader, SpongeTraderRenderState, SpongeTraderModel> {
    private static final Identifier WATER_SKIN = Constants.id("textures/entity/sponge_trader/water_sponge_trader.png");
    private static final Identifier LAVA_SKIN = Constants.id("textures/entity/sponge_trader/lava_sponge_trader.png");
    private static final Identifier FIRE_SKIN = Constants.id("textures/entity/sponge_trader/fire_sponge_trader.png");
    private static final Identifier SNOW_SKIN = Constants.id("textures/entity/sponge_trader/snow_sponge_trader.png");

    public SpongeTraderRenderer(EntityRendererProvider.Context context) {
        super(context, new SpongeTraderModel(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(SpongeTraderRenderState villagerRenderState) {
        SpongeTrader.Type traderType = villagerRenderState.traderType;

        return switch (traderType){
            case SNOW -> SNOW_SKIN;
            case LAVA -> LAVA_SKIN;
            case FIRE -> FIRE_SKIN;
            case WATER -> WATER_SKIN;
        };
    }

    @Override
    public SpongeTraderRenderState createRenderState() {
        return new SpongeTraderRenderState();
    }

    public void extractRenderState(final SpongeTrader entity, final SpongeTraderRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        HoldingEntityRenderState.extractHoldingEntityRenderState(entity, state, this.itemModelResolver);
        state.isUnhappy = entity.getUnhappyCounter() > 0;
        state.traderType = entity.getTraderType();
    }
}
