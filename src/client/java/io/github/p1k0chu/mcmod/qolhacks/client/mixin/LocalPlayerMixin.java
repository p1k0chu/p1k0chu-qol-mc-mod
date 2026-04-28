package io.github.p1k0chu.mcmod.qolhacks.client.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.PlayerRideableJumping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static io.github.p1k0chu.mcmod.qolhacks.client.MainClient.isElytraSpamEnabled;

@Mixin(LocalPlayer.class)
abstract class LocalPlayerMixin extends AbstractClientPlayer {
	public LocalPlayerMixin(ClientLevel level, GameProfile gameProfile) {
		super(level, gameProfile);
	}

	@Shadow
    abstract PlayerRideableJumping jumpableVehicle();

    @Definition(id = "jump", method = "Lnet/minecraft/world/entity/player/Input;jump()Z")
    @Expression("? = @(?.jump())")
    @ModifyExpressionValue(method = "aiStep", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean getJump(boolean original) {
        return original && !isElytraSpamEnabled() || (jumpableVehicle() != null || getAbilities().mayfly);
    }
}
