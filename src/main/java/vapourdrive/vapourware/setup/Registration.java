package vapourdrive.vapourware.setup;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vapourdrive.vapourware.content.HandymanWrench;

import static vapourdrive.vapourware.VapourWare.MODID;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<HandymanWrench> FARMER_WRENCH = ITEMS.register("wrench", () -> new HandymanWrench(new Item.Properties().tab(ModSetup.VAPOUR_GROUP)));

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
