package me.therealdan.tribes.util;

import me.therealdan.tribes.Tribes;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 24/08/2016.
 */
public class Permissions {

    private boolean allCommands(Player player) {
        return player.hasPermission("tribes.commands.*");
    }

    public boolean commandTribe(Player player) {
        return allCommands(player) || player.hasPermission("tribes.command.tribe") || !Tribes.getInstance().configuration.requiresPermissionTribe;
    }

    public boolean commandRelic(Player player) {
        return allCommands(player) || player.hasPermission("tribes.command.relic");
    }

    public boolean commandCore(Player player) {
        return allCommands(player) || player.hasPermission("tribes.command.core");
    }

    public boolean commandWeapons(Player player) {
        return allCommands(player) || player.hasPermission("tribes.command.weapons");
    }

    public boolean commandFly(Player player) {
        return allCommands(player) || player.hasPermission("tribes.command.fly");
    }

    public boolean commandToggleExplosions(Player player) {
        return allCommands(player) || player.hasPermission("tribes.commands.toggleexplosions");
    }
}