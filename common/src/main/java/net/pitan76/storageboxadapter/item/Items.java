package net.pitan76.storageboxadapter.item;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.midohra.item.ItemGroups;
import net.pitan76.storageboxadapter.block.Blocks;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;

import static net.pitan76.storageboxadapter.StorageBoxAdapter._id;
import static net.pitan76.storageboxadapter.StorageBoxAdapter.registry;

public class Items {

    public static final ItemSettingsBuilder STANDARD_ITEM_SETTINGS = ItemSettingsBuilder.of()
            .addGroup(ItemGroups.MISC);

    public static RegistryResult<Item> ADAPTER;
    public static RegistryResult<Item> IMPORTER;
    public static RegistryResult<Item> EXPORTER;

    public static void init() {
        ADAPTER = registry.registerItem(_id("adapter"), () -> ItemUtil.create(Blocks.ADAPTER.getOrNull(), STANDARD_ITEM_SETTINGS.build(_id("adapter"))));
        IMPORTER = registry.registerItem(_id("importer"), () -> ItemUtil.create(Blocks.IMPORTER.getOrNull(), STANDARD_ITEM_SETTINGS.build(_id("importer"))));
        EXPORTER = registry.registerItem(_id("exporter"), () -> ItemUtil.create(Blocks.EXPORTER.getOrNull(), STANDARD_ITEM_SETTINGS.build(_id("exporter"))));
    }
}
