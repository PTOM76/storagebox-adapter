
package net.pitan76.storageboxadapter.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
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
    public AdapterBlockEntity tile = null;

    public AdapterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, null, InventoryUtil.createSimpleInventory(9));
        NbtCompound data = PacketByteUtil.readNbt(buf);
        if (data == null) return;
        int x, y, z;
        if (NbtUtil.has(data, "x") && NbtUtil.has(data, "y") && NbtUtil.has(data, "z")) {
            x = NbtUtil.getInt(data, "x");
            y = NbtUtil.getInt(data, "y");
            z = NbtUtil.getInt(data, "z");

            Player player = new Player(playerInventory.player);
            tile = (AdapterBlockEntity) WorldUtil.getBlockEntity(player.getWorld(), PosUtil.flooredBlockPos(x, y, z));
        }
    }

    public AdapterScreenHandler(int syncId, PlayerInventory playerInventory, @Nullable AdapterBlockEntity tile, Inventory inv) {
        super(ScreenHandlers.ADAPTER, syncId);

        this.inv = inv;
        this.playerInventory = playerInventory;
        this.tile = tile;
        addPlayerMainInventorySlots(playerInventory, 8, 84);
        addPlayerHotbarSlots(playerInventory, 8, 142);
        addNormalSlot(inv, 0, 80, 35); // Target Slot
    }

    @Override
    public ItemStack quickMoveOverride(Player player, int index) {
        Slot slot = ScreenHandlerUtil.getSlot(this, index);
        if (SlotUtil.hasStack(slot)) {
            ItemStack originalStack = SlotUtil.getStack(slot);

            if (index == 37) { // Target Slot
                SlotUtil.setStack(slot, ItemStackUtil.empty());
                return ItemStackUtil.empty();
            }

            // Inventory
            if (index < 36) {
                for (int i = 37; i <= 46; i++) {
                    Slot targetSlot = ScreenHandlerUtil.getSlot(this, i);
                    if (SlotUtil.getStack(targetSlot).isEmpty()) {
                        ItemStack newTargetStack = originalStack.copy();
                        newTargetStack.setCount(1);
                        SlotUtil.setStack(targetSlot, newTargetStack);
                        return ItemStackUtil.empty();
                    }
                }

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

    @Override
    public void overrideOnSlotClick(int index, int button, SlotActionType actionType, Player player) {
        if (index >= 37 && index <= 46) { // Target Slot
            ItemStack oldStack = getCursorStack().copy();
            super.overrideOnSlotClick(index, button, actionType, player);
            if (!ItemStackUtil.isEmpty(oldStack))
                callSetCursorStack(oldStack);

            return;
        }
        super.overrideOnSlotClick(index, button, actionType, player);
    }
}