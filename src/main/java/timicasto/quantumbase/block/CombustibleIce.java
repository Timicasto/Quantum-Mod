package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import timicasto.quantumbase.ModItems;
import timicasto.quantumbase.creative.TabLoader;

import java.util.Random;

public class CombustibleIce extends Block {
    public CombustibleIce() {
        super(Material.ICE);
        this.setRegistryName("combustible_ice");
        this.setUnlocalizedName("combustible_ice");
        this.setCreativeTab(TabLoader.envTab);
        this.setHardness(3.0F);
        assert I18n.format("tile.combustible_ice.name").equals("可燃冰");
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(ModItems.combustibleIcecItemBlock,1);
    }

}
