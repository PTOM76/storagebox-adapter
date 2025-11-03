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
import net.pitan76.mcpitanlib.api.gui.v2.ExtendedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.tile.ExtendBlockEntityTicker;
import net.pitan76.mcpitanlib.api.util.InventoryUtil;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.RegistryLookupUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.storageboxadapter.screen.AdapterScreenHandler;
import net.pitan76.storageboxadapter.util.StorageBoxUtil;

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
        BlockPos pos = callGetPos();
        args.writeVar(PosUtil.x(pos));
        args.writeVar(PosUtil.y(pos));
        args.writeVar(PosUtil.z(pos));
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
        IInventory inv = () -> this.inv;
        return new AdapterScreenHandler(e.syncId, e.playerInventory, this, inv);
    }

    @Override
    public void tick(TileTickEvent<AdapterBlockEntity> e) {
        if (e.isClient()) return;
        if (inv.isEmpty()) return;

        ItemStack storageBoxStack = inv.get(0);
        if (!StorageBoxUtil.isStorageBox(storageBoxStack)) return;

        // StorageBoxの中身を取得してadapterInvにセット (tickまいなのでそろそろ最適化すべき)
        ItemStack stack = StorageBoxUtil.getStack(storageBoxStack, RegistryLookupUtil.getRegistryLookup(this));
        int count = StorageBoxUtil.getCount(storageBoxStack);
        if (ItemStackUtil.isEmpty(stack) || count <= 0) {
            adapterInv.set(0, ItemStackUtil.empty());
            return;
        }

        int min = Math.min(count, 64);
        ItemStackUtil.setCount(stack, min); // Configで最大64を変更可能にするべき
        adapterInv.set(0, stack);



        // adapterInvのstackが変わったらStorageBoxの中身を更新 (矛盾が生じている、だってすでにadapterInvに上でセットしているから)
        ItemStack adapterStack = adapterInv.get(0);
        if (adapterStack.getCount() != min) {
            StorageBoxUtil.setCount(storageBoxStack, adapterStack.getCount());
        }
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
}
