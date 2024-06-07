package io.github.haykam821.gamemodeunrestrictor.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.screen.GameModeSelectionScreen;
import net.minecraft.client.network.ClientPlayerEntity;

@SuppressWarnings("target")
@Mixin(value = GameModeSelectionScreen.class, remap = false)
public class GameModeSelectionScreenMixinOld {
	@Redirect(method = "method_28064(Lnet/minecraft/class_310;Ljava/util/Optional;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_746;method_5687(I)Z"))
	private static boolean bypassApplyPermissionCheck(ClientPlayerEntity entity, int permissionLevel) {
		return true;
	}
}
