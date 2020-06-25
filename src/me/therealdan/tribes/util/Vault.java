package me.therealdan.tribes.util;

import me.therealdan.tribes.Tribes;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created by Daniel on 1/08/2016.
 */
public class Vault {

    private static Economy economy;

    public static void addMoney(Player player, double money) {
        getEconomy().depositPlayer(player, money);
    }

    public static void takeMoney(Player player, double money) {
        getEconomy().withdrawPlayer(player, money);
    }

    public static boolean hasMoney(Player player, double money) {
        return getEconomy().getBalance(player) >= money;
    }

    public static double getMoney(Player player) {
        return getEconomy().getBalance(player);
    }

    public static String getString(double money) {
        return getEconomy().format(money);
    }

    private static Economy getEconomy() {
        if (economy == null)
            economy = Tribes.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class).getProvider();

        return economy;
    }
}