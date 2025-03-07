package com.github.sejoslaw.multiversecraft.mixin;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Final
    @Shadow
    private Map<RegistryKey<World>, ServerWorld> worlds;

    @Inject(method = "createWorlds", at = @At("TAIL"))
    private void onCreateWorlds(WorldGenerationProgressListener progressListener, CallbackInfo callback) {
        // TODO: Create callback for using "worlds" to register additional ServerWorld's
    }
}
