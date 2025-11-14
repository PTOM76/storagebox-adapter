package net.pitan76.storageboxadapter.util.fabric;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.storagebox.api.StorageBoxUtil;
import org.jetbrains.annotations.Nullable;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return StorageBoxUtil.getStackInStorageBox(storageBoxStack);
    }

    public static int getCount(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return StorageBoxUtil.getAmountInStorageBox(storageBoxStack);
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        StorageBoxUtil.setAmountInStorageBox(storageBoxStack, count);
    }
}
