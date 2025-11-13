package net.pitan76.storageboxadapter.forge;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.storageboxadapter.client.StorageBoxAdapterClient;

public class StorageBoxAdapterClientForge {
    public static void clientInit(FMLClientSetupEvent e) {
        StorageBoxAdapterClient.init();
    }
}