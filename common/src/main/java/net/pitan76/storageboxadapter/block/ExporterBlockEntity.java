package net.pitan76.storageboxadapter.block;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class ExporterBlockEntity extends CompatBlockEntity {
    public ExporterBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public ExporterBlockEntity(TileCreateEvent e) {
        this(null, e); // BlockEntities.EXPORTER.getOrNull()
    }
}
