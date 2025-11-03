
package net.pitan76.storageboxadapter.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandler;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.util.*;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.storageboxadapter.block.AdapterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class AdapterScreenHandler extends ExtendedScreenHandler {
    public Inventory inv;
    public PlayerInventory playerInventory;
    public AdapterBlockEntity tile;

    public AdapterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, null, InventoryUtil.createSimpleInventory(1));

        try {
            int x = PacketByteUtil.readInt(buf);
            int y = PacketByteUtil.readInt(buf);
            int z = PacketByteUtil.readInt(buf);

            Player player = new Player(playerInventory.player);
            tile = (AdapterBlockEntity) WorldUtil.getBlockEntity(player.getWorld(), PosUtil.flooredBlockPos(x, y, z));
        } catch (IndexOutOfBoundsException ignored) { }
    }

    public AdapterScreenHandler(int syncId, PlayerInventory playerInventory, @Nullable AdapterBlockEntity tile, Inventory inv) {
        super(ScreenHandlers.ADAPTER, syncId);

        this.inv = inv;
        this.playerInventory = playerInventory;
        this.tile = tile;
        addPlayerMainInventorySlots(playerInventory, 8, 84);
        addPlayerHotbarSlots(playerInventory, 8, 142);
        addNormalSlot(inv, 0, 80, 35); // StorageBox Slot
    }

    @Override
    public ItemStack quickMoveOverride(Player player, int index) {
        Slot slot = ScreenHandlerUtil.getSlot(this, index);
        if (SlotUtil.hasStack(slot)) {
            ItemStack originalStack = SlotUtil.getStack(slot);

            if (index == 36) { // StorageBox Slot
                if (!this.callInsertItem(originalStack, 0, 36, false)) {
                    return ItemStackUtil.empty();
                }
            } else if (!this.callInsertItem(originalStack, 36, 37, false)) {
                return ItemStackUtil.empty();
            } else if (!this.callInsertItem(originalStack, 0, 36, false)) {
                return ItemStackUtil.empty();
            }

            if (ItemStackUtil.isEmpty(originalStack)) {
                SlotUtil.setStack(slot, ItemStackUtil.empty());
            } else {
                SlotUtil.markDirty(slot);
            }
        }
        return ItemStackUtil.empty();
    }
}