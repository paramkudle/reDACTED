package me.tastybulb.asylum.impl.module.modules.client;

import java.util.ArrayList;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import org.lwjgl.input.Keyboard;

import net.minecraft.util.text.TextFormatting;

public class ColorMain extends Module {

    private static final Module ColorMain = null;
    public static ModeSetting colorModel = new ModeSetting("right?", ColorMain, "RGB", "RGB", "HSB");

    public ColorMain() {
        super ("colorMain", "world of colors", Keyboard.KEY_NONE, Category.CLIENT);
        this.addSettings(colorModel);
    }

    public void setup() {
        ArrayList<String> tab = new ArrayList<>();
        tab.add("Black");
        tab.add("Dark Green");
        tab.add("Dark Red");
        tab.add("Gold");
        tab.add("Dark Gray");
        tab.add("Green");
        tab.add("Red");
        tab.add("Yellow");
        tab.add("Dark Blue");
        tab.add("Dark Aqua");
        tab.add("Dark Purple");
        tab.add("Gray");
        tab.add("Blue");
        tab.add("Aqua");
        tab.add("Light Purple");
        tab.add("White");
        ArrayList<String> models=new ArrayList<>();
        models.add("RGB");
        models.add("HSB");
    }

    @Override
    public void onEnable() {
        this.disable();
    }

    private static TextFormatting settingToFormatting () {
        return TextFormatting.AQUA;
    }

    public static TextFormatting getEnabledColor() { return settingToFormatting(); }

    public static TextFormatting getDisabledColor() { return settingToFormatting(); }

}
