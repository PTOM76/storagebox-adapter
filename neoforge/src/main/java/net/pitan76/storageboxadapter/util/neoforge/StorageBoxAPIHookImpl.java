package net.pitan76.storageboxadapter.util.neoforge;

import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import org.jetbrains.annotations.Nullable;

public class StorageBoxAPIHookImpl {
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return null;
    }

    public static int getCount(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        return -1;
    }

    public static void setCount(ItemStack storageBoxStack, int count) {

    }
}
