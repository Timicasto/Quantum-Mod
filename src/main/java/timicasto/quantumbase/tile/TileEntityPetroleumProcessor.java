package timicasto.quantumbase.tile;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.fluid.FluidLoader;
import timicasto.quantumbase.register.QuantumBaseItems;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityPetroleumProcessor extends TileEntity implements ITickable {

    private int temperature;
    private boolean isWorking;
    private boolean isTempIncreasing;
    private int fuel;
    private final Logger logger = LogManager.getLogger();
    private final FluidTank petroleumTank = new FluidTank(4000);
    private final FluidTank keroseneTank = new FluidTank(4000);
    private final FluidTank gasolineTank = new FluidTank(4000);
    private final FluidTank dieseiOilTank = new FluidTank(4000);
    private final ItemStackHandler paraffin = new ItemStackHandler();
    private final ItemStackHandler asphalt = new ItemStackHandler();
    private final Random random = new Random();

    public FluidTank getPetroleumTank() {
        return petroleumTank;
    }

    public FluidTank getKeroseneTank() {
        return keroseneTank;
    }

    public FluidTank getGasolineTank() {
        return gasolineTank;
    }

    public FluidTank getDieseiOilTank() {
        return dieseiOilTank;
    }

    public int getFuel() {
        return fuel;
    }

    public ItemStack tryAcceptFuel() {
        if (petroleumTank.getFluidAmount() <= 3000 && fuel <= 3000) {
            petroleumTank.fill(new FluidStack(FluidLoader.PETROLEUM, 1000), true);
            this.fuel = fuel + 1000;
            return new ItemStack(Items.BUCKET);
        } else {
            return FluidUtil.getFilledBucket(new FluidStack(FluidLoader.PETROLEUM, Fluid.BUCKET_VOLUME));
        }
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (fuel <= 0) {
                if (petroleumTank.getFluidAmount() >= 0) {
                    fuel = fuel + petroleumTank.getFluidAmount();
                }
            }
            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == Blocks.FIRE) {
                if (!isWorking) {
                    ++temperature;
                    isTempIncreasing = true;
                }
            }
            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() != Blocks.FIRE) {
                isTempIncreasing = false;
                if (temperature >= 30) {
                    --temperature;
                }
            }

            if (temperature == 135) {
                if (fuel > 0) {
                    isWorking = true;
                    int petroleumAmount = petroleumTank.getFluidAmount();
                    int gasolineP1 = random.nextInt(300);
                    double gasolineP = (double) gasolineP1 / 1000.0;
                    double gasolineAmount = petroleumAmount * gasolineP;
                    gasolineTank.fill(new FluidStack(FluidLoader.GASOLINE, (int) gasolineAmount), true);
                    petroleumTank.drain((int) gasolineAmount, true);
                    double dieseiP = 0.5 - gasolineP;
                    double dieseiAmount = petroleumAmount * dieseiP;
                    dieseiOilTank.fill(new FluidStack(FluidLoader.DIESEI, (int) dieseiAmount), true);
                    petroleumTank.drain((int) dieseiAmount, true);
                    int keroseneAmount = (int) (0.3 * petroleumAmount);
                    keroseneTank.fill(new FluidStack(FluidLoader.KEROSENE, keroseneAmount), true);
                    petroleumTank.drain(keroseneAmount, true);
                }
            }

            if (temperature >= random.nextInt(800) + 2400) {
                world.spawnEntity(new PrimedIce(world, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, null));
            }

            if (temperature < 135) {
                isWorking = false;
            }

            if (fuel > 0) {
                if (temperature == 125) {
                    if (isTempIncreasing) {
                        if (fuel < 500) {
                            paraffin.insertItem(0, new ItemStack(QuantumBaseItems.paraffin, 1), false);
                            asphalt.insertItem(0, new ItemStack(QuantumBaseItems.asphalt, random.nextInt(1) + 1), false);
                        }
                        if (500 <= fuel && fuel < 1000) {
                            paraffin.insertItem(0, new ItemStack(QuantumBaseItems.paraffin, random.nextInt(1) + 1), false);
                            asphalt.insertItem(0, new ItemStack(QuantumBaseItems.asphalt, random.nextInt(2) + 1), false);
                        }
                        if (1000 <= fuel && fuel < 2000) {
                            paraffin.insertItem(0, new ItemStack(QuantumBaseItems.paraffin, random.nextInt(1) + 2), false);
                            asphalt.insertItem(0, new ItemStack(QuantumBaseItems.asphalt, random.nextInt(2) + 2), false);
                        }
                        if (2000 <= fuel && fuel <= 4000) {
                            paraffin.insertItem(0, new ItemStack(QuantumBaseItems.paraffin, random.nextInt(2) + 3), false);
                            asphalt.insertItem(0, new ItemStack(QuantumBaseItems.asphalt, random.nextInt(6) + 2), false);
                        }
                    }
                }
            }
        }
        logger.info("current fuel : " + fuel);
        logger.info("current petroleum : " + petroleumTank.getFluidAmount());
        logger.info("current paraffin : " + paraffin.getStackInSlot(0).getCount());
        logger.info("current asphalt : " + asphalt.getStackInSlot(0).getCount());
        logger.info("current temperature : " + temperature);
        logger.info("increasing : " + isTempIncreasing);
        logger.info("working : " + isWorking);
        logger.info("current gasoline : " + gasolineTank.getFluidAmount());
        logger.info("current diesei : " + dieseiOilTank.getFluidAmount());
        logger.info("current kerosene : " + keroseneTank.getFluidAmount());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.UP) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(petroleumTank);
            }
            if (facing == EnumFacing.DOWN) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(gasolineTank);
            }
            if (facing == EnumFacing.NORTH) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(keroseneTank);
            }
            if (facing == EnumFacing.EAST) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(dieseiOilTank);
            }
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == EnumFacing.DOWN) {
                logger.info("Pong!");
                return (T) this.asphalt;
            }
            if (facing == EnumFacing.UP) {
                return (T) this.paraffin;
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.temperature = compound.getInteger("temperature");
        this.fuel = compound.getInteger("Fuel");
        this.petroleumTank.readFromNBT(compound);
        this.keroseneTank.readFromNBT(compound);
        this.gasolineTank.readFromNBT(compound);
        this.dieseiOilTank.readFromNBT(compound);
        this.paraffin.deserializeNBT(compound.getCompoundTag("paraffin"));
        this.asphalt.deserializeNBT(compound.getCompoundTag("asphalt"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("temperature", this.temperature);
        compound.setInteger("Fuel", this.fuel);
        this.petroleumTank.writeToNBT(compound);
        this.keroseneTank.writeToNBT(compound);
        this.gasolineTank.writeToNBT(compound);
        this.dieseiOilTank.writeToNBT(compound);
        compound.setTag("paraffin", this.paraffin.serializeNBT());
        compound.setTag("asphalt", this.asphalt.serializeNBT());
        return super.writeToNBT(compound);
    }
}
