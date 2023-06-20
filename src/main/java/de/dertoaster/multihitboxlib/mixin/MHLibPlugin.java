package de.dertoaster.multihitboxlib.mixin;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.google.common.collect.ImmutableMap;

import net.minecraftforge.fml.ModList;

public class MHLibPlugin implements IMixinConfigPlugin {
	
	private static final Supplier<Boolean> TRUE = () -> true;
	private static final Supplier<Boolean> GECKOLIB_LOADED = () -> {
		ModList ml = ModList.get();
		if (ml == null) {
			return false;
		}
		return ml.isLoaded("geckolib");
	};
	
	private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
			// TODO: Fix, doesn't seem to work, if active the layer won't be added
			"de.dertoaster.multihitboxlib.mixin.geckolib.MixinGeoEntityRenderer", GECKOLIB_LOADED,
			"de.dertoaster.multihitboxlib.mixin.geckolib.MixinGeoReplacedEntityRenderer", GECKOLIB_LOADED
	);

	@Override
	public void onLoad(String mixinPackage) {

	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

}
