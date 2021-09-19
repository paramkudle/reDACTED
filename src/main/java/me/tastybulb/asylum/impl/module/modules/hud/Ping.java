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


public class Ping extends HudModule {
    public ColorSetting color = new ColorSetting("Color", this, new JColor(230, 0, 0, 255));
    public BooleanSetting sort = new BooleanSetting("SortRight", this, false);

    public Ping() {
        super("Ping", "Pong?", new Point(-2,19), Category.HUD);
        this.addSettings(sort, color);
    }

    @Override
    public void populate (Theme theme) {
        component = new ListComponent(getName(), theme.getPanelRenderer(), position, new PingList());
    }

    private static int getPing () {
        int p = -1;
        if (mc.player == null || mc.getConnection() == null || mc.getConnection().getPlayerInfo(mc.player.getName()) == null) {
            p = -1;
        }
        else {
            p = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
        }
        return p;
    }

    private class PingList implements HUDList {

        @Override
        public int getSize() {
            return 1;
        }

        @Override
        public String getItem(int index) {
            if(getPing() >= 200) return "ping " + getPing();
            else return ChatFormatting.WHITE + "ping " + getPing();
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
