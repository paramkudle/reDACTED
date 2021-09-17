package me.tastybulb.asylum.impl.setting.settings;

import java.util.Arrays;
import java.util.List;

import com.lukflug.panelstudio.settings.EnumSetting;
import me.tastybulb.asylum.impl.Asylum;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.setting.Setting;
import java.util.Arrays;

public class ModeSetting extends Setting implements EnumSetting {

	public int index;

	public List<String> modes;

	public ModeSetting(String name, Module parent, String defaultMode, String... modes) {
		this.name = name;
		this.parent = parent;
		this.modes = Arrays.asList(modes);
		this.index = this.modes.indexOf(defaultMode);
	}

	public String getMode() {
		return this.modes.get(this.index);
	}

	public void setMode(String mode) {
		this.index = this.modes.indexOf(mode);

		if(Asylum.saveLoadConfig != null) {
			Asylum.saveLoadConfig.save();
		}
	}

	public boolean is(String mode) {
		return (this.index == this.modes.indexOf(mode));
	}

	public void cycle() {
		if (this.index < this.modes.size() - 1) {
			this.index++;
		}else {
			this.index = 0;
		}

		if(Asylum.saveLoadConfig != null) {
			Asylum.saveLoadConfig.save();
		}
	}

	@Override
	public String getValueName() {
		return this.modes.get(this.index);
	}

	@Override
	public void increment() {
		if (this.index < this.modes.size() - 1) {
			this.index++;
		}else {
			this.index = 0;
		}
	}
}