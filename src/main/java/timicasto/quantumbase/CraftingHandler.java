package timicasto.quantumbase;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CraftingHandler {
    Map<ItemStack, ItemStack> smeltingMap = new HashMap<>();
    private final Logger logger = LogManager.getLogger();

    public CraftingHandler() {
    }

    public void removeSmeltingRecipe() {
        doRemoveSmeltingRecipe(new ItemStack(Items.IRON_INGOT));
        doRemoveSmeltingRecipe(new ItemStack(Items.GOLD_INGOT));
        doRemoveSmeltingRecipe(new ItemStack(Blocks.GLASS));
        doRemoveSmeltingRecipe(new ItemStack(Items.BRICK));
        doRemoveSmeltingRecipe(new ItemStack(Items.DIAMOND));
        doRemoveSmeltingRecipe(new ItemStack(Items.REDSTONE));
        doRemoveSmeltingRecipe(new ItemStack(Items.EMERALD));
        doRemoveSmeltingRecipe(new ItemStack(Items.QUARTZ));
        doRemoveSmeltingRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()));
        doRemoveSmeltingRecipe(new ItemStack(Items.COAL));
        doRemoveSmeltingRecipe(new ItemStack(Items.IRON_NUGGET));
        doRemoveSmeltingRecipe(new ItemStack(Items.GOLD_NUGGET));
        doRemoveSmeltingRecipe(new ItemStack(Blocks.STONE));
    }

    public void doRemoveSmeltingRecipe(ItemStack result) {
        ItemStack recipeResult = null;
        Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
        Iterator<ItemStack> iterator = recipes.keySet().iterator();
        while (iterator.hasNext()) {
            ItemStack temp = iterator.next();
            recipeResult = recipes.get(temp);
            if (ItemStack.areItemStacksEqual(result, recipeResult)) {
                iterator.remove();
                logger.info("Removed Recipe : " + temp + " -> " + recipeResult);
            }
        }
    }
}
















