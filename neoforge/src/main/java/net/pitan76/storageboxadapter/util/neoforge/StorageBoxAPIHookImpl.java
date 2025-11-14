package net.pitan76.storageboxadapter.util.neoforge;

import net.minecraft.item.ItemStack;
import net.minecraft.storagebox.ItemStorageBox;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import org.jetbrains.annotations.Nullable;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return ItemStorageBox.peekItemStack(storageBoxStack, registryLookup.getRegistryLookup());
    }

    public static int getCount(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return ItemStorageBox.peekItemStackAll(storageBoxStack, registryLookup.getRegistryLookup()).getCount();
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        ItemStorageBox.setItemStackSize(storageBoxStack, count);
    }
}
