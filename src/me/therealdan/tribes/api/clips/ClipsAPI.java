package me.therealdan.tribes.api.clips;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import me.therealdan.tribes.Tribes;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 23/03/2017.
 */
public class ClipsAPI extends EZPlaceholderHook {

    public ClipsAPI(Tribes main) {
        super(main, "tribes");
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        // All of these placeholders REQUIRE a player
        if (player == null) return null;

        if (identifier.equals("tribename")) {
            return Tribes.getInstance().getTribeHandler().getTribe(player);
        } else if (identifier.equals("tribecolor")) {
            return Tribes.getInstance().getTribeHandler().colorhandler.getColor(Tribes.getInstance().getTribeHandler().getTribe(player));
        } else if (identifier.equals("triberank")) {
            return Tribes.getInstance().getTribeHandler().rank.getRank(player);
        }
        return null;
    }
}