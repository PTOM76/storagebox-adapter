package net.pitan76.storageboxadapter.util.forge;

import net.minecraft.item.ItemStack;
import net.minecraft.storagebox.ItemStorageBox;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import org.jetbrains.annotations.Nullable;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return ItemStorageBox.peekItemStack(storageBoxStack, 1);
    }

    public static int getCount(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        ItemStack stack = ItemStorageBox.peekItemStackAll(storageBoxStack);
        if (stack.isEmpty()) return 0;
        return stack.getCount();
    }

    public static void setCount(ItemStack storageBoxStack, int count) {
        ItemStorageBox.setItemStackSize(storageBoxStack, count);
    }

    public static void setStack(ItemStack storageBoxStack, ItemStack newStack, CompatRegistryLookup registryLookup) {
        ItemStorageBox.setItemStack(storageBoxStack, newStack);
    }

    public static boolean canInsert(ItemStack insertStack) {
        return !(insertStack.getItem() instanceof ItemStorageBox)
                && !insertStack.hasNbt()
                && insertStack.isStackable()
                && !insertStack.hasEnchantments();
    }
}
