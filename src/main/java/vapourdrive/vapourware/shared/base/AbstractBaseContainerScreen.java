package vapourdrive.vapourware.shared.base;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.fml.ModList;
import org.jetbrains.annotations.NotNull;
import vapourdrive.vapourware.shared.utils.DeferredComponent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AbstractBaseContainerScreen<T extends AbstractBaseContainerMenu> extends AbstractContainerScreen<T> {
    protected final AbstractBaseContainerMenu containerMenu;
    protected final ResourceLocation GUI;
    protected int INFO_XPOS = 158;
    protected int INFO_YPOS = 6;
    protected int INFO_ICONX = 184;
    protected int INFO_ICONY = 0;
    protected boolean HAS_RECIPES = true;
    final int INFO_HEIGHT = 12;
    final int INFO_WIDTH = 12;
    protected final DeferredComponent comp;
    protected final DecimalFormat df = new DecimalFormat("#,###");

    public AbstractBaseContainerScreen(T menu, Inventory inv, Component name, DeferredComponent compIn) {
        super(menu, inv, name);
        this.containerMenu = menu;
        this.titleLabelY = -10;
        this.comp = compIn;
//        this.GUI = new ResourceLocation(compIn.getMod(), "textures/gui/" + compIn.getTail() + "_gui.png");
        this.GUI = ResourceLocation.fromNamespaceAndPath(compIn.getMod(), "textures/gui/" + compIn.getTail() + "_gui.png");
    }

    public AbstractBaseContainerScreen(T menu, Inventory inv, Component name, DeferredComponent compIn, int helpX, int helpY, int titleX) {
        super(menu, inv, name);
        this.containerMenu = menu;
        this.titleLabelX = titleX;
        this.titleLabelY = -10;
        this.INFO_XPOS = helpX;
        this.INFO_YPOS = helpY;
        this.comp = compIn;
        this.GUI = ResourceLocation.fromNamespaceAndPath(compIn.getMod(), "textures/gui/" + compIn.getTail() + "_gui.png");
    }

    public AbstractBaseContainerScreen(T menu, Inventory inv, Component name, DeferredComponent compIn, int helpX, int helpY, int helpSpriteX, int helpSpriteY, int titleX, boolean hasRecipes) {
        super(menu, inv, name);
        this.containerMenu = menu;
        this.titleLabelX = titleX;
        this.titleLabelY = -10;
        this.INFO_XPOS = helpX;
        this.INFO_YPOS = helpY;
        this.INFO_ICONX = helpSpriteX;
        this.INFO_ICONY = helpSpriteY;
        this.comp = compIn;
        this.HAS_RECIPES = hasRecipes;
        this.GUI = ResourceLocation.fromNamespaceAndPath(compIn.getMod(), "textures/gui/" + compIn.getTail() + "_gui.png");
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
        return pMouseX < (double) pGuiLeft || pMouseY < (double) pGuiTop || pMouseX >= (double) (pGuiLeft + getXSize()) || pMouseY >= (double) (pGuiTop + getYSize());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics, mouseX, mouseY, partialTicks);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 16777215);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - getXSize()) / 2;
        int relY = (this.height - getYSize()) / 2;

        int guiLeft = this.leftPos;
        int guiTop = this.topPos;

        graphics.blit(this.GUI, relX, relY, 0, 0, getXSize(), getYSize());

        graphics.blit(this.GUI, guiLeft + INFO_XPOS, guiTop + INFO_YPOS, INFO_ICONX, INFO_ICONY + INFO_HEIGHT, INFO_WIDTH, INFO_HEIGHT);
        if (ModList.get().isLoaded("jei") && HAS_RECIPES) {
            blitAlt(graphics, INFO_XPOS, INFO_YPOS + 15, INFO_ICONX + INFO_WIDTH, INFO_ICONY, INFO_WIDTH, INFO_HEIGHT, mouseX, mouseY);
        }
    }

    @Override
    protected void renderTooltip(@NotNull GuiGraphics graphics, int mouseX, int mouseY) {
        boolean notCarrying = this.menu.getCarried().isEmpty();

        List<Component> hoveringText = new ArrayList<>();

        if (notCarrying && isInRect(this.leftPos + INFO_XPOS - 1, this.topPos + INFO_YPOS - 1, INFO_WIDTH + 2, INFO_HEIGHT + 2, mouseX, mouseY)) {
            getAdditionalInfoHover(hoveringText);
        }

        // If hoveringText is not empty draw the hovering text.  Otherwise, use vanilla to render tooltip for the slots
        if (!hoveringText.isEmpty()) {
            graphics.renderComponentTooltip(this.font, hoveringText, mouseX, mouseY);
        } else {
            super.renderTooltip(graphics, mouseX, mouseY);
        }
    }

    protected void getAdditionalInfoHover(List<Component> hoveringText) {
        hoveringText.add(comp.get());
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    public void blitAlt(@NotNull GuiGraphics graphics, int offsetX, int offsetY, int iconX, int iconY, int width, int height, int mouseX, int mouseY) {
        if (isInRect(this.leftPos + offsetX, this.topPos + offsetY, width, height, mouseX, mouseY)) {
            graphics.blit(this.GUI, this.leftPos + offsetX, this.topPos + offsetY, iconX, iconY + height, width, height);
        } else {
            graphics.blit(this.GUI, this.leftPos + offsetX, this.topPos + offsetY, iconX, iconY, width, height);
        }
    }

}
