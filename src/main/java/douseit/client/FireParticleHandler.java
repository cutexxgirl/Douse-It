package douseit.client;

import douseit.config.DouseItConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Spawns fire particles around burning entities.
 * Since MixinEntity hides both the fire texture AND vanilla particles,
 * we spawn our own particles here.
 * 
 * All settings are configurable in config/douseit-client.toml
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "douseit", value = Dist.CLIENT)
public class FireParticleHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.isPaused()) return;
        
        // Iterate through all entities in the world
        for (var entity : mc.level.entitiesForRendering()) {
            if (entity instanceof LivingEntity living && living.isOnFire()) {
                spawnFireParticles(living);
            }
        }
    }
    
    private static void spawnFireParticles(LivingEntity entity) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;
        
        DouseItConfig.ClientConfig config = DouseItConfig.CLIENT;
        
        // ===== Flame particles =====
        if (config.enableFlameParticles.get()) {
            int chance = config.flameParticleChance.get();
            if (entity.getRandom().nextInt(chance) == 0) {
                int count = config.flameParticleCount.get();
                double speed = config.flameParticleSpeed.get();
                double spread = config.flameParticleSpread.get();
                
                for (int i = 0; i < count; i++) {
                    double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth() * spread;
                    double y = entity.getY() + entity.getRandom().nextDouble() * entity.getBbHeight();
                    double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth() * spread;
                    
                    double vy = speed + entity.getRandom().nextDouble() * speed * 0.5;
                    
                    mc.level.addParticle(ParticleTypes.FLAME, x, y, z, 0, vy, 0);
                }
            }
        }
        
        // ===== Smoke particles (from body, not head) =====
        if (config.enableSmokeParticles.get()) {
            int chance = config.smokeParticleChance.get();
            if (entity.getRandom().nextInt(chance) == 0) {
                int count = config.smokeParticleCount.get();
                double speed = config.smokeParticleSpeed.get();
                double spread = config.smokeParticleSpread.get();
                
                for (int i = 0; i < count; i++) {
                    // Spawn anywhere within the entity bounding box, scaled by spread
                    double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth() * spread;
                    double y = entity.getY() + entity.getRandom().nextDouble() * entity.getBbHeight(); 
                    double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * entity.getBbWidth() * spread;
                    
                    // Very small random velocity for natural drift
                    double vx = (entity.getRandom().nextDouble() - 0.5) * 0.01;
                    double vz = (entity.getRandom().nextDouble() - 0.5) * 0.01;

                    // Use CAMPFIRE_COSY_SMOKE as requested (rises in column)
                    mc.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, vx, speed, vz);
                }
            }
        }
    }
}
