package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import timicasto.quantumbase.entity.PrimedIce;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModItems.willowWood);
        event.getRegistry().register(ModItems.willowLeaves);
        event.getRegistry().register(ModItems.willowSapling);
        event.getRegistry().register(ModItems.poplarLeaves);
        event.getRegistry().register(ModItems.poplarSapling);
        event.getRegistry().register(ModItems.poplarWood);
        event.getRegistry().register(ModItems.combustibleIce);
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ModItems.willowWoodItemBlock.setRegistryName("willow_wood"));
        event.getRegistry().register(ModItems.willowLeavesItemBlock.setRegistryName("willow_leaves"));
        event.getRegistry().register(ModItems.willowSaplingItemBlock.setRegistryName("willow_sapling"));
        event.getRegistry().register(ModItems.poplarLeavesItemBlock.setRegistryName("poplar_leaves"));
        event.getRegistry().register(ModItems.poplarSaplingItemBlock.setRegistryName("poplar_sapling"));
        event.getRegistry().register(ModItems.poplarWoodItemBlock.setRegistryName("poplar_wood"));
        event.getRegistry().register(ModItems.combustibleIceItemBlock.setRegistryName("combustible_ice"));
    }

    @SubscribeEvent
    public static void registerItemModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(ModItems.willowWoodItemBlock,0,new ModelResourceLocation(ModItems.willowWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowLeavesItemBlock,0,new ModelResourceLocation(ModItems.willowLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowSaplingItemBlock,0,new ModelResourceLocation(ModItems.willowSaplingItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarLeavesItemBlock,0,new ModelResourceLocation(ModItems.poplarLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarSaplingItemBlock,0,new ModelResourceLocation(ModItems.poplarSaplingItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarWoodItemBlock,0,new ModelResourceLocation(ModItems.poplarWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.combustibleIceItemBlock,0,new ModelResourceLocation(ModItems.combustibleIceItemBlock.getRegistryName(),"inventory"));
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
}
