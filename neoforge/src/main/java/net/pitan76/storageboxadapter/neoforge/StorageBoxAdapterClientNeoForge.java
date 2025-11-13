package net.pitan76.storageboxadapter.neoforge;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.storageboxadapter.client.StorageBoxAdapterClient;

public class StorageBoxAdapterClientNeoForge {
    public static void clientInit(FMLClientSetupEvent e) {
        StorageBoxAdapterClient.init();
    }
}