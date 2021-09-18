package me.tastybulb.asylum.impl.module.modules.player;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import org.lwjgl.input.Keyboard;


public class AutoRespawn extends Module {

    public AutoRespawn() {
        super("autoRespawn", "automatically respawns after death occurs.", Keyboard.KEY_NONE, Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        if(mc.player.isDead) {
            mc.player.respawnPlayer();
        }
    }

}
