package timicasto.quantumbase.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDebugTool extends Item {
    private Logger logger = LogManager.getLogger();
    public ItemDebugTool() {
        super();
        setRegistryName("debug_tool");
        setUnlocalizedName("debug_tool");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            player.sendMessage(new TextComponentString(String.format("" +
                                                                             "§l§m================================================================§r\n" +
                                                                             "   §0$0 §1$1 §2$2 §3$3 §4$4 §5$5 §6$6 §7$7 §8$8 §9$9 §a$a §b$b §c$c §d$d §e$e §f$f   §r§k$k§r  §l$l§r  §m$m§r  §n$n§r  §o$o§r  §r$r\n" +
                                                                             "§l§m================================================================§r\n" +
                                                                             "                               §a§lExtra Message\n" +
                                                                             "\n"  +
                                                                             "\n"  +
                                                                             "\n"  +
                                                                             "\n"  +
                                                                             "\n"  +
                                                                             "\n"  +
                                                                             "\n"  +
                                                                             " §7§otodo\n"  +
                                                                             "§l----------------------------------------------------------------§r\n" +
                                                                             "                        §a§lQuantum Dev Message Summoned\n" +
                                                                             "\n"  +
                                                                             "  §lCurrent Player: §r§e%s §r§7§o%s\n"+
                                                                             "  §lFacing Block: §r§e%s §r§7§o%s\n"  +
                                                                             "\n" +
                                                                             "\n" +
                                                                             "\n" +
                                                                             " §7§otodo\n" +
                                                                             "§l§m================================================================§r"
                    , player.getName(),
                    player.getUniqueID().toString(),
                    worldIn.getBlockState(pos).getBlock().getLocalizedName(),
                    worldIn.getBlockState(pos).getBlock().getRegistryName()
            )));
        }
        return null;
    }
}
