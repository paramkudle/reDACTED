package me.tastybulb.asylum.impl.ui;

import me.tastybulb.asylum.api.utils.JColor;
import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Category;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.module.ModuleManager;
import me.tastybulb.asylum.impl.setting.settings.BooleanSetting;
import me.tastybulb.asylum.impl.setting.settings.ModeSetting;
import me.tastybulb.asylum.impl.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;
import me.tastybulb.asylum.impl.setting.settings.ColorSetting;
import me.zero.alpine.listener.EventHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickGuiModule extends Module {
    public static ClickGuiModule INSTANCE;

    public ModeSetting theme = new ModeSetting("theme", this, "new", "new", "old");
    public BooleanSetting blur = new BooleanSetting("blur", this, true);
    public NumberSetting animationSpeed = new NumberSetting("animation", this, 150, 0, 1000, 50);
    public NumberSetting scrolls = new NumberSetting("scrollSpeed", this, 10, 0, 100, 1);
    public ModeSetting scrollMode = new ModeSetting("scroll", this, "container", "container", "screen");
    public ModeSetting description = new ModeSetting("description", this, "mouse", "mouse", "fixed");
    public ColorSetting enabledColor = new ColorSetting("enabledColor", this, new JColor(Asylum.ASYLUM_COLOR, 255)); //(0, 157, 255, 255));
    public ColorSetting backgroundColor = new ColorSetting("bgColor", this, new JColor(0, 0, 0, 200)); //(0, 121, 194, 255));
    public ColorSetting settingBackgroundColor = new ColorSetting("settinBgColor", this, new JColor(0, 0, 0, 255));
    public ColorSetting outlineColor = new ColorSetting("settingsHighlight", this, new JColor(255, 255, 255, 255));
    public ColorSetting fontColor = new ColorSetting("categoryColor", this, new JColor(Asylum.ASYLUM_COLOR, 255));
    public NumberSetting opacity = new NumberSetting("opacity", this, 255, 0, 255, 5);

    public ClickGuiModule() {
        super("ClickGUI", "ClickGUI", Keyboard.KEY_P, Category.CLIENT);
        this.addSettings(blur, scrollMode, scrolls, description, animationSpeed);
        INSTANCE = this;
    }
    private ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/blur" + ".json");

    public static Module getClickGuiModule() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        Asylum.clickGui.enterGUI();
        if(blur.isEnabled())
            mc.entityRenderer.loadShader(shader);
    }

    @Override
    public void onDisable() {
        mc.entityRenderer.getShaderGroup().deleteShaderGroup();
    }

    @Override
    public void onUpdate() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            this.disable();
        }
        if(ModuleManager.getModuleByName("hudEditor").isToggled()) {
            this.disable();
        }

    }

    private final ResourceLocation watermark = new ResourceLocation(Asylum.modid, "asylum/logo.png");
    @Override
    public void onRender() {
        ScaledResolution sr = new ScaledResolution(mc);
        mc.renderEngine.bindTexture(watermark);
        Gui.drawScaledCustomSizeModalRect(0, sr.getScaledHeight() - 10, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
