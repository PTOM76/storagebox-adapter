package net.pitan76.storageboxadapter.client;

import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.storageboxadapter.StorageBoxAdapter;
import net.pitan76.storageboxadapter.screen.ScreenHandlers;

public class StorageBoxAdapterClient {
    public static void init() {
    }

    public static void registerScreens() {
        CompatRegistryClient.registerScreen(StorageBoxAdapter.MOD_ID, ScreenHandlers.ADAPTER, AdapterScreen::new);
    }
}
