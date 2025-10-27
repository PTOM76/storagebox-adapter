package net.pitan76.storageboxadapter.screen;

import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

import static net.pitan76.storageboxadapter.StorageBoxAdapter._id;
import static net.pitan76.storageboxadapter.StorageBoxAdapter.registry;

public class ScreenHandlers {
    public static ScreenHandlerType<AdapterScreenHandler> ADAPTER = new ExtendedScreenHandlerTypeBuilder<>(AdapterScreenHandler::new).build();
    public static ScreenHandlerType<ImporterScreenHandler> IMPORTER = new ExtendedScreenHandlerTypeBuilder<>(ImporterScreenHandler::new).build();
    public static ScreenHandlerType<ExporterScreenHandler> EXPORTER = new ExtendedScreenHandlerTypeBuilder<>(ExporterScreenHandler::new).build();

    public static void init() {
        registry.registerScreenHandlerType(_id("adapter"), () -> ADAPTER);
        registry.registerScreenHandlerType(_id("importer"), () -> IMPORTER);
        registry.registerScreenHandlerType(_id("exporter"), () -> EXPORTER);
    }
}
