package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import me.therealdan.tribes.util.Vault;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Daniel on 4/10/2016.
 */
public class TopHandler {

    public void list(Player player) {
        HashMap<String, Double> tribesMap = new HashMap<String, Double>();

        for (String tribeName : Tribes.getInstance().getTribeHandler().getTribes())
            tribesMap.put(tribeName, Tribes.getInstance().getTribeHandler().wealth.getWealth(tribeName));

        String nextTop = "";
        double nextTopWealth = 0;
        player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.topHeader));
        for (int i = 1; i <= 10; i++) {
            for (String tribe : tribesMap.keySet()) {
                if (tribesMap.get(tribe) > nextTopWealth) {
                    nextTop = tribe;
                    nextTopWealth = tribesMap.get(tribe);
                }
            }
            player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.top
                    .replace("{#}", Integer.toString(i))
                    .replace("{TRIBE}", nextTop)
                    .replace("{WEALTH}", Vault.getString(nextTopWealth))));
            tribesMap.remove(nextTop);
            nextTop = "";
            nextTopWealth = 0;
        }
    }
}