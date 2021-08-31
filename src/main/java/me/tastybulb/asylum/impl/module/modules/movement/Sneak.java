package me.tastybulb.asylum.impl.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.api.event.Event.Era;
import me.tastybulb.asylum.api.event.events.NetworkPacketEvent;
import me.tastybulb.asylum.api.event.events.PlayerMotionUpdateEvent;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;

public class Sneak extends Module {

    public Sneak() {
        super ("Sneak", "Sneaky Sneaky", Keyboard.KEY_NONE, Category.MOVEMENT);
    }

    @Override
    public void onDisable() {
        if (mc.world != null && !mc.player.isSneaking()) {
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }

    @EventHandler
    private Listener<PlayerMotionUpdateEvent> OnPlayerUpdate = new Listener<>(event -> {
        if (event.getEra() != Era.PRE)
            return;

        mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
    });

    @EventHandler
    private Listener<NetworkPacketEvent> PacketEvent = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && !mc.player.isSneaking()) {
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    });
}