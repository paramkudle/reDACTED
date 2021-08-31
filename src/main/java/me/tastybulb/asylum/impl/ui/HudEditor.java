package me.tastybulb.asylum.impl.ui;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.settings.BooleanSetting;
import org.lwjgl.input.Keyboard;
import net.minecraft.util.ResourceLocation;

public class HudEditor extends Module {
    public BooleanSetting exitToClickGui = new BooleanSetting("exitToClickGui", this, true);

    public HudEditor() {
        super("hudEditor", "descrp", Keyboard.KEY_NONE, Category.CLIENT);
        this.addSettings(exitToClickGui);
    }
    private ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");

    @Override
    public void onEnable() {
        Asylum.clickGui.enterHUDEditor();
        if(ClickGuiModule.INSTANCE.blur.isEnabled())
            mc.entityRenderer.loadShader(shader);
    }

    @Override
    public void onUpdate() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if(exitToClickGui.isEnabled()) {
                this.disable();
                Asylum.clickGui.enterGUI();
            }else {
                this.disable();
            }
        }

    }

    @Override
    public void onDisable() {
        mc.entityRenderer.getShaderGroup().deleteShaderGroup();
    }
}
