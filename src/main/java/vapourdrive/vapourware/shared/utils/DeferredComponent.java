package vapourdrive.vapourware.shared.utils;

import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import vapourdrive.vapourware.VapourWare;

public class DeferredComponent {
    private final String modID;
    private final String tail;
    private final ForgeConfigSpec.ConfigValue<?> configValue;
    private final Object arg;

    public DeferredComponent(String modIDIn, String tailIn, ForgeConfigSpec.ConfigValue<?> configValueIn) {
        modID = modIDIn;
        tail = tailIn;
        configValue = configValueIn;
        arg = null;
    }

    public DeferredComponent(String modIDIn, String tailIn, Object argIn) {
        modID = modIDIn;
        tail = tailIn;
        configValue = null;
        arg = argIn;
    }

    public DeferredComponent(String tailIn, ForgeConfigSpec.ConfigValue<?> configValueIn) {
        modID = VapourWare.MODID;
        tail = tailIn;
        configValue = configValueIn;
        arg = null;
    }

    public DeferredComponent(String modIDIn, String tailIn) {
        modID = modIDIn;
        tail = tailIn;
        configValue = null;
        arg = null;
    }


    public DeferredComponent(String tailIn) {
        modID = VapourWare.MODID;
        tail = tailIn;
        configValue = null;
        arg = null;
    }

    public MutableComponent get() {
        if (configValue != null) {
            return CompUtils.getArgComp(modID, tail, configValue.get());
        } else if (arg != null) {
            return CompUtils.getArgComp(modID, tail, arg);
        } else {
            return CompUtils.getComp(modID, tail);
        }
    }

    public String getMod() {
        return modID;
    }

    public String getTail() {
        return tail;
    }
}
