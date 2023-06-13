package vapourdrive.vapourware.shared.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import vapourdrive.vapourware.VapourWare;

public class CompUtils {
    public static MutableComponent getComp(String tail) {
        return Component.translatable(VapourWare.MODID + "." + tail);
    }

    public static MutableComponent getComp(String modID, String tail) {
        return Component.translatable(modID + "." + tail);
    }

    public static MutableComponent getArgComp(String tail, Object arg) {
        return Component.translatable(VapourWare.MODID + "." + tail, arg);
    }

    public static MutableComponent getArgComp(String modID, String tail, Object arg) {
        return Component.translatable(modID + "." + tail, arg);
    }

}
