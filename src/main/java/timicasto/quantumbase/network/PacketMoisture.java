package timicasto.quantumbase.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import timicasto.quantumbase.capability.CapabilityHandler;
import timicasto.quantumbase.capability.IMoisture;

public class PacketMoisture implements IMessage {
    public NBTTagCompound compound;

    @Override
    public void fromBytes(ByteBuf buf) {
        compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, compound);
    }

    public static class Handler implements IMessageHandler<PacketMoisture, IMessage> {
        @Override
        public IMessage onMessage(PacketMoisture moisture, MessageContext context) {
            // 判断side
            if (context.side == Side.CLIENT) {
                // 获取接受来的数据中的moisture标识
                final NBTBase nbtBase = moisture.compound.getTag("moisture");
                Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        // 获取客户端的玩家
                        EntityPlayer player = Minecraft.getMinecraft().player;
                        // 获取玩家的capability并写入接收到的新数据
                        if (player.hasCapability(CapabilityHandler.capMoisture, null)) {
                            IMoisture moisture = player.getCapability(CapabilityHandler.capMoisture, null);
                            Capability.IStorage<IMoisture> storage = CapabilityHandler.capMoisture.getStorage();
                            storage.readNBT(CapabilityHandler.capMoisture, moisture, null, nbtBase);
                        }
                    }
                });
            }
            return null;
        }
    }
}
