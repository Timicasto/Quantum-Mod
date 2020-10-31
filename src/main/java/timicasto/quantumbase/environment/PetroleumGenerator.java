package timicasto.quantumbase.environment;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class PetroleumGenerator implements IWorldGenerator {
    WorldGenPetroleumLake genPetroleumLake = new WorldGenPetroleumLake();
    private Logger logger = LogManager.getLogger();
    public PetroleumGenerator(){}

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            int x = chunkX * 16 + random.nextInt(16) + 8;
            int y = 5 + random.nextInt(30);
            int z = chunkZ * 16 + random.nextInt(16) + 8;
            if (world.getBiomeForCoordsBody(new BlockPos(x,y,z)) instanceof BiomeOcean) {
                if (random.nextDouble() < 0.2) {
                    genPetroleumLake.generate(world, random, new BlockPos(x, y, z));
                    logger.info("GENERATED Petroleum at " + x + " , " + y + " , " + z);
                }
            }
        }
    }
}
