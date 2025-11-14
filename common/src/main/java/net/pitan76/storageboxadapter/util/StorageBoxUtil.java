package net.pitan76.storageboxadapter.util;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;

public class StorageBoxUtil {
    public static boolean isStorageBox(ItemStack stack) {
        return ItemUtil.toId(ItemStackUtil.getItem(stack)).toString().equalsIgnoreCase("storagebox:storagebox");
    }

    public static ItemStack getStack(ItemStack storageBoxStack) {
        return StorageBoxAPIHook.getStack(storageBoxStack);
    }

    public static int getCount(ItemStack storageBoxStack) {
        return StorageBoxAPIHook.getCount(storageBoxStack);
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        StorageBoxAPIHook.setCount(storageBoxStack, count);
    }

    public static void setStack(ItemStack storageBoxStack, ItemStack newStack) {
        StorageBoxAPIHook.setStack(storageBoxStack, newStack);
    }

    public static boolean canInsert(ItemStack insertStack) {
        return StorageBoxAPIHook.canInsert(insertStack);
    }
}
