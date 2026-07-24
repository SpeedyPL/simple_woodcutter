package net.zebatek.simple_woodcutter.menu;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipeView;

public class WoodcutterScreen extends AbstractContainerScreen<WoodcutterMenu> {
    private static final Identifier SCROLLER_SPRITE = Identifier.fromNamespaceAndPath("minecraft", "container/stonecutter/scroller");
    private static final Identifier SCROLLER_DISABLED_SPRITE = Identifier.fromNamespaceAndPath("minecraft", "container/stonecutter/scroller_disabled");
    private static final Identifier RECIPE_SELECTED_SPRITE = Identifier.fromNamespaceAndPath("minecraft","container/stonecutter/recipe_selected");
    private static final Identifier RECIPE_HIGHLIGHTED_SPRITE = Identifier.fromNamespaceAndPath("minecraft","container/stonecutter/recipe_highlighted");
    private static final Identifier RECIPE_SPRITE = Identifier.fromNamespaceAndPath("minecraft","container/stonecutter/recipe");
    private static final Identifier BG_LOCATION = Identifier.fromNamespaceAndPath("minecraft","textures/gui/container/stonecutter.png");
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public WoodcutterScreen(WoodcutterMenu woodcutterMenu, Inventory inventory, Component component) {
        super(woodcutterMenu, inventory, component);
        woodcutterMenu.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    @Override
    public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        int k = this.leftPos;
        int l = this.topPos;

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BG_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

        int m = (int)(41.0F * this.scrollOffs);
        Identifier scrollerType = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, scrollerType, k + 119, l + 15 + m, 12, 15);

        int n = this.leftPos + 52;
        int o = this.topPos + 14;
        int p = this.startIndex + 12;

        this.renderButtons(guiGraphics, mouseX, mouseY, n, o, p);
        this.renderRecipes(guiGraphics, n, o, p);

        super.extractContents(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
        super.extractTooltip(guiGraphics, mouseX, mouseY);
        if (this.displayRecipes) {
            int k = this.leftPos + 52;
            int l = this.topPos + 14;
            int m = this.startIndex + 12;
            List<WoodcutterRecipeView> list = this.menu.getRecipes();

            for(int n = this.startIndex; n < m && n < this.menu.getNumRecipes(); ++n) {
                int o = n - this.startIndex;
                int p = k + o % 4 * 16;
                int q = l + o / 4 * 18 + 2;
                if (mouseX >= p && mouseX < p + 16 && mouseY >= q && mouseY < q + 18) {
                    guiGraphics.setTooltipForNextFrame(this.font, list.get(n).result(), mouseX, mouseY);
                }
            }
        }
    }

    private void renderButtons(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int k, int l, int m) {
        for(int n = this.startIndex; n < m && n < this.menu.getNumRecipes(); ++n) {
            int o = n - this.startIndex;
            int p = k + o % 4 * 16;
            int q = o / 4;
            int r = l + q * 18 + 2;

            Identifier buttonSprite;
            if (n == this.menu.getSelectedRecipeIndex()) {
                buttonSprite = RECIPE_SELECTED_SPRITE;
            } else if (mouseX >= p && mouseY >= r && mouseX < p + 16 && mouseY < r + 18) {
                buttonSprite = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                buttonSprite = RECIPE_SPRITE;
            }

            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, buttonSprite, p, r - 1, 16, 18);
        }
    }

    private void renderRecipes(GuiGraphicsExtractor guiGraphics, int x, int y, int lastIndex) {
        List<WoodcutterRecipeView> list = this.menu.getRecipes();
        for(int l = this.startIndex; l < lastIndex && l < this.menu.getNumRecipes(); ++l) {
            int m = l - this.startIndex;
            int n = x + m % 4 * 16;
            int o = m / 4;
            int p = y + o * 18 + 2;
            guiGraphics.item(list.get(l).result(), n, p);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean doubleClick) {
        this.scrolling = false;
        double d = mouseButtonEvent.x();
        double e = mouseButtonEvent.y();
        if (this.displayRecipes) {
            int j = this.leftPos + 52;
            int k = this.topPos + 14;
            int l = this.startIndex + 12;

            for(int m = this.startIndex; m < l; ++m) {
                int n = m - this.startIndex;
                double f = d - (double)(j + n % 4 * 16);
                double g = e - (double)(k + n / 4 * 18);
                if (f >= 0.0D && g >= 0.0D && f < 16.0D && g < 18.0D && this.menu.clickMenuButton(this.minecraft.player, m)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, m);
                    return true;
                }
            }

            j = this.leftPos + 119;
            k = this.topPos + 9;
            if (d >= (double)j && d < (double)(j + 12) && e >= (double)k && e < (double)(k + 54)) {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(mouseButtonEvent, doubleClick);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent mouseButtonEvent, double dragX, double dragY) {
        double e = mouseButtonEvent.y();
        if (this.scrolling && this.isScrollBarActive()) {
            int j = this.topPos + 14;
            int k = j + 54;
            this.scrollOffs = ((float)e - (float)j - 7.5F) / ((float)(k - j) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5D) * 4;
            return true;
        }
        return super.mouseDragged(mouseButtonEvent, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.isScrollBarActive()) {
            int i = this.getOffscreenRows();
            float f = (float)scrollY / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5D) * 4;
        }
        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
    }

    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
}