package timicasto.quantumbase.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import timicasto.quantumbase.creative.TabLoader;

public class LiquidPetroleumBlock extends BlockFluidClassic {
    public LiquidPetroleumBlock() {
        super(FluidLoader.PETROLEUM, Material.WATER);
        setUnlocalizedName("petroleum");
        setCreativeTab(TabLoader.envTab);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
