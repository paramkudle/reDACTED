package me.tastybulb.asylum.impl.module.modules.combat;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import me.tastybulb.asylum.api.event.events.PacketEvent;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.BooleanSetting;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AutoCope extends Module {
    public ModeSetting msg = new ModeSetting("MSG", this, "GG", "Cope", "Slur", "EZ", "GG", "CustomMSG");
    public BooleanSetting greenText = new BooleanSetting("GreenText", this, true);

    public AutoCope() {
        super("AutoGG", "Says custom messages after killing a enemy", Keyboard.KEY_NONE, Category.COMBAT);
        this.addSettings(msg, greenText);
    }
    int delay = 0;
    private static final ConcurrentHashMap<Object, Integer> targetedPlayers = new ConcurrentHashMap<Object, Integer>();

    public static String customMsgArg = "";
    public static void setMessage(String msg) {
        customMsgArg = msg;
    }

    @Override
    public void onUpdate() {

        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.getHealth() <= 0) {
                    if (targetedPlayers.containsKey(player.getName())) {
                        announce(player.getName());
                    }
                }
            }
        }

        targetedPlayers.forEach((name, timeout) -> {
            if ((int)timeout <= 0) {
                targetedPlayers.remove(name);
            } else {
                targetedPlayers.put(name, (int)timeout - 1);
            }

        });

        delay++;

    }

    @EventHandler
    private Listener<PacketEvent.Send> sendListener = new Listener<>(event -> {

        if (mc.player == null) return;

        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity cPacketUseEntity = (CPacketUseEntity) event.getPacket();
            if (cPacketUseEntity.getAction().equals(CPacketUseEntity.Action.ATTACK)) {
                Entity targetEntity = cPacketUseEntity.getEntityFromWorld(mc.world);
                if (targetEntity instanceof EntityPlayer) {
                    addTarget(targetEntity.getName());
                }
            }
        }

    });

    @EventHandler
    private Listener<LivingDeathEvent> livingDeathListener = new Listener<>(event -> {

        if (mc.player == null) return;

        EntityLivingBase e = event.getEntityLiving();
        if (e == null) return;

        if (e instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e;

            if (player.getHealth() <= 0) {
                if (targetedPlayers.containsKey(player.getName())) {
                    announce(player.getName());
                }
            }
        }

    });

    public void announce(String name) {
        if (delay < 150) {
            return;
        }
        delay = 0;
        targetedPlayers.remove(name);

        String starter = "";
        if(greenText.isEnabled()) starter = "> ";

        String message = "";
        if(msg.is("Cope")) message = starter + "Cope harder";
        if(msg.is("Slur")) message = starter + "You are so bad nigga";
        if(msg.is("EZ")) message = starter + "EZZZZZZZZZZZ";
        if(msg.is("GG")) message = starter + "GG";
        if(msg.is("CustomMSG")) message = starter + customMsgArg;

        mc.player.connection.sendPacket(new CPacketChatMessage(message));
    }

    public static void addTarget(String name) {
        if (!Objects.equals(name, mc.player.getName())) {
            targetedPlayers.put(name, 20);
        }
    }

}
