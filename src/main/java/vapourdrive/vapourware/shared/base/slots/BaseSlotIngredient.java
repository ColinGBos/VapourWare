package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import javax.annotation.Nonnull;

public class BaseSlotIngredient extends AbstractMachineSlot {
    protected final IItemHandler itemHandler;
    private final int index;

    public BaseSlotIngredient(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, new DeferredComponent("ingredientslot"));
        this.itemHandler = itemHandler;
        this.index = index;
    }

    public BaseSlotIngredient(IItemHandler itemHandler, int index, int xPosition, int yPosition, DeferredComponent comp) {
        super(itemHandler, index, xPosition, yPosition, comp);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        if (stack.isEmpty() || !this.isValidIngredient(stack)) {
            return false;
        }
        return itemHandler.isItemValid(index, stack);
    }

    protected boolean isValidIngredient(ItemStack stack) {
        return false;
    }
}
