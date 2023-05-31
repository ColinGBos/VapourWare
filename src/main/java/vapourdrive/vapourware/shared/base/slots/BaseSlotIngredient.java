package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import vapourdrive.vapourware.VapourWare;

import javax.annotation.Nonnull;

public class BaseSlotIngredient extends AbstractMachineSlot {
    protected final IItemHandler itemHandler;
    private final int index;

    public BaseSlotIngredient(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, VapourWare.MODID+".ingredientslot");
        this.itemHandler = itemHandler;
        this.index = index;
    }

    public BaseSlotIngredient(IItemHandler itemHandler, int index, int xPosition, int yPosition, String title) {
        super(itemHandler, index, xPosition, yPosition, title);
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
//        return this.world.getRecipeManager().getRecipeFor(Registration.FERTILIZER_TYPE.get(), new SimpleContainer(stack), this.world).isPresent();
    }
}
