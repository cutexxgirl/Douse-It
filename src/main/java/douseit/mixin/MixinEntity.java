package douseit.mixin;

import douseit.config.DouseItConfig;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to hide the fire texture overlay on entities.
 * This makes Entity.displayFireAnimation() return false when config allows,
 * which prevents the blocky fire texture from rendering.
 * 
 * Note: This also hides fire particles. FireParticleHandler spawns
 * custom particles to replace them.
 */
@Mixin(Entity.class)
public class MixinEntity {

    @Inject(method = "displayFireAnimation", at = @At("HEAD"), cancellable = true)
    private void douseit$hideFireAnimation(CallbackInfoReturnable<Boolean> cir) {
        try {
            if (DouseItConfig.CLIENT.hideFireTexture.get()) {
                cir.setReturnValue(false);
            }
        } catch (Exception e) {
            // Config may not be loaded yet, default to hiding
            cir.setReturnValue(false);
        }
    }
}
