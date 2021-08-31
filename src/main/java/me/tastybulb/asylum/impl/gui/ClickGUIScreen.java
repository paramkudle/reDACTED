/**package me.tastybulb.asylum.impl.gui;

import java.io.IOException;
import java.util.ArrayList;
import me.tastybulb.asylum.impl.module.Category;

import net.minecraft.client.gui.GuiScreen;

public class ClickGUIScreen extends GuiScreen {

    public static ClickGUIScreen INSTANCE = new ClickGUIScreen();

    ArrayList<GUIFrame> frames;

    public ClickGUIScreen(){

        frames = new ArrayList<>();
        int offset = 0;
        for (Category category : Category.values()){
            frames.add(new GUIFrame(category, 10 + offset, 20));
            offset += 110;
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (GUIFrame frame : frames){
            frame.render(mouseX, mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (GUIFrame frame : frames){
            frame.onClick(mouseX, mouseY, mouseButton);
        }
    }

}
*/