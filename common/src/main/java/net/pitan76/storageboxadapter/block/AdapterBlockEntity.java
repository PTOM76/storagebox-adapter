package net.pitan76.storageboxadapter.block;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.inventory.IInventory;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.VanillaStyleSidedInventory;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.AvailableSlotsArgs;
import net.pitan76.mcpitanlib.api.gui.v2.ExtendedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.storageboxadapter.screen.AdapterScreenHandler;

public class AdapterBlockEntity extends CompatBlockEntity implements ExtendBlockEntityTicker<AdapterBlockEntity>, VanillaStyleSidedInventory, IInventory, ExtendedScreenHandlerFactory {

    public ItemStackList inv = ItemStackList.ofSize(1);
    public ItemStackList adapterInv = ItemStackList.ofSize(1);

    public AdapterBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public AdapterBlockEntity(TileCreateEvent e) {
        this(BlockEntities.ADAPTER.getOrNull(), e);
    }

    @Override
    public Text getDisplayName(DisplayNameArgs args) {
        return TextUtil.translatable("container.storagebox-adapter.adapter");
    }

    @Override
    public void writeExtraData(ExtraDataArgs args) {
        NbtCompound data = NbtUtil.create();
        BlockPos pos = callGetPos();

        NbtUtil.putInt(data, "x", PosUtil.x(pos));
        NbtUtil.putInt(data, "y", PosUtil.y(pos));
        NbtUtil.putInt(data, "z", PosUtil.z(pos));
        args.writeVar(data);
    }

    @Override
    public ItemStackList getItems() {
        return adapterInv;
    }

    @Override
    public int[] getAvailableSlots(AvailableSlotsArgs args) {
        return new int[0];
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        return new AdapterScreenHandler(e.syncId, e.playerInventory, this, inv.toInventory());
    }

    @Override
    public void tick(TileTickEvent<AdapterBlockEntity> e) {
        if (e.isClient()) return;
        if (inv.isEmpty()) return;


    }
}
