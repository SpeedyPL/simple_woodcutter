package net.zebatek.simple_woodcutter.menu;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;

public class WoodcutterScreen extends AbstractContainerScreen<WoodcutterMenu> {
    private static final ResourceLocation SCROLLER_SPRITE = new ResourceLocation("container/stonecutter/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = new ResourceLocation("container/stonecutter/scroller_disabled");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = new ResourceLocation("container/stonecutter/recipe_selected");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = new ResourceLocation("container/stonecutter/recipe_highlighted");
    private static final ResourceLocation RECIPE_SPRITE = new ResourceLocation("container/stonecutter/recipe");
    private static final ResourceLocation BG_LOCATION = new ResourceLocation("textures/gui/container/stonecutter.png");
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int k = this.leftPos;
        int l = this.topPos;

        guiGraphics.blit(BG_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);

        int m = (int)(41.0F * this.scrollOffs);
        ResourceLocation scrollerType = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        guiGraphics.blitSprite(scrollerType, k + 119, l + 15 + m, 12, 15);

        int n = this.leftPos + 52;
        int o = this.topPos + 14;
        int p = this.startIndex + 12;

        this.renderButtons(guiGraphics, mouseX, mouseY, n, o, p);
        this.renderRecipes(guiGraphics, n, o, p);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);
        if (this.displayRecipes) {
            int k = this.leftPos + 52;
            int l = this.topPos + 14;
            int m = this.startIndex + 12;
            List<RecipeHolder<WoodcutterRecipe>> list = this.menu.getRecipes();

            for(int n = this.startIndex; n < m && n < this.menu.getNumRecipes(); ++n) {
                int o = n - this.startIndex;
                int p = k + o % 4 * 16;
                int q = l + o / 4 * 18 + 2;
                if (mouseX >= p && mouseX < p + 16 && mouseY >= q && mouseY < q + 18) {
                    guiGraphics.renderTooltip(this.font, list.get(n).value().getResultItem(this.minecraft.level.registryAccess()), mouseX, mouseY);
                }
            }
        }
    }

    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int k, int l, int m) {
        for(int n = this.startIndex; n < m && n < this.menu.getNumRecipes(); ++n) {
            int o = n - this.startIndex;
            int p = k + o % 4 * 16;
            int q = o / 4;
            int r = l + q * 18 + 2;

            ResourceLocation buttonSprite;
            if (n == this.menu.getSelectedRecipeIndex()) {
                buttonSprite = RECIPE_SELECTED_SPRITE;
            } else if (mouseX >= p && mouseY >= r && mouseX < p + 16 && mouseY < r + 18) {
                buttonSprite = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                buttonSprite = RECIPE_SPRITE;
            }

            guiGraphics.blitSprite(buttonSprite, p, r - 1, 16, 18);
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int x, int y, int lastIndex) {
        List<RecipeHolder<WoodcutterRecipe>> list = this.menu.getRecipes();
        for(int l = this.startIndex; l < lastIndex && l < this.menu.getNumRecipes(); ++l) {
            int m = l - this.startIndex;
            int n = x + m % 4 * 16;
            int o = m / 4;
            int p = y + o * 18 + 2;
            guiGraphics.renderItem(list.get(l).value().getResultItem(this.minecraft.level.registryAccess()), n, p);
        }
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        this.scrolling = false;
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
        return super.mouseClicked(d, e, i);
    }

    @Override
    public boolean mouseDragged(double d, double e, int i, double f, double g) {
        if (this.scrolling && this.isScrollBarActive()) {
            int j = this.topPos + 14;
            int k = j + 54;
            this.scrollOffs = ((float)e - (float)j - 7.5F) / ((float)(k - j) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5D) * 4;
            return true;
        }
        return super.mouseDragged(d, e, i, f, g);
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