package vapourdrive.vapourware.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.VapourWare;

@Mod.EventBusSubscriber(modid = VapourWare.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final CreativeModeTab VAPOUR_GROUP = new CreativeModeTab("vapourware") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Registration.FARMER_WRENCH.get());
        }
    };

}
