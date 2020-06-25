package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import me.therealdan.tribes.util.Util;
import me.therealdan.tribes.util.Vault;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 24/08/2016.
 */
public class _TribeHandler {

    public ColorHandler colorhandler = new ColorHandler();
    public InviteHandler inviteHandler = new InviteHandler();
    public RankHandler rank = new RankHandler();
    public RelationHandler relation = new RelationHandler();
    public TopHandler top = new TopHandler();
    public TribeData data = new TribeData();
    public WealthHandler wealth = new WealthHandler();

    public long disbandAfterInactiveFor = 2 * 7 * 24 * 60 * 60 * 1000; // 2 Weeks

    public void setTribe(Player player, String tribe, boolean leaving) {
        Tribes.getInstance().PlayerData.set("Players." + player.getUniqueId() + ".Tribe.Name", leaving ? null : tribe);
        Tribes.getInstance().savePlayerData();
        if (leaving) {
            relation.removeUser(tribe, player.getName(), true);
        } else {
            relation.addMember(tribe, player.getName());
        }
    }

    public void setTribe(String playerUUID, String tribe, boolean leaving) {
        Tribes.getInstance().PlayerData.set("Players." + playerUUID + ".Tribe.Name", tribe);
        Tribes.getInstance().savePlayerData();
        if (leaving) {
            relation.removeUser(tribe, Tribes.getInstance().uuid.getName(playerUUID), true);
        } else {
            relation.addMember(tribe, Tribes.getInstance().uuid.getName(playerUUID));
        }
    }

