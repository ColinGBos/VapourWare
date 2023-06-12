package vapourdrive.vapourware.setup;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.common.Mod;
import vapourdrive.vapourware.VapourWare;

@Mod.EventBusSubscriber(modid = VapourWare.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

//    public static final CreativeModeTab VAPOUR_GROUP = new CreativeModeTab("vapourware") {
//        @Override
//        public @NotNull ItemStack makeIcon() {
//            return new ItemStack(Registration.FARMER_WRENCH.get());
//        }
//    };

//    public static final CreativeModeTab VAPOUR_GROUP = CreativeModeTab.builder().title(Component.translatable("itemGroup.spawnEggs")).icon(() -> {
//        return new ItemStack(Registration.FARMER_WRENCH.get());
//    }).build();

    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Add to ingredients tab
        if (event.getTabKey() == Registration.VAPOUR_GROUP.getKey()) {
            event.accept(Registration.FARMER_WRENCH);
        }
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(Registration.FARMER_WRENCH);
        }
    }

}
