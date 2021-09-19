package me.tastybulb.asylum.impl.setting.settings;

import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.Setting;
import org.lwjgl.input.Keyboard;

import com.lukflug.panelstudio.theme.Renderer;


public class KeybindSetting extends Setting implements com.lukflug.panelstudio.settings.KeybindSetting {

	public int code;

	public KeybindSetting(int code) {
		this.name = "KeyBind";
		this.code = code;
	}

	public KeybindSetting(Renderer componentRenderer, Module module) {
		// TODO Auto-generated constructor stub
	}

	public int getKeyCode() {
		return this.code;
	}

	public void setKeyCode(int code) {
		this.code = code;
	}

	@Override
	public int getKey() {
		return code;
	}

	@Override
	public String getKeyName() {
		return Keyboard.getKeyName(code);
	}

	@Override
	public void setKey(int key) {
		code=key;
	}

}
