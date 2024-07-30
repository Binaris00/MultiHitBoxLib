package de.dertoaster.multihitboxlib;

import de.dertoaster.multihitboxlib.api.event.server.AssetEnforcementManagerRegistrationEvent;
import de.dertoaster.multihitboxlib.api.event.server.SynchAssetFinderRegistrationEvent;
import de.dertoaster.multihitboxlib.assetsynch.assetfinders.HitboxProfileAssetFinder;
import de.dertoaster.multihitboxlib.assetsynch.impl.AlibAnimationEnforcementManager;
import de.dertoaster.multihitboxlib.assetsynch.impl.AlibModelEnforcementManager;
import de.dertoaster.multihitboxlib.assetsynch.impl.GlibAnimationEnforcementManager;
import de.dertoaster.multihitboxlib.assetsynch.impl.GlibModelEnforcementManager;
import de.dertoaster.multihitboxlib.assetsynch.impl.TextureEnforcementManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Constants.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventHandler {

	@SubscribeEvent
	public static void onAssetEnforcementRegistration(AssetEnforcementManagerRegistrationEvent event) {
		// TODO: Log
		// Textures
		if (!event.tryAdd(MHLibMod.prefixAssesEnforcementManager("textures"), new TextureEnforcementManager())) {
			
		}
		// Dependency specific
		if (Constants.Dependencies.GECKOLIB_LOADED.get()) {
			if (!event.tryAdd(MHLibMod.prefixAssesEnforcementManager("models/geckolib"), new GlibModelEnforcementManager())) {
				
			}
			if (!event.tryAdd(MHLibMod.prefixAssesEnforcementManager("animations/geckolib"), new GlibAnimationEnforcementManager())) {
				
			}
		}
		if (Constants.Dependencies.AZURELIB_LOADED.get()) {
			if (!event.tryAdd(MHLibMod.prefixAssesEnforcementManager("models/azurelib"), new AlibModelEnforcementManager())) {
				
			}
			if (!event.tryAdd(MHLibMod.prefixAssesEnforcementManager("animations/azurelib"), new AlibAnimationEnforcementManager())) {
				
			}
		}
	}
	
	@SubscribeEvent
	public static void onAssetFinderRegistration(SynchAssetFinderRegistrationEvent event) {
		if (!event.tryAdd(MHLibMod.prefixAssetFinder("hitbox_profile"), new HitboxProfileAssetFinder()));
	}
	
}
