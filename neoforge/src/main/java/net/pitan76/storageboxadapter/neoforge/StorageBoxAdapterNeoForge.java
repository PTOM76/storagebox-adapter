package net.pitan76.storageboxadapter.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.pitan76.storageboxadapter.StorageBoxAdapter;

@Mod(StorageBoxAdapter.MOD_ID)
public class StorageBoxAdapterNeoForge {
    public StorageBoxAdapterNeoForge(ModContainer modContainer) {
        IEventBus eventBus = modContainer.getEventBus();

        new StorageBoxAdapter();
    }
}