package net.pitan76.storageboxadapter.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.pitan76.storageboxadapter.StorageBoxAdapter;

public class StorageBoxAdapterFabric implements ModInitializer {

    public static boolean isStorageBox149over = true; // StorageBox for Fabricが1.4.9以上であるかどうか

    @Override
    public void onInitialize() {
        new StorageBoxAdapter();

        FabricLoader.getInstance().getModContainer("storagebox").ifPresent(container -> {
            String rawVersion = container.getMetadata().getVersion().getFriendlyString();
            if (rawVersion.startsWith("1.4.9")) {
                isStorageBox149over = true;
            } else {
                try {
                    Version version = Version.parse(rawVersion);
                    isStorageBox149over = version.compareTo(Version.parse("1.4.9")) >= 0;
                } catch (VersionParsingException ignored) {}
            }

        });
    }
}