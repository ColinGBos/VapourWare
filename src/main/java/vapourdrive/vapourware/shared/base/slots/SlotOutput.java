package vapourdrive.vapourware.shared.base.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import javax.annotation.Nonnull;

public class SlotOutput extends AbstractMachineSlot {

    public SlotOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, new DeferredComponent("outputslot"));
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        return VapourWare.isDebugMode();
    }
}