package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.utils.CompUtils;
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
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(component.get().withStyle(ChatFormatting.GRAY));
        } else {
            CompUtils.addShiftInfo(tooltipComponents);
        }
    }
}

