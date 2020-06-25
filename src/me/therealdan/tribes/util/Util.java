package me.therealdan.tribes.util;

import me.therealdan.tribes.Tribes;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Util {

    public void titleBar(Player player, String top, String sub, int fadeIn, int stay, int fadeOut) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + sub + "\"" + "}")));
        entityPlayer.playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + top + "\"" + "}")));
    }

    public void starttimer(final Player player, ItemStack itemStack, String format, long timer) {
        long max = timer;
        while (timer > 0) {
            format = Tribes.getInstance().util.getMessage(format);
            display(player, itemStack, format, timer, max);
            timer--;
        }
    }

    private void display(final Player player, final ItemStack itemStack, final String format, final long timer, final long max) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Tribes.getInstance(), new Runnable() {
            public void run() {
                if (player.getItemInHand().isSimilar(itemStack)) {
                    actionBar(player, format.replace("{TIME}", getTime(max - timer + 1)));
                }
            }
        }, (timer - 1) * 20);
    }

    public void actionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"), ChatMessageType.CHAT);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static String getMessage(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public String getTime(long time) {
        return getTime((int) time);
    }

    public String getTime(int time) {
        String seconds = Integer.toString(time % 60);
        String minutes = Integer.toString((time / 60) % 60);
        String hours = Integer.toString((time / 60) / 60);

        seconds = (seconds.length() > 1) ? seconds : "0" + seconds;
        minutes = (minutes.length() > 1) ? minutes : "0" + minutes;
        hours = (hours.length() > 1) ? hours : "0" + hours;

        return hours + ":" + minutes + ":" + seconds;
    }

    public static String getWXYZ(Location location) {
        return location.getWorld().getName() + ";" +
                location.getBlockX() + ";" +
                location.getBlockY() + ";" +
                location.getBlockZ();
    }

    public Location getWXYZ(String wxyz) {
        String[] args = wxyz.split(";");
        return new Location(
                Bukkit.getWorld(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        );
    }

    public boolean isNumber(String string) {
        boolean isNumber = true;
        for (String letter : string.split("")) {
            if (!"1234567890".contains(letter)) {
                isNumber = false;
            }
        }
        return isNumber;
    }
}