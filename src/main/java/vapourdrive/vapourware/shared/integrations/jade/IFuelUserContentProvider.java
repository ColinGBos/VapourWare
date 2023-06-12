package vapourdrive.vapourware.shared.integrations.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vapourdrive.vapourware.shared.base.IFuelUser;
import vapourdrive.vapourware.shared.utils.CompUtils;

import java.text.DecimalFormat;

public enum IFuelUserContentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    private final DecimalFormat df = new DecimalFormat("#,###");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig pluginConfig) {
        if (blockAccessor.getServerData().contains("Fuel")) {
            int i = blockAccessor.getServerData().getInt("Fuel");
            tooltip.add(CompUtils.getArgComp("fuel", df.format(i)).withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return JadePlugin.FUEL;
    }

//    @Override
//    public void appendServerData(CompoundTag data, ServerPlayer player, Level world, BlockEntity t, boolean showDetails) {
//        if (t instanceof IFuelUser user) {
//            data.putInt("Fuel", user.getCurrentFuel() / 100);
//        }
//    }


    @Override
    public void appendServerData(CompoundTag tag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlockEntity() instanceof IFuelUser user) {
            tag.putInt("Fuel", user.getCurrentFuel() / 100);
        }
    }
}
