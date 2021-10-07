package quantumstudio.quantumbase.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class PetroleumFeature extends Feature<BlockStateConfiguration> {
	public static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

	public PetroleumFeature(Codec<BlockStateConfiguration> c) {
		super(c);
	}

	@Override
	public boolean place(WorldGenLevel level, ChunkGenerator generator, Random random, BlockPos pos, BlockStateConfiguration config) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		for (x -= 8, z -= 8; y > 5 && isAir(level, pos); --y);
		if (y <= 12) {
			return false;
		}
		y -= 4;
		boolean[] buffer = new boolean[4096];
		for (int i = 0, loops = random.nextInt(8) + 4 ; i < loops; ++i) {
			double d0 = random.nextDouble() * 6.0D + 3.0D;
			double d1 = random.nextDouble() * 4.0D + 2.0D;
			double d2 = random.nextDouble() * 6.0D + 3.0D;
			double d3 = random.nextDouble() * (14.0D - d0) + 1.0D + d0 / 2.0D;
			double d4 = random.nextDouble() * (4.0D - d1) + 2.0D + d1 / 2.0D;
			double d5 = random.nextDouble() * (14.0D - d2) + 1.0D + d2 / 2.0D;

			for (int j = 1 ; j < 15 ; ++j) {
				for (int k = 1 ; k < 15 ; ++k) {
					for (int l = 1 ; l < 7 ; ++l) {
						double d6 = (j - d3) / (d0 / 2.0D);
						double d7 = (l - d4) / (d1 / 2.0D);
						double d8 = (k - d5) / (d2 / 2.0D);
						double d9 = d6 * d6 + d7 * d7 + d8 * d8;

						if (d9 < 0.6D) {
							buffer[(j * 16 + k) * 8 + l] = true;
						}
					}
				}
			}
		}

		boolean flag;
		for (int i = 0 ; i < 16 ; ++i) {
			for (int j2 = 0 ; j2 < 16 ; ++j2) {
				for (int j1 = 0 ; j1 < 8 ; ++j1) {
					flag = !buffer[(i * 16 + j2) * 8 + j1] && buffer[((i + 1) * 16 + j2) * 8 + j1] ||
							i > 0 && buffer[((i - 1 ) * 16 + j2) + 8 + j1] || j2 < 15 && buffer[(i * 16 + j2 + 1) * 8 + j1] ||
							j2 > 0 && buffer[(i * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && buffer[(i * 16 + j2) * 8 + j1 + 1] ||
							j1 > 0 && buffer[(i * 16 + j2) * 8 + (j1 - 1)];
					if (flag) {
						BlockPos pos1 = new BlockPos(x + i, y + j1 , z + j2);
						BlockState state = level.getBlockState(pos1);
						Material material = state.getMaterial();

						if (j1 >= 4 && material.isLiquid()) {
							return false;
						}

						if (j1 < 4 && !material.isSolid() && state.getBlock() != this.block) {
							return false;
						}
					}
				}
			}
		}

		for (int i = 0 ; i < 16 ; ++i) {
			for (int j2 = 0 ; j2 < 16 ; ++j2) {
				for (int j1 = 0 ; j1 < 8 ; ++j1) {
					if (buffer[(i * 16 + j2) * 8 + j1]) {
						world.setBlockState(new BlockPos(x + i , y + j1, z + j2), j1 >= 4 ? Blocks.AIR.getDefaultState() : this.block.getDefaultState(), 2);
						world.setBlockState(new BlockPos(x + i, y + j1 + random.nextInt(2) + 4, z + j2), j1>4 ? Blocks.AIR.getDefaultState() : Blocks.COAL_ORE.getDefaultState(), 2);
					}
				}
			}
		}

		for (int i = 0 ; i < 16 ; ++i) {
			for (int j2 = 0 ; j2 < 16 ; ++j2) {
				for (int j1 = 4 ; j1 < 8 ; ++j1) {
					BlockPos pos1 = new BlockPos(x + i, y + j1, z + j2);
					IBlockState state = world.getBlockState(pos1);
					if (buffer[(i * 16 + j2) * 8 + j1] && state.getBlock() == Blocks.DIRT && state.getBlock().getLightValue(state, world, new BlockPos(x + i, y + j1, z + j2)) > 0) {
						Biome biome = world.getBiomeProvider().getBiome(pos1);

						if (biome instanceof BiomeMushroomIsland) {
							world.setBlockState(pos1, Blocks.MYCELIUM.getDefaultState(), 2);
						} else {
							world.setBlockState(pos1, Blocks.GRASS.getDefaultState(), 2);
						}
					}
				}
			}
		}

		for (int i1 = 0; i1 < 16; ++i1) {
			for (int j2 = 0; j2 < 16; ++j2) {
				BlockPos bPos = new BlockPos(x + i1, y + 4, z + j2);
				if (world.canBlockFreezeWater(bPos)) {
					world.setBlockState(bPos, Blocks.ICE.getDefaultState(), 2);
				}
			}
		}
		return true;
	}
}
