package vapourdrive.vapourware.api.integrations.jade;

import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.api.base.AbstractBaseFuelUserTile;
import vapourdrive.vapourware.api.base.AbstractBaseMachineBlock;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    public static final ResourceLocation FUEL = new ResourceLocation(VapourWare.MODID+".fuel");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(IFuelUserContentProvider.INSTANCE, AbstractBaseFuelUserTile.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(IFuelUserContentProvider.INSTANCE, AbstractBaseMachineBlock.class);
    }

}
