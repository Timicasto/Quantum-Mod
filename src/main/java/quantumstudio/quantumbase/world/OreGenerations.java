package quantumstudio.quantumbase.world;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.ModLoader;
import quantumstudio.quantumbase.Constants;
import quantumstudio.quantumbase.blocks.EnumOres;
import quantumstudio.quantumbase.blocks.OreBlock;
import quantumstudio.quantumbase.registries.Contents;

import javax.annotation.Nonnull;

public class OreGenerations {
	private static ConfiguredFeature<?, ?> PYRITE;
	private static ConfiguredFeature<?, ?> CHALCOPYRITE;
	private static ConfiguredFeature<?, ?> GALENA;
	private static ConfiguredFeature<?, ?> SPHALERITE;
	private static ConfiguredFeature<?, ?> REALGAR;
	private static ConfiguredFeature<?, ?> CARNALLITE;
	private static ConfiguredFeature<?, ?> MAGNETITE;
	private static ConfiguredFeature<?, ?> CALCITE;
	private static ConfiguredFeature<?, ?> MALACHITE;
	private static ConfiguredFeature<?, ?> PLASTER;
	private static ConfiguredFeature<?, ?> BARITE;
	private static ConfiguredFeature<?, ?> APATITE;
	private static ConfiguredFeature<?, ?> MICA;
	private static ConfiguredFeature<?, ?> FELDSPAR;
	private static ConfiguredFeature<?, ?> HORNBLENDE;
	private static ConfiguredFeature<?, ?> PYROXENE;
	private static ConfiguredFeature<?, ?> OLIVINE;
	private static ConfiguredFeature<?, ?> ANTIMONITE;
	private static ConfiguredFeature<?, ?> CINNABAR;
	private static ConfiguredFeature<?, ?> CASSITERITE;
	private static ConfiguredFeature<?, ?> CORUNDUM;
	private static ConfiguredFeature<?, ?> QUMOITE;

	public static void initFeatures() {
		if (ModLoader.isLoadingStateValid()) {
			PYRITE = getOreFeature(Feature.ORE, Contents.ORE.defaultBlockState().setValue(OreBlock.TYPES, EnumOres.PYRITE), "pyrite", new OreFeatureConf(4, 16, 31, 2));
			CHALCOPYRITE = getOreFeature(Feature.ORE, Contents.ORE.defaultBlockState().setValue(OreBlock.TYPES, EnumOres.CHALCOPYRITE), "chalcopyrite", new OreFeatureConf(8, 43, 7, 3));
			/*GALENA = ;
			SPHALERITE = ;
			REALGAR = ;
			CARNALLITE = ;
			MAGNETITE = ;
			CALCITE = ;
			MALACHITE = ;
			PLASTER = ;
			BARITE = ;
			APATITE = ;
			MICA = ;
			FELDSPAR = ;
			HORNBLENDE = ;
			PYROXENE = ;
			OLIVINE = ;
			ANTIMONITE = ;
			CINNABAR = ;
			CASSITERITE = ;
			CORUNDUM = ;
			QUMOITE = ;*/
		}
	}

	public static void onBiomeLoading(@Nonnull BiomeLoadingEvent e) {
		Biome.BiomeCategory category = e.getCategory();
		if (category != Biome.BiomeCategory.THEEND && category != Biome.BiomeCategory.NETHER && category != Biome.BiomeCategory.NONE) {
			final BiomeGenerationSettingsBuilder g = e.getGeneration();
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PYRITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CHALCOPYRITE);
			/*g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GALENA);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SPHALERITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, REALGAR);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CARNALLITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MAGNETITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CALCITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MALACHITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLASTER);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BARITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, APATITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MICA);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FELDSPAR);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, HORNBLENDE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PYROXENE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OLIVINE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ANTIMONITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CINNABAR);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CASSITERITE);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CORUNDUM);
			g.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, QUMOITE);
*/		}
	}

	/**
	 * This Method is Used For Generating |ConfiguredFeature|
	 *
	 * @param feature Feature Type (Ore, Tree, and so on)
	 * @param block   Generating Block
	 * @param config  Wrapped Config (Including Size, Range And Chance)
	 * @return ConfiguredFeature with defined config
	 */
	@SuppressWarnings("SameParameterValue")
	private static ConfiguredFeature<?, ?> getOreFeature(Feature<OreConfiguration> feature, BlockState block,
	                                                     String key, OreFeatureConf config) {
		OreConfiguration configuration = new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE,
				block, config.maxSize);
		ConfiguredFeature<?, ?> configured = feature.configured(configuration)
				.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(
						config.bottomOffset, config.topOffset, config.maximum)))
				.squared()
				.count(config.perChunk);
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MODID, key), configured);
	}
}
