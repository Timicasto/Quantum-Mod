package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import timicasto.quantumbase.block.WillowWood;

public class ModItems {
    private static HashMap<String, Item> itemRegisterMap = new HashMap<>();
    private static HashMap<String, Block> blockRegisterMap = new HashMap<>();

    {
        blockRegisterMap.put("b:willow_wood", new WillowWood());
        itemRegisterMap.put("willow_wood", new ItemBlock(willowWood));
    }

    public static void registerItems(Registry<Item> registry) {
        itemRegisterMap.forEach((k, v) -> {
            registry.register(v.setRegistryName(k));
        });
    }

    public static void registerBlocks(Registry<Block> registry) {
        blockRegisterMap.values().forEach((v) -> {
            registry.register(v);
        });
    }
}
