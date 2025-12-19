package douseit.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomSmokeParticle extends TextureSheetParticle {
    
    private final SpriteSet sprites;

    protected CustomSmokeParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(level, x, y, z, vx, vy, vz);
        this.sprites = spriteSet;
        
        this.quadSize *= 0.75F;
        this.lifetime = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        
        // Lighter grey color (0.7 to 0.9)
        float grey = 0.7F + this.random.nextFloat() * 0.2F;
        this.rCol = grey;
        this.gCol = grey;
        this.bCol = grey;
        
        this.xd = vx;
        this.yd = vy + 0.1D;
        this.zd = vz;
        
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
            this.yd += 0.004D; // Slight gravity/buoyancy adjustment?
            // Actually vanilla smoke accelerates up slightly or slows down?
            // Vanilla Smoke moves up.
            this.move(this.xd, this.yd, this.zd);
            
            // Slow down
            this.xd *= 0.96F;
            this.yd *= 0.96F;
            this.zd *= 0.96F;
            
            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
            return new CustomSmokeParticle(level, x, y, z, vx, vy, vz, this.sprites);
        }
    }
}
