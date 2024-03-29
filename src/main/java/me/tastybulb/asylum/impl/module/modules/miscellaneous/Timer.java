package me.tastybulb.asylum.impl.module.modules.miscellaneous;

import me.tastybulb.asylum.impl.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Timer extends Module {
	public NumberSetting speed = new NumberSetting("speed", this, 20, 1, 300, 1);
	
	public Timer() {
		super("Timer", "changes the game speed", Keyboard.KEY_NONE, Category.MISCELLANEOUS);
		this.addSettings(speed);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void onDisable() {
		mc.timer.tickLength = 50f;
		MinecraftForge.EVENT_BUS.unregister(this);
	}
	
	@SubscribeEvent
	public void onTick(final TickEvent.ClientTickEvent event) {
		mc.timer.tickLength = (float) (50f / (speed.getValue() / 10f));
	}
}