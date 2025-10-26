package net.pitan76.storageboxadapter.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;

import static net.pitan76.storageboxadapter.StorageBoxAdapter._id;
import static net.pitan76.storageboxadapter.StorageBoxAdapter.registry;

public class BlockEntities {
    public static RegistryResult<BlockEntityType<?>> IMPORTER;
    public static RegistryResult<BlockEntityType<?>> EXPORTER;

    public static void init() {
        IMPORTER = registry.registerBlockEntityType(_id("importer"), () -> create(ImporterBlockEntity::new, Blocks.IMPORTER.getOrNull()));
        EXPORTER = registry.registerBlockEntityType(_id("exporter"), () -> create(ExporterBlockEntity::new, Blocks.EXPORTER.getOrNull()));
    }

    public static <T extends BlockEntity> BlockEntityType<T> create(BlockEntityTypeBuilder.Factory<T> supplier, Block... blocks) {
        return BlockEntityTypeBuilder.create(supplier, blocks).build();
    }
}