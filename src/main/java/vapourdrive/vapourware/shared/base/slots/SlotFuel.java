package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import vapourdrive.vapourware.VapourWare;

import javax.annotation.Nonnull;

public class SlotFuel extends AbstractMachineSlot {
    private final IItemHandler itemHandler;
    private final int index;

    public SlotFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, VapourWare.MODID+".fuelslot");
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        if (stack.isEmpty() || ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) == 0)
            return false;
        return itemHandler.isItemValid(index, stack);
    }
}
