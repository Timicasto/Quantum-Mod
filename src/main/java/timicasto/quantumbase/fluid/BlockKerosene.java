package timicasto.quantumbase.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import timicasto.quantumbase.creative.TabLoader;

public class BlockKerosene extends BlockFluidClassic {
    public BlockKerosene() {
        super(FluidLoader.KEROSENE, Material.WATER);
        setUnlocalizedName("kerosene");
        setCreativeTab(TabLoader.envTab);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
