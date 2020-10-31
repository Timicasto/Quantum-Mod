package timicasto.quantumbase.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockSeaWater extends BlockFluidClassic {
    public BlockSeaWater() {
        super(new FluidSeaWater(), Material.WATER);
        setUnlocalizedName("sea_water");
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
