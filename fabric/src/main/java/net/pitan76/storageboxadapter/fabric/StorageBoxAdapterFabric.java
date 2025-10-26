package net.pitan76.storageboxadapter.fabric;

import net.fabricmc.api.ModInitializer;
import net.pitan76.storageboxadapter.StorageBoxAdapter;

public class StorageBoxAdapterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new StorageBoxAdapter();
    }
}