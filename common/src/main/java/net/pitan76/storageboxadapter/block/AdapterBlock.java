package net.pitan76.storageboxadapter.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.ItemScattererUtil;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;
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
        BlockEntity blockEntity = e.getBlockEntity();
        if (!(blockEntity instanceof AdapterBlockEntity)) return e.success();

        AdapterBlockEntity adapterBlockEntity = (AdapterBlockEntity) blockEntity;
        player.openExtendedMenu(adapterBlockEntity);

        return e.success();
    }

    @Override
    public void onStateReplaced(StateReplacedEvent e) {
        World world = e.getMidohraWorld();
        BlockPos pos = e.getMidohraPos();
        if (e.isSameState()) return;

        BlockEntity blockEntity = e.getBlockEntity();
        if (blockEntity instanceof AdapterBlockEntity) {
            AdapterBlockEntity adapter = (AdapterBlockEntity) blockEntity;
            ItemScattererUtil.spawn(world, pos, adapter.inv);
            e.updateComparators();
        }
        super.onStateReplaced(e);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(TileCreateEvent e) {
        return new AdapterBlockEntity(e);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityType<T> getBlockEntityType() {
        return (BlockEntityType<T>) BlockEntities.ADAPTER.getOrNull();
    }

    @Override
    public boolean isTick() {
        return true;
    }
}
