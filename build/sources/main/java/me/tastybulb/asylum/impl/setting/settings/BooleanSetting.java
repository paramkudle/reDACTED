package me.tastybulb.asylum.impl.setting.settings;

import com.lukflug.panelstudio.settings.Toggleable;

import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.Setting;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class BooleanSetting extends Setting implements Toggleable {
	public boolean enabled;

	public BooleanSetting(String name, Module parent, boolean enabled) {
		this.name = name;
		this.parent = parent;
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;

		if(Asylum.saveLoadConfig != null) {
			Asylum.saveLoadConfig.save();
		}
	}

	public void toggle() {
		this.enabled = !this.enabled;

		if(Asylum.saveLoadConfig != null) {
			Asylum.saveLoadConfig.save();
		}
	}

	@Override
	public boolean isOn() {
		return this.isEnabled();
	}
}
