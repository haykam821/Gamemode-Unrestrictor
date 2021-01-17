package io.github.haykam821.gamemodeunrestrictor.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.client.Keyboard;
import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(Keyboard.class)
public class KeyboardMixin {
	@Redirect(method = "processF3", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasPermissionLevel(I)Z"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasPermissionLevel(I)Z", ordinal = 1)))
	private boolean bypassProcessPermissionCheck(ClientPlayerEntity entity, int permissionLevel) {
		return true;
	}
}
