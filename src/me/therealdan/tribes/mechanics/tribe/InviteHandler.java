package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 9/09/2016.
 */
public class InviteHandler {

    public void sendInvite(Player player, String tribe) {
        List<String> invites = getInvites(player);
        invites.add(tribe);
        Tribes.getInstance().PlayerData.set("Players." + player.getUniqueId() + ".Invites", invites);
        Tribes.getInstance().savePlayerData();
    }

    public void clearInvites(Player player) {
        Tribes.getInstance().PlayerData.set("Players." + player.getUniqueId() + ".Invites", null);
        Tribes.getInstance().savePlayerData();
    }

    public boolean hasInviteFrom(Player player, String tribe) {
        return getInvites(player).contains(tribe);
    }

    private List<String> getInvites(Player player) {
        try {
            return Tribes.getInstance().PlayerData.getStringList("Players." + player.getUniqueId() + ".Invites");
        } catch (Exception e) {
            //
        }
        return new ArrayList<String>();
    }
}