package quantumstudio.quantumbase.registries;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quantumstudio.quantumbase.Constants;
import quantumstudio.quantumbase.blocks.OreBlock;
import quantumstudio.quantumbase.items.OreDeposits;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    public static final CreativeModeTab TAB = new CreativeModeTab(Constants.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.ANVIL);
        }
    };

    @SubscribeEvent
    public static void registerBlocks(@Nonnull RegistryEvent.Register<Block> e) {
        e.getRegistry().registerAll(
                new OreBlock().setRegistryName("ore")
        );
    }

    @SubscribeEvent
    public static void registerItems(@Nonnull RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(
                new OreDeposits.Pyrite().setRegistryName("pyrite"),
                new OreDeposits.Chalcopyrite().setRegistryName("chalcopyrite"),
                new OreDeposits.Galena().setRegistryName("galena"),
                new OreDeposits.Sphalerite().setRegistryName("sphalerite"),
                new OreDeposits.Realgar().setRegistryName("realgar"),
                new OreDeposits.Calcite().setRegistryName("calcite"),
                new OreDeposits.Malachite().setRegistryName("malachite"),
                new OreDeposits.Plaster().setRegistryName("plaster"),
                new OreDeposits.Barite().setRegistryName("barite"),
                new OreDeposits.Apatite().setRegistryName("apatite"),
                new OreDeposits.Mica().setRegistryName("mica"),
                new OreDeposits.Feldspar().setRegistryName("feldspar"),
                new OreDeposits.Hornblende().setRegistryName("hornblende"),
                new OreDeposits.Pyroxene().setRegistryName("pyroxene"),
                new OreDeposits.Olivine().setRegistryName("olivine"),
                new OreDeposits.Antimonite().setRegistryName("antimonite"),
                new OreDeposits.Cinnabar().setRegistryName("cinnabar"),
                new OreDeposits.Cassiterite().setRegistryName("cassiterite"),
                new OreDeposits.Corundum().setRegistryName("corundum"),
                new OreDeposits.Qumoite().setRegistryName("qumoite")
        );
    }

}
