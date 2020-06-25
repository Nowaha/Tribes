package me.therealdan.tribes.events;

import me.therealdan.tribes.Tribes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Daniel on 1/09/2016.
 */
public class InventoryHandler implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Tribes.getInstance().getTribeHandler().colorhandler.click(event);
    }

}