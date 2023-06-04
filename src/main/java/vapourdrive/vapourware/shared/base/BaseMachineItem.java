package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.shared.utils.CompUtils;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class BaseMachineItem extends BaseInfoItemBlock {

    protected final DecimalFormat df = new DecimalFormat("#,###");

    public BaseMachineItem(Block pBlock, Properties pProperties, DeferredComponent comp) {
        super(pBlock, pProperties, comp);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(CompUtils.getComp("fuel.info").withStyle(ChatFormatting.GRAY));
        if (stack.getTag() != null) {
            list = appendAdditionalTagInfo(list, stack.getTag());
            String fuel = df.format(stack.getTag().getInt(VapourWare.MODID + ".fuel") / 100);
            list.add(CompUtils.getArgComp("fuel", fuel));
        }
        super.appendHoverText(stack, level, list, flag);
    }

    protected List<Component> appendAdditionalTagInfo(List<Component> list, CompoundTag tag) {
        return list;
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@NotNull BlockPos pPos, Level pLevel, @Nullable Player pPlayer, @NotNull ItemStack pStack, @NotNull BlockState pState) {
        MinecraftServer minecraftserver = pLevel.getServer();
        if (minecraftserver == null || pStack.getTag() == null) {
            return false;
        }

        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity == null || !pLevel.isClientSide && blockentity.onlyOpCanSetNbt() && (pPlayer == null || !pPlayer.canUseGameMasterBlocks())) {
            return false;
        }

        CompoundTag tag = pStack.getTag();

        if (blockentity instanceof AbstractBaseFuelUserTile fuelUserTile) {
            int fuel = tag.getInt(VapourWare.MODID + ".fuel");
            fuelUserTile.addFuel(fuel, false);
        }

        updateAdditional(blockentity, tag);

        return true;
    }

    protected void updateAdditional(BlockEntity blockentity, CompoundTag tag) {
        blockentity.setChanged();
    }
}

