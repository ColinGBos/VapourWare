package vapourdrive.vapourware.setup;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vapourdrive.vapourware.content.HandymanWrench;

import java.util.function.Supplier;

import static vapourdrive.vapourware.VapourWare.MODID;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);
    public static final Supplier<Item> HANDYMAN_WRENCH = ITEMS.register("wrench", () -> new HandymanWrench(new Item.Properties()));

    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            MODID);
    public static final Supplier<CreativeModeTab> VAPOUR_GROUP = TABS.register(MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.vapourware"))
                    .icon(() -> new ItemStack(Registration.HANDYMAN_WRENCH.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Registration.HANDYMAN_WRENCH.get());
                    })
                    .build());

    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> FUEL = REGISTRAR.registerComponentType(
            "fuel",
            builder -> builder
                    .persistent(ExtraCodecs.NON_NEGATIVE_INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
    );


    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
        TABS.register(eventBus);
        REGISTRAR.register(eventBus);
    }

    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(Registration.HANDYMAN_WRENCH.get());
        }
    }

}
