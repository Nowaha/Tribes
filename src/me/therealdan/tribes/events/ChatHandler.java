package me.therealdan.tribes.events;

import me.therealdan.tribes.Tribes;
import me.therealdan.tribes.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Daniel on 7/10/2016.
 */
public class ChatHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        String format = String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage());
        format = format
                .replace("{TRIBERANK}", Tribes.getInstance().getTribeHandler().rank.getRank(event.getPlayer()))
                .replace("{TRIBENAME}", Tribes.getInstance().getTribeHandler().getTribe(event.getPlayer()))
                .replace("{TRIBECOLOR}", Util.getMessage(Tribes.getInstance().getTribeHandler().colorhandler.getColor(Tribes.getInstance().getTribeHandler().getTribe(event.getPlayer()))));

        event.setFormat(format);
    }
}