package quantumstudio.quantumbase.multiblock;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quantumstudio.quantumbase.multiblock.api.MultiBlockHandler;
import quantumstudio.quantumbase.multiblock.api.MultiBlockMachineInstance;
import quantumstudio.quantumbase.registries.Contents;
import quantumstudio.quantumbase.tile.BlastFurnaceTileEntity;

public class MultiBlockBlast implements MultiBlockHandler.IMultiBlock {
	public static MultiBlockBlast instance = new MultiBlockBlast();
	public static BlockState[][][] structure = new BlockState[3][3][3];
	public static ItemStack[][][] guiStructure = new ItemStack[3][3][3];

	static {
		BlockState A = Blocks.NETHER_BRICK_SLAB.defaultBlockState();
		BlockState B = Blocks.STONE.defaultBlockState();
		BlockState C = Blocks.BRICK_SLAB.defaultBlockState();
		BlockState D = Blocks.NETHER_BRICKS.defaultBlockState();
		BlockState E = Blocks.NETHER_BRICK_FENCE.defaultBlockState();
		ItemStack AI = new ItemStack(Blocks.NETHER_BRICK_SLAB);
		ItemStack BI = new ItemStack(Blocks.STONE);
		ItemStack CI = new ItemStack(Blocks.BRICK_SLAB);
		ItemStack DI = new ItemStack(Blocks.NETHER_BRICKS);
		ItemStack EI = new ItemStack(Blocks.NETHER_BRICK_FENCE);
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < 3; z++) {
					boolean b = (z == 1 && (x == 0 || x == 2)) || (x == 1 && (z == 0 || z == 2));
					if (y == 0) {
						if ((x == 0 && (z == 0 || z == 2) || (x == 2 && (z == 0 || z == 2)))) {
							structure[x][y][z] = E;
							guiStructure[x][y][z] = EI;
						} else if (b) {
							structure[x][y][z] = A;
							guiStructure[x][y][z] = AI;
						}
					} else if (y == 1) {
						if (x == 1 || z == 1) {
							structure[x][y][z] = C;
							guiStructure[x][y][z] = CI;
						} else {
							structure[x][y][z] = B;
							guiStructure[x][y][z] = BI;
						}
					} else {
						if (b) {
							structure[x][y][z] = D;
							guiStructure[x][y][z] = DI;
						}
					}
					if (structure[x][y][z] == null) {
						structure[x][y][z] = Blocks.AIR.defaultBlockState();
					}
					if (guiStructure[x][y][z] == null) {
						guiStructure[x][y][z] = ItemStack.EMPTY;
					}
				}
			}
		}
	}

	@Override
	public ItemStack[][][] renderStructureInGUI() {
		return guiStructure;
	}

	@Override
	public ItemStack[] materials() {
		return new ItemStack[] {
				new ItemStack(Blocks.NETHER_BRICK_SLAB, 4),
				new ItemStack(Blocks.STONE, 8),
				new ItemStack(Blocks.BRICK_SLAB, 4),
				new ItemStack(Blocks.NETHER_BRICKS, 4),
				new ItemStack(Blocks.NETHER_BRICK_FENCE, 4)
		};
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean overwriteBlockRender(ItemStack stack, int it) {
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean canRenderFormed() {
		return true;
	}

	static ItemStack renderStack = ItemStack.EMPTY;

	@OnlyIn(Dist.CLIENT)
	@Override
	public void renderFormed() {
		if (renderStack.isEmpty()) {
			renderStack = new ItemStack(Blocks.STONE);
		}

		GlStateManager._translated(2.5, 2.25, 2.25);
		GlStateManager._rotatef(-45, 0, 1, 0);
		GlStateManager._rotatef(-20, 1, 0, 0);
		GlStateManager._scaled(6.5, 6.5, 6.5);
		GlStateManager._disableCull();
//		Minecraft.getInstance().getItemRenderer().render(renderStack, ItemTransforms.TransformType.GUI);
		GlStateManager._enableCull();
	}

	@Override
	public float getInGUIScale() {
		return 12;
	}

	@Override
	public String getUniqueName() {
		return "quantumbase:blast_furnace";
	}

	@Override
	public BlockState[][][] getStructure() {
		return structure;
	}

	@Override
	public boolean isTrigger(BlockState s) {
		return s.getBlock() == Blocks.BRICK_SLAB;
	}

	@Override
	public boolean form(Level level, BlockPos pos, Direction facing, Player player) {
		if (facing == Direction.DOWN || facing == Direction.UP) {
			facing = facing.getClockWise();
		}
		BlockPos start = pos;
		boolean mirrored = false;
		boolean isValid = check(level, start, facing, mirrored);

		if (!isValid) {
			mirrored = true;
			isValid = check(level, start, facing, mirrored);
		}

		if (isValid) {
			for (int x = 0 ; x < 5 ; x++) {
				for (int z = -2 ; z < 2 ; z++) {
					for (int y = -1 ; y < 5 ; y++) {
						if (structure[x][y + 1][z + 2].getBlock() == Blocks.AIR) {
							int zz = mirrored ? -z : z;
							BlockPos startx = start.relative(facing, x).relative(facing.getClockWise(), zz).offset(0, y, 0);
							level.setBlock(startx, ((MultiBlockMachineInstance) Contents.MACHINE).state(EnumMachines.BLAST_FURNACE.ordinal()), 3);
							BlockEntity current = level.getBlockEntity(startx);
							if (current instanceof BlastFurnaceTileEntity) {
								BlastFurnaceTileEntity te = (BlastFurnaceTileEntity)current;
								te.facing = facing;
								te.formed = true;
								te.pos = (y + 1) * 25 + x * 5 + (z + 2);
								te.offset = new int[]{(facing == Direction.WEST ? -z + 2 : facing == Direction.EAST ? z - 2 : facing == Direction.NORTH ? zz : -zz), y - 1, (facing == Direction.NORTH ? -z + 2 : facing == Direction.SOUTH ? z - 2 : facing == Direction.EAST ? zz : -zz)};
								te.mirrored = mirrored;
								te.setChanged();
								level.blockEvent(startx, Contents.MACHINE, 255, 0);
							}
						}
					}
				}
			}
		}

		return isValid;
	}

	boolean check(Level world, BlockPos start, Direction facing, boolean mirrored) {
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				for (int z = -1; z < 2; z++) {
					if (!structure[x + 1][y + 1][z + 1].getBlock().is(Blocks.AIR)) {
						int zz = mirrored ? -z : z;
						BlockPos pos = start.relative(facing, x).relative(facing.getClockWise(), zz).offset(0, y, 0);
						if (world.getBlockState(start).getBlock() == Blocks.AIR) {
							return false;
						}
						if (structure[x + 1][y + 1][z + 1].getBlock() == Blocks.NETHER_BRICK_SLAB) {
							if (world.getBlockState(start).getBlock() != Blocks.NETHER_BRICK_SLAB) {
								return false;
							}
						} else if (structure[x + 1][y + 1][z + 1].getBlock() == Blocks.STONE) {
							if (world.getBlockState(start).getBlock() != Blocks.STONE) {
								return false;
							}
						} else if (structure[x + 1][y + 1][z + 1].getBlock() == Blocks.BRICK_SLAB) {
							if (world.getBlockState(start).getBlock() != Blocks.BRICK_SLAB) {
								return false;
							}
						} else if (structure[x + 1][y + 1][z + 1].getBlock() == Blocks.NETHER_BRICKS) {
							if (world.getBlockState(start).getBlock() != Blocks.NETHER_BRICKS) {
								return false;
							}
						} else if (structure[x + 1][y + 1][z + 1].getBlock() == Blocks.NETHER_BRICK_FENCE) {
							if (world.getBlockState(start).getBlock() != Blocks.NETHER_BRICK_FENCE) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}
