package net.pitan76.storageboxadapter.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.storageboxadapter.StorageBoxAdapter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(StorageBoxAdapter.MOD_ID)
public class StorageBoxAdapterForge {
    public StorageBoxAdapterForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EventBuses.registerModEventBus(StorageBoxAdapter.MOD_ID, modEventBus);
        new StorageBoxAdapter();

        if (PlatformUtil.isClient())
            FMLJavaModLoadingContext.get().getModEventBus().addListener(StorageBoxAdapterClientForge::clientInit);
    }
}