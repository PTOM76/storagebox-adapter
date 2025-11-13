package net.pitan76.storageboxadapter.util;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import org.jetbrains.annotations.Nullable;

public class StorageBoxUtil {
    public static boolean isStorageBox(ItemStack stack) {
        return ItemUtil.toId(ItemStackUtil.getItem(stack)).toString().equalsIgnoreCase("storagebox:storagebox");
    }

    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return StorageBoxAPIHook.getStack(storageBoxStack, registryLookup);
    }

    public static int getCount(ItemStack storageBoxStack) {
        return StorageBoxAPIHook.getCount(storageBoxStack);
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        StorageBoxAPIHook.setCount(storageBoxStack, count);
    }
}
