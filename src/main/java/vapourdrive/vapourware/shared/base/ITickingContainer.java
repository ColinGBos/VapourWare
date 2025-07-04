package vapourdrive.vapourware.shared.base;

import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;
import vapourdrive.vapourware.shared.utils.MachineUtils;

public interface ITickingContainer {
    void tickServer(BlockState state);

    void removeFromSlot(MachineUtils.Area area, int index, int amount, boolean simulate);

    ItemStack getStackInSlot(MachineUtils.Area area, int index);

    ItemStack insertToSlot(MachineUtils.Area area, int index, ItemStack stack, boolean simulate);

    int[] getOutputSlots();

    ContainerData getContainerData();

    IItemHandler getItemHandler(@Nullable Direction side);
}
