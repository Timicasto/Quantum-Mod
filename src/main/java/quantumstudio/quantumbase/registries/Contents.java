package quantumstudio.quantumbase.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ObjectHolder;
import quantumstudio.quantumbase.Constants;

@ObjectHolder(Constants.MODID)
public class Contents {
	public static final Block ORE = Blocks.AIR;
	public static final Block PETROLEUM = Blocks.AIR;

	public static final Item PYRITE = Items.AIR;
	public static final Item CHALCOPYRITE = Items.AIR;
	public static final Item GALENA = Items.AIR;
	public static final Item SPHALERITE = Items.AIR;
	public static final Item REALGAR = Items.AIR;
	public static final Item CARNALLITE = Items.AIR;
	public static final Item MAGNETITE = Items.AIR;
	public static final Item CALCITE = Items.AIR;
	public static final Item MALACHITE = Items.AIR;
	public static final Item PLASTER = Items.AIR;
	public static final Item BARITE = Items.AIR;
	public static final Item APATITE = Items.AIR;
	public static final Item MICA = Items.AIR;
	public static final Item FELDSPAR = Items.AIR;
	public static final Item HORNBLENDE = Items.AIR;
	public static final Item PYROXENE = Items.AIR;
	public static final Item OLIVINE = Items.AIR;
	public static final Item ANTIMONITE = Items.AIR;
	public static final Item CINNABAR = Items.AIR;
	public static final Item CASSITERITE = Items.AIR;
	public static final Item CORUNDUM = Items.AIR;
	public static final Item QUMOITE = Items.AIR;
	public static final Item PETROLEUM_BUCKET = Items.AIR;

	public static ResourceLocation PETROLEUM_STILL_TEXTURE;
	public static ResourceLocation PETROLEUM_FLOWING_TEXTURE;

	public static final FlowingFluid PETROLEUM_STILL = Fluids.WATER;
	public static final FlowingFluid PETROLEUM_FLOWING = Fluids.WATER;
	public static ForgeFlowingFluid.Properties PETROLEUM_PROP;
}
