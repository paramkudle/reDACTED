package me.tastybulb.asylum.impl.setting;

import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.module.ModuleManager;

import java.util.ArrayList;

public class SettingManager {

    private ArrayList<Setting> settings;

    public SettingManager(){
        this.settings = new ArrayList<Setting>();
    }

    public void rSetting(Setting in){
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<Setting>();
        for(Setting s : getSettings()) {
            if(s.parent.equals(mod)) {
                out.add(s);
            }
        }
        if(out.isEmpty()) {
            return null;
        }
        return out;
    }

    public Setting getSettingByName(Module mod, String name) {
        for (Module m : ModuleManager.modules) {
            for (Setting set : m.settings) {
                if (set.name.equalsIgnoreCase(name) && set.parent == mod) {
                    return set;
                }
            }
        }
        System.err.println("[Asylum] Error Setting NOT found: '" + name +"'!");
        return null;
    }
}
