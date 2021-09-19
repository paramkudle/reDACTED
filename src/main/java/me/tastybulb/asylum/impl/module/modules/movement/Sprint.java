package me.tastybulb.asylum.impl.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", "Always sprint", Keyboard.KEY_R, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		if(mc.player.movementInput.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally) {
			mc.player.setSprinting(true);
		}
	}
}