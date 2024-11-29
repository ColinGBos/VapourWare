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
}
