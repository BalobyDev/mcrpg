package net.baloby.mcrpg.mixin.client;

import com.mojang.serialization.Lifecycle;
import net.baloby.mcrpg.mcrpg;
import net.minecraft.world.storage.ServerWorldInfo;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorldInfo.class)
public class MixinServerWorldInfo {

    @Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
    private void forceStableLifeCycle(CallbackInfoReturnable<Lifecycle> cir) {
        cir.setReturnValue(Lifecycle.stable());
        mcrpg.LOGGER.log(Level.DEBUG, "Shut up");
    }

}
