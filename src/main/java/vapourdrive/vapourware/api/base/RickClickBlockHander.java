package vapourdrive.vapourware.api.base;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vapourdrive.vapourware.setup.Registration;

@Mod.EventBusSubscriber
public class RickClickBlockHander {

    @SubscribeEvent
    public static void rightClickBlockEvent(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().is(Registration.FARMER_WRENCH.get())) {
            event.setUseBlock(Event.Result.DENY);
        }
    }
}
