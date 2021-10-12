package quantumstudio.quantumbase.registries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import quantumstudio.quantumbase.Constants;
import quantumstudio.quantumbase.blocks.OreBlock;
import quantumstudio.quantumbase.gui.BlastFurnaceScreen;
import quantumstudio.quantumbase.gui.containers.BlastFurnaceMenu;
import quantumstudio.quantumbase.items.OreDeposits;
import quantumstudio.quantumbase.multiblock.api.MultiBlockMachineInstance;
import quantumstudio.quantumbase.tile.BlastFurnaceTileEntity;

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
                new OreBlock().setRegistryName("ore"),
                new LiquidBlock(() -> Contents.PETROLEUM_STILL, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100F).noDrops()).setRegistryName("petroleum"),
                new MultiBlockMachineInstance().setRegistryName("machine")
        );
    }

    @SubscribeEvent
    public static void registerFluids(@Nonnull RegistryEvent.Register<Fluid> e) {
        Contents.PETROLEUM_STILL_TEXTURE = new ResourceLocation("block/petroleum_still");
        Contents.PETROLEUM_FLOWING_TEXTURE = new ResourceLocation("block/petroleum_flowing");
        Contents.PETROLEUM_PROP = new ForgeFlowingFluid.Properties(() -> Contents.PETROLEUM_STILL, () -> Contents.PETROLEUM_FLOWING, FluidAttributes.builder(Contents.PETROLEUM_STILL_TEXTURE, Contents.PETROLEUM_FLOWING_TEXTURE).color(0xFF0D0D0E).density(4000).viscosity(4000)).bucket(() -> Contents.PETROLEUM_BUCKET).block(() -> (LiquidBlock) Contents.PETROLEUM).slopeFindDistance(3).explosionResistance(100F);
        e.getRegistry().registerAll(
                new ForgeFlowingFluid.Source(Contents.PETROLEUM_PROP).setRegistryName("petroleum_still"),
                new ForgeFlowingFluid.Flowing(Contents.PETROLEUM_PROP).setRegistryName("petroleum_flowing")
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
                new OreDeposits.Qumoite().setRegistryName("qumoite"),
                new BucketItem(() -> Contents.PETROLEUM_STILL, new Item.Properties().tab(TAB).craftRemainder(Items.BUCKET)).setRegistryName("petroleum_bucket")
        );
    }

    @SubscribeEvent
    public static void registerTileEntities(@Nonnull RegistryEvent.Register<BlockEntityType<?>> e) {
        e.getRegistry().registerAll(
                BlockEntityType.Builder.of(BlastFurnaceTileEntity::new, Contents.MACHINE).build(null).setRegistryName("blast_furnace")
        );
    }

    @SubscribeEvent
    public static void registerMenus(@Nonnull RegistryEvent.Register<MenuType<?>> e) {
        e.getRegistry().registerAll(
                IForgeContainerType.create(BlastFurnaceMenu::new).setRegistryName("blast_furnace_menu")
        );
    }

    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Client {
        @SubscribeEvent
        public static void setup(FMLClientSetupEvent e) {
            MenuScreens.register(Contents.BLAST_FURNACE_MENU, BlastFurnaceScreen::new);
        }
    }
}