    public void newTribe(Player owner, String tribe) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Created", System.currentTimeMillis() / 1000);
        Tribes.getInstance().saveTribeData();
        setTribe(owner, tribe, false);
        relation.removeMember(tribe, owner.getName(), true);
        rank.setOwner(owner);
    }

    public void removeTribe(String tribe, boolean save) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe, null);
        if (save) Tribes.getInstance().saveTribeData();
    }

    public void messageTribe(String tribe, String message) {
        String color = Tribes.getInstance().getTribeHandler().colorhandler.getColor(tribe);
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (getTribe(online).equals(tribe)) {
                online.sendMessage(Tribes.getInstance().util.getMessage(
                        (Tribes.getInstance().language.broadcastToTribe.replace("{MESSAGE}", message))
                                .replace("{COLOR}", color)
                                .replace("{TRIBE}", tribe)));
            }
        }
    }

    public void showTribe(Player player, String tribe) {
        boolean online;
        String color = colorhandler.getColor(tribe),
                members = "", admins = "", truces = "", enemies = "";

        // Owner online/offline
        String owner = relation.getOwner(tribe);
        try {
            online = Bukkit.getPlayer(owner).isOnline();
        } catch (Exception e) {
            online = false;
        }
        if (online) {
            owner = "&a" + owner;
        } else {
            owner = "&c" + owner;
        }

        // Members online/offline
        for (String member : relation.getMembers(tribe)) {
            try {
                online = Bukkit.getPlayer(member).isOnline();
            } catch (Exception e) {
                online = false;
            }
            if (online) {
                members += "&f, &a" + member;
            } else {
                members += "&f, &c" + member;
            }
        }
        members = members.replaceFirst("&f, ", "");

        // Admins online/offline
        for (String admin : relation.getAdmins(tribe)) {
            try {
                online = Bukkit.getPlayer(admin).isOnline();
            } catch (Exception e) {
                online = false;
            }
            if (online) {
                admins += "&f, &a" + admin;
            } else {
                admins += "&f, &c" + admin;
            }
        }
        admins = admins.replaceFirst("&f, ", "");

        // Truces
        for (String member : relation.getTruces(tribe))
            truces += "&f, " + "&a" + member;
        truces = truces.replaceFirst("&f, ", "");

        // Enemies
        for (String member : relation.getEnemies(tribe))
            enemies += "&f, " + "&c" + member;
        enemies = enemies.replaceFirst("&f, ", "");

        // Message
        for (String line : Tribes.getInstance().language.tribeShow)
            player.sendMessage(Util.getMessage(line
                    .replace("{COLOR}", color)
                    .replace("{WEALTH}", Vault.getString(wealth.getWealth(tribe)))
                    .replace("{DESCRIPTION}", data.getDescription(tribe))
                    .replace("{OWNER}", owner)
                    .replace("{MEMBERS}", members)
                    .replace("{ADMINS}", admins)
                    .replace("{TRUCES}", truces)
                    .replace("{ENEMIES}", enemies)
                    .replace("{TRIBE}", tribe)));
    }

    public void disbandTribe(Player player, String tribe, boolean any) {
        long timestamp = System.currentTimeMillis();
        if (player != null && !any) tribe = Tribes.getInstance().getTribeHandler().getTribe(player);

        for (String user : Tribes.getInstance().getTribeHandler().relation.getEveryone(tribe)) {
            Tribes.getInstance().getTribeHandler().rank.setNone(Bukkit.getPlayer(Tribes.getInstance().uuid.getUUID(user)));
            Tribes.getInstance().getTribeHandler().setTribe(Bukkit.getPlayer(Tribes.getInstance().uuid.getUUID(user)), null, true);
        }

        removeTribe(tribe, false);
        Tribes.getInstance().saveTribeData();

        if (player != null) player.sendMessage(Tribes.SECONDARY + tribe + Tribes.MAIN + " has been disbanded.");
        Tribes.getInstance().getLogger().info("Tribe '" + tribe + "' was disbanded in " + (((double) (System.currentTimeMillis() - timestamp)) / 1000.0) + " seconds.");
    }

    public void saveActivity(String tribe) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Last Activity", System.currentTimeMillis());
        Tribes.getInstance().saveTribeData();
    }

    public void checkActivity(boolean full) {
        long startTime = System.currentTimeMillis();
        boolean stopped = false;
        for (String tribe : getTribes()) {
            if (Tribes.getInstance().TribeData.contains("Tribe." + tribe + ".Last Activity"))
                if (System.currentTimeMillis() - Tribes.getInstance().TribeData.getLong("Tribe." + tribe + ".Last Activity") > disbandAfterInactiveFor)
                    disbandTribe(null, tribe, false);

            if (!full && System.currentTimeMillis() - startTime > 300) { // Only search for a maximum of 300ms
                stopped = true;
                break;
            }
        }

        if (stopped) {
            Tribes.getInstance().getLogger().info("Tribes activity check has been halted since it was taking too long; " + (((double) (System.currentTimeMillis() - startTime)) / 1000.0) + " seconds. (Will do a full check on next server restart)");
            return;
        }
        Tribes.getInstance().getLogger().info("All Tribes activity checked in " + (((double) (System.currentTimeMillis() - startTime)) / 1000.0) + " seconds.");
    }

    public boolean hasTribe(Player player) {
        try {
            return Tribes.getInstance().PlayerData.contains("Players." + player.getUniqueId() + ".Tribe.Name");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean tribeExists(String tribe, boolean exact) {
        try {
            for (String key : Tribes.getInstance().TribeData.getConfigurationSection("Tribes").getKeys(false)) {
                if (key.equals(tribe)) return true;
                if (!exact && key.equalsIgnoreCase(tribe)) return true;
            }
        } catch (Exception e) {
            //
        }
        return false;
    }

    public String getTribe(String string) {
        for (String tribe : getTribes())
            if (tribe.equalsIgnoreCase(string)) return tribe;
        return "";
    }

    public String getTribe(Player player) {
        if (hasTribe(player)) return Tribes.getInstance().PlayerData.getString("Players." + player.getUniqueId() + ".Tribe.Name");
        return "";
    }

    public List<String> getTribes() {
        List<String> tribes = new ArrayList<String>();
        try {
            for (String tribe : Tribes.getInstance().TribeData.getConfigurationSection("Tribes").getKeys(false)) tribes.add(tribe);
        } catch (Exception e) {
            //
        }
        return tribes;
    }

}