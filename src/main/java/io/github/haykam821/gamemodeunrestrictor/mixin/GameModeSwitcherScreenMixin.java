package io.github.haykam821.gamemodeunrestrictor.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.screen.GameModeSwitcherScreen;
import net.minecraft.client.network.ClientPlayerEntity;

@Mixin(GameModeSwitcherScreen.class)
public class GameModeSwitcherScreenMixin {
	@Redirect(method = "apply(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/screen/GameModeSwitcherScreen$GameModeSelection;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasPermissionLevel(I)Z"))
	private static boolean bypassApplyPermissionCheck(ClientPlayerEntity entity, int permissionLevel) {
		return true;
	}
}
