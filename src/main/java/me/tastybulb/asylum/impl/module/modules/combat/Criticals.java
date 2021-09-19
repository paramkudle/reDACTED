package me.tastybulb.asylum.impl.module.modules.combat;

import me.tastybulb.asylum.api.event.events.NetworkPacketEvent;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;

public class Criticals extends Module {
    public ModeSetting mode = new ModeSetting("Mode", this, "Packet", "Packet", "Jump");

    public Criticals() {
        super ("Criticals", "?????", Keyboard.KEY_NONE, Category.COMBAT);
        this.addSettings(mode);
    }

    @EventHandler
    private Listener<NetworkPacketEvent> PacketEvent = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();

            if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
                if (packet.getEntityFromWorld(mc.world) instanceof EntityLivingBase && mc.player.onGround && !mc.gameSettings.keyBindJump.isKeyDown()) {

                    if(mode.is("Jump")) {
                        mc.player.jump();
                    }

                    if(mode.is("Packet")) {
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1f, mc.player.posZ, false));
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
                    }
                }
            }
        }
    });
}
