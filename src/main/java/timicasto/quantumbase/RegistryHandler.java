package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import org.apache.logging.log4j.LogManager;
import timicasto.quantumbase.block.BlockModSaplings;
import timicasto.quantumbase.block.IModBlock;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.item.IModItem;
import timicasto.quantumbase.utils.ReflectionUtil;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class RegistryHandler {
    private static final HashMap<String, IModBlock<? extends Block>> blockRegisterMap = new HashMap<>();
    private static final HashMap<String, IModItem<? extends Item>> itemRegisterMap = new HashMap<>();

    {
        //special constructor need manual register
        IModBlock<? extends Block> b0 = new BlockModSaplings(0);
        blockRegisterMap.put(b0.name(), b0);

        IModBlock<? extends Block> b1 = new BlockModSaplings(0);
        blockRegisterMap.put(b1.name(), b1);
    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        /*
        event.getRegistry().register(ModItems.willowWood);
        event.getRegistry().register(ModItems.willowLeaves);
        event.getRegistry().register(ModItems.willowSapling);
        event.getRegistry().register(ModItems.poplarLeaves);
        event.getRegistry().register(ModItems.poplarSapling);
        event.getRegistry().register(ModItems.poplarWood);
        event.getRegistry().register(ModItems.combustibleIce);
         */

        ReflectionUtil.listPkgClasses("timicasto.quantumbase.block").forEach((v) -> {
            IModBlock<? extends Block> block = ReflectionUtil.initBlockClass(v);
            if(block != null) {
                event.getRegistry().register(block.getBlock());
                blockRegisterMap.put(block.name(), block);
            }
        });

    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        /*
        event.getRegistry().register(ModItems.willowWoodItemBlock.setRegistryName("willow_wood"));
        event.getRegistry().register(ModItems.willowLeavesItemBlock.setRegistryName("willow_leaves"));
        event.getRegistry().register(ModItems.willowSaplingItemBlock.setRegistryName("willow_sapling"));
        event.getRegistry().register(ModItems.poplarLeavesItemBlock.setRegistryName("poplar_leaves"));
        event.getRegistry().register(ModItems.poplarSaplingItemBlock.setRegistryName("poplar_sapling"));
        event.getRegistry().register(ModItems.poplarWoodItemBlock.setRegistryName("poplar_wood"));
        event.getRegistry().register(ModItems.combustibleIceItemBlock.setRegistryName("combustible_ice"));
         */
        blockRegisterMap.forEach((k, v) -> event.getRegistry().register(v.toItemBlock().setRegistryName(k)));
        ReflectionUtil.listPkgClasses("timicasto.quantumbase.block").forEach((v) -> {
            IModItem<? extends Item> item = ReflectionUtil.initItemClass(v);
            if(item != null) {
                event.getRegistry().register(item.getItem());
                itemRegisterMap.put(item.name(), item);
            }
        });
    }

    @SubscribeEvent
    public static void registerItemModel(ModelRegistryEvent event){
        /*
        ModelLoader.setCustomModelResourceLocation(ModItems.willowWoodItemBlock,0,new ModelResourceLocation(ModItems.willowWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowLeavesItemBlock,0,new ModelResourceLocation(ModItems.willowLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowSaplingItemBlock,0,new ModelResourceLocation(ModItems.willowSaplingItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarLeavesItemBlock,0,new ModelResourceLocation(ModItems.poplarLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarSaplingItemBlock,0,new ModelResourceLocation(ModItems.poplarSaplingItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarWoodItemBlock,0,new ModelResourceLocation(ModItems.poplarWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.combustibleIceItemBlock,0,new ModelResourceLocation(ModItems.combustibleIceItemBlock.getRegistryName(),"inventory"));
        */
        blockRegisterMap.forEach((k, v) -> ModelLoader.setCustomModelResourceLocation(v.toItemBlock(), 0, new ModelResourceLocation(v.getBlock().getRegistryName(), "inventory")));
    }


    @SubscribeEvent
    public static void onEntityRegistation(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(PrimedIce.class)
                .id(new ResourceLocation(QuantumBase.MODID, "PrimedIce"), 233)
                .name("PrimedIce")
                .tracker(80, 3, false)
                .build()
        );
    }

    public static IModBlock<? extends Block> getBlock(String registerName) {
        return blockRegisterMap.get(registerName);
    }

    public static IModItem<? extends Item> getItem(String registerName) {
        return itemRegisterMap.get(registerName);
    }

    public static ItemBlock getItemBlock(String registerName) {
        return blockRegisterMap.containsKey(registerName) ? blockRegisterMap.get(registerName).toItemBlock() : itemRegisterMap.get(registerName).getItemBlock();
    }
}
