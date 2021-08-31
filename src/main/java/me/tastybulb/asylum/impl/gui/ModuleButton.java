/**package me.tastybulb.asylum.impl.gui;

import me.tastybulb.asylum.impl.module.Module;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class ModuleButton {

    int x, y, width, height;

    Module module;

    GUIFrame parent;

    Minecraft mc = Minecraft.getMinecraft();

    public ModuleButton(Module module, int x, int y, GUIFrame parent){
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
    }

    public void draw(int MouseX, int MouseY){
        if (module.toggled) {
            mc.fontRenderer.drawString(module.getName(), x + 2, y + 2, new Color(255, 0, 0).getRGB());
        } else {
            mc.fontRenderer.drawString(module.getName(), x + 2, y + 2, new Color(154, 154, 154).getRGB());
        }
    }

    public void onClick(int x, int y, int button){
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height){
            module.toggle();
        }
    }

}
 */
