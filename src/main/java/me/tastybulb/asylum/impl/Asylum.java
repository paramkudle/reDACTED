package me.tastybulb.asylum.impl;

import me.tastybulb.asylum.api.command.CommandManager;
import me.tastybulb.asylum.api.config.ClickGuiLoad;
import me.tastybulb.asylum.api.config.ClickGuiSave;
import me.tastybulb.asylum.api.config.ConfigStopper;
import me.tastybulb.asylum.api.config.SaveLoadConfig;
import me.tastybulb.asylum.api.event.EventProcessor;
import me.tastybulb.asylum.api.friend.FriendManager;
import me.tastybulb.asylum.api.utils.JColor;
import me.tastybulb.asylum.impl.setting.SettingManager;
import me.tastybulb.asylum.impl.ui.ClickGui;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;

import java.awt.Font;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.tastybulb.asylum.api.utils.font.CustomFontRenderer;
import me.tastybulb.asylum.impl.module.ModuleManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;
//import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
//import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Asylum.modid, name = Asylum.name, version = Asylum.version)
public class Asylum {
	public static final String modid = "asylum";
	public static final String name = "Asylum";
	public static final String version = "2.1";

	//public static final String acceptedVersions = "[1.12.2]";
	//public static final String clientProxyClass = "me.tastybulb.asylum.api.proxy.ClientProxy";
	//public static final String commonProxyClass = "me.tastybulb.asylum.api.proxy.CommonProxy";

	public static final String acceptedVersions = "[1.12.2]";

	public static ClickGui clickGui;
	public static final JColor ASYLUM_COLOR = new JColor(137, 72, 255, 100);

	public static final Logger log = LogManager.getLogger("asylum");
	public static final EventBus EVENT_BUS = new EventManager();

	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static SaveLoadConfig saveLoadConfig;
	public static CustomFontRenderer customFontRenderer;
	public static CommandManager commandManager;
	public EventProcessor eventProcessor;
	public static FriendManager friendManager;
	public static ClickGuiSave clickGuiSave;
	public static ClickGuiLoad clickGuiLoad;
	
	@EventHandler
	public void Init (FMLInitializationEvent event) {
		Display.setTitle(name + " " + version);
		eventProcessor = new EventProcessor();
		eventProcessor.init();
		log.info("Asylum Event System Initialized!");

		MinecraftForge.EVENT_BUS.register(this);
		log.info("Forge Event System Initialized!");

		customFontRenderer = new CustomFontRenderer(new Font("Verdana", Font.PLAIN, 18), true,true);
		log.info("Custom Font Initialized!");

		settingManager = new SettingManager();
		log.info("Setting Manager Initialized!");

		commandManager = new CommandManager();
		//CommandManager.init();
		log.info("Command Manager Initialized!");

		friendManager = new FriendManager();
		log.info("Friend Manager Started");

		MinecraftForge.EVENT_BUS.register(new ModuleManager());
		moduleManager = new ModuleManager();
		log.info("Module Manager Initialized!");

		clickGui = new ClickGui();


		clickGuiSave = new ClickGuiSave();
		clickGuiLoad = new ClickGuiLoad();
		Runtime.getRuntime().addShutdownHook(new ConfigStopper());
		log.info("configs initialized.");

		saveLoadConfig = new SaveLoadConfig();
		log.info("Config Initialized!");

		log.info("Asylum Finished Initialization");

		



	}
}
