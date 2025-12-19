package douseit.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/**
 * Configuration for the DouseIt mod.
 * All settings can be changed in config/douseit-client.toml
 * Author: cutexxgirl
 */
public class DouseItConfig {
    
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ClientConfig CLIENT;
    
    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        CLIENT = new ClientConfig(builder);
        CLIENT_SPEC = builder.build();
    }
    
    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC, "douseit-client.toml");
    }
    
    public static class ClientConfig {
        // ===== Flame particles =====
        public final ForgeConfigSpec.BooleanValue enableFlameParticles;
        public final ForgeConfigSpec.IntValue flameParticleCount;
        public final ForgeConfigSpec.IntValue flameParticleChance;
        public final ForgeConfigSpec.DoubleValue flameParticleSpeed;
        public final ForgeConfigSpec.DoubleValue flameParticleSpread;
        
        // ===== Smoke particles =====
        public final ForgeConfigSpec.BooleanValue enableSmokeParticles;
        public final ForgeConfigSpec.IntValue smokeParticleCount;
        public final ForgeConfigSpec.IntValue smokeParticleChance;
        public final ForgeConfigSpec.DoubleValue smokeParticleSpeed;
        public final ForgeConfigSpec.DoubleValue smokeParticleSpread;
        

        // ===== Fire texture =====
        public final ForgeConfigSpec.BooleanValue hideFireTexture;
        
        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("DouseIt - Fire Particle Configuration")
                   .comment("Author: cutexxgirl")
                   .push("particles");
            
            // ===== Flame settings =====
            builder.comment("Flame particle settings").push("flame");
            
            enableFlameParticles = builder
                .comment("Enable flame particles on burning entities")
                .define("enabled", true);
            
            flameParticleCount = builder
                .comment("Number of flame particles to spawn per tick when chance succeeds (1-20)")
                .defineInRange("count", 3, 1, 20);
            
            flameParticleChance = builder
                .comment("Chance to spawn flame each tick (1 = always, 2 = 50% - default, 5 = 20%)")
                .defineInRange("chance", 2, 1, 10);
            
            flameParticleSpeed = builder
                .comment("Upward speed of flame particles (0.01-0.3)")
                .defineInRange("speed", 0.04, 0.01, 0.3);
            
            flameParticleSpread = builder
                .comment("How far from entity center flames can spawn (0.5-3.0, multiplier of entity width)")
                .defineInRange("spread", 1.2, 0.5, 3.0);
            
            builder.pop();
            
            // ===== Smoke settings =====
            builder.comment("Smoke particle settings - rises slowly above entity").push("smoke");
            
            enableSmokeParticles = builder
                .comment("Enable smoke particles on burning entities")
                .define("enabled", true);
            
            smokeParticleCount = builder
                .comment("Number of smoke particles to spawn per tick when chance succeeds (1-10)")
                .defineInRange("count", 1, 1, 10);
            
            smokeParticleChance = builder
                .comment("Chance to spawn smoke each tick (1 = always, 10 = 10% - default, 20 = 5%)")
                .defineInRange("chance", 10, 1, 20);
            
            smokeParticleSpeed = builder
                .comment("Upward speed of smoke particles (0.005-0.1)")
                .defineInRange("speed", 0.008, 0.005, 0.1);
            
            smokeParticleSpread = builder
                .comment("How far from entity center smoke can spawn (0.1-2.0) - 1.0 = full width")
                .defineInRange("spread", 1.0, 0.1, 2.0);
            
            builder.pop();
            
            builder.pop(); // particles
            
            // ===== Fire texture settings =====
            builder.comment("Fire texture overlay settings").push("fire_texture");
            
            hideFireTexture = builder
                .comment("Hide the blocky fire texture overlay on burning entities")
                .define("hide", true);
            
            builder.pop();
        }
    }
}
