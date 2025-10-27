package net.pitan76.storageboxadapter.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class StorageBoxUtil {
    public static boolean isStorageBox(ItemStack stack) {
        return ItemUtil.toId(ItemStackUtil.getItem(stack)).toString().equalsIgnoreCase("storagebox:storagebox");
    }

    public static ItemStack getStack(ItemStack storageBoxStack, @Nullable CompatRegistryLookup registryLookup) {
        if (!isStorageBox(storageBoxStack)) return ItemStackUtil.empty();

        NbtCompound nbt = storageBoxStack.getNbt();

        if (nbt == null) return ItemStackUtil.empty();
        Optional<ItemStack> optionalStack = NbtUtil.getItemStack(nbt, "StorageItemData", registryLookup);
        return optionalStack.orElseGet(ItemStackUtil::empty);

    }

    public static int getCount(ItemStack storageBoxStack) {
        if (!isStorageBox(storageBoxStack)) return 0;

        NbtCompound nbt = storageBoxStack.getNbt();
        if (nbt == null) return 0;
        return NbtUtil.getInt(nbt, "StorageSize");
    }
}
