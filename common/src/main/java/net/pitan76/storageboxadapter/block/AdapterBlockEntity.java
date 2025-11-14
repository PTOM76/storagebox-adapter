package net.pitan76.storageboxadapter.block;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.event.container.factory.DisplayNameArgs;
import net.pitan76.mcpitanlib.api.event.container.factory.ExtraDataArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.event.tile.TileTickEvent;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.api.gui.inventory.IInventory;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.VanillaStyleSidedInventory;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.AvailableSlotsArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanInsertArgs;
import net.pitan76.mcpitanlib.api.gui.v2.ExtendedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.storageboxadapter.screen.AdapterScreenHandler;
import net.pitan76.storageboxadapter.util.StorageBoxUtil;

public class AdapterBlockEntity extends CompatBlockEntity implements ExtendBlockEntityTicker<AdapterBlockEntity>, VanillaStyleSidedInventory, IInventory, ExtendedScreenHandlerFactory {

    public ItemStackList inv = ItemStackList.ofSize(1, ItemStackUtil.empty());
    public ItemStackList tmpInv = ItemStackList.ofSize(1, ItemStackUtil.empty());

    public ItemStack prevStack = ItemStackUtil.empty();
    public int tmpStackMaxCount = 32;

    public AdapterBlockEntity(BlockEntityType<?> type, TileCreateEvent e) {
        super(type, e);
    }

    public AdapterBlockEntity(TileCreateEvent e) {
        this(BlockEntities.ADAPTER.getOrNull(), e);
    }

    @Override
    public Text getDisplayName(DisplayNameArgs args) {
        return TextUtil.translatable("container.storageboxadapter.adapter");
    }

    @Override
    public void writeExtraData(ExtraDataArgs args) {
        BlockPos pos = callGetPos();
        args.writeVar(PosUtil.x(pos));
        args.writeVar(PosUtil.y(pos));
        args.writeVar(PosUtil.z(pos));
    }

    @Override
    public ItemStackList getItems() {
        return tmpInv;
    }

    @Override
    public int[] getAvailableSlots(AvailableSlotsArgs args) {
        return new int[]{0};
    }

    @Override
    public boolean canInsert(CanInsertArgs args) {
        if (inv.get(0).isEmpty()) return false;
        return VanillaStyleSidedInventory.super.canInsert(args);
    }

    @Override
    public void writeNbt(WriteNbtArgs args) {
        super.writeNbt(args);
        InventoryUtil.writeNbt(args, inv);
    }

    @Override
    public void readNbt(ReadNbtArgs args) {
        super.readNbt(args);
        InventoryUtil.readNbt(args, inv);
    }

    @Override
    public ScreenHandler createMenu(CreateMenuEvent e) {
        IInventory inv = () -> this.inv;
        return new AdapterScreenHandler(e.syncId, e.playerInventory, this, inv);
    }

    @Override
    public void tick(TileTickEvent<AdapterBlockEntity> e) {
        if (e.isClient()) return;
        if (inv.isEmpty()) {
            tmpInv.set(0, ItemStackUtil.empty());
            return;
        }

        ItemStack storageBoxStack = inv.get(0);
        if (!StorageBoxUtil.isStorageBox(storageBoxStack)) return;

        int count = StorageBoxUtil.getCount(storageBoxStack);
        int min = Math.min(count, tmpStackMaxCount / 2);
        if (storageBoxStack != prevStack) {
            // StorageBoxの中身を取得してadapterInvにセット
            ItemStack stack = StorageBoxUtil.getStack(storageBoxStack);

            prevStack = storageBoxStack;
            if (ItemStackUtil.isEmpty(stack) || count <= 0) {
                tmpInv.set(0, ItemStackUtil.empty());
                inv.set(0, storageBoxStack);
                return;
            }

            tmpStackMaxCount = ItemStackUtil.getMaxCount(stack);
            min = Math.min(count, tmpStackMaxCount / 2);
            ItemStackUtil.setCount(stack, min);
            tmpInv.set(0, stack);
        }

        ItemStack adapterStack = tmpInv.get(0);
        if (count <= 0) { // StorageBoxが空のときはadapterInvの中身をStorageBoxに移す
            if (StorageBoxUtil.canInsert(adapterStack)) {
                ItemStack newStack = ItemStackUtil.copy(adapterStack);
                StorageBoxUtil.setStack(storageBoxStack, newStack);
                StorageBoxUtil.setCount(storageBoxStack, ItemStackUtil.getCount(newStack));
                inv.set(0, storageBoxStack);
                tmpStackMaxCount = ItemStackUtil.getMaxCount(newStack);
                return;
            }
        }

        // adapterInvのstackが変わったらStorageBoxの中身を更新
        int adapterCount = ItemStackUtil.getCount(adapterStack);
        if (adapterCount != min) {
            count = count + (adapterCount - min);
            StorageBoxUtil.setCount(storageBoxStack, count);
            ItemStackUtil.setCount(adapterStack, Math.min(count, tmpStackMaxCount / 2));
            tmpInv.set(0, adapterStack);
        }
    }
}
