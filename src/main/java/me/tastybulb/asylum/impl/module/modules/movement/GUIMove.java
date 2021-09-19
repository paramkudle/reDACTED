package me.tastybulb.asylum.impl.module.modules.movement;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

public class GUIMove extends Module {

    public GUIMove() {
        super ("GUIMove", "Let's you move while a GUI is open", Keyboard.KEY_NONE, Category.MOVEMENT);
    }

    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onUpdate(){
        if (mc.currentScreen != null){
            if (!(mc.currentScreen instanceof GuiChat)){
                if (Keyboard.isKeyDown(200)){
                    mc.player.rotationPitch -= 5;
                }
                if (Keyboard.isKeyDown(208)){
                    mc.player.rotationPitch += 5;
                }
                if (Keyboard.isKeyDown(205)){
                    mc.player.rotationYaw += 5;
                }
                if (Keyboard.isKeyDown(203)){
                    mc.player.rotationYaw -= 5;
                }
                if (mc.player.rotationPitch > 90){
                    mc.player.rotationPitch = 90;
                }
                if (mc.player.rotationPitch < -90){
                    mc.player.rotationPitch = -90;
                }
            }
        }
    }
}
