package me.tastybulb.asylum.impl.module.modules.movement;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

public class AutoWalk extends Module {

    public AutoWalk() {
        super ("AutoWalk", "Walks automatically", Keyboard.KEY_NONE, Category.MOVEMENT);
    }

    @Override
    public void onDisable() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
    }

    @Override
    public void onUpdate() {
        if(mc.currentScreen == null) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
        }
    }

}
