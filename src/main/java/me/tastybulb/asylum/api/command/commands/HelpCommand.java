package me.tastybulb.asylum.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.tastybulb.asylum.api.command.Command;
import me.tastybulb.asylum.api.command.CommandManager;
import me.tastybulb.asylum.api.utils.MessageUtil;
import me.tastybulb.asylum.impl.Asylum;
import net.minecraft.util.text.TextFormatting;

public class HelpCommand extends Command {
    public static Vanish vanish;

    public HelpCommand() {
        super("help", "helps lol.", "help", "h");
    }

    TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    TextFormatting WHITE = TextFormatting.WHITE;
    TextFormatting GRAY = TextFormatting.GRAY;
    TextFormatting AQUA = TextFormatting.AQUA;
    TextFormatting BOLD = TextFormatting.BOLD;
    TextFormatting ITALIC = TextFormatting.ITALIC;
    TextFormatting RED = TextFormatting.RED;

    @Override
    public void onCommand(String[] args, String command) {
        String PREFIX = CommandManager.prefix;
        vanish = new Vanish();

        MessageUtil.sendClientPrefixMessage(ChatFormatting.GREEN + "-------------------");

        MessageUtil.sendClientPrefixMessage(ChatFormatting.BOLD + Asylum.name + " " + Asylum.version + "!");

        helpMessage(vanish.name, vanish.description, vanish.syntax);

        MessageUtil.sendClientPrefixMessage(ChatFormatting.GREEN + "-------------------");

    }

    private void helpMessage(String name, String desc, String syntax) {
        MessageUtil.sendClientPrefixMessage(WHITE + name + GRAY + " - " + desc + RED + ITALIC + " [ " + syntax + " ]");
    }

}
