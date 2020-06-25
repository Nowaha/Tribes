package me.therealdan.tribes.commands;

import me.therealdan.tribes.Tribes;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 24/08/2016.
 */
public class _CommandsHandler implements CommandExecutor {

    private TribeCommand tribe = new TribeCommand();

    private String noFlyMessage;

    public _CommandsHandler() {
        noFlyMessage = ChatColor.translateAlternateColorCodes('&', Tribes.getInstance().getConfig().getString("No_Fly_Permission_Message"));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.equalsIgnoreCase("T") || command.equalsIgnoreCase("TribeCommand") || command.equalsIgnoreCase("Tribes")) {
                if (Tribes.getInstance().permissions.commandTribe(player))
                    tribe.command(player, args);
            }
        }
        return true;
    }
}