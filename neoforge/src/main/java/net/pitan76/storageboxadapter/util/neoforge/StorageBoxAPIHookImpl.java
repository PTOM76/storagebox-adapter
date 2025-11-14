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
        ItemStack stack = ItemStorageBox.peekItemStackAll(storageBoxStack, registryLookup);
        if (stack == null) return 0;
        return stack.getCount();
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        ItemStorageBox.setItemStackSize(storageBoxStack, count);
    }

    public static void setStack(ItemStack storageBoxStack, ItemStack newStack, RegistryWrapper.WrapperLookup registryLookup) {
        ItemStorageBox.setItemStack(storageBoxStack, newStack, registryLookup);
    }

    public static boolean canInsert(ItemStack insertStack) {
        return !(insertStack.getItem() instanceof ItemStorageBox)
                && insertStack.getComponentChanges().isEmpty()
                && insertStack.isStackable()
                && !insertStack.hasEnchantments();
    }
}
