package de.dertoaster.multihitboxlib.api;

import java.util.Optional;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DataPackRegistryEvent.NewRegistry;

public record DatapackRegistry<T>(
		ResourceKey<Registry<T>> registryKey,
		Codec<T> objectCodec,
		Codec<Holder<T>> registryCodec
	) {
	
	public DatapackRegistry(final ResourceLocation id, final Codec<T> codec) {
		this(ResourceKey.createRegistryKey(id), codec);
	}
	
	public DatapackRegistry(final ResourceKey<Registry<T>> resourceKey, final Codec<T> codec) {
		this(resourceKey, codec, RegistryFileCodec.create(resourceKey, codec));
	}

	public void registerSynchable(NewRegistry registryEvent) {
		register(registryEvent, true);
	}
	
	public void register(NewRegistry registryEvent) {
		register(registryEvent, false);
	}
	
	public void register(NewRegistry registryEvent, boolean synchable) {
		if (synchable) {
			registryEvent.dataPackRegistry(registryKey, objectCodec, objectCodec);
		} else {
			registryEvent.dataPackRegistry(registryKey, objectCodec);
		}
	}
	
	@Nullable
	public T get(ResourceLocation id, RegistryAccess registryAccess) {
		Optional<Registry<T>> optRegistry = registryAccess.registry(this.registryKey());
		if (optRegistry.isEmpty()) {
			return null;
		}
		return optRegistry.get().get(id);
	}
	
}
