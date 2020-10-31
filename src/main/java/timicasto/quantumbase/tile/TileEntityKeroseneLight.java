package timicasto.quantumbase.tile;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.block.BlockKeroseneLight;

public class TileEntityKeroseneLight extends TileEntity implements ITickable {
    private int fuel;
    private Logger logger = LogManager.getLogger();

    public void update() {
        if (!world.isRemote) {
            if (fuel > 0) {
                --fuel;
                BlockKeroseneLight.setLuminous(world, pos);
            }
            if (fuel <= 0) {
                BlockKeroseneLight.setDLuminous(world, pos);
            }
            logger.info("current fuel : " + fuel);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.fuel = compound.getInteger("Fuel");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("Fuel", (short)this.fuel);
        return compound;
    }

    public ItemStack tryAcceptFuel() {
        this.fuel = fuel + 1000;
        logger.info("Added 1000 fuel to KEROSENE");
        return new ItemStack(Items.BUCKET);
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int receiveFuel) {
        this.fuel = receiveFuel;
    }
}
