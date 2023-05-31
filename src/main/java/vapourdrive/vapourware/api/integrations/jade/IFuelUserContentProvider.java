package vapourdrive.vapourware.api.integrations.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vapourdrive.vapourware.VapourWare;
import vapourdrive.vapourware.api.base.IFuelUser;

import java.text.DecimalFormat;

public enum IFuelUserContentProvider implements IBlockComponentProvider, IServerDataProvider<BlockEntity> {
    INSTANCE;
    private final DecimalFormat df = new DecimalFormat("#,###");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig pluginConfig) {
        if (blockAccessor.getServerData().contains("Fuel")) {
            int i = blockAccessor.getServerData().getInt("Fuel");
            tooltip.add(Component.translatable(VapourWare.MODID+".fuel", df.format(i)).withStyle(ChatFormatting.GOLD));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return JadePlugin.FUEL;
    }

    @Override
    public void appendServerData(CompoundTag data, ServerPlayer player, Level world, BlockEntity t, boolean showDetails) {
        if (t instanceof IFuelUser user) {
            data.putInt("Fuel", user.getCurrentFuel() / 100);
        }
    }
}
