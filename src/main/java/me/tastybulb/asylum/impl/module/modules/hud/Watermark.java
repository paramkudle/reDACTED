package me.tastybulb.asylum.impl.module.modules.hud;

import java.awt.Color;
import java.awt.Point;
import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.tastybulb.asylum.api.utils.JColor;
import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.HudModule;
import me.tastybulb.asylum.impl.setting.settings.ColorSetting;


public class Watermark extends HudModule {
    public ColorSetting color = new ColorSetting("Color", this, new JColor(Asylum.ASYLUM_COLOR));

    public Watermark() {
        super("Watermark", "Asylum Watermark!", new Point(-2, 1), Category.HUD);
        this.addSettings(color);
    }

    @Override
    public void populate (Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new WatermarkList());
    }

    private class WatermarkList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            return ChatFormatting.WHITE + Asylum.name + " " + ChatFormatting.RESET + Asylum.version;
        }

        @Override
        public Color getItemColor(int index) {
            return color.getValue();
        }

        @Override
        public boolean sortUp() {
            return false;
        }

        @Override
        public boolean sortRight() {
            return false;
        }
    }
}
