package me.tastybulb.asylum.api.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.text.TextComponentString;

public class MessageUtil {

    public static String watermark = ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Asylum" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET;
    public static ChatFormatting messageFormatting = ChatFormatting.GRAY;

    protected static final Minecraft mc = Minecraft.getMinecraft();

    /**
     * Sends a client-sided message With the client prefix
     **/
    public static void sendClientPrefixMessage(String message) {
        TextComponentString string1 = new TextComponentString(watermark + messageFormatting + message);
        mc.player.sendMessage(string1);
    }

    /**
     * Command Message
     **/
    public static void sendCommandMessage(String message, boolean prefix) {
        String watermark1 = prefix ? watermark : "";
        TextComponentString string = new TextComponentString(watermark1 + messageFormatting + message);

        mc.player.sendMessage(string);
    }

    /**
     * @Unused Sends a client-sided message WITHOUT the client prefix
     **/
    public static void sendClientRawMessage(String message) {
        TextComponentString string = new TextComponentString(messageFormatting + message);
        mc.player.sendMessage(string);
    }

    /**
     * Sends a server-sided message
     **/
    public static void sendServerMessage(String message) {
        mc.player.connection.sendPacket(new CPacketChatMessage(message));
    }
}
