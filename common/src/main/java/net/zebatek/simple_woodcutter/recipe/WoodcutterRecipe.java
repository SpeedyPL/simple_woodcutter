package net.zebatek.simple_woodcutter.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

public class WoodcutterRecipe extends SingleItemRecipe {
    private final Ingredient inputIngredient;
    private final ItemStack outputResult;
    public static final SingleItemRecipe.Factory<WoodcutterRecipe> FACTORY = WoodcutterRecipe::new;

    public WoodcutterRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.getTYPE(), ModRecipes.getSERIALIZER(), group, ingredient, result);
        this.inputIngredient = ingredient;
        this.outputResult = result;
    }

    public Ingredient getInputIngredient() {
        return this.inputIngredient;
    }

    public ItemStack getOutputResult() {
        return this.outputResult;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.inputIngredient.test(container.getItem(0));
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.WOODCUTTER_ITEM);
    }

    public static class Serializer implements RecipeSerializer<WoodcutterRecipe> {

        private static final MapCodec<WoodcutterRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(WoodcutterRecipe::getGroup),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(WoodcutterRecipe::getInputIngredient),
                ItemStack.CODEC.fieldOf("result").forGetter(WoodcutterRecipe::getOutputResult)
        ).apply(instance, WoodcutterRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipe> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, WoodcutterRecipe::getGroup,
                Ingredient.CONTENTS_STREAM_CODEC, WoodcutterRecipe::getInputIngredient,
                ItemStack.STREAM_CODEC, WoodcutterRecipe::getOutputResult,
                WoodcutterRecipe::new
        );

        @Override
        public @NotNull MapCodec<WoodcutterRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, WoodcutterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}