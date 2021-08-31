package me.tastybulb.asylum.impl.module.modules.Client;

import java.awt.Font;

import me.tastybulb.asylum.api.utils.font.CustomFontRenderer;
import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

public class ClientFont extends Module {
    public ModeSetting font = new ModeSetting("font", this, "Comic Sans Ms", "Comic Sans Ms", "Arial", "Verdana");

    public ClientFont() {
        super ("clientFont", "changes the font the client uses.", Keyboard.KEY_NONE, Category.CLIENT);
        this.addSettings(font);
    }

    @Override
    public void onEnable() {
        if(font.is("Comic Sans Ms")) {
            Asylum.customFontRenderer = new CustomFontRenderer(new Font("Comic Sans MS", Font.PLAIN, 18), true, true);
        }

        if(font.is("Arial")) {
            Asylum.customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 18), true, true);
        }

        if(font.is("Verdana")) {
            Asylum.customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true);
        }
    }
}
