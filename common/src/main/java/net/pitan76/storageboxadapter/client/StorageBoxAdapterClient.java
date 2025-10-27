package net.pitan76.storageboxadapter.client;

import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.storageboxadapter.screen.ScreenHandlers;

public class StorageBoxAdapterClient {
    public static void init() {
        CompatRegistryClient.registerScreen("adapter", ScreenHandlers.ADAPTER, AdapterScreen::new);
    }
}
