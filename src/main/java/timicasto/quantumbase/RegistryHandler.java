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
import timicasto.quantumbase.block.BlockWillowSaplings;
import timicasto.quantumbase.block.IModBlock;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.item.IModItem;
import timicasto.quantumbase.utils.ReflectionUtil;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class RegistryHandler {
    private static final HashMap<String, IModBlock<? extends Block>> blockRegisterMap = new HashMap<>();
    private static final HashMap<String, IModItem<? extends Item>> itemRegisterMap = new HashMap<>();

    static {
        //special constructor need manual register
        IModBlock<? extends Block> b0 = new BlockWillowSaplings(0);
        blockRegisterMap.put(b0.name(), b0);

        IModBlock<? extends Block> b1 = new BlockWillowSaplings(0);
        blockRegisterMap.put(b1.name(), b1);

        blockRegisterMap.keySet().forEach(ReflectionUtil::addIgnoreClass);
    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        blockRegisterMap.forEach((k, v) -> event.getRegistry().register(v.getBlock()));
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
        blockRegisterMap.forEach((k, v) -> event.getRegistry().register(v.toItemBlock()));
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

    public static void putModItem(IModItem<? extends Item> item) {
        itemRegisterMap.put(item.name(), item);
    }
}
