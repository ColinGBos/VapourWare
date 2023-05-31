package vapourdrive.vapourware.api.base.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import vapourdrive.vapourware.VapourWare;

import javax.annotation.Nonnull;

public class SlotOutput extends AbstractMachineSlot {

    public SlotOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, VapourWare.MODID+".outputslot");
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        return VapourWare.isDebugMode();
    }
}