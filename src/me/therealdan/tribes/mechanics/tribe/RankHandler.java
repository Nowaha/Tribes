package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Daniel on 24/08/2016.
 */
public class RankHandler {

    private HashMap<Player, String> owners = new HashMap<Player, String>();
    private HashMap<Player, String> admins = new HashMap<Player, String>();
    private HashMap<Player, String> members = new HashMap<Player, String>();
    private HashMap<Player, String> none = new HashMap<Player, String>();

    private enum Rank {
        OWNER, ADMIN, MEMBER, NONE
    }

    public void loadRanks() {
        for (Player player : Bukkit.getOnlinePlayers()) load(player);
    }

    public void load(Player player) {
        // Remove
        owners.remove(player);
        admins.remove(player);
        members.remove(player);
        none.remove(player);

        // Add
        if (Tribes.getInstance().getTribeHandler().hasTribe(player) && Tribes.getInstance().PlayerData.contains("Players." + player.getUniqueId() + ".Tribe.Rank")) {
            Rank rank = Rank.valueOf(Tribes.getInstance().PlayerData.getString("Players." + player.getUniqueId() + ".Tribe.Rank"));
            if (rank == Rank.OWNER) owners.put(player, rank.toString());
            if (rank == Rank.ADMIN) admins.put(player, rank.toString());
            if (rank == Rank.MEMBER) members.put(player, rank.toString());
            if (rank == Rank.NONE) none.put(player, rank.toString());
        } else {
            none.put(player, Rank.NONE.toString());
        }
    }

    public void setOwner(Player player) {
        set(player, Rank.OWNER);
        Tribes.getInstance().TribeData.set("Tribes." + Tribes.getInstance().getTribeHandler().getTribe(player) + ".Owner", player.getName());
        Tribes.getInstance().saveTribeData();
    }

    public void setAdmin(Player player) {
        set(player, Rank.ADMIN);
    }

    public void setMember(Player player) {
        set(player, Rank.MEMBER);
    }

    public void setNone(Player player) {
        set(player, Rank.NONE);
    }

    public void setNone(String playerUUID) {
        set(playerUUID, Rank.NONE);
    }

    private void set(Player player, Rank rank) {
        Tribes.getInstance().PlayerData.set("Players." + player.getUniqueId() + ".Tribe.Rank", rank.toString());
        Tribes.getInstance().savePlayerData();
        load(player);
    }

    private void set(String playerUUID, Rank rank) {
        Tribes.getInstance().PlayerData.set("Players." + playerUUID + ".Tribe.Rank", rank.toString());
        Tribes.getInstance().savePlayerData();
    }

    public boolean isOwner(Player player) {
        return owners.containsKey(player);
    }

    public boolean isAdminPlus(Player player) {
        return admins.containsKey(player) || isOwner(player);
    }

    public boolean isMemberPlus(Player player) {
        return members.containsKey(player) || isAdminPlus(player);
    }

    public boolean isAdmin(Player player) {
        return admins.containsKey(player);
    }

    public boolean isMember(Player player) {
        return members.containsKey(player);
    }

    public String getRank(Player player) {
        if (isOwner(player)) return "Owner";
        if (isAdmin(player)) return "Admin";
        return "Member";
    }
}