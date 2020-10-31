package timicasto.quantumbase.capability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.network.PacketMoisture;

import javax.annotation.Nullable;

public class CapabilityMoisture {
    public static class Storage implements Capability.IStorage<IMoisture> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IMoisture> capability, IMoisture instance, EnumFacing side) {
            //创建一个新的NBT标签
            NBTTagCompound compound = new NBTTagCompound();
            //将清醒度值存入NBT的“consciousness”标签中
            compound.setDouble("moisture", instance.getMoisture());
            //将该NBT标签返回上级代码
            return compound;
        }

        @Override
        public void readNBT(Capability<IMoisture> capability, IMoisture instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            int conV = 20;
            //判断是否有所需要的标签
            if (compound.hasKey("moisture")) {
                //读取标签
                conV = compound.getInteger("moisture");
            }
            instance.setMoisture(conV);
        }
    }

    public static class ProvideToPlayer implements ICapabilitySerializable<NBTTagCompound>, ICapabilityProvider {
        private IMoisture moisture = new Implementation(); // 创建Moisture实例
        private Capability.IStorage<IMoisture> storage = CapabilityHandler.capMoisture.getStorage(); // 获取Storage结构

        // 判断目前玩家身上的Capability
        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return CapabilityHandler.capMoisture.equals(capability);
        }

        // 获取游戏对象Capability
        @Nullable
        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (CapabilityHandler.capMoisture.equals(capability)) {
                @SuppressWarnings("UNCHECKED")
                T result = (T) moisture;
                return result;
            }
            return null;
        }

        // 把Capability中的数据结构序列化成NBT
        @Override
        public NBTTagCompound serializeNBT() {
            // 新建标签
            NBTTagCompound compound = new NBTTagCompound();
            // 从Storage结构中取出数据结构并写入标签
            compound.setTag("moisture", storage.writeNBT(CapabilityHandler.capMoisture, moisture, null));
            // 把处理好的标签传上去等待有人理它
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound tag) {
            // 提取数据
            NBTTagCompound compound = tag.getCompoundTag("moisture");
            // 塞进moisture变量里面
            storage.readNBT(CapabilityHandler.capMoisture, moisture, null, compound);
        }
    }

    public static class MoistureEventHandler {
        @SubscribeEvent
        public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof EntityPlayer) {
                ICapabilitySerializable<NBTTagCompound> providerMoisture = new CapabilityMoisture.ProvideToPlayer();
                event.addCapability(new ResourceLocation("quantumabase:moisture"), providerMoisture);
            }
        }

        @SubscribeEvent
        public static void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
            if (!event.player.world.isRemote) {
                EntityPlayer player = event.player;
                if (player.hasCapability(CapabilityHandler.capMoisture, null)) {
                    // 套娃啊套娃 又来一个数据包
                    PacketMoisture moisture = new PacketMoisture();
                    IMoisture moisture1 = player.getCapability(CapabilityHandler.capMoisture, null);
                    Capability.IStorage<IMoisture> storage = CapabilityHandler.capMoisture.getStorage();
                    // 把数据塞进包 准备发货
                    moisture.compound = new NBTTagCompound();
                    moisture.compound.setTag("moisture", storage.writeNBT(CapabilityHandler.capMoisture, moisture1, null));
                    // 修好了 再给人家发回去
                    QuantumBase.getNetwork().sendTo(moisture, (EntityPlayerMP) player);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        // 再来个capability变量
        Capability<IMoisture> moistureCapability = CapabilityHandler.capMoisture;
        // 把新建变量中的Storage空间拽过来
        Capability.IStorage<IMoisture> moistureIStorage = moistureCapability.getStorage();

        // 判定原玩家是否带对应capability
        if (event.getOriginal().hasCapability(moistureCapability, null) && event.getEntityPlayer().hasCapability(moistureCapability, null)) {
            // 把原玩家的capability拽出来
            NBTBase nbtBase = moistureIStorage.writeNBT(moistureCapability, event.getOriginal().getCapability(moistureCapability, null), null);
            // 塞进新玩家
            moistureIStorage.readNBT(moistureCapability, event.getEntityPlayer().getCapability(moistureCapability, null), null, nbtBase);
        }
    }

    @SideOnly(Side.CLIENT)
    public class PlayerGuiSign {
        @SubscribeEvent
        public void render(RenderGameOverlayEvent.Pre event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
                EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
                int conV = 20;
                if (playerSP.hasCapability(CapabilityHandler.capMoisture, null)) {
                    IMoisture moisture = (IMoisture) playerSP.getCapability(CapabilityHandler.capMoisture, null);
                    conV = moisture.getMoisture();
                }
            }
        }
    }
}
