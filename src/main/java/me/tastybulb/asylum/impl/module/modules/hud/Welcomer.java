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
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;


public class Welcomer extends HudModule {
    public ColorSetting color = new ColorSetting("Color", this, new JColor(Asylum.ASYLUM_COLOR, 255));
    public ModeSetting mode = new ModeSetting("Mode", this, "Normal", "Normal", "Cool");

    public Welcomer() {
        super("Welcomer", "What do you think it does?", new Point(75, 70), Category.HUD);
        this.addSettings(color);
    }

    @Override
    public void populate (Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new WelcomerList());
    }

    private class WelcomerList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            if(mode.is("Normal")){
                return "Welcome to Asylum, " + ChatFormatting.WHITE + mc.player.getName() + ChatFormatting.RESET + "!";
            }
            else {
                return "Hey Handsome, Welcome to the Asylum " + ChatFormatting.RED + mc.player.getName() + ChatFormatting.RESET + "!";
            }

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
