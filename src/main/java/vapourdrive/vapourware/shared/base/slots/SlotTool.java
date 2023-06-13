package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

public class SlotTool extends BaseSlotIngredient {
    public SlotTool(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, new DeferredComponent("toolslot"));
    }

    @Override
    protected boolean isValidIngredient(ItemStack stack) {
        return stack.getMaxStackSize() == 1;
    }
}
