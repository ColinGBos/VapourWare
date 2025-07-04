package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

public class AbstractMachineSlot extends SlotItemHandler {
    public final DeferredComponent comp;

    public AbstractMachineSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, DeferredComponent slotComp) {
        super(itemHandler, index, xPosition, yPosition);
        this.comp = slotComp;
    }

    public MutableComponent getComp() {
        return this.comp.get();
    }

    @Override
    public void set(@NotNull ItemStack stack) {
        ((IItemHandlerModifiable)this.getItemHandler()).setStackInSlot(this.index, stack);
        this.setChanged();
    }
}
