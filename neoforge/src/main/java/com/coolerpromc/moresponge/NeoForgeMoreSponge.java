package com.coolerpromc.moresponge;


import com.coolerpromc.moresponge.block.entity.MSBlockEntities;
import com.coolerpromc.moresponge.datagen.MSGlobalLootModifierProvider;
import com.coolerpromc.moresponge.platform.NeoForgeRegistryHelper;
import com.coolerpromc.moresponge.recipe.MSRecipes;
import net.minecraft.world.SimpleContainer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.transfer.CombinedResourceHandler;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;

@Mod(Constants.MODID)
public class NeoForgeMoreSponge {
    public NeoForgeMoreSponge(IEventBus eventBus) {
        MoreSponge.init();
        NeoForgeRegistryHelper.register(eventBus);
        MSGlobalLootModifierProvider.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus);

        eventBus.addListener(this::onRegisterCapabilities);
        NeoForge.EVENT_BUS.addListener(this::onOnDatapackSync);
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
}