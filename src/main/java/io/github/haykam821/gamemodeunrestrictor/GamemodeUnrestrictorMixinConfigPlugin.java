package io.github.haykam821.gamemodeunrestrictor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.google.common.base.Predicates;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;

public class GamemodeUnrestrictorMixinConfigPlugin implements IMixinConfigPlugin {
	private static final String MIXIN_CLASS_PREFIX = "io.github.haykam821.gamemodeunrestrictor.mixin.";

	private static final String MIXIN_CLASS_OLD = MIXIN_CLASS_PREFIX + "GameModeSelectionScreenMixinOld";
	private static final String MIXIN_CLASS_NEW = MIXIN_CLASS_PREFIX + "GameModeSelectionScreenMixin";

	private static final Predicate<Version> HAS_GAME_MODE_SELECTION_PARAMETER = createVersionCompatibility(">=1.20-alpha.23.16.a");

	@Override
	public void onLoad(String mixinPackage) {
		return;
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClass, String mixinClass) {
		if (mixinClass.equals(MIXIN_CLASS_OLD)) {
			return !HAS_GAME_MODE_SELECTION_PARAMETER.test(getMinecraftVersion());
		} else if (mixinClass.equals(MIXIN_CLASS_NEW)) {
			return HAS_GAME_MODE_SELECTION_PARAMETER.test(getMinecraftVersion());
		}

		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		return;
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		return;
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		return;
	}

	private static Version getMinecraftVersion() {
		Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("minecraft");

		if (container.isPresent()) {
			Version version = container.get().getMetadata().getVersion();

			if (version instanceof SemanticVersion) {
				return version;
			}
		}

		return null;
	}

	private static Predicate<Version> createVersionCompatibility(String versionRange) {
		try {
			return VersionPredicate.parse(versionRange);
		} catch (VersionParsingException exception) {
			return Predicates.alwaysFalse();
		}
	}	
}