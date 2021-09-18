package me.tastybulb.asylum.api.config;

import me.tastybulb.asylum.impl.Asylum;

import java.io.IOException;

public class ConfigStopper extends Thread {

    @Override
    public void run() {
        saveConfig();
    }

    public static void saveConfig() {
        try {
            Asylum.clickGuiSave.clickGuiSave();
            Asylum.clickGuiSave.saveClickGUIPositions();
            Asylum.log.info("saved config.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
