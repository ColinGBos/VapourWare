package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
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
}
