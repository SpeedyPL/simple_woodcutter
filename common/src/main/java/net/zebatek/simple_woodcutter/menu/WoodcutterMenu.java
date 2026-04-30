package net.zebatek.simple_woodcutter.menu;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import net.zebatek.simple_woodcutter.recipe.ModRecipes;
import net.zebatek.simple_woodcutter.recipe.WoodcutterRecipe;
import net.zebatek.simple_woodcutter.registry.ModMenuTypes;

import java.util.ArrayList;
import java.util.List;

public class WoodcutterMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            WoodcutterMenu.this.slotsChanged(this);
            WoodcutterMenu.this.slotUpdateListener.run();
        }
    };
    private final ResultContainer resultContainer = new ResultContainer();
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<WoodcutterRecipe> recipes = new ArrayList<>();
    private ItemStack inputStack = ItemStack.EMPTY;
    long lastSoundTime;

    Runnable slotUpdateListener = () -> {};

    public WoodcutterMenu(int id, Inventory playerInv) {
        this(id, playerInv, ContainerLevelAccess.NULL);
    }

    public WoodcutterMenu(int id, Inventory playerInv, ContainerLevelAccess access) {
        super(ModMenuTypes.WOODCUTTER_MENU.get(), id);
        this.access = access;
        this.level = playerInv.player.level();

        this.addSlot(new Slot(this.container, 0, 20, 33));
        this.addDataSlot(selectedRecipeIndex);

        this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) { return false; }

            @Override
            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player.level(), player, stack.getCount());
                ItemStack inputStack = WoodcutterMenu.this.container.removeItem(0, 1);

                if (!inputStack.isEmpty()) {
                    WoodcutterMenu.this.setupResultSlot();
                }

                access.execute((level, blockPos) -> {
                    long time = level.getGameTime();
                    if (WoodcutterMenu.this.lastSoundTime != time) {
                        level.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        WoodcutterMenu.this.lastSoundTime = time;
                    }
                });

                super.onTake(player, stack);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    public void registerUpdateListener(Runnable runnable) {
        this.slotUpdateListener = runnable;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.WOODCUTTER);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index == 1) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.canTake(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 2 && index < 29) {
                    if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 29 && index < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    private boolean canTake(ItemStack stack) {
        return true;
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack itemstack = this.container.getItem(0);
        if (!itemstack.is(this.inputStack.getItem())) {
            this.inputStack = itemstack.copy();
            this.setupRecipeList(container, itemstack);
        }
    }

    private void setupRecipeList(Container container, ItemStack stack) {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultContainer.setItem(1, ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(ModRecipes.WOODCUTTER_TYPE.get(), container, this.level);
        }
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (id >= 0 && id < this.recipes.size()) {
            this.selectedRecipeIndex.set(id);
            this.setupResultSlot();
            return true;
        }
        return false;
    }

    private void setupResultSlot() {
        if (!this.recipes.isEmpty() && this.selectedRecipeIndex.get() != -1) {
            WoodcutterRecipe recipe = this.recipes.get(this.selectedRecipeIndex.get());
            this.resultContainer.setItem(1, recipe.assemble(this.container, this.level.registryAccess()));
        } else {
            this.resultContainer.setItem(1, ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        this.resultContainer.removeItemNoUpdate(1);

        this.access.execute((level, blockpos) -> {
            this.clearContainer(player, this.container);
        });
    }

    public int getSelectedRecipeIndex() { return this.selectedRecipeIndex.get(); }
    public List<WoodcutterRecipe> getRecipes() { return this.recipes; }
    public int getNumRecipes() { return this.recipes.size(); }
    public boolean hasInputItem() { return !this.container.getItem(0).isEmpty() && !this.recipes.isEmpty(); }
}