package me.tastybulb.asylum.api.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.tastybulb.asylum.api.command.Command;
import me.tastybulb.asylum.api.command.CommandManager;
import me.tastybulb.asylum.api.utils.MessageUtil;
import me.tastybulb.asylum.impl.module.Module;
import me.tastybulb.asylum.impl.module.ModuleManager;

public class Toggle extends Command {

    public Toggle() {
        super("toggle", "toggles a module on or off.", "toggle <module>", "t");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 0) {
            String moduleName = args[0];
            boolean moduleFound = false;
            for(Module module : ModuleManager.modules) {
                if(module.name.equalsIgnoreCase(moduleName)) {
                    module.toggle();
                    MessageUtil.sendClientPrefixMessage(module.name + " " + (module.isToggled() ? ChatFormatting.GREEN + "Enabled" + ChatFormatting.GRAY + "." : ChatFormatting.RED + "disabled" + ChatFormatting.GRAY + "."));
                    moduleFound = true;
                    break;
                }
            }
            if(!moduleFound) {
                MessageUtil.sendClientPrefixMessage(ChatFormatting.DARK_RED + "Module not found.");
            }
        }else CommandManager.correctUsageMsg(getName(), getSyntax());
    }

}
