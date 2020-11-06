package timicasto.quantumbase.tile;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.event.ForgeEventFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.block.BlockKeroseneLight;
import timicasto.quantumbase.environment.CustomBoom;
import timicasto.quantumbase.environment.IceBoom;

public class TileEntityKeroseneLight extends TileEntity implements ITickable {
    private int fuel;
    private boolean isBurning;
    private Logger logger = LogManager.getLogger();

    public void update() {
        if (!world.isRemote) {
            if (fuel > 0 && isBurning) {
                --fuel;
                BlockKeroseneLight.setLuminous(world, pos);
            }
            if (fuel <= 0) {
                BlockKeroseneLight.setDLuminous(world, pos);
                isBurning = false;
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

    public void makeBurn() {
        isBurning = true;
    }

    public CustomBoom createExplode() {
        int range = fuel / 1000;
        CustomBoom explosion = new CustomBoom(this.world, null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 4.0F, false, true);
        if(ForgeEventFactory.onExplosionStart(this.world, explosion)) return explosion;
        explosion.doExplosionA(range);
        explosion.doExplosionB(true);
        return explosion;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int receiveFuel) {
        this.fuel = receiveFuel;
    }
}
