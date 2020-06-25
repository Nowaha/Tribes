package me.therealdan.tribes;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.therealdan.tribes.api.clips.ClipsAPI;
import me.therealdan.tribes.commands._CommandsHandler;
import me.therealdan.tribes.events.*;
import me.therealdan.tribes.mechanics.MobSpawners;
import me.therealdan.tribes.mechanics.tribe._TribeHandler;
import me.therealdan.tribes.util.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Daniel on 24/08/2016.
 */
public class Tribes extends JavaPlugin {

    private static Tribes tribes;

    // Util Classes
    public Configuration configuration = new Configuration();
    public Language language = new Language();
    public Permissions permissions = new Permissions();
    public Util util = new Util();
    public UUIDHolder uuid = new UUIDHolder();
    public Vault vault = new Vault();

    // Classes
    private _TribeHandler tribeHandler;
    private MobSpawners mobSpawners;

    // Configuration
    public FileConfiguration TribeConfig, CoreItemsConfig, LanguageData, Weapons, BlastResistance,
            PlayerData, ServerData, TribeData, BlockData, BalanceData, UUIDData, RelicData, Config = getConfig();
    private File TribeConfigFile = new File(getDataFolder(), "TribeConfig.yml");
    private File CoreItemsConfigFile = new File(getDataFolder(), "CoreItemsConfig.yml");
    private File LanguageDataFile = new File(getDataFolder(), "LanguageData.yml");
    private File WeaponsFile = new File(getDataFolder(), "Weapons.yml");
    private File BlastResistanceFile = new File(getDataFolder(), "BlastResistance.yml");
    private File PlayerDataFile = new File(getDataFolder() + "/data", "PlayerData.yml");
    private File ServerDataFile = new File(getDataFolder() + "/data", "ServerData.yml");
    private File TribeDataFile = new File(getDataFolder() + "/data", "TribeData.yml");
    private File BlockDataFile = new File(getDataFolder() + "/data", "BlockData.yml");
    private File BalanceDataFile = new File(getDataFolder() + "/data", "BalanceData.yml");
    private File UUIDDataFile = new File(getDataFolder() + "/data", "UUIDData.yml");
    private File RelicDataFile = new File(getDataFolder() + "/data", "RelicData.yml");

    public WorldGuardPlugin worldGuard;

    public static String MAIN, SECONDARY;

    public HashMap<Player, Location> lastLocation = new HashMap<Player, Location>();

    public void onEnable() {
        tribes = this;

        _CommandsHandler commandsHandler = new _CommandsHandler();
        getCommand("t").setExecutor(commandsHandler);
        getCommand("tribe").setExecutor(commandsHandler);
        getCommand("tribes").setExecutor(commandsHandler);

        getServer().getPluginManager().registerEvents(new ChatHandler(), this);
        getServer().getPluginManager().registerEvents(new InventoryHandler(), this);

        saveDefaultConfig();
        this.saveResource("TribeConfig.yml", false);
        TribeConfig = YamlConfiguration.loadConfiguration(TribeConfigFile);
        CoreItemsConfig = YamlConfiguration.loadConfiguration(CoreItemsConfigFile);
        LanguageData = YamlConfiguration.loadConfiguration(LanguageDataFile);
        Weapons = null;
        BlastResistance = null;
        PlayerData = YamlConfiguration.loadConfiguration(PlayerDataFile);
        ServerData = YamlConfiguration.loadConfiguration(ServerDataFile);
        TribeData = YamlConfiguration.loadConfiguration(TribeDataFile);
        BlockData = YamlConfiguration.loadConfiguration(BlockDataFile);
        BalanceData = YamlConfiguration.loadConfiguration(BalanceDataFile);
        UUIDData = YamlConfiguration.loadConfiguration(UUIDDataFile);
        RelicData = null;
        saveTribeConfig();
        saveLanguageData();
        savePlayerData();
        saveServerData();
        saveTribeData();
        saveBlockData();
        saveBalanceData();
        saveUUIDData();

        tribeHandler = new _TribeHandler();
        mobSpawners = new MobSpawners();

        MAIN = "§f";
        SECONDARY = "§a";

        configuration.loadAll();
        language.load();

        getTribeHandler().rank.loadRanks();

        // Worldguard (Requires WorldEdit)
        worldGuard = getWorldGuard();

        // MVdWPlaceholderAPI
        if (getServer().getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
            PlaceholderAPI.registerPlaceholder(this, "tribename", new PlaceholderReplacer() {
                @Override
                public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
                    return getTribeHandler().getTribe(event.getPlayer());
                }
            });
            PlaceholderAPI.registerPlaceholder(this, "tribecolor", new PlaceholderReplacer() {
                @Override
                public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
                    return getTribeHandler().colorhandler.getColor(getTribeHandler().getTribe(event.getPlayer()));
                }
            });
            PlaceholderAPI.registerPlaceholder(this, "triberank", new PlaceholderReplacer() {
                @Override
                public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
                    return getTribeHandler().rank.getRank(event.getPlayer());
                }
            });
        }

        // PlaceholderAPI (Clips)
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new ClipsAPI(this);
        }

        getTribeHandler().checkActivity(true);

        getLogger().info("Custom plugin by TheRealDan");
    }

    public void onDisable() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            getTribeHandler().inviteHandler.clearInvites(player);
        }

        getTribeHandler().wealth.save();

    }

    private void saveData(FileConfiguration data, File file) {
        try {
            data.save(file);
        } catch (Exception e) {
            //
        }
    }

    public void saveTribeConfig() {
        saveData(TribeConfig, TribeConfigFile);
    }

    public void saveCoreItemsConfig() {
        saveData(CoreItemsConfig, CoreItemsConfigFile);
    }

    public void saveLanguageData() {
        saveData(LanguageData, LanguageDataFile);
    }

    public void saveWeapons() {
        saveData(Weapons, WeaponsFile);
    }

    public void saveBlastResistance() {
        saveData(BlastResistance, BlastResistanceFile);
    }

    public void savePlayerData() {
        saveData(PlayerData, PlayerDataFile);
    }

    public void saveServerData() {
        saveData(ServerData, ServerDataFile);
    }

    public void saveTribeData() {
        saveData(TribeData, TribeDataFile);
    }

    public void saveBlockData() {
        saveData(BlockData, BlockDataFile);
    }

    public void saveBalanceData() {
        saveData(BalanceData, BalanceDataFile);
    }

    public void saveUUIDData() {
        saveData(UUIDData, UUIDDataFile);
    }

    public void saveRelicData() {
        saveData(RelicData, RelicDataFile);
    }

    public _TribeHandler getTribeHandler() {
        return tribeHandler;
    }

    public MobSpawners getMobSpawners() {
        return mobSpawners;
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) return null;
        return (WorldGuardPlugin) plugin;
    }

    public static Tribes getInstance() {
        return tribes;
    }
}