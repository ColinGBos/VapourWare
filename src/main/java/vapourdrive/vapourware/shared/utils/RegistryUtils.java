package vapourdrive.vapourware.shared.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class RegistryUtils {
    public static boolean modItemExists(String namespace, String tail) {
        return BuiltInRegistries.ITEM.containsKey(ResourceLocation.fromNamespaceAndPath(namespace, tail));
    }

    public static Item getModItem(String namespace, String tail) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(namespace, tail));
//        return BuiltInRegistries.ITEM.containsKey(ResourceLocation.fromNamespaceAndPath(namespace, tail));
    }

    public static boolean itemTagIsNotEmpty(String namespace, String tail) {
        TagKey<Item> tag = getItemTag(namespace, tail);
        for (Item item : BuiltInRegistries.ITEM) {
            ItemStack stack = new ItemStack(item);
            if (stack.is(tag)) {
                return true;
            }
        }
        return false;
    }

    public static Ingredient getIngredientFromTag(String namespace, String tail){
        return Ingredient.of(getItemTag(namespace,tail));
    }

    public static TagKey<Item> getItemTag(String namespace, String tail){
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace,tail));
    }

    public static TagKey<Block> getBlockTag(String namespace, String tail) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, tail));
    }
}
