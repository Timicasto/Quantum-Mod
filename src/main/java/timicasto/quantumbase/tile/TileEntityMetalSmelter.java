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
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMetalSmelter extends TileEntity implements ITickable {
    private int progress;
    private int fuel;
    private int reduceProgress;
    private boolean isWorking;
    private boolean isTempIncreasing;
    private int temperature;
    private final ItemStackHandler inventory = new ItemStackHandler(3);
    private final FluidTank carbonMonoxide = new FluidTank(32000);

    @Override
    public void update() {
        Item source = inventory.getStackInSlot(0).getItem();

        if (isTempIncreasing) {
            if (fuel > 0) {
                ++temperature;
                --fuel;
            } else {
                isTempIncreasing = false;
            }
        }

        if (temperature >= getSmeltingTemp(source) && fuel > 0) {
            isWorking = true;
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

        if (isWorking && fuel > 0) {
            ItemStack result = getItemSmeltResult(source);
            boolean isNeedReduce = isSourceNeedReduce(result.getItem());
            ++progress;
            --fuel;
            if (progress >= getSmeltTime(source)) {
                if (isNeedReduce) {
                    if (carbonMonoxide.getFluidAmount() > 0) {
                        ++reduceProgress;
                        carbonMonoxide.drain(1, true);
                        if (reduceProgress >= 3200) {
                            inventory.insertItem(2, getReduceResult(result.getItem()), false);
                            reduceProgress = 0;
                            progress = 0;
                            inventory.extractItem(0, 1, false);
                        }
                    } else {
                        return;
                    }
                } else {
                    inventory.insertItem(2, result, false);
                    inventory.extractItem(0, 1, false);
                }
            }
        }

    }

    private int getSmeltTime(Item source) {
        if (source == Item.getItemFromBlock(Blocks.IRON_ORE)) {
            return 12800;
        }
        if (source == Item.getItemFromBlock(Blocks.GOLD_ORE)) {
            return 19200;
        }
        return 0;
    }

    private ItemStack getReduceResult(Item source) {
        if (source == Items.IRON_INGOT) {
            return new ItemStack(Items.IRON_INGOT);
        }
        if (source == Items.GOLD_INGOT) {
            return new ItemStack(Items.GOLD_INGOT);
        }
        return null;
    }

    private boolean isSourceNeedReduce(Item source) {
        if (source == Items.GOLD_INGOT) {
            return true;
        }
        if (source == Items.IRON_INGOT) {
            return true;
        }
        return false;
    }

    private ItemStack getItemSmeltResult(Item source) {
        if (source == Item.getItemFromBlock(Blocks.IRON_ORE)) {
            return new ItemStack(Items.IRON_INGOT, 1);
        }
        if (source == Item.getItemFromBlock(Blocks.GOLD_ORE)) {
            return new ItemStack(Items.GOLD_INGOT, 1);
        }
        return null;
    }

    private int getSmeltingTemp(Item source) {
        int id;
        id = 0;
        if (source == Item.getItemFromBlock(Blocks.IRON_ORE)) {
            id = 1;
        }
        if (source == Item.getItemFromBlock(Blocks.GOLD_ORE)) {
            id = 2;
        }
        switch (id) {
            case 1:
                return 1538;
            case 2:
                return 1064;
            default:
                return 0;
        }
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
