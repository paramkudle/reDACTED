package me.tastybulb.asylum.impl.module.modules.hud;

import java.awt.Color;
import java.awt.Point;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Interface;
import com.lukflug.panelstudio.hud.HUDComponent;
import com.lukflug.panelstudio.theme.Theme;

import me.tastybulb.asylum.api.utils.JColor;
import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.HudModule;
import me.tastybulb.asylum.impl.setting.settings.ColorSetting;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import me.tastybulb.asylum.impl.ui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Inventory extends HudModule {
    public ColorSetting color = new ColorSetting("Color", this, new JColor(Asylum.ASYLUM_COLOR, 100));
    public ModeSetting sizee = new ModeSetting("Size", this, "Normal", "Normal", "Compact");

    public Inventory() {
        super("InventoryHUD","Sticks your inventory on your HUD", new Point(122, 2), Category.HUD);
        this.addSettings(color, sizee);
    }

    @Override
    public void populate (Theme theme) {
        component = new InventoryViewerComponent(theme);
    }

    private class InventoryViewerComponent extends HUDComponent {

        public InventoryViewerComponent (Theme theme) {
            super(getName(), theme.getPanelRenderer(), Inventory.this.position);
        }

        @Override
        public void render (Context context) {

            super.render(context);
            Color bgcolor = new JColor(color.getValue());
            context.getInterface().fillRect(context.getRect(), bgcolor, bgcolor, bgcolor, bgcolor);

            NonNullList<ItemStack> items = Minecraft.getMinecraft().player.inventory.mainInventory;
            for (int size = items.size(), item = 9; item < size; ++item) {
                if(sizee.is("Normal")) {
                    int slotX = context.getPos().x + 1 + item % 9 * 18;
                    int slotY = context.getPos().y + 1 + (item / 9 - 1) * 18;
                    ClickGui.renderItem(items.get(item), new Point(slotX,slotY));
                }else {
                    int slotX = context.getPos().x + 1 + item % 9 * 17;
                    int slotY = context.getPos().y + 1 + (item / 9 - 1) * 17;
                    ClickGui.renderItem(items.get(item), new Point(slotX,slotY));
                }
            }
        }

        @Override
        public int getWidth (Interface inter) {
            if(sizee.is("Normal")) {
                return 162;
            }else {
                return 154;
            }
        }

        @Override
        public void getHeight (Context context) {
            if(sizee.is("Normal")) {
                context.setHeight(54);
            }else {
                context.setHeight(52);
            }
        }
    }
}
