package vapourdrive.vapourware.shared.base.itemhandlers;

import net.minecraft.world.item.ItemStack;
import vapourdrive.vapourware.shared.base.AbstractBaseFuelUserTile;

import javax.annotation.Nonnull;

public class ToolHandler extends IngredientHandler {
    public ToolHandler(AbstractBaseFuelUserTile tile, int size) {
        super(tile, size);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getMaxStackSize() == 1;
    }
}
