package me.tastybulb.asylum.api.command.commands;

import me.tastybulb.asylum.api.command.Command;
import me.tastybulb.asylum.api.command.CommandManager;
import me.tastybulb.asylum.api.utils.MessageUtil;
import me.tastybulb.asylum.impl.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Vanish extends Command {
    private static Entity ridden;

    public Vanish() {
        super("vanish", "vanish ridden entities.", "vanish", "v");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length == 0) {
            if (Minecraft.getMinecraft().player.getRidingEntity() != null && ridden == null) {
                ridden = Minecraft.getMinecraft().player.getRidingEntity();

                Minecraft.getMinecraft().player.dismountRidingEntity();
                Minecraft.getMinecraft().world.removeEntityFromWorld(ridden.getEntityId());
                MessageUtil.sendClientPrefixMessage("entity " + ridden.getName() + " removed.");
            }else {
                if (ridden != null) {
                    ridden.isDead = false;

                    Minecraft.getMinecraft().world.addEntityToWorld(ridden.getEntityId(), ridden);
                    Minecraft.getMinecraft().player.startRiding(ridden, true);
                    MessageUtil.sendClientPrefixMessage("entity " + ridden.getName() + " created.");
                    ridden = null;
                }else {
                    MessageUtil.sendClientPrefixMessage("no entity is being ridden");
                }
            }
        }else CommandManager.correctUsageMsg(getName(), getSyntax());
    }
}
