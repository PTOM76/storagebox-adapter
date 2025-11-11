package net.pitan76.storageboxadapter.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.gui.slot.CompatibleSlot;
import net.pitan76.storageboxadapter.screen.AdapterScreenHandler;
import net.pitan76.storageboxadapter.util.StorageBoxUtil;

public class StorageBoxSlot extends CompatibleSlot {
    AdapterScreenHandler handler;

    public StorageBoxSlot(AdapterScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (!StorageBoxUtil.isStorageBox(stack)) return false;
        return super.canInsert(stack);
    }
//
//    @Override
//    public void callSetStack(ItemStack stack) {
//        //handler.inv
//    }
}
