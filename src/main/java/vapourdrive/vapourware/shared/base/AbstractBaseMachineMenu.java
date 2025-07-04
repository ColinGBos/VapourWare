package vapourdrive.vapourware.shared.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public abstract class AbstractBaseMachineMenu extends AbstractBaseContainerMenu {

    protected final AbstractBaseFuelUserTile baseFuelUserTile;
    protected final ContainerData machineData;

    public AbstractBaseMachineMenu(int windowId, Level world, BlockPos pos, Inventory inv, Player player, @Nullable MenuType<?> menu, ContainerData machineData) {
        super(windowId, world, pos, inv, player, menu);
        baseFuelUserTile = (AbstractBaseFuelUserTile) world.getBlockEntity(pos);
        this.machineData = machineData;
    }

    //Full disclosure, I don't really know how tf to do bit 'stuff' but it seems to work
//    protected void addSplitDataSlots(ContainerData data) {
//        for (int i = 0; i < data.getCount(); ++i) {
//            int index = i;
//            addDataSlot(new DataSlot() {
//                @Override
//                public int get() {
//                    return data.get(index) & 0xffff;
//                }
//
//                @Override
//                public void set(int value) {
//                    int stored = data.get(index) & 0xffff0000;
//                    data.set(index, stored + (value & 0xffff));
//                }
//            });
//            addDataSlot(new DataSlot() {
//                @Override
//                public int get() {
//                    return (data.get(index) >> 16) & 0xffff;
//                }
//
//                @Override
//                public void set(int value) {
//                    int stored = data.get(index) & 0x0000ffff;
//                    data.set(index, stored | value << 16);
//                }
//            });
//        }
//    }

    @OnlyIn(Dist.CLIENT)
    public float getFuelPercentage() {
        int i = this.machineData.get(0);
        if (i == 0) {
            return 0;
        }
        return (float) i / (float) baseFuelUserTile.getMaxFuel();
    }

    @OnlyIn(Dist.CLIENT)
    public float getMaxFuel() {
        return baseFuelUserTile.getMaxFuel();
    }

    @OnlyIn(Dist.CLIENT)
    public int getFuelStored() {
        return this.machineData.get(0);
    }

}
