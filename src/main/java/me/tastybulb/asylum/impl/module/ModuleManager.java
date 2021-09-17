package me.tastybulb.asylum.impl.module;

import java.util.ArrayList;
import java.util.stream.Collectors;

import me.tastybulb.asylum.impl.module.modules.combat.*;
import me.tastybulb.asylum.impl.module.modules.miscellaneous.*;
import me.tastybulb.asylum.impl.Asylum;
//import me.tastybulb.asylum.impl.module.modules.Client.ClientFont;
import me.tastybulb.asylum.impl.module.modules.movement.*;
import me.tastybulb.asylum.impl.module.modules.player.AutoRespawn;
import me.tastybulb.asylum.impl.module.modules.render.*;
import me.tastybulb.asylum.impl.module.modules.client.*;
import me.tastybulb.asylum.impl.ui.ClickGuiModule;
import me.tastybulb.asylum.impl.ui.HudEditor;
import org.lwjgl.input.Keyboard;

import me.tastybulb.asylum.api.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ModuleManager {
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
		
		// Player
		modules.add(new AutoRespawn());

		// Render
		modules.add(new Fullbright());
		
		// Combat
		modules.add(new KillAura());
		modules.add(new AutoCrystal());
		modules.add(new AutoCope());
		modules.add(new AutoTotem());
		modules.add(new SmartOffHand());
		
		// Miscellaneous
		modules.add(new Timer());
		
		// Movement
		modules.add(new Sprint());
		modules.add(new Strafe());
		modules.add(new Velocity());
		
		// Client
		modules.add(new ClickGuiModule());
		modules.add(new HudEditor());
		modules.add(new ClientFont());
		
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
	public static void onRender() {
		modules.stream().filter(Module::isToggled).forEach(Module::onRender);
		Asylum.clickGui.render();
	}
	
	public static void onWorldRender(RenderWorldLastEvent event) {
		Minecraft.getMinecraft().mcProfiler.startSection("asylum");
		Minecraft.getMinecraft().mcProfiler.startSection("setup");
		RenderUtil.prepare();
		Minecraft.getMinecraft().mcProfiler.endSection();

		modules.stream().filter(module -> module.isToggled()).forEach(module -> {
			Minecraft.getMinecraft().mcProfiler.startSection(module.getName());
			//module.onWorldRender(e);
			Minecraft.getMinecraft().mcProfiler.endSection();
		});

		Minecraft.getMinecraft().mcProfiler.startSection("release");
		RenderUtil.release();
		Minecraft.getMinecraft().mcProfiler.endSection();
		Minecraft.getMinecraft().mcProfiler.endSection();
	}
	
	public static boolean isModuleEnabled(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m.isToggled();
	}

	public Module getModule (String name) {
		for (Module m : ModuleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static ArrayList<Module> getModuleList() {
		return ModuleManager.modules;
	}
	
	public static ArrayList<Module> getModulesInCategory(Category c){
		ArrayList<Module> list = (ArrayList<Module>) getModuleList().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
		return list;
	}
	
	public static Module getModuleByName(String name) {
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m;
	}
	
	@SubscribeEvent
	public void key(KeyInputEvent e) {
		if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
			return;
		try {
			if(Keyboard.isCreated()) {
				if(Keyboard.getEventKeyState()) {
					int keyCode = Keyboard.getEventKey();
					if(keyCode <= 0)
						return;
					for(Module m : ModuleManager.modules) {
						if(m.getKey() == keyCode && keyCode > 0) {
							m.toggle();
						}
					}
				}
			}
		} catch (Exception q) { q.printStackTrace(); }
	}

}
