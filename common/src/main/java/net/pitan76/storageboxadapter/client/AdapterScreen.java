package net.pitan76.storageboxadapter.client;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.gui.screen.CompatInventoryScreen;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawBackgroundArgs;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.DrawForegroundArgs;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.util.client.ScreenUtil;
import net.pitan76.storageboxadapter.screen.AdapterScreenHandler;

import static net.pitan76.storageboxadapter.StorageBoxAdapter._id;

public class AdapterScreen extends CompatInventoryScreen<AdapterScreenHandler> {
    public PlayerInventory playerInventory;
    protected AdapterScreenHandler screenHandler;

    public AdapterScreen(AdapterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.playerInventory = inventory;
        setBackgroundWidth(176);
        setBackgroundHeight(166);
        this.screenHandler = handler;
    }

    @Override
    public void initOverride() {
        if (this.textRenderer == null)
            this.textRenderer = ClientUtil.getTextRenderer();

        setTitleX(backgroundWidth / 2 - textRenderer.getWidth(title) / 2);
    }

    @Override
    public CompatIdentifier getCompatTexture() {
        return _id("textures/gui/a_slot_inv.png");
    }

    @Override
    public void drawForegroundOverride(DrawForegroundArgs args) {
        ScreenUtil.RendererUtil.drawText(textRenderer, args.drawObjectDM, callGetTitle(), getTitleX(), 6, 4210752);
        ScreenUtil.RendererUtil.drawText(textRenderer, args.drawObjectDM, getPlayerInvTitle(), getPlayerInvTitleX(), 73, 4210752);
    }

    @Override
    public void drawBackgroundOverride(DrawBackgroundArgs args) {
        super.drawBackgroundOverride(args);
        if (screenHandler == null) return;
    }
}