package timicasto.quantumbase.environment;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomStructure implements IWorldGenerator {
    public static final StructureGenerator combustibleIce = new StructureGenerator("combustible_ice");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0:
                // combustible ice generation
                if (world.getBiomeForCoordsBody(new BlockPos(chunkX * 16, 70, chunkZ * 16)) instanceof BiomeOcean) {
                    generateStructure(combustibleIce, world, random, chunkX, chunkZ, 1, Blocks.WATER);
                }
                break;
        }
    }
    
    /**
     * 在距顶层方块的5层之下生成指定结构
     *
     * @param generator 指定结构的生成器
     * @param world 所处世界
     * @param random dunno
     * @param chunkX 区块的X轴坐标
     * @param chunkZ 区块的Z轴坐标
     * @param chance 生成机会
     * @param topBlock 顶层方块
     */
    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock) {
        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world,x,z,topBlock);
        BlockPos pos = new BlockPos(x,y,z);
          if (random.nextInt(chance) == 0) {
                generator.generate(world,random,pos);
            }
    }
    
    /**
     * 计算出以指定的顶层方块向下五格的方块位置
     *
     * @param world 所处世界
     * @param x X轴坐标
     * @param z Z轴坐标
     * @param topBlock 指定的顶层方块
     * @return 比顶层方块低5格的高度
     */
    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
        int y = world.getHeight();
        boolean foundGround = false;
        while(!foundGround && y-- >= 0) {
            Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = block == topBlock;
        }
        return y-5;
    }
}



















