package me.tastybulb.asylum.impl.module.modules.render;

import me.tastybulb.asylum.api.event.events.EventPlayerUpdate;
import org.lwjgl.input.Keyboard;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.MobEffects;

public class Fullbright extends Module {
	
	public Fullbright() {
		super("FullBright", "makes ur game fully bright", Keyboard.KEY_U, Category.RENDER);
	}
	private float lastGamma;

	 @Override
	 public void onEnable() {
	     super.onEnable();
	     Asylum.EVENT_BUS.subscribe(this);
	        
	     lastGamma = mc.gameSettings.gammaSetting;
	 }

	 @Override
	 public void onDisable() {
	     super.onDisable();
	     Asylum.EVENT_BUS.unsubscribe(this);
	           
	     mc.gameSettings.gammaSetting = this.lastGamma;
	 }

	 @EventHandler
	 private Listener<EventPlayerUpdate> OnPlayerUpdate = new Listener<>(p_Event -> {
	     mc.gameSettings.gammaSetting = 1000;
        mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
	 });

}
