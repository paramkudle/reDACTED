package me.tastybulb.asylum.impl.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.hud.HUDList;
import com.lukflug.panelstudio.hud.ListComponent;
import com.lukflug.panelstudio.theme.Theme;
import com.mojang.realmsclient.gui.ChatFormatting;

import me.tastybulb.asylum.api.utils.JColor;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.HudModule;
import me.tastybulb.asylum.impl.setting.settings.BooleanSetting;
import me.tastybulb.asylum.impl.setting.settings.ColorSetting;

public class NetherCoords extends HudModule {

    public ColorSetting color = new ColorSetting("Color", this, new JColor(255, 72, 72, 255));
    public BooleanSetting sort = new BooleanSetting("SortRight", this, false);

    public NetherCoords() {
        super("NetherCoords", "Coords on the HUD??!? But Nether?!??!", new Point(122, 50), Category.HUD);
        this.addSettings(sort, color);
    }

    @Override
    public void populate(Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new NetherCoordsList());
    }

    private class NetherCoordsList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            if (mc.player.dimension == -1) {
                return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posX)
                        + ChatFormatting.RESET + "(y)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posY)
                        + ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posZ);
            }
            return ChatFormatting.RESET + "(x)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posX / 8f)
                    + ChatFormatting.RESET + "(y)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posY)
                    + ChatFormatting.RESET + "(z)" + ChatFormatting.WHITE + String.format("%.1f", mc.player.posZ / 8f);
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
            return sort.isEnabled();
        }
    }

}
