package net.pitan76.storageboxadapter.neoforge;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.pitan76.storageboxadapter.client.StorageBoxAdapterClient;

public class StorageBoxAdapterClientNeoForge {

    public static void clientInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                StorageBoxAdapterClient.init();
            } catch (Exception ignore) {
            }
        });
    }

    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        try {
            StorageBoxAdapterClient.registerScreens();
        } catch (Exception ignore) {
        }
    }
}