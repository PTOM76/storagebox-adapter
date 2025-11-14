package net.pitan76.storageboxadapter.util.neoforge;

import net.minecraft.item.ItemStack;
import net.minecraft.storagebox.ItemStorageBox;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack) {
        return ItemStorageBox.peekItemStack(storageBoxStack);
    }

    public static int getCount(ItemStack storageBoxStack) {
        ItemStack stack = ItemStorageBox.peekItemStackAll(storageBoxStack);
        if (stack == null) return 0;
        return stack.getCount();
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        ItemStorageBox.setItemStackSize(storageBoxStack, count);
    }

    public static void setStack(ItemStack storageBoxStack, ItemStack newStack) {
        ItemStorageBox.setItemStack(storageBoxStack, newStack);
    }

    public static boolean canInsert(ItemStack insertStack) {
        return !(insertStack.getItem() instanceof ItemStorageBox)
                && insertStack.getComponentChanges().isEmpty()
                && insertStack.isStackable()
                && !insertStack.hasEnchantments();
    }
}
