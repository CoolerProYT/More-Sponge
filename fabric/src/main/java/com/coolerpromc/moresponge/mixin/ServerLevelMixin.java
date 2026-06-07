package com.coolerpromc.moresponge.mixin;

import com.coolerpromc.moresponge.entity.spawner.SpongeTraderSpawner;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true, name = "customSpawners")
    private static List<CustomSpawner> addList(List<CustomSpawner> customSpawners, @Local(argsOnly = true, name = "server") MinecraftServer server)
    {
        List<CustomSpawner> modified = new ArrayList<>(customSpawners);
        modified.add(new SpongeTraderSpawner(server.getDataStorage()));
        return modified;
    }
}