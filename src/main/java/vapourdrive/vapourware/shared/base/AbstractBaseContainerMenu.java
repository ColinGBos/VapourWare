package vapourdrive.vapourware.shared.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractBaseContainerMenu extends AbstractContainerMenu {

    public static final int PLAYER_INVENTORY_XPOS = 8;
    public static final int PLAYER_INVENTORY_YPOS = 84;
    protected final Player playerEntity;
    protected final IItemHandler playerInventory;
    protected final Inventory playerInv;
    protected final Level world;
    protected final BlockEntity tileEntity;

    public AbstractBaseContainerMenu(int windowId, Level world, BlockPos pos, Inventory inv, Player player, @Nullable MenuType<?> menu) {
        super(menu, windowId);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(inv);
        this.playerInv = inv;
        this.world = world;
        tileEntity = world.getBlockEntity(pos);
    }


    @Override
    public boolean stillValid(@NotNull Player playerIn) {
        return true;
    }


    private int addPlayerInvRow(IItemHandler handler, int index, int x, int y) {
        for (int i = 0; i < 9; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += 18;
            index++;
        }
        return index;
    }

    private void addPlayerInv(IItemHandler handler, int index, int x, int y) {
        for (int j = 0; j < 3; j++) {
            index = addPlayerInvRow(handler, index, x, y);
            y += 18;
        }
    }

    protected void layoutPlayerInventorySlots(int leftCol, int topRow) {
        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(this.playerInv, j + k * 9 + 9, leftCol + j * 18, topRow + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(this.playerInv, k, leftCol + k * 18, topRow + 58));
        }
    }

}
