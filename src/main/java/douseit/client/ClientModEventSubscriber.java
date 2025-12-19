package douseit.client;

import douseit.client.particle.CustomSmokeParticle;
import douseit.registry.DouseItParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "douseit", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        // Register our custom smoke particle with its provider
        // This will look for assets/douseit/particles/grey_smoke.json
        event.registerSpriteSet(DouseItParticles.GREY_SMOKE.get(), CustomSmokeParticle.Provider::new);
    }
}
