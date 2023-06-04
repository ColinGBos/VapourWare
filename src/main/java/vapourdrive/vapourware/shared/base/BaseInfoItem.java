package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import javax.annotation.Nullable;
import java.util.List;

public class BaseInfoItem extends Item {

    protected final DeferredComponent component;

    public BaseInfoItem(Properties pProperties, DeferredComponent inComp) {
        super(pProperties);
        component = inComp;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(component.get().withStyle(ChatFormatting.GRAY));
    }
}

