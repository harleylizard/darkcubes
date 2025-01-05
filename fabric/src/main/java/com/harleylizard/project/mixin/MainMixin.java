package com.harleylizard.project.mixin;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Main.class, remap = false)
public final class MainMixin {

    @Inject(method = "main", at = @At("HEAD"), cancellable = true, remap = false)
    private static void main(String[] args, CallbackInfo ci) {
        com.harleylizard.project.Main.main(args);
        ci.cancel();
    }
}
