package timicasto.quantumbase.item;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.*;
import timicasto.quantumbase.utils.EnumSortCategoryItem;
import timicasto.quantumbase.utils.FluidUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

public class ItemModBucket extends ItemBucket implements ISortableItem, ICapabilityProvider {
    private String domain;
    public Fluid acceptsFluid;
    private static HashMap<String, ItemModBucket> fluids = new HashMap<>();

    public ItemModBucket(Block block, Fluid fluid) {
        super(block);
        this.setContainerItem(Items.BUCKET);
        this.acceptsFluid = fluid;
        fluids.put(fluid.getName(), this);
    }

    public static ItemModBucket getBucketForFluid(Fluid fluid) {
        return fluids.get(fluid.getName());
    }

    public static ItemStack fillBucketFrom(IFluidHandler fluidHandler) {
        FluidStack fluidStack = fluidHandler.drain(Fluid.BUCKET_VOLUME, false);
        if (fluidStack != null && fluidStack.amount == Fluid.BUCKET_VOLUME) {
            ItemModBucket result = ItemModBucket.getBucketForFluid(fluidStack.getFluid());
            if (result != null) {
                fluidHandler.drain(Fluid.BUCKET_VOLUME, true);
                return new ItemStack(result);
            }
        } return null;
    }

    public ItemStack drainBucketTo(IFluidHandler fluidHandler) {
        FluidStack stack = new FluidStack(this.acceptsFluid, Fluid.BUCKET_VOLUME);
        int transferred = fluidHandler.fill(stack, false);
        if (transferred == Fluid.BUCKET_VOLUME) {
            fluidHandler.fill(stack, true);
            return new ItemStack(Items.BUCKET);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public EnumSortCategoryItem getCategory(int meta) {
        return EnumSortCategoryItem.BUCKET;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new FluidHandlerBucket(stack);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY ? (T) this : null;
    }

    public static class FluidHandlerBucket implements IFluidHandlerItem, ICapabilityProvider {
        public static final String NBT_KEY = "Fluid";

        @Nonnull
        protected ItemStack container;
        protected int capacity;
        private ItemModBucket bucket;

        public FluidHandlerBucket(ItemStack container) {
            container.setCount(1);
            this.container = container;
            this.bucket = (ItemModBucket) container.getItem();
            this.capacity = Fluid.BUCKET_VOLUME;
        }

        @Override
        @Nonnull
        public ItemStack getContainer() {
            return container;
        }

        @Nullable
        public FluidStack getFluid() {
            return new FluidStack(bucket.acceptsFluid, this.capacity);
        }

        protected void setFluid(FluidStack fluid) {

        }

        @Override
        public IFluidTankProperties[] getTankProperties() {
            return new IFluidTankProperties[] {
                    new FluidTankProperties(getFluid(), capacity)
            };
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            return 0;
        }

        @Nullable
        @Override
        public FluidStack drain(FluidStack resource, boolean doDrain) {
            if (resource == null || !this.canDrainFluidType(resource)) {
                return null;
            }
            return drain(resource.amount, doDrain);
        }

        @Nullable
        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            if (maxDrain < this.capacity) {
                return null;
            }
            if (doDrain) {
                container = new ItemStack(Items.BUCKET);
            }
            return this.getFluid();
        }

        public boolean canFillFluidType(FluidStack fluidStack) {
            return false;
        }

        public boolean canDrainFluidType(FluidStack stack) {
            return FluidUtil.fluidsSame(bucket.acceptsFluid, stack.getFluid(), true);
        }

        protected void setContainerToEmpty() {
            container = new ItemStack(Items.BUCKET);
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY ? (T) this : null;
        }
    }
}
