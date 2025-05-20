package vapourdrive.vapourware.shared.utils;

import net.minecraft.world.item.ItemStack;

public class ItemStackUtils {

    public static String getModAndName(ItemStack stack){
        String ret = stack.getDescriptionId().replaceFirst("item.", "");
        ret = ret.replaceFirst("block.", "");
        return ret;
    }
}
