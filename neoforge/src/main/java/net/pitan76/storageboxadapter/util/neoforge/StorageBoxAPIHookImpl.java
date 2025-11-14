package net.pitan76.storageboxadapter.util.neoforge;

import net.minecraft.item.ItemStack;
import net.minecraft.storagebox.ItemStorageBox;
import org.jetbrains.annotations.Nullable;
import net.minecraft.registry.RegistryWrapper;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return ItemStorageBox.peekItemStack(storageBoxStack, registryLookup);
    }

    public static int getCount(ItemStack storageBoxStack, @Nullable RegistryWrapper.WrapperLookup registryLookup) {
        return ItemStorageBox.peekItemStackAll(storageBoxStack, registryLookup).getCount();
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        ItemStorageBox.setItemStackSize(storageBoxStack, count);
    }
}
