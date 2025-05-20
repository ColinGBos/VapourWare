package vapourdrive.vapourware.shared.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class InvUtils {
    public static NonNullList<ItemStack> getIngredientsFromInvHandler(IItemHandler handler){
        NonNullList<ItemStack> stacks = NonNullList.withSize(handler.getSlots(), ItemStack.EMPTY);
        for(int i =0; i < stacks.size(); i++){
            stacks.set(i, handler.getStackInSlot(i));
        }
        return stacks;
    }

    public static int getEmptyOutputSlotCount(IItemHandler inv) {
        int empty = 0;
        for (int i = 0; i<inv.getSlots(); i++) {
            if(inv.getStackInSlot(i).isEmpty()){
                empty++;
            }
        }
        return empty;
    }

    public static boolean pushStack(ItemStack stack, boolean simulate, IItemHandler inv) {
        //iterates through non-empty slots
        ItemStack procStack = stack.copy();
        for (int i = 0; i<inv.getSlots(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) {
                ItemStack changedStack = inv.insertItem(i, procStack, simulate);
                if (changedStack == ItemStack.EMPTY) {
                    return true;
                }
                if(changedStack.getCount() != procStack.getCount()){
                    procStack = changedStack.copy();
                }
            }
        }

        //iterate through the slots (empty or not)
        for (int i = 0; i<inv.getSlots(); i++) {
            if (inv.getStackInSlot(i).isEmpty()) {
                if (inv.insertItem(i, procStack, simulate) == ItemStack.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }
}
