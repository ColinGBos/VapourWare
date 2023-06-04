package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

public class SlotFuel extends BaseSlotIngredient {


    public SlotFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, new DeferredComponent("fuelslot"));
    }

    @Override
    protected boolean isValidIngredient(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
    }
}
