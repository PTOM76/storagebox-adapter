package net.pitan76.storageboxadapter.block;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import org.jetbrains.annotations.Nullable;

public class AdapterBlock extends CompatBlock implements ExtendBlockEntityProvider {

    public AdapterBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    @Override
    public CompatActionResult onRightClick(BlockUseEvent e) {
        if (e.isSneaking()) return super.onRightClick(e);
        if (e.isClient()) return e.success();

        Player player = e.getPlayer();
        player.sendMessage(TextUtil.literal("The block was right-clicked!"));

        BlockEntity blockEntity = e.getBlockEntity();
        if (!(blockEntity instanceof AdapterBlockEntity)) {
            return e.success();
        }

        AdapterBlockEntity adapterBlockEntity = (AdapterBlockEntity) blockEntity;
        player.openExtendedMenu(adapterBlockEntity);

        return e.success();
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(TileCreateEvent e) {
        return new AdapterBlockEntity(e);
    }
}
