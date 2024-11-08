package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import java.util.List;

public class BaseInfoItem extends Item {

    protected final DeferredComponent component;

    public BaseInfoItem(Properties pProperties, DeferredComponent inComp) {
        super(pProperties);
        component = inComp;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(component.get().withStyle(ChatFormatting.GRAY));
    }
}

