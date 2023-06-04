package vapourdrive.vapourware.shared.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.base.slots.AbstractMachineSlot;
import vapourdrive.vapourware.shared.utils.CompUtils;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AbstractBaseMachineScreen<T extends AbstractBaseMachineContainer> extends AbstractContainerScreen<T> {
    protected final AbstractBaseMachineContainer container;
    private final ResourceLocation GUI;
    protected int FUEL_XPOS = 12;
    protected int FUEL_YPOS;
    protected int FUEL_ICONX = 176;   // texture position of flame icon [u,v]
    protected int FUEL_ICONY = 0;
    protected int FUEL_HEIGHT = 46;
    final int FUEL_WIDTH = 8;
    protected int INFO_XPOS = 158;
    protected int INFO_YPOS = 6;
    final int INFO_ICONX = 184;   // texture position of flame icon [u,v]
    final int INFO_ICONY = 0;
    final int INFO_HEIGHT = 12;
    final int INFO_WIDTH = 12;
    protected final DeferredComponent comp;
    protected final boolean STACK_INFO_SIDEWAYS;
    protected final DecimalFormat df = new DecimalFormat("#,###");

    public AbstractBaseMachineScreen(T container, Inventory inv, Component name, DeferredComponent compIn, boolean stackInfoSideways) {
        super(container, inv, name);
        this.container = container;
        this.titleLabelY = -10;
        this.STACK_INFO_SIDEWAYS = false;
        this.comp = compIn;
        this.GUI = new ResourceLocation(compIn.getMod(), "textures/gui/" + compIn.getTail() + "_gui.png");
    }

    public AbstractBaseMachineScreen(T container, Inventory inv, Component name, DeferredComponent compIn,  int fuelX, int fuelY, int fuelH, int helpX, int helpY, int titleX, boolean stackInfoSideways) {
        super(container, inv, name);
        this.container = container;
        this.titleLabelX = titleX;
        this.titleLabelY = -10;
        this.FUEL_XPOS = fuelX;
        this.FUEL_YPOS = fuelY;
        this.INFO_XPOS = helpX;
        this.INFO_YPOS = helpY;
        this.STACK_INFO_SIDEWAYS = stackInfoSideways;
        this.comp = compIn;
        this.GUI = new ResourceLocation(compIn.getMod(), "textures/gui/" + compIn.getTail() + "_gui.png");
        this.FUEL_HEIGHT = fuelH;
    }

    @Override
    public int getYSize() {
        return this.imageHeight;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - getXSize()) / 2;
        this.topPos = (this.height - getYSize()) / 2;
    }

    @Override
    protected boolean hasClickedOutside(double pMouseX, double pMouseY, int pGuiLeft, int pGuiTop, int pMouseButton) {
        return pMouseX < (double)pGuiLeft || pMouseY < (double)pGuiTop || pMouseX >= (double)(pGuiLeft + getXSize()) || pMouseY >= (double)(pGuiTop + getYSize());
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack matrixStack, int mouseX, int mouseY) {
        this.font.draw(matrixStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, 16777215);
    }

    @Override
    protected void renderBg(@NotNull PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - getXSize()) / 2;
        int relY = (this.height - getYSize()) / 2;

        int guiLeft = this.leftPos;
        int guiTop = this.topPos;

        this.blit(matrixStack, relX, relY, 0, 0, getXSize(), getYSize());

        int m = (int) (container.getFuelPercentage() * (FUEL_HEIGHT));

        this.blit(matrixStack, guiLeft + FUEL_XPOS, guiTop + FUEL_YPOS + FUEL_HEIGHT - m, FUEL_ICONX, FUEL_ICONY + FUEL_HEIGHT - m, FUEL_WIDTH, m);
        this.blit(matrixStack, guiLeft + INFO_XPOS, guiTop + INFO_YPOS, INFO_ICONX, INFO_ICONY + INFO_HEIGHT, INFO_WIDTH, INFO_HEIGHT);
        if(ModList.get().isLoaded("jei")) {
            if (STACK_INFO_SIDEWAYS) {
                blitAlt(matrixStack, INFO_XPOS - 15, INFO_YPOS, INFO_ICONX + INFO_WIDTH, INFO_ICONY, INFO_WIDTH, INFO_HEIGHT, mouseX, mouseY);
            } else {
                blitAlt(matrixStack, INFO_XPOS, INFO_YPOS + 15, INFO_ICONX + INFO_WIDTH, INFO_ICONY, INFO_WIDTH, INFO_HEIGHT, mouseX, mouseY);
            }
        }
    }

    @Override
    protected void renderTooltip(@NotNull PoseStack matrixStack, int mouseX, int mouseY) {
        boolean notCarrying = this.menu.getCarried().isEmpty();

        List<Component> hoveringText = new ArrayList<>();

        if (this.hoveredSlot != null && !this.hoveredSlot.hasItem() && this.hoveredSlot instanceof AbstractMachineSlot machineSlot) {
            MutableComponent comp = machineSlot.getComp();
            if (comp != null) {
                hoveringText.add(comp.withStyle(ChatFormatting.GREEN));
            }
        }

        // If the mouse is over the experience bar, add hovering text
        if (notCarrying && isInRect(this.leftPos + FUEL_XPOS, this.topPos + FUEL_YPOS, FUEL_WIDTH, FUEL_HEIGHT, mouseX, mouseY)) {
            int fuel = container.getFuelStored() / 100;
            String strFuel = df.format(fuel) + "/" + df.format(container.getMaxFuel() / 100);
//            hoveringText.add(Component.literal("Fuel: ").append(df.format(fuel) + "/" + df.format(container.getMaxFuel() / 100)));
            hoveringText.add(CompUtils.getArgComp("fuel", strFuel));
        }

        if (notCarrying && isInRect(this.leftPos + INFO_XPOS - 1, this.topPos + INFO_YPOS - 1, INFO_WIDTH + 2, INFO_HEIGHT + 2, mouseX, mouseY)) {

            getAdditionalInfoHover(hoveringText);

        }

        // If hoveringText is not empty draw the hovering text.  Otherwise, use vanilla to render tooltip for the slots
        if (!hoveringText.isEmpty()) {
            renderComponentTooltip(matrixStack, hoveringText, mouseX, mouseY);
        } else {
            super.renderTooltip(matrixStack, mouseX, mouseY);
        }
    }

    protected void getAdditionalInfoHover(List<Component> hoveringText) {
        hoveringText.add(comp.get());
        hoveringText.add(CompUtils.getComp("fuel_excess.info"));
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    public void blitAlt(@NotNull PoseStack matrixStack, int offsetX, int offsetY, int iconX, int iconY, int width, int height, int mouseX, int mouseY) {
        if (isInRect(this.leftPos + offsetX, this.topPos + offsetY, width, height, mouseX, mouseY)) {
            this.blit(matrixStack, this.leftPos + offsetX, this.topPos + offsetY, iconX, iconY + height, width, height);
        } else {
            this.blit(matrixStack, this.leftPos + offsetX, this.topPos + offsetY, iconX, iconY, width, height);
        }
    }

}
