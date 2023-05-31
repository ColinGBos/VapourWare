package vapourdrive.vapourware.shared.base.itemhandlers;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import vapourdrive.vapourware.shared.base.AbstractBaseFuelUserTile;

import javax.annotation.Nonnull;

public class IngredientHandler extends ItemStackHandler {
    final AbstractBaseFuelUserTile tile;

    public IngredientHandler(AbstractBaseFuelUserTile tile, int size) {
        this.tile = tile;
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    @Override
    protected void onContentsChanged(int slot) {
        // To make sure the TE persists when the chunk is saved later we need to
        // mark it dirty every time the item handler changes
        tile.setChanged();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        Level level = tile.getLevel();
        assert level != null;
        return false;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return super.extractItem(slot, amount, simulate);
    }
}
