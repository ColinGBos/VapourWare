package vapourdrive.vapourware.setup;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vapourdrive.vapourware.content.HandymanWrench;

import static vapourdrive.vapourware.VapourWare.MODID;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            MODID);

    public static final RegistryObject<HandymanWrench> FARMER_WRENCH = ITEMS.register("wrench",
            () -> new HandymanWrench(new Item.Properties()));

    public static final RegistryObject<CreativeModeTab> VAPOUR_GROUP = TABS.register(MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.vapourware"))
                    .icon(() -> new ItemStack(Registration.FARMER_WRENCH.get()))
                    .build());


    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
        TABS.register(eventBus);
    }

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
