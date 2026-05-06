package net.zebatek.simple_woodcutter.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.zebatek.simple_woodcutter.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

public class WoodcutterRecipe extends SingleItemRecipe {
    public WoodcutterRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.WOODCUTTER_TYPE.get(), ModRecipes.WOODCUTTER_SERIALIZER.get(), group, ingredient, result);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.WOODCUTTER_ITEM);
    }

    public static class Serializer implements RecipeSerializer<WoodcutterRecipe> {
        private static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                BuiltInRegistries.ITEM.byNameCodec().fieldOf("result").forGetter(ItemStack::getItem),
                Codec.INT.optionalFieldOf("count", 1).forGetter(ItemStack::getCount)
        ).apply(instance, ItemStack::new));

        private final Codec<WoodcutterRecipe> codec = RecordCodecBuilder.create((instance) -> instance.group(
                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter((recipe) -> recipe.group),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter((recipe) -> recipe.ingredient),
                RESULT_CODEC.forGetter((recipe) -> recipe.result)
        ).apply(instance, WoodcutterRecipe::new));

        @Override
        public @NotNull Codec<WoodcutterRecipe> codec() {
            return this.codec;
        }

        @Override
        public @NotNull WoodcutterRecipe fromNetwork(FriendlyByteBuf buf) {
            String group = buf.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            return new WoodcutterRecipe(group, ingredient, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, WoodcutterRecipe recipe) {
            buf.writeUtf(recipe.getGroup());
            recipe.ingredient.toNetwork(buf);
            buf.writeItem(recipe.result);
        }
    }
}
