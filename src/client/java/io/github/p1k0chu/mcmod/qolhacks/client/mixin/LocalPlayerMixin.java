package io.github.p1k0chu.mcmod.qolhacks.client.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static io.github.p1k0chu.mcmod.qolhacks.client.MainClient.isElytraSpamEnabled;

@Mixin(LocalPlayer.class)
abstract class LocalPlayerMixin extends Player {
    public LocalPlayerMixin(Level level, GameProfile gameProfile) {
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
