package net.pitan76.storageboxadapter.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

public class StorageBoxAPIHook {
    @ExpectPlatform
    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable RegistryWrapper.WrapperLookup registryLookup) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getCount(ItemStack storageBoxStack, @Nullable RegistryWrapper.WrapperLookup registryLookup) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setCount(ItemStack storageBoxStack, int count) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setStack(ItemStack storageBoxStack, ItemStack newStack, CompatRegistryLookup registryLookup) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean canInsert(ItemStack insertStack) {
        throw new AssertionError();
    }
}
