package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.setup.Registration;
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
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        int fuelInt = stack.getOrDefault(Registration.FUEL, 0);
        if(fuelInt >0) {
            String fuel = df.format(fuelInt / 100);
            tooltipComponents.add(CompUtils.getArgComp("fuel", fuel));
        }
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(CompUtils.getComp("fuel.info").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    protected List<Component> appendAdditionalTagInfo(List<Component> list, CompoundTag tag) {
        return list;
    }

    @Override
    protected boolean updateCustomBlockEntityTag(@NotNull BlockPos pPos, Level pLevel, @Nullable Player pPlayer, @NotNull ItemStack pStack, @NotNull BlockState pState) {
        MinecraftServer minecraftserver = pLevel.getServer();
        if (minecraftserver == null) {
            return false;
        }
        if (pStack.get(Registration.FUEL) == null) {
            return false;
        }

        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity == null || !pLevel.isClientSide && blockentity.onlyOpCanSetNbt() && (pPlayer == null || !pPlayer.canUseGameMasterBlocks())) {
            return false;
        }

        if (blockentity instanceof AbstractBaseFuelUserTile fuelUserTile) {
            int fuelInt = pStack.getOrDefault(Registration.FUEL, 0);
            fuelUserTile.addFuel(fuelInt, false);
        }

        updateAdditional(blockentity, pStack);

        return true;
    }

    protected void updateAdditional(BlockEntity blockentity, ItemStack pStack) {
        blockentity.setChanged();
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(this);
        stack.set(Registration.FUEL, 0);
        return stack;
    }
}

