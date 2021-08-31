/**package me.tastybulb.asylum.impl.module.modules.Client;

import me.tastybulb.asylum.impl.gui.ClickGUIScreen;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {
    public ClickGUI(){
        super("ClickGUI", "GUI", Keyboard.KEY_P, Category.CLIENT);
    }

    @Override
    public void onEnable(){
        super.onEnable();
        mc.displayGuiScreen(ClickGUIScreen.INSTANCE);
    }
}
*/