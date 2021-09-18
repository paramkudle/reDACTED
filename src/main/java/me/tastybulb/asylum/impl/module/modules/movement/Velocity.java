package me.tastybulb.asylum.impl.module.modules.movement;

import me.tastybulb.asylum.api.event.events.PacketEvent;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

public class Velocity extends Module {
    public NumberSetting percent = new NumberSetting("Percent", this, 0, 0, 100, 10);

    private Minecraft mc = Minecraft.getMinecraft();

    public boolean on;

    public Velocity() {
        super ("Velocity", "Prevents knockback", Keyboard.KEY_NONE, Category.MOVEMENT);
        this.addSettings();
    }

    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketEntityVelocity){
            if (((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId()) {
                event.cancel();
            }
        }
        if (event.getPacket() instanceof SPacketExplosion){
            event.cancel();
        }
    });
}
