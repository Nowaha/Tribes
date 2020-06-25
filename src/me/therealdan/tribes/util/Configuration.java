package me.therealdan.tribes.util;

import me.therealdan.tribes.Tribes;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Daniel on 24/08/2016.
 */
public class Configuration {

    // Configuration
    public String color, color2;

    // Permissions
    public boolean opOverridesEverything,
            requiresPermissionTribe, requiresPermissionTruce, requiresPermissionEnemy;

    // Block Values
    public HashMap<Material, Integer> blockValues;

    // Tribe Defaults
    public Material defaultRingMaterial;
    public long gracePeriod, tribeHomeTeleportDelay;
    public String gracePeriodOnScreenTextTitle, gracePeriodOnScreenTextSubTitle;
    public int gracePeriodOnScreenTextA, gracePeriodOnScreenTextB, gracePeriodOnScreenTextC;
    public int HP_1, HP_2, HP_3, HP_4, HP_5;

    // Tribe Buffs
    public List<String> buff1, buff2, buff3, buff4, buff5;
    public boolean[] doubleXP = new boolean[5];

    // Tribe Core Item
    public String tribeCoreItemName;
    public List<String> tribeCoreItemLore;

    // Tribe Color UI+
    public short[] tribeColor;
    public String tribeColorUITitle;
    public List<String> tribeColorUIName;
    public List<List<String>> tribeColorUILore;

    // Tribe Core UI
    public String tribeCoreUITitle;
    public List<String> tribeCoreLockedUIName, tribeCoreUnlockedUIName;
    public List<Material> tribeCoreLockedUIType, tribeCoreUnlockedUIType;
    public short[] tribeCoreLockedUIData, tribeCoreUnlockedUIData;
    public List<List<String>> tribeCoreLockedUILore, tribeCoreUnlockedUILore;

    public void loadAll() {
        // Load Defaults
        loadConfig();
        loadTribeConfig();
    }

    @Deprecated
    private void defaultConfig() {
        // Permissions
        Tribes.getInstance().Config.addDefault("OP Overrides Everything", false);
        Tribes.getInstance().Config.addDefault("Requires Permission.Tribe command", false);
        Tribes.getInstance().Config.addDefault("Requires Permission.Truce command", false);
        Tribes.getInstance().Config.addDefault("Requires Permission.Enemy command", false);

        // Explosions
        Tribes.getInstance().Config.addDefault("Explosions.DisableEntityExplosions", true);
        Tribes.getInstance().Config.addDefault("Explosions.DisableBlockExplosions", true);
        Tribes.getInstance().Config.addDefault("Explosions.Degrade Rate per block", 50);
        Tribes.getInstance().Config.addDefault("Explosions.Time to display HP for", 100);
        Tribes.getInstance().Config.addDefault("Explosions.Display HP.Format.High", "&a{HP}HP");
        Tribes.getInstance().Config.addDefault("Explosions.Display HP.Format.Medium", "&e{HP}HP");
        Tribes.getInstance().Config.addDefault("Explosions.Display HP.Format.Low", "&c{HP}HP");
        Tribes.getInstance().Config.addDefault("Explosions.Display HP.Percentage.High", 0.6);
        Tribes.getInstance().Config.addDefault("Explosions.Display HP.Percentage.Medium", 0.3);
        Tribes.getInstance().Config.addDefault("Explosions.Damage.Players", false);
        Tribes.getInstance().Config.addDefault("Explosions.Damage.Other Entities", false);
        Tribes.getInstance().Config.addDefault("Explosions.Push.Players", true);
        Tribes.getInstance().Config.addDefault("Explosions.Push.Other Entities", true);

        Tribes.getInstance().Config.addDefault("Explosions.Spawner Drop Chance", 0.25);

        Tribes.getInstance().Config.options().copyDefaults(true);
        Tribes.getInstance().saveConfig();
    }

    private void loadConfig() {
        // Configuration
        color = Tribes.getInstance().Config.getString("Color.Main");
        color2 = Tribes.getInstance().Config.getString("Color.Secondary");

        // Permissions
        opOverridesEverything = Tribes.getInstance().Config.getBoolean("OP Overrides Everything");
        requiresPermissionTribe = Tribes.getInstance().Config.getBoolean("Requires Permission.Tribe command");
        requiresPermissionTruce = Tribes.getInstance().Config.getBoolean("Requires Permission.Truce command");
        requiresPermissionEnemy = Tribes.getInstance().Config.getBoolean("Requires Permission.Enemy command");
    }

