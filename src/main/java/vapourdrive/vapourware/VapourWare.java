package vapourdrive.vapourware;


import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vapourdrive.vapourware.config.ConfigSettings;
import vapourdrive.vapourware.setup.Registration;

import java.util.ArrayList;
import java.util.Objects;

@Mod(VapourWare.MODID)
public class VapourWare {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "vapourware";
    public static final boolean debugMode = true;
    public static final ArrayList<ItemLike> seeds = new ArrayList<>();

    public VapourWare(ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, ConfigSettings.SERVER_CONFIG);
        Registration.init(container.getEventBus());
        Objects.requireNonNull(container.getEventBus()).addListener(Registration::buildContents);
    }

    public static void debugLog(String toLog) {
        if (isDebugMode()) {
            LOGGER.log(Level.DEBUG, toLog);
        }
    }

    public static boolean isDebugMode() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp") && debugMode;
    }

}
