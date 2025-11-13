package net.pitan76.storageboxadapter.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import static net.pitan76.storageboxadapter.StorageBoxAdapter._id;
import static net.pitan76.storageboxadapter.StorageBoxAdapter.registry;

public class Blocks {

    public static RegistryResult<Block> ADAPTER;
    //public static RegistryResult<Block> IMPORTER;
    //public static RegistryResult<Block> EXPORTER;

    public static void init() {
        ADAPTER = registry.registerBlock(_id("adapter"), () -> new AdapterBlock(CompatibleBlockSettings.of(_id("adapter"), CompatibleMaterial.STONE)));
        //IMPORTER = registry.registerBlock(_id("importer"), () -> new ImporterBlock(CompatibleBlockSettings.of(_id("importer"), CompatibleMaterial.STONE)));
        //EXPORTER = registry.registerBlock(_id("exporter"), () -> new ExporterBlock(CompatibleBlockSettings.of(_id("exporter"), CompatibleMaterial.STONE)));
    }
}
