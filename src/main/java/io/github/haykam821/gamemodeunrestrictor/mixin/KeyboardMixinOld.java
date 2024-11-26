package io.github.haykam821.gamemodeunrestrictor.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.client.Keyboard;
import net.minecraft.client.network.ClientPlayerEntity;

@SuppressWarnings("target")
@Mixin(value = Keyboard.class, remap = false)
public class KeyboardMixinOld {
	@Redirect(method = "method_1468(I)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_746;method_5687(I)Z"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/class_746;method_5687(I)Z", ordinal = 1)))
	private boolean bypassProcessPermissionCheck(ClientPlayerEntity entity, int permissionLevel) {
		return true;
	}
}
