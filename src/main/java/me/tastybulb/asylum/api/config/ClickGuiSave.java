package me.tastybulb.asylum.api.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.ui.ClickGuiConfig;

public class ClickGuiSave {

    public ClickGuiSave() {
        try {
            clickGuiSave();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String fileName = "asylum/";
    String mainName = "clickGui/";

    public void clickGuiSave() throws IOException {
        if (!Files.exists(Paths.get(fileName))) {
            Files.createDirectories(Paths.get(fileName));
        }
        if (!Files.exists(Paths.get(fileName + mainName))) {
            Files.createDirectories(Paths.get(fileName + mainName));
        }
    }

    public void registerFiles(String location, String name) throws IOException {
        if (!Files.exists(Paths.get(fileName + location + name + ".json"))) {
            Files.createFile(Paths.get(fileName + location + name + ".json"));
        }
        else {
            File file = new File(fileName + location + name + ".json");

            file.delete();

            Files.createFile(Paths.get(fileName + location +name + ".json"));
        }
    }

    public void saveClickGUIPositions() throws IOException {
        registerFiles(mainName, "ClickGUI");
        Asylum.clickGui.gui.saveConfig(new ClickGuiConfig(fileName+mainName));
    }
}
