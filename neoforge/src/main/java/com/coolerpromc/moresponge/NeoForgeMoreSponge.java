package com.coolerpromc.moresponge;


import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.datagen.MSGlobalLootModifierProvider;
import com.coolerpromc.moresponge.entity.MSEntities;
import com.coolerpromc.moresponge.entity.spawner.SpongeTraderSpawner;
import com.coolerpromc.moresponge.platform.NeoForgeRegistryHelper;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent;
import net.neoforged.neoforge.transfer.CombinedResourceHandler;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;

@Mod(Constants.MODID)
public class NeoForgeMoreSponge {
    public NeoForgeMoreSponge(IEventBus eventBus) {
        MoreSponge.init();
        NeoForgeRegistryHelper.register(eventBus);
        MSGlobalLootModifierProvider.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus);

        eventBus.addListener(this::onRegisterCapabilities);
        eventBus.addListener(this::onEntityAttributeCreation);
        NeoForge.EVENT_BUS.addListener(this::onOnDatapackSync);
        NeoForge.EVENT_BUS.addListener(this::onModifyCustomSpawners);
    }

    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, MSBlockEntities.FREEZER.get(), (blockEntity, direction) -> {
            SimpleContainer container = blockEntity.getCapabilityBySide(direction);
            if (container == null){
                return new CombinedResourceHandler<>(VanillaContainerWrapper.of(blockEntity.getInputSlot()), VanillaContainerWrapper.of(blockEntity.getFuelSlot()), VanillaContainerWrapper.of(blockEntity.getResultSlot()));
            }
            return VanillaContainerWrapper.of(container);
        });
    }

    public void onOnDatapackSync(OnDatapackSyncEvent event) {
        event.sendRecipes(MSRecipes.FREEZER_TYPE.get());
    }

    public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(MSEntities.WATER_SPONGE_TRADER.get(), Mob.createMobAttributes().build());
        event.put(MSEntities.LAVA_SPONGE_TRADER.get(), Mob.createMobAttributes().build());
        event.put(MSEntities.FIRE_SPONGE_TRADER.get(), Mob.createMobAttributes().build());
        event.put(MSEntities.SNOW_SPONGE_TRADER.get(), Mob.createMobAttributes().build());
    }

    public void onModifyCustomSpawners(ModifyCustomSpawnersEvent event) {
        event.addCustomSpawner(new SpongeTraderSpawner(event.getLevel().getDataStorage()));
    }
}