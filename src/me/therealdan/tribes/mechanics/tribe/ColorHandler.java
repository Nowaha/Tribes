package me.therealdan.tribes.mechanics.tribe;

import me.therealdan.tribes.Tribes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 31/08/2016.
 */
public class ColorHandler {

    private Inventory colorUI;

    public void setColor(String tribe, short durability) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Color.Short", durability);
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Color.Byte", durability);
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Color.Text", getColor(durability));
        Tribes.getInstance().saveTribeData();
    }

    public void open(Player player) {
        if (colorUI == null) {
            colorUI = Bukkit.createInventory(null, InventoryType.HOPPER, getTitle());
            for (int i = 1; i <= 5; i++)
                colorUI.addItem(getWool(i));
        }
        player.openInventory(colorUI);
    }

    public void click(InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equals(getTitle())) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;

        if (!Tribes.getInstance().getTribeHandler().hasTribe(player)) return;
        String tribe = Tribes.getInstance().getTribeHandler().getTribe(player);

        if (getTimeRemaining(tribe) >= 0) {
            player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.MAIN + "You can change your Tribe color in " +
                    Tribes.SECONDARY + (getTimeRemaining(tribe) / 1000) +
                    Tribes.MAIN + " seconds"));
            return;
        }
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".TimeStamp.Color", System.currentTimeMillis());
        Tribes.getInstance().saveTribeData();

        if (event.getCurrentItem().getDurability() == Tribes.getInstance().configuration.tribeColor[0]) {
            setColor(tribe, Tribes.getInstance().configuration.tribeColor[0]);
        } else if (event.getCurrentItem().getDurability() == Tribes.getInstance().configuration.tribeColor[1]) {
            setColor(tribe, Tribes.getInstance().configuration.tribeColor[1]);
        } else if (event.getCurrentItem().getDurability() == Tribes.getInstance().configuration.tribeColor[2]) {
            setColor(tribe, Tribes.getInstance().configuration.tribeColor[2]);
        } else if (event.getCurrentItem().getDurability() == Tribes.getInstance().configuration.tribeColor[3]) {
            setColor(tribe, Tribes.getInstance().configuration.tribeColor[3]);
        } else if (event.getCurrentItem().getDurability() == Tribes.getInstance().configuration.tribeColor[4]) {
            setColor(tribe, Tribes.getInstance().configuration.tribeColor[4]);
        }

        Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.color
                .replace("{TRIBECOLOR}", event.getCurrentItem().getItemMeta().getDisplayName())
                .replace("{PLAYER}", player.getName()));
    }

    public boolean haveColor(String tribe) {
        return Tribes.getInstance().TribeData.contains("Tribes." + tribe + ".Color.Text");
    }

    public long getTimeRemaining(String tribe) {
        long timeStamp = Tribes.getInstance().TribeData.getLong("Tribes." + tribe + ".TimeStamp.Color");
        return (1000 * 60) - (System.currentTimeMillis() - timeStamp);
    }

    public short getDurability(String tribe) {
        try {
            return (short) Tribes.getInstance().TribeData.getInt("Tribes." + tribe + ".Color.Short");
        } catch (Exception e) {
            //
        }
        return 0;
    }

    public byte getByte(String tribe) {
        try {
            return (byte) Tribes.getInstance().TribeData.getInt("Tribes." + tribe + ".Color.Byte");
        } catch (Exception e) {
            //
        }
        return 0;
    }

    public String getColor(String tribe) {
        try {
            if (Tribes.getInstance().TribeData.contains("Tribes." + tribe + ".Color.Text"))
                return Tribes.getInstance().TribeData.getString("Tribes." + tribe + ".Color.Text");
        } catch (Exception e) {
            //
        }
        return "&7";
    }

    private ItemStack getWool(int i) {
        List<String> lore = new ArrayList<String>();
        for (String line : Tribes.getInstance().configuration.tribeColorUILore.get(i - 1))
            lore.add(Tribes.getInstance().util.getMessage(line));
        ItemStack wool = new ItemStack(Material.WHITE_WOOL);
        ItemMeta itemMeta = wool.getItemMeta();
        itemMeta.setDisplayName(Tribes.getInstance().util.getMessage(Tribes.getInstance().configuration.tribeColorUIName.get(i - 1)));
        itemMeta.setLore(lore);
        wool.setItemMeta(itemMeta);
        wool.setDurability(Tribes.getInstance().configuration.tribeColor[i - 1]);
        return wool;
    }

    private String getTitle() {
        return Tribes.getInstance().util.getMessage(Tribes.getInstance().configuration.tribeColorUITitle);
    }

    private String getColor(short durability) {
        switch (durability) {
            case 0:
                return "&f";
            case 1:
                return "&6";
            case 2:
                return "&d";
            case 3:
                return "&9";
            case 4:
                return "&e";
            case 5:
                return "&a";
            case 6:
                return "&d";
            case 7:
                return "&8";
            case 8:
                return "&7";
            case 9:
                return "&3";
            case 10:
                return "&5";
            case 11:
                return "&1";
            case 12:
                return "&6";
            case 13:
                return "&2";
            case 14:
                return "&c";
            case 15:
                return "&0";
        }
        return "&f;";
    }
}