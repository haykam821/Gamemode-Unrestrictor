package io.github.haykam821.gamemodeunrestrictor.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.screen.GameModeSwitcherScreen;
import net.minecraft.client.network.ClientPlayerEntity;

@SuppressWarnings("target")
@Mixin(value = GameModeSwitcherScreen.class, remap = false)
public class GameModeSwitcherScreenMixinOld2 {
	@Redirect(method = "method_28064(Lnet/minecraft/class_310;Lnet/minecraft/class_5289$class_5290;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_746;method_5687(I)Z"))
	private static boolean bypassApplyPermissionCheck(ClientPlayerEntity entity, int permissionLevel) {
		return true;
	}
}
