package net.pitan76.storageboxadapter.util.fabric;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.storagebox.StorageBoxItem;
import net.pitan76.storagebox.api.StorageBoxUtil;
import net.pitan76.storageboxadapter.fabric.StorageBoxAdapterFabric;
import org.jetbrains.annotations.Nullable;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return StorageBoxUtil.getStackInStorageBox(storageBoxStack);
    }

    public static int getCount(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return StorageBoxUtil.getAmountInStorageBox(storageBoxStack);
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        if (!StorageBoxAdapterFabric.isStorageBox149over && count <= 0) {
            StorageBoxItem.removeItemDataAsInt(storageBoxStack, StorageBoxItem.KEY_SIZE);
            StorageBoxItem.removeItemDataAsInt(storageBoxStack, StorageBoxItem.KEY_ITEM_DATA);
            StorageBoxItem.removeItemDataAsInt(storageBoxStack, StorageBoxItem.KEY_ITEM_ID);
            StorageBoxItem.removeItemDataAsInt(storageBoxStack, StorageBoxItem.KEY_AUTO);
            return;
        }

        StorageBoxUtil.setAmountInStorageBox(storageBoxStack, count);
    }

    public static void setStack(ItemStack storageBoxStack, ItemStack newStack, CompatRegistryLookup registryLookup) {
        StorageBoxUtil.setStackInStorageBox(storageBoxStack, newStack);
    }

    public static boolean canInsert(ItemStack insertStack) {
        return StorageBoxUtil.canInsertStack(insertStack);
    }
}
