package timicasto.quantumbase.environment;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import timicasto.quantumbase.register.QuantumBaseBlocks;

import java.util.Random;

public class WorldGenOres implements IWorldGenerator {
    private WorldGenerator magnetite, hematite;

    public WorldGenOres() {
        magnetite = new WorldGenMinable(QuantumBaseBlocks.magnetite.getDefaultState(), 6);
        hematite = new WorldGenMinable(QuantumBaseBlocks.hematite.getDefaultState(), 4);
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            generateOre(hematite, world, random, chunkX,chunkZ, 25, 25, 58);
            generateOre(magnetite, world, random, chunkX, chunkZ, 38, 25, 68);
        }
    }

    private void generateOre(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, int minY, int maxY) {
        if (minY > maxY || minY < 0 || maxY > 256) {
            throw new IllegalArgumentException("Ore generated out of bounds");
        }

        int deltaY = maxY - minY + 1;
        for (int i = 0 ; i < chance ; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minY + random.nextInt(deltaY);
            int z = chunkZ * 16 + random.nextInt(16);

            generator.generate(world, random, new BlockPos(x, y, z));
        }
    }
}










