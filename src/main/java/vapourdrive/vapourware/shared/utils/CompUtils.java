package vapourdrive.vapourware.shared.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.VapourWare;

import java.util.List;

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

    public static void addShiftInfo(@NotNull List<Component> tooltipComponents){
        tooltipComponents.add(CompUtils.getComp("shift_info").withStyle(ChatFormatting.DARK_GRAY));
    }

}
