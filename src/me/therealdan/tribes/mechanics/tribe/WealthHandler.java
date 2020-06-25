package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import me.therealdan.tribes.util.Vault;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Daniel on 4/10/2016.
 */
public class WealthHandler {

    private HashMap<UUID, Double> balance = new HashMap<UUID, Double>();

    public void save() {
        for (UUID uuid : this.balance.keySet())
            Tribes.getInstance().BalanceData.set("Balance." + uuid, this.balance.get(uuid));
        Tribes.getInstance().saveBalanceData();
    }

    public void update(Player player) {
        this.balance.put(player.getUniqueId(), Vault.getMoney(player));
    }

    public double getWealth(String tribe) {
        double wealth = 0;
        for (String player : Tribes.getInstance().getTribeHandler().relation.getEveryone(tribe)) {
            try {
                if (Bukkit.getOnlinePlayers().toString().contains(player)) {
                    wealth += Vault.getMoney(Bukkit.getPlayer(player));
                } else {
                    wealth += getBalance(Tribes.getInstance().uuid.getUUID(player));
                }
            } catch (Exception e) {
                wealth += getBalance(Tribes.getInstance().uuid.getUUID(player));
            }
        }
        return wealth;
    }

    public double getBalance(UUID uuid) {
        if (!this.balance.containsKey(uuid))
            this.balance.put(uuid, Tribes.getInstance().BalanceData.getDouble("Balance." + uuid.toString()));
        return this.balance.get(uuid);
    }
}