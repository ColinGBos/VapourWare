package vapourdrive.vapourware.shared.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import javax.annotation.Nullable;
import java.util.List;

public class BaseInfoItemBlock extends BlockItem {

    protected final DeferredComponent component;

    public BaseInfoItemBlock(Block pBlock, Properties pProperties, DeferredComponent inComp) {
        super(pBlock, pProperties);
        component = inComp;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        list.add(component.get().withStyle(ChatFormatting.GRAY));
    }
}

