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
import net.pitan76.storageboxadapter.block.ExporterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ExporterScreenHandler extends ExtendedScreenHandler {
    public Inventory filter;
    public PlayerInventory playerInventory;
    public ExporterBlockEntity tile = null;

    public ExporterScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, null, InventoryUtil.createSimpleInventory(9));
        NbtCompound data = PacketByteUtil.readNbt(buf);
        if (data == null) return;
        int x, y, z;
        if (NbtUtil.has(data, "x") && NbtUtil.has(data, "y") && NbtUtil.has(data, "z")) {
            x = NbtUtil.getInt(data, "x");
            y = NbtUtil.getInt(data, "y");
            z = NbtUtil.getInt(data, "z");

            Player player = new Player(playerInventory.player);
            tile = (ExporterBlockEntity) WorldUtil.getBlockEntity(player.getWorld(), PosUtil.flooredBlockPos(x, y, z));
        }
    }

    public ExporterScreenHandler(int syncId, PlayerInventory playerInventory, @Nullable ExporterBlockEntity tile, Inventory filter) {
        super(null, syncId); // ScreenHandlers.EXPORTER

        this.filter = filter;
        this.playerInventory = playerInventory;
        this.tile = tile;
        addPlayerMainInventorySlots(playerInventory, 8, 102);
        addPlayerHotbarSlots(playerInventory, 8, 160);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addNormalSlot(filter, j + i * 3, 62 + j * 18, 22 + i * 18);
            }
        }
    }

    @Override
    public ItemStack quickMoveOverride(Player player, int index) {
        Slot slot = ScreenHandlerUtil.getSlot(this, index);
        if (SlotUtil.hasStack(slot)) {
            ItemStack originalStack = SlotUtil.getStack(slot);
            // Filter Slot
            if (index >= 37 && index <= 46) {
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