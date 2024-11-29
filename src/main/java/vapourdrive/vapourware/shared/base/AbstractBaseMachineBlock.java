package vapourdrive.vapourware.shared.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.setup.Registration;

import java.util.function.ToIntFunction;

import static net.minecraft.world.Containers.dropItemStack;

public abstract class AbstractBaseMachineBlock extends AbstractBaseContainerBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private final float offset;

    protected AbstractBaseMachineBlock(Properties pProperties, float offset) {
        super(pProperties
                .sound(SoundType.STONE)
                .strength(10.0f)
                .requiresCorrectToolForDrops()
                .lightLevel(litBlockEmission())
        );
        this.offset = offset;
    }

    private static ToIntFunction<BlockState> litBlockEmission() {
        return isLit -> isLit.getValue(BlockStateProperties.LIT) ? 13 : 0;
    }

//    @Override
//    @SuppressWarnings("deprecation")
//    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult trace) {
//        if (player.getItemInHand(hand).is(HandymanWrench.wrench)) {
//            return InteractionResult.PASS;
//        } else if (!level.isClientSide) {
//            openContainer(level, pos, player);
//        }
//        return InteractionResult.CONSUME;
//    }


    @SuppressWarnings("deprecation")
    @Override
    protected ItemStack getProtectedItemStack(@NotNull Level world, @NotNull BlockPos blockPos, BlockState state) {
        ItemStack stack = getCloneItemStack(world, blockPos, state).copy();
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof IFuelUser machine) {
            VapourWare.debugLog("within the protected thing");
            stack.set(Registration.FUEL, machine.getCurrentFuel());
        }
        stack = putAdditionalInfo(stack, blockEntity);
        return stack;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (pState.getValue(LIT) && pRandom.nextFloat() > 0.7f) {
            double d0 = (double) pPos.getX() + 0.5D;
            double d1 = pPos.getY();
            double d2 = (double) pPos.getZ() + 0.5D;
            if (pRandom.nextFloat() > 0.9D) {
                pLevel.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = pState.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d4 = pRandom.nextDouble() * 0.4D - 0.2D;
            double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
            double d6 = pRandom.nextDouble() * 6.0D / 16.0D + offset;
            double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
            pLevel.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            pLevel.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(LIT, false);
    }

    @Override
    public @NotNull BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT);
    }
}
