package timicasto.quantumbase.utils;

import com.google.common.collect.Maps;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.register.QuantumBaseBlocks;

import java.util.Map;

public class MetalSmelterRecipe {
    private static final MetalSmelterRecipe instance = new MetalSmelterRecipe();
    private final Map<ItemStack, ItemStack> recipeList = Maps.newHashMap();
    private final Map<ItemStack, Integer> smeltingTemp = Maps.newHashMap();
    private final Map<ItemStack, Boolean> isSourceNeedReduce = Maps.newHashMap();
    private final Map<ItemStack, ItemStack> reduceResult = Maps.newHashMap();
    private final Map<ItemStack, Integer> smeltTime = Maps.newHashMap();
    private final Map<ItemStack, Integer> smeltTimeDelta = Maps.newHashMap();

    public static MetalSmelterRecipe instance() {
        return instance;
    }

    private MetalSmelterRecipe() {
        this.createRecipe(new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE)), new ItemStack(Items.IRON_INGOT), 12800, 1538, false, ItemStack.EMPTY, 6400);
        this.createRecipe(new ItemStack(Item.getItemFromBlock(Blocks.GOLD_ORE)), new ItemStack(Items.GOLD_INGOT), 19200, 1064, false, ItemStack.EMPTY, 9600);
        this.createRecipe(new ItemStack(QuantumBaseBlocks.hematiteItemBlock), new ItemStack(QuantumBaseBlocks.hematiteItemBlock), 15360, 1565, true, new ItemStack(Items.IRON_INGOT), 7680);
        this.createRecipe(new ItemStack(QuantumBaseBlocks.magnetiteItemBlock), new ItemStack(QuantumBaseBlocks.magnetiteItemBlock), 16000, 1594, true, new ItemStack(Items.IRON_INGOT), 8000);
    }

    public void createRecipe(ItemStack input, ItemStack output, int smeltingTimeBase, int smeltingTemp, boolean isNeedReduce, ItemStack reduceResult, int smeltTime) {
        this.recipeList.put(input, output);
        this.smeltingTemp.put(input, smeltingTemp);
        this.isSourceNeedReduce.put(input, isNeedReduce);
        if (isNeedReduce) {
            this.reduceResult.put(output, reduceResult);
        }
        this.smeltTime.put(input, smeltingTimeBase);
        this.smeltTimeDelta.put(input, smeltTime);
    }

    public ItemStack getSmeltingResult(ItemStack output) {
        for (Map.Entry<ItemStack, ItemStack> entry : this.recipeList.entrySet()) {
            if (output.getItem() == entry.getKey().getItem()) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean isSourceNeedReduce(ItemStack input) {
        for (Map.Entry<ItemStack, Boolean> entry : this.isSourceNeedReduce.entrySet()) {
            if (input.getItem() == entry.getKey().getItem()) {
                return entry.getValue();
            }
        }
        return false;
    }

    public ItemStack getReduceResult(ItemStack output) {
        for (Map.Entry<ItemStack, ItemStack> entry : this.reduceResult.entrySet()) {
            if (output.getItem() == entry.getKey().getItem()) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    public int getField(String type, ItemStack source) {
        if (type.equals("temp")) {
            for (Map.Entry<ItemStack, Integer> entry : this.smeltingTemp.entrySet()) {
                if (source.getItem() == entry.getKey().getItem()) {
                    return entry.getValue();
                }
            }
        }

        if (type.equals("smeltTime")) {
            for (Map.Entry<ItemStack, Integer> entry : this.smeltTime.entrySet()) {
                if (source.getItem() == entry.getKey().getItem()) {
                    return entry.getValue();
                }
            }
        }

        if (type.equals("smeltTimeDelta")) {
            for (Map.Entry<ItemStack, Integer> entry : this.smeltTimeDelta.entrySet()) {
                if (source.getItem() == entry.getKey().getItem()) {
                    return entry.getValue();
                }
            }
        }
        return 2147483647;
    }
}
