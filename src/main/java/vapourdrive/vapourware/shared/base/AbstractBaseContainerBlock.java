package vapourdrive.vapourware.shared.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.setup.Registration;

import static net.minecraft.world.Containers.dropItemStack;

public abstract class AbstractBaseContainerBlock extends BaseEntityBlock {

    protected AbstractBaseContainerBlock(Properties pProperties) {
        super(pProperties
                .sound(SoundType.WOOD)
                .strength(3.0f)
        );
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getItemInHand(hand).is(Registration.HANDYMAN_WRENCH.get())) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else if (!level.isClientSide) {
            openContainer(level, pos, player);
        }
        return ItemInteractionResult.CONSUME;
    }

    protected void openContainer(Level level, @NotNull BlockPos pos, @NotNull Player player) {
    }

    @Override
    public void attack(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player) {
        VapourWare.debugLog(player.getMainHandItem().toString());
        if (player.getMainHandItem().is(Registration.HANDYMAN_WRENCH.get())) {
            disassemble(state, level, pos);
            VapourWare.debugLog("in block disassembly call from attack field");
        }
        VapourWare.debugLog("in block disassembly call from attack field, not with wrench");
    }

    public boolean sneakWrenchMachine(Player player, Level level, BlockPos pos) {
        return false;
    }

    public void disassemble(BlockState state, @NotNull Level level, @NotNull BlockPos blockPos) {
        dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), getProtectedItemStack(level, blockPos, state));
        onRemove(state, level, blockPos, Blocks.AIR.defaultBlockState(), false);
        level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
        VapourWare.debugLog("in th end of the disassembly");

    }

    @SuppressWarnings("deprecation")
    protected ItemStack getProtectedItemStack(@NotNull Level world, @NotNull BlockPos blockPos, BlockState state) {
        ItemStack stack = getCloneItemStack(world, blockPos, state).copy();
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        stack = putAdditionalInfo(stack, blockEntity);
        return stack;
    }

    protected ItemStack putAdditionalInfo(ItemStack stack, BlockEntity blockEntity) {
        return stack;
    }

    protected static void dropContents(Level world, BlockPos blockPos, IItemHandler handler) {
        for (int i = 0; i < handler.getSlots(); ++i) {
            dropItemStack(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), handler.getStackInSlot(i));
        }

    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

}
