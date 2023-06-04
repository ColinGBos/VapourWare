package vapourdrive.vapourware.content;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.base.AbstractBaseMachineBlock;
import vapourdrive.vapourware.shared.base.BaseInfoItem;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import java.util.Objects;

public class HandymanWrench extends BaseInfoItem {
    public static final TagKey<Item> wrench = ItemTags.create(new ResourceLocation("forge", "tools/wrench"));

    public HandymanWrench(Item.Properties pProperties) {
        super(pProperties.stacksTo(1), new DeferredComponent("wrench.info"));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext ctx) {
        BlockPos pos = ctx.getClickedPos();
        BlockState state = ctx.getLevel().getBlockState(pos);

        if (Objects.requireNonNull(ctx.getPlayer()).isCrouching()) {
            if (state.getBlock() instanceof AbstractBaseMachineBlock machine) {
                if (machine.sneakWrenchMachine(ctx.getPlayer(), ctx.getLevel(), pos)) {
                    return InteractionResult.SUCCESS;
                }
            }
        }

        ctx.getLevel().setBlockAndUpdate(pos, state.rotate(ctx.getLevel(), pos, Rotation.CLOCKWISE_90));
        return InteractionResult.SUCCESS;
    }
}
