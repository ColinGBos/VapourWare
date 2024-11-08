package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import java.util.List;

public class BaseInfoItemBlock extends BlockItem {

    protected final DeferredComponent component;

    public BaseInfoItemBlock(Block pBlock, Properties pProperties, DeferredComponent inComp) {
        super(pBlock, pProperties);
        component = inComp;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(component.get().withStyle(ChatFormatting.GRAY));
    }
}

