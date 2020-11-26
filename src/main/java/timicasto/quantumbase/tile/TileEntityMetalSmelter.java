package timicasto.quantumbase.tile;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.fluid.FluidLoader;
import timicasto.quantumbase.register.QuantumBaseBlocks;
import timicasto.quantumbase.utils.MetalSmelterRecipe;

import java.util.Random;

public class TileEntityMetalSmelter extends TileEntity implements ITickable {
    private Logger logger = LogManager.getLogger();
    private int progress;
    private int fuel;
    private int reduceProgress;
    private boolean isWorking;
    private boolean isTempIncreasing;
    private int temperature;
    private final ItemStackHandler inventory = new ItemStackHandler(3);
    private final FluidTank carbonMonoxide = new FluidTank(32000);
    private Random random = new Random();

    @Override
    public void update() {
        if (!world.isRemote) {
            Item source = inventory.getStackInSlot(0).getItem();

            if (isTempIncreasing) {
                if (fuel > 0) {
                    ++temperature;
                    --fuel;
                } else {
                    isTempIncreasing = false;
                }
            }

            if (temperature >= MetalSmelterRecipe.instance().getField("temp", new ItemStack(source)) && fuel > 0) {
                isWorking = true;
                isTempIncreasing = false;
            } else {
                isWorking = false;
            }

            if (fuel <= 1) {
                if (inventory.getStackInSlot(1).getItem() == Items.COAL && inventory.getStackInSlot(1).getCount() >= 1) {
                    fuel = fuel + 3200;
                    inventory.extractItem(1, 1, false);
                } else {
                    return;
                }
            }

            if (isWorking && fuel > 0 && !isTempIncreasing) {
                ItemStack result = MetalSmelterRecipe.instance().getSmeltingResult(new ItemStack(source));
                boolean isNeedReduce = MetalSmelterRecipe.instance().isSourceNeedReduce(result);
                ++progress;
                --fuel;
                if (progress >= MetalSmelterRecipe.instance().getField("smeltTime", new ItemStack(source)) + random.nextInt(MetalSmelterRecipe.instance().getField("smeltTimeDelta", new ItemStack(source)))) {
                    if (isNeedReduce) {
                        if (carbonMonoxide.getFluidAmount() > 0) {
                            ++reduceProgress;
                            carbonMonoxide.drain(1, true);
                            if (reduceProgress >= 3200) {
                                reduceProgress = 0;
                                int count = random.nextInt(100);
                                if (count <= 40) {
                                    inventory.extractItem(0, 1, false);
                                    progress = 0;
                                }
                                if (count > 40 && count < 75) {
                                    inventory.insertItem(2, MetalSmelterRecipe.instance().getReduceResult(result), false);
                                    inventory.extractItem(0, 1, false);
                                    progress = 0;
                                }
                                if (count >= 75) {
                                    inventory.insertItem(2, new ItemStack(MetalSmelterRecipe.instance().getReduceResult(result).getItem(), count / 10), false);
                                    inventory.extractItem(0, 1, false);
                                    progress = 0;
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        int count = random.nextInt(100);
                        if (count <= 40) {
                            inventory.extractItem(0, 1, false);
                            progress = 0;
                        }
                        if (count > 40 && count < 75) {
                            inventory.insertItem(2, result, false);
                            inventory.extractItem(0, 1, false);
                            progress = 0;
                        }
                        if (count >= 75) {
                            inventory.insertItem(2, new ItemStack(result.getItem(), count / 10), false);
                            inventory.extractItem(0, 1, false);
                            progress = 0;
                        }
                    }
                }
            }
            logger.info("current progress : " + progress);
            logger.info("current temp : " + temperature);
            logger.info("current fuel : " + fuel);
            logger.info("current co : " + carbonMonoxide.getFluidAmount());
            logger.info("current reduce progress : " + reduceProgress);
        }
    }

    public int getCO() {
        return carbonMonoxide.getFluidAmount();
    }

    public ItemStack tryAcceptFuel() {
        carbonMonoxide.fill(new FluidStack(FluidLoader.PETROLEUM, 1000), true);
        return new ItemStack(Items.BUCKET);
    }

    public void makeTempIncrease() {
        isTempIncreasing = true;
    }

    public FluidTank getCarbonMonoxide() {
        return carbonMonoxide;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.inventory;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) {
            return (T) this.carbonMonoxide;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }
}
