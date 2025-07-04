package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

public class SpecificItemSlot extends BaseSlotIngredient{
    final Item item;
    public SpecificItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Item item) {
        super(itemHandler, index, xPosition, yPosition, new DeferredComponent(VapourWare.MODID,"itemslot", item.getName(new ItemStack(item))));
        this.item = item;
    }

    protected boolean isValidIngredient(ItemStack stack) {
        return stack.is(item);
    }
}
