package douseit;

import douseit.config.DouseItConfig;
import net.minecraftforge.fml.common.Mod;

@Mod("douseit")
public class DouseItMod {
    public DouseItMod() {
        // Register configuration
        DouseItConfig.register();
        
        // Register particles
        douseit.registry.DouseItParticles.register(net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus());
    }
}

