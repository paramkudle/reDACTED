package me.tastybulb.asylum.api.utils.font;

import me.tastybulb.asylum.api.utils.JColor;
import me.tastybulb.asylum.impl.Asylum;
import net.minecraft.client.Minecraft;

public class FontUtils {

	private static final Minecraft mc = Minecraft.getMinecraft();
	
	/**public static float drawStringWithShadow(boolean customFont, String text, int x, int y, JColor color) {
		if(customFont) {
			return Asylum.getInstance().customFontRenderer.drawStringWithShadow(text, x, y, color);
		}
		else {
			return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
		}
	}

	public static int getStringWidth(boolean customFont, String string) {
		if (customFont) {
			return Asylum.getInstance().customFontRenderer.getStringWidth(string);
		}
		else {
			return mc.fontRenderer.getStringWidth(string);
		}
	}

	public static int getFontHeight(boolean customFont) {
		if (customFont) {
			return Asylum.getInstance().customFontRenderer.getHeight();
		}
		else {
			return mc.fontRenderer.FONT_HEIGHT;
		}
	}*/

	public static float drawStringWithShadow(boolean customFont, String text, int x, int y, JColor color) {
		if(customFont) {
			return Asylum.customFontRenderer.drawStringWithShadow(text, x, y, color);
		}
		else {
			return mc.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
		}
	}

	public static int getStringWidth(boolean customFont, String string) {
		if (customFont) {
			return Asylum.customFontRenderer.getStringWidth(string);
		}
		else {
			return mc.fontRenderer.getStringWidth(string);
		}
	}

	public static int getFontHeight(boolean customFont) {
		if (customFont) {
			return Asylum.customFontRenderer.getHeight();
		}
		else {
			return mc.fontRenderer.FONT_HEIGHT;
		}
	}
}