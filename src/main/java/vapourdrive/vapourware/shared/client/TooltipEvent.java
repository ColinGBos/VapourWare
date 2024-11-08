package vapourdrive.vapourware.shared.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import vapourdrive.vapourware.VapourWare;

import java.util.Arrays;
import java.util.List;

@EventBusSubscriber(modid = VapourWare.MODID)
public class TooltipEvent {
    @SubscribeEvent
    public static void onToolTipEarly(ItemTooltipEvent event) {
        if (VapourWare.isDebugMode()) {
            List<Component> tips = event.getToolTip();
            ItemStack stack = event.getItemStack();
            tips.add(Component.literal(Arrays.toString(stack.getTags().toArray())));
        }
    }
}
