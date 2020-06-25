package me.therealdan.tribes.util;

import me.therealdan.tribes.Tribes;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Daniel on 4/10/2016.
 */
public class UUIDHolder {

    public void save(Player player) {
        Tribes.getInstance().UUIDData.set("Player." + player.getName(), player.getUniqueId().toString());
        Tribes.getInstance().UUIDData.set("Player." + player.getUniqueId(), player.getName());
        Tribes.getInstance().saveUUIDData();
    }

    public UUID getUUID(String playerName) {
        return UUID.fromString(Tribes.getInstance().UUIDData.getString("Player." + playerName));
    }

    public String getName(String playerUUID) {
        return Tribes.getInstance().UUIDData.getString("Player." + playerUUID);
    }
}