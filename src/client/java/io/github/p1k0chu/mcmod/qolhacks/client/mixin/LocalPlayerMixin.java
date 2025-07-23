package io.github.p1k0chu.mcmod.qolhacks.client.mixin;

import io.github.p1k0chu.mcmod.qolhacks.client.MainClient;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static io.github.p1k0chu.mcmod.qolhacks.client.MainClient.isElytraSpamEnabled;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
    @Shadow
    public abstract PlayerRideableJumping jumpableVehicle();

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Input;jump()Z", ordinal = 0))
    boolean getJump(Input instance) {
        return !isElytraSpamEnabled() || (jumpableVehicle() != null || cast().getAbilities().mayfly) && instance.jump();
    }

    @Unique
    private LocalPlayer cast() {
        return (LocalPlayer) (Object) this;
    }
}
