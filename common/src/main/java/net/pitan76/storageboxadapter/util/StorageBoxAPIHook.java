package net.pitan76.storageboxadapter.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.ItemStack;

public class StorageBoxAPIHook {
    @ExpectPlatform
    public static ItemStack getStack(ItemStack storageBoxStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getCount(ItemStack storageBoxStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setCount(ItemStack storageBoxStack, int count) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setStack(ItemStack storageBoxStack, ItemStack newStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean canInsert(ItemStack insertStack) {
        throw new AssertionError();
    }
}
