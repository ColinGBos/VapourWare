package vapourdrive.vapourware.shared.base;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.setup.Registration;

import java.util.List;

import static net.minecraft.world.Containers.dropItemStack;

public abstract class AbstractBaseContainerBlock extends BaseEntityBlock {

    protected AbstractBaseContainerBlock(Properties pProperties) {
        super(pProperties);
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
            SoundEvent sound = level.getBlockState(pos).getSoundType(level,pos,player).getPlaceSound();
            disassemble(state, level, pos);
            level.playSound(player, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.5f,1f-(level.getRandom().nextFloat()*0.3f));
            level.playSound(player, pos, sound, SoundSource.BLOCKS, 0.8f,1f+(level.getRandom().nextFloat()*0.3f));
            VapourWare.debugLog("in block disassembly call from attack field");
        }
        VapourWare.debugLog("in block disassembly call from attack field, not with wrench");

    }

    public boolean sneakWrenchMachine(Player player, Level level, BlockPos pos) {
        return false;
    }

    @Override
    protected @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder); // Get default drops
        drops.clear();
        BlockEntity blockEntity = builder.getParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof ITickingContainer be) {
            ItemStack self = putAdditionalInfo(new ItemStack(this), blockEntity);
            drops.add(self);
            for (int i = 0; i< be.getItemHandler(null).getSlots(); i++){
                drops.add(be.getItemHandler(null).getStackInSlot(i));
            }
        }

        return drops;
    }

    public void disassemble(BlockState state, @NotNull Level level, @NotNull BlockPos blockPos) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof ITickingContainer be) {
            ItemStack self = putAdditionalInfo(new ItemStack(this), blockEntity);
            dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), self);
            for (int i = 0; i< be.getItemHandler(null).getSlots(); i++){
                dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), be.getItemHandler(null).getStackInSlot(i));
            }
        }
        onRemove(state, level, blockPos, Blocks.AIR.defaultBlockState(), false);
        level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
        VapourWare.debugLog("in th end of the disassembly");

    }

//    protected ItemStack getProtectedItemStack(@NotNull Level world, @NotNull BlockPos blockPos, BlockState state) {
//        ItemStack stack = getCloneItemStack(world, blockPos, state).copy();
//        BlockEntity blockEntity = world.getBlockEntity(blockPos);
//        stack = putAdditionalInfo(stack, blockEntity);
//        return stack;
//    }

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
