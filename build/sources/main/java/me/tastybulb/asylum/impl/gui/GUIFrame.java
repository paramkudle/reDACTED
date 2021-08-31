/**package me.tastybulb.asylum.impl.gui;

import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.util.ArrayList;

public class GUIFrame {

    int x, y, width, height;

    Category category;
    Minecraft mc = Minecraft.getMinecraft();

    ArrayList<ModuleButton> moduleButtons;

    public GUIFrame(Category category, int x, int y){

        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 240;
        this.category = category;

        moduleButtons = new ArrayList<>();
        int offsetY = 0;
        for (Module module : ModuleManager.getModulesInCategory(category)){
            moduleButtons.add(new ModuleButton(module, x, y + offsetY, this));
            offsetY += 14;
        }

        this.height = offsetY;

    }

    public void render(int MouseX, int MouseY){
        mc.fontRenderer.drawString(category.toString(), x + 2, y + -5, new Color(255, 255, 255 ).getRGB());
        Gui.drawRect(x, y, x + width, y + height, new Color(0, 0, 0, 100).getRGB());
        for (ModuleButton moduleButton : moduleButtons){
            moduleButton.draw(MouseX, MouseY);
        }

    }

    public void onClick(int x, int y, int button){
        for (ModuleButton moduleButton : moduleButtons){
            moduleButton.onClick(x, y, button);
        }
    }

}
*/