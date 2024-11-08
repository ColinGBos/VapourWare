package vapourdrive.vapourware.shared.base;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.setup.Registration;

@EventBusSubscriber(modid = VapourWare.MODID)
public class RickClickBlockHander {

    @SubscribeEvent
    public static void rightClickBlockEvent(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().is(Registration.HANDYMAN_WRENCH.get())) {
//            event.setUseBlock(Event.Result.DENY);
            event.setUseBlock(TriState.FALSE);
        }
    }
}
