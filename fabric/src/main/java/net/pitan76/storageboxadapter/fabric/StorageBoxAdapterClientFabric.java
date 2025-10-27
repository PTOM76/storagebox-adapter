package net.pitan76.storageboxadapter.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.pitan76.storageboxadapter.client.StorageBoxAdapterClient;

public class StorageBoxAdapterClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        StorageBoxAdapterClient.init();
    }
}