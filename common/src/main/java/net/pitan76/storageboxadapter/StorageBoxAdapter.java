package net.pitan76.storageboxadapter;

import net.pitan76.storageboxadapter.block.BlockEntities;
import net.pitan76.storageboxadapter.block.Blocks;
import net.pitan76.storageboxadapter.item.Items;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class StorageBoxAdapter extends CommonModInitializer {
    public static final String MOD_ID = "storagebox-adapter";
    public static final String MOD_NAME = "StorageBox Adapter";

    public static StorageBoxAdapter INSTANCE;
    public static CompatRegistryV2 registry;

    @Override
    public void init() {
        INSTANCE = this;
        registry = super.registry;

        BlockEntities.init();
        Blocks.init();
        Items.init();
    }

    // ----
    /**
     * @param path The path of the id
     * @return The id
     */
    public static CompatIdentifier _id(String path) {
        return CompatIdentifier.of(MOD_ID, path);
    }

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }
}