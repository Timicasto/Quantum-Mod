package timicasto.quantumbase.environment;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import timicasto.quantumbase.ModItems;

import java.util.Random;

public class GenTree implements IWorldGenerator {

    public WorldGenTrees willowTree = new WorldGenTrees(false, 8, ModItems.willowWood.getDefaultState(), ModItems.willowLeaves.getDefaultState(), false);
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()) {
            case 0: //Overworld
                if(world.getBiomeForCoordsBody(new BlockPos(chunkX * 16, 70, chunkZ * 16)) instanceof BiomePlains) {
                    populate(willowTree, world, random, chunkX, chunkZ, 16);

                }
                break;
            case -1: //Nether
                break;
            case 1: //End
                break;
        }
    }

    private void gen(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight) {
        if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chancesToSpawn; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);
            generator.generate(world, random, new BlockPos(x, y, z));
        }
    }

    private void populate(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int amountPerChunk) {
        for(int i = 0; i < amountPerChunk; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = world.getChunkFromChunkCoords(x >> 4, z >> 4).getHeight(new BlockPos(x & 15, 0, z & 15)) - 1;
            generator.generate(world, random, new BlockPos(x, y, z));
        }
    }

}
