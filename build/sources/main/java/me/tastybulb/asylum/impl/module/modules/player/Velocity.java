package me.tastybulb.asylum.impl.module.modules.player;

import me.tastybulb.asylum.api.event.events.EventPacket;
import me.tastybulb.asylum.impl.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

public class Velocity extends Module {
	public NumberSetting percent = new NumberSetting("percent", this, 0, 0, 100, 10);
	
	public Velocity() {
		super("Velocity", "stops you from taking knockback", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnable() {
		Asylum.EVENT_BUS.subscribe(this);
	}

	public void onDisable() {
		Asylum.EVENT_BUS.unsubscribe(this);
	}
	
	@EventHandler
	private final Listener<EventPacket.Receive> receiveListener = new Listener<>(event -> {
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
