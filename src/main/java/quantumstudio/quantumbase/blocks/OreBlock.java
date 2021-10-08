package quantumstudio.quantumbase.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import quantumstudio.quantumbase.registries.Contents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OreBlock extends Block {
	public static EnumProperty<EnumOres> TYPES = EnumProperty.create("type", EnumOres.class);

	public OreBlock() {
		super(Properties.of(Material.STONE).strength(2.5F));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPES);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public List<ItemStack> getDrops(BlockState p_220076_1_, LootContext.Builder p_220076_2_) {
		switch (p_220076_1_.getValue(TYPES)) {
			case MICA:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.MICA, RANDOM.nextInt(3 + 1))));
			case BARITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.BARITE, RANDOM.nextInt(3 + 1))));
			case GALENA:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.GALENA, RANDOM.nextInt(3 + 1))));
			case PYRITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.PYRITE, RANDOM.nextInt(3 + 1))));
			case APATITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.APATITE, RANDOM.nextInt(3 + 1))));
			case CALCITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.CALCITE, RANDOM.nextInt(3 + 1))));
			case OLIVINE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.OLIVINE, RANDOM.nextInt(3 + 1))));
			case PLASTER:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.PLASTER, RANDOM.nextInt(3 + 1))));
			case QUMOITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.QUMOITE, RANDOM.nextInt(3 + 1))));
			case REALGAR:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.REALGAR, RANDOM.nextInt(3 + 1))));
			case CINNABAR:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.CINNABAR, RANDOM.nextInt(3 + 1))));
			case CORUNDUM:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.CORUNDUM, RANDOM.nextInt(3 + 1))));
			case FELDSPAR:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.FELDSPAR, RANDOM.nextInt(3 + 1))));
			case PYROXENE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.PYROXENE, RANDOM.nextInt(3 + 1))));
			case MAGNETITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.MAGNETITE, RANDOM.nextInt(3 + 1))));
			case MALACHITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.MALACHITE, RANDOM.nextInt(3 + 1))));
			case ANTIMONITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.ANTIMONITE, RANDOM.nextInt(3 + 1))));
			case CARNALLITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.CARNALLITE, RANDOM.nextInt(3 + 1))));
			case HORNBLENDE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.HORNBLENDE, RANDOM.nextInt(3 + 1))));
			case SPHALERITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.SPHALERITE, RANDOM.nextInt(3 + 1))));
			case CASSITERITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.CASSITERITE, RANDOM.nextInt(3 + 1))));
			case CHALCOPYRITE:
				return new ArrayList<>(Collections.singleton(new ItemStack(Contents.CHALCOPYRITE, RANDOM.nextInt(3 + 1))));
			default:
				return new ArrayList<>();
		}
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader world, BlockPos pos, int fortune, int silktouch) {
		return RANDOM.nextInt(5) + 2;
	}
}
