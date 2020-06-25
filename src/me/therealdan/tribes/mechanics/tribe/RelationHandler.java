package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 24/08/2016.
 */
public class RelationHandler {

    public String getTribe(Player player) {
        return Tribes.getInstance().getTribeHandler().getTribe(player);
    }

    public String getOwner(String tribe) {
        return Tribes.getInstance().TribeData.getString("Tribes." + tribe + ".Owner");
    }

    public boolean sameTribe(Player player, Player target) {
        if (!Tribes.getInstance().getTribeHandler().hasTribe(player) || !Tribes.getInstance().getTribeHandler().hasTribe(target)) return false;
        return getTribe(player).equals(getTribe(target));
    }

    public boolean isTruce(Player player, Player target) {
        return isTruce(getTribe(player), getTribe(target));
    }

    public boolean isTruce(String tribe1, String tribe2) {
        return getTruces(tribe1).contains(tribe2) &&
                getTruces(tribe2).contains(tribe1);
    }

    public boolean isEnemy(Player player, Player target) {
        return isEnemy(getTribe(player), getTribe(target));
    }

    public boolean isEnemy(String tribe1, String tribe2) {
        return getEnemies(tribe1).contains(tribe2) &&
                getEnemies(tribe2).contains(tribe1);
    }

    public boolean isNeutral(Player player, Player target) {
        return isNeutral(getTribe(player), getTribe(target));
    }

    public boolean isNeutral(String tribe1, String tribe2) {
        return !isTruce(tribe1, tribe2) && !isEnemy(tribe1, tribe2);
    }

    public void addMember(String tribe, String player) {
        List<String> members = getMembers(tribe);
        if (!members.contains(player)) {
            members.add(player);
            setMembers(tribe, members, true);
        }
    }

    public void removeUser(String tribe, String player, boolean save) {
        List<String> members = getMembers(tribe);
        List<String> admins = getAdmins(tribe);
        if (members.contains(player)) {
            members.remove(player);
            setMembers(tribe, members, false);
        }
        if (admins.contains(player)) {
            admins.remove(player);
            setAdmins(tribe, admins, false);
        }
        if (save) Tribes.getInstance().saveTribeData();
    }

    public void removeMember(String tribe, String player, boolean save) {
        List<String> members = getMembers(tribe);
        if (members.contains(player)) {
            members.remove(player);
            setMembers(tribe, members, save);
        }
    }

    public void addAdmin(String tribe, String player) {
        List<String> admins = getAdmins(tribe);
        if (!admins.contains(player)) {
            admins.add(player);
            setAdmins(tribe, admins, true);
        }
    }

    public void removeAdmin(String tribe, String player, boolean save) {
        List<String> admins = getAdmins(tribe);
        if (admins.contains(player)) {
            admins.remove(player);
            setAdmins(tribe, admins, save);
        }
    }

    public void setTruce(String tribe1, String tribe2) {
        List<String> truces = getTruces(tribe1);
        List<String> enemies = getEnemies(tribe1);
        if (!truces.contains(tribe2)) truces.add(tribe2);
        enemies.remove(tribe2);
        setTruces(tribe1, truces);
        setEnemies(tribe1, enemies);
    }

    public void setEnemy(String tribe1, String tribe2) {
        List<String> truces = getTruces(tribe1);
        List<String> enemies = getEnemies(tribe1);
        truces.remove(tribe2);
        if (!enemies.contains(tribe2)) enemies.add(tribe2);
        setTruces(tribe1, truces);
        setEnemies(tribe1, enemies);

        truces = getTruces(tribe2);
        enemies = getEnemies(tribe2);
        truces.remove(tribe1);
        if (!enemies.contains(tribe1)) enemies.add(tribe1);
        setTruces(tribe2, truces);
        setEnemies(tribe2, enemies);
    }

    private void setMembers(String tribe, List<String> members, boolean save) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Members", members);
        if (save) Tribes.getInstance().saveTribeData();
    }

    private void setAdmins(String tribe, List<String> admins, boolean save) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Admins", admins);
        if (save) Tribes.getInstance().saveTribeData();
    }

    private void setTruces(String tribe, List<String> truces) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Truces", truces);
        Tribes.getInstance().saveTribeData();
    }

    private void setEnemies(String tribe, List<String> enemies) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Enemies", enemies);
        Tribes.getInstance().saveTribeData();
    }

    public List<String> getEveryone(String tribe) {
        List<String> everyone = new ArrayList<String>();
        everyone.add(getOwner(tribe));
        for (String player : getAdmins(tribe)) everyone.add(player);
        for (String player : getMembers(tribe)) everyone.add(player);
        return everyone;
    }

    public List<String> getMembers(String tribe) {
        try {
            return Tribes.getInstance().TribeData.getStringList("Tribes." + tribe + ".Members");
        } catch (Exception e) {
            //
        }
        return new ArrayList<String>();
    }

    public List<String> getAdmins(String tribe) {
        try {
            return Tribes.getInstance().TribeData.getStringList("Tribes." + tribe + ".Admins");
        } catch (Exception e) {
            //
        }
        return new ArrayList<String>();
    }

    public List<String> getTruces(String tribe) {
        try {
            boolean edit = false;
            List<String> truces = Tribes.getInstance().TribeData.getStringList("Tribes." + tribe + ".Truces");
            for (String truce : truces) {
                if (!Tribes.getInstance().getTribeHandler().tribeExists(truce, false)) {
                    truces.remove(truce);
                    edit = true;
                }
            }
            if (edit) setTruces(tribe, truces);
            return truces;
        } catch (Exception e) {
            //
        }
        return new ArrayList<String>();
    }

    public List<String> getEnemies(String tribe) {
        try {
            boolean edit = false;
            List<String> enemies = Tribes.getInstance().TribeData.getStringList("Tribes." + tribe + ".Enemies");
            for (String enemy : enemies) {
                if (!Tribes.getInstance().getTribeHandler().tribeExists(enemy, false)) {
                    enemies.remove(enemy);
                    edit = true;
                }
            }
            if (edit) setEnemies(tribe, enemies);
            return enemies;
        } catch (Exception e) {
            //
        }
        return new ArrayList<String>();
    }
}