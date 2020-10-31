package timicasto.quantumbase.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.creative.TabLoader;

import java.lang.reflect.Field;

public class ItemDebugTool extends Item {
    private Logger logger = LogManager.getLogger();
    public ItemDebugTool() {
        super();
        setRegistryName("debug_tool");
        setUnlocalizedName("debug_tool");
        setCreativeTab(TabLoader.envTab);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
