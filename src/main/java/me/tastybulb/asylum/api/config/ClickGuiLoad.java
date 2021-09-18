package me.tastybulb.asylum.api.config;

import java.io.IOException;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.ui.ClickGuiConfig;

public class ClickGuiLoad {

    public ClickGuiLoad() {
        try {
            clickGuiLoad();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    String fileName = "postman/";
    String mainName = "clickGui/";

    public void clickGuiLoad() throws IOException {
        loadClickGUIPositions();
    }

    public void loadClickGUIPositions() throws IOException {
        Asylum.clickGui.gui.loadConfig(new ClickGuiConfig(fileName+mainName));
    }
}