    private void loadTribeConfig() {
        // Tribe Defaults
        defaultRingMaterial = Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Default.Ring Material"));
        gracePeriod = Tribes.getInstance().TribeConfig.getLong("Grace Period");
        tribeHomeTeleportDelay = Tribes.getInstance().TribeConfig.getLong("Tribe Home Teleport Delay");
        gracePeriodOnScreenTextTitle = Tribes.getInstance().TribeConfig.getString("Grace Period On-Screen Text.Title");
        gracePeriodOnScreenTextSubTitle = Tribes.getInstance().TribeConfig.getString("Grace Period On-Screen Text.SubTitle");
        gracePeriodOnScreenTextA = Tribes.getInstance().TribeConfig.getInt("Grace Period On-Screen Text.A");
        gracePeriodOnScreenTextB = Tribes.getInstance().TribeConfig.getInt("Grace Period On-Screen Text.B");
        gracePeriodOnScreenTextC = Tribes.getInstance().TribeConfig.getInt("Grace Period On-Screen Text.C");
        HP_1 = Tribes.getInstance().TribeConfig.getInt("Tribe Core.HP.Level 1");
        HP_2 = Tribes.getInstance().TribeConfig.getInt("Tribe Core.HP.Level 2");
        HP_3 = Tribes.getInstance().TribeConfig.getInt("Tribe Core.HP.Level 3");
        HP_4 = Tribes.getInstance().TribeConfig.getInt("Tribe Core.HP.Level 4");
        HP_5 = Tribes.getInstance().TribeConfig.getInt("Tribe Core.HP.Level 5");

        // Tribe Buffs
        buff1 = Tribes.getInstance().TribeConfig.getStringList("Tribe Buffs.Level 1");
        buff2 = Tribes.getInstance().TribeConfig.getStringList("Tribe Buffs.Level 2");
        buff3 = Tribes.getInstance().TribeConfig.getStringList("Tribe Buffs.Level 3");
        buff4 = Tribes.getInstance().TribeConfig.getStringList("Tribe Buffs.Level 4");
        buff5 = Tribes.getInstance().TribeConfig.getStringList("Tribe Buffs.Level 5");
        if (buff1.contains("DOUBLEXP")) doubleXP[0] = true;
        if (buff2.contains("DOUBLEXP")) doubleXP[1] = true;
        if (buff3.contains("DOUBLEXP")) doubleXP[2] = true;
        if (buff4.contains("DOUBLEXP")) doubleXP[3] = true;
        if (buff5.contains("DOUBLEXP")) doubleXP[4] = true;

        // Tribe Core Item
        tribeCoreItemName = Tribes.getInstance().TribeConfig.getString("Tribe Core.Item.Name");
        tribeCoreItemLore = Tribes.getInstance().TribeConfig.getStringList("Tribe Core.Item.Lore");

        // Tribe Color UI+
        tribeColor = new short[5];
        tribeColorUITitle = Tribes.getInstance().TribeConfig.getString("Tribe Color.UI.Title");
        tribeColorUIName = new ArrayList<String>();
        tribeColorUILore = new ArrayList<List<String>>();
        for (int i = 1; i <= 5; i++) {
            tribeColor[i - 1] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Color." + i);
            tribeColorUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Color.UI." + i + ".Name"));
            tribeColorUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Color.UI." + i + ".Lore"));
        }

        // Title
        tribeCoreUITitle = Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Title", "{COLOR}{TRIBE} Core");

        // Unlocked Initiation
        tribeCoreUnlockedUIName = new ArrayList<String>();
        tribeCoreUnlockedUIType = new ArrayList<Material>();
        tribeCoreUnlockedUIData = new short[5];
        tribeCoreUnlockedUILore = new ArrayList<List<String>>();
        // Unlocked Data
        tribeCoreUnlockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.1.Name", "{COLOR}Level 1"));
        tribeCoreUnlockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.1.Type")));
        tribeCoreUnlockedUIData[0] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Unlocked.1.Data");
        tribeCoreUnlockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Unlocked.1.Lore"));
        tribeCoreUnlockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.2.Name", "{COLOR}Level 2"));
        tribeCoreUnlockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.2.Type")));
        tribeCoreUnlockedUIData[1] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Unlocked.2.Data");
        tribeCoreUnlockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Unlocked.2.Lore"));
        tribeCoreUnlockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.3.Name", "{COLOR}Level 3"));
        tribeCoreUnlockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.3.Type")));
        tribeCoreUnlockedUIData[2] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Unlocked.3.Data");
        tribeCoreUnlockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Unlocked.3.Lore"));
        tribeCoreUnlockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.4.Name", "{COLOR}Level 4"));
        tribeCoreUnlockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.4.Type")));
        tribeCoreUnlockedUIData[3] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Unlocked.4.Data");
        tribeCoreUnlockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Unlocked.4.Lore"));
        tribeCoreUnlockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.5.Name", "{COLOR}Level 5"));
        tribeCoreUnlockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Unlocked.5.Type")));
        tribeCoreUnlockedUIData[4] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Unlocked.5.Data");
        tribeCoreUnlockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Unlocked.5.Lore"));

        // Locked Initiation
        tribeCoreLockedUIName = new ArrayList<String>();
        tribeCoreLockedUIType = new ArrayList<Material>();
        tribeCoreLockedUIData = new short[5];
        tribeCoreLockedUILore = new ArrayList<List<String>>();
        // Locked Data
        tribeCoreLockedUIName.add("");
        tribeCoreLockedUIType.add(Material.AIR);
        tribeCoreLockedUIData[0] = 1;
        tribeCoreLockedUILore.add(new ArrayList<String>());
        tribeCoreLockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.2.Name", "{COLOR}Level 2"));
        tribeCoreLockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.2.Type")));
        tribeCoreLockedUIData[1] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Locked.2.Data");
        tribeCoreLockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Locked.2.Lore"));
        tribeCoreLockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.3.Name", "{COLOR}Level 3"));
        tribeCoreLockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.3.Type")));
        tribeCoreLockedUIData[2] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Locked.3.Data");
        tribeCoreLockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Locked.3.Lore"));
        tribeCoreLockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.4.Name", "{COLOR}Level 4"));
        tribeCoreLockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.4.Type")));
        tribeCoreLockedUIData[3] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Locked.4.Data");
        tribeCoreLockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Locked.4.Lore"));
        tribeCoreLockedUIName.add(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.5.Name", "{COLOR}Level 5"));
        tribeCoreLockedUIType.add(Material.getMaterial(Tribes.getInstance().TribeConfig.getString("Tribe Core.UI.Locked.5.Type")));
        tribeCoreLockedUIData[4] = (short) Tribes.getInstance().TribeConfig.getInt("Tribe Core.UI.Locked.5.Data");
        tribeCoreLockedUILore.add(Tribes.getInstance().TribeConfig.getStringList("Tribe Core.UI.Locked.5.Lore"));
    }

}