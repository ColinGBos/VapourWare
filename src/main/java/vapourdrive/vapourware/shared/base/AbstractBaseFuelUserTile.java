package vapourdrive.vapourware.shared.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.utils.MachineUtils;

import java.util.Objects;

public abstract class AbstractBaseFuelUserTile extends BlockEntity implements IFuelUser {

    public final int maxFuel;
    public final int minWorkFuel;

    public int toAdd = 0;
    public int increment = 0;

    private ItemStack currentFuelStack = ItemStack.EMPTY;
    private int currentBurn = 0;

    @SuppressWarnings("unused")
    public final int[] FUEL_SLOT = {0};

    public final int[] OUTPUT_SLOTS;

    public AbstractBaseFuelUserTile(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int maxFuel, int minWorkFuel, int[] OUTPUT_SLOTS) {
        super(pType, pPos, pBlockState);
        this.maxFuel = maxFuel;
        this.minWorkFuel = minWorkFuel;
        this.OUTPUT_SLOTS = OUTPUT_SLOTS;
    }

    public void tickServer(BlockState state) {
        ItemStack fuel = getStackInSlot(MachineUtils.Area.FUEL, 0);
        MachineUtils.doFuelProcess(fuel, this);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        increment = tag.getInt("increment");
        toAdd = tag.getInt("toAdd");
        addFuel(tag.getInt("fuel"), false);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("increment", increment);
        tag.putInt("toAdd", toAdd);
        tag.putInt("fuel", getCurrentFuel());
    }

    @Override
    public boolean canWork(BlockState state) {
        boolean canWork = true;
        if (Objects.requireNonNull(this.getLevel()).hasNeighborSignal(this.worldPosition)) {
            canWork = false;
        } else if (getCurrentFuel() < getMinFuelToWork()) {
            canWork = false;
        }
        changeStateIfNecessary(state, canWork);
        return canWork;
    }

    public void changeStateIfNecessary(BlockState state, Boolean working) {
        assert this.level != null;
        if (state.getValue(BlockStateProperties.LIT) && !working) {
            this.level.setBlockAndUpdate(worldPosition, state.setValue(BlockStateProperties.LIT, false));
        } else if (!state.getValue(BlockStateProperties.LIT) && working) {
            this.level.setBlockAndUpdate(worldPosition, state.setValue(BlockStateProperties.LIT, true));
        }
    }

    @Override
    public int getMaxFuel() {
        return this.maxFuel;
    }

    @Override
    public int getMinFuelToWork() {
        return this.minWorkFuel;
    }

    @Override
    public double getSpeedMultiplier() {
        return 1.0;
    }

    @Override
    public double getEfficiencyMultiplier() {
        return 1.0;
    }

    @Override
    public int getCurrentFuel() {
        return 0;
    }

    @Override
    public int getCurrentBurn() {
        return this.currentBurn;
    }

    @Override
    public void setCurrentBurn(int burn) {
        this.currentBurn = burn;
    }

    @Override
    public int getIncrementalFuelToAdd() {
        return this.increment;
    }

    @Override
    public void setIncrementalFuelToAdd(int increment) {
        this.increment = increment;

    }

    @Override
    public int getFuelToAdd() {
        return this.toAdd;
    }

    @Override
    public void setFuelToAdd(int toAdd) {
        this.toAdd = toAdd;

    }

    @Override
    public ItemStack getCurrentFuelStack() {
        return this.currentFuelStack;
    }

    @Override
    public void setCurrentFuelStack(ItemStack stack) {
        this.currentFuelStack = stack;
    }

    @Override
    public int[] getOutputSlots() {
        return this.OUTPUT_SLOTS;
    }

}
