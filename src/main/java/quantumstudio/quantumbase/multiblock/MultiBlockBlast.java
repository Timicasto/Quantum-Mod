package quantumstudio.quantumbase.multiblock;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import quantumstudio.quantumbase.multiblock.api.MultiBlockHandler;

public class MultiBlockBlast implements MultiBlockHandler.IMultiBlock {
	public static MultiBlockBlast instance = new MultiBlockBlast();
	public static BlockState[][][] structure = new BlockState[3][3][3];
	
	static {
		BlockState A = Blocks.NETHER_BRICK_SLAB.defaultBlockState();
		BlockState B = Blocks.STONE.defaultBlockState();
		BlockState C = Blocks.BRICK_SLAB.defaultBlockState();
		BlockState D = Blocks.NETHER_BRICKS.defaultBlockState();
		BlockState E = Blocks.NETHER_BRICK_FENCE.defaultBlockState();
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < 3; z++) {
					if (y == 0) {
						if (x == 1 && z == 1) {
							structure[x][y][z] = Blocks.AIR.defaultBlockState();
						} else {
							if ((x == 1 && z == 0) || (x == 1 && z == 2) || (x == 0 && z == 1) || (x == 2 && z == 1)) {
								structure[x][y][z] = A;
							} else {
								structure[x][y][z] = E;
							}
						}
					} else if (y == 1) {

					} else {

					}
				}
			}
		}
	}

}
