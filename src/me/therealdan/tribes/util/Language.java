package me.therealdan.tribes.util;

import me.therealdan.tribes.Tribes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 24/08/2016.
 */
public class Language {

    public String createdNewTribe,
            differentTribe, youHaveNoTribe,
            truceYourTribe, truceSameTribe, truceHasNoTribe, truceTribeDoesntExist, truceNoTribe, truceTribeA, truceTribeB, truceTribeC,
            enemyYourTribe, enemySameTribe, enemyHasNoTribe, enemyTribeDoesntExist, enemyNoTribe, enemyTribeA, enemyTribeB, enemyTribeC,
            targetAlreadyHasTribe, sentInvite, receivedInvite, noInvitesFrom, tribeDoesntExist, playerJoinedTribe, userHasNoTribe,
            broadcastToTribe, promote, demote, newOwner, kick, description, color, leftTribe, disbandTribe,
            top, topHeader,
            claimedLand, reclaimedLand, unclaimedLand, cantClaimHere,
            combatSameTribeVictim, combatSameTribeAttacker, combatTruceVictim, combatTruceAttacker,
            tribeCoreNotSet, corePlaced, coreAlreadySet, coreIsMaxLevel, coreUpgradedToLevel, coreBroken, coreMustBeInTerritory,
            teleportingToTribeCoreShortly, teleportingToTribeCoreCancelled, teleportingToTribeCore;

    // TribeCommand Show
    public List<String> tribeShow;

    public String relicWait;

    public String launcherToggleGrenade, launcherNoAmmo;
    public List<String> weaponsCommandList;

    public void load() {
        createdNewTribe = Tribes.getInstance().LanguageData.getString("CreatedNewTribe");

        differentTribe = Tribes.getInstance().LanguageData.getString("DifferentTribe");
        youHaveNoTribe = Tribes.getInstance().LanguageData.getString("YouHaveNoTribe");

        truceYourTribe = Tribes.getInstance().LanguageData.getString("TruceYourTribe");
        truceSameTribe = Tribes.getInstance().LanguageData.getString("TruceSameTribe");
        truceHasNoTribe = Tribes.getInstance().LanguageData.getString("TruceHasNoTribe");
        truceTribeDoesntExist = Tribes.getInstance().LanguageData.getString("TruceTribeDoesntExist");
        truceNoTribe = Tribes.getInstance().LanguageData.getString("TruceNoTribe");
        truceTribeA = Tribes.getInstance().LanguageData.getString("TruceTribeA");
        truceTribeB = Tribes.getInstance().LanguageData.getString("TruceTribeB");
        truceTribeC = Tribes.getInstance().LanguageData.getString("TruceTribeC");

        enemyYourTribe = Tribes.getInstance().LanguageData.getString("EnemyYourTribe");
        enemySameTribe = Tribes.getInstance().LanguageData.getString("EnemySameTribe");
        enemyHasNoTribe = Tribes.getInstance().LanguageData.getString("EnemyHasNoTribe");
        enemyTribeDoesntExist = Tribes.getInstance().LanguageData.getString("EnemyTribeDoesntExist");
        enemyNoTribe = Tribes.getInstance().LanguageData.getString("EnemyNoTribe");
        enemyTribeA = Tribes.getInstance().LanguageData.getString("EnemyTribeA");
        enemyTribeB = Tribes.getInstance().LanguageData.getString("EnemyTribeB");
        enemyTribeC = Tribes.getInstance().LanguageData.getString("EnemyTribeC");

        targetAlreadyHasTribe = Tribes.getInstance().LanguageData.getString("TargetAlreadyHasTribe");
        sentInvite = Tribes.getInstance().LanguageData.getString("SentInvite");
        receivedInvite = Tribes.getInstance().LanguageData.getString("ReceivedInvite");
        reclaimedLand = Tribes.getInstance().LanguageData.getString("ReceivedInvite");
        noInvitesFrom = Tribes.getInstance().LanguageData.getString("NoInvitesFrom");
        tribeDoesntExist = Tribes.getInstance().LanguageData.getString("TribeDoesntExist");
        playerJoinedTribe = Tribes.getInstance().LanguageData.getString("PlayerJoinedTribe");
        userHasNoTribe = Tribes.getInstance().LanguageData.getString("UserHasNoTribe");

        broadcastToTribe = Tribes.getInstance().LanguageData.getString("BroadcastToTribeFormat");
        promote = Tribes.getInstance().LanguageData.getString("Promote");
        demote = Tribes.getInstance().LanguageData.getString("Demote");
        newOwner = Tribes.getInstance().LanguageData.getString("NewOwner");
        kick = Tribes.getInstance().LanguageData.getString("Kick");
        description = Tribes.getInstance().LanguageData.getString("Description");
        color = Tribes.getInstance().LanguageData.getString("Color");
        leftTribe = Tribes.getInstance().LanguageData.getString("LeftTribe");
        disbandTribe = Tribes.getInstance().LanguageData.getString("DisbandTribe");
        claimedLand = Tribes.getInstance().LanguageData.getString("ClaimedLand");
        reclaimedLand = Tribes.getInstance().LanguageData.getString("ReclaimedLand");
        unclaimedLand = Tribes.getInstance().LanguageData.getString("UnclaimedLand");
        cantClaimHere = Tribes.getInstance().LanguageData.getString("CantClaimHere");

        // TribeCommand Show
        tribeShow = new ArrayList<String>();
        tribeShow = Tribes.getInstance().LanguageData.getStringList("TribeShow");

        relicWait = Tribes.getInstance().LanguageData.getString("RelicWait");

        top = Tribes.getInstance().LanguageData.getString("Top");
        topHeader = Tribes.getInstance().LanguageData.getString("TopHeader");

        tribeCoreNotSet = Tribes.getInstance().LanguageData.getString("TribeCoreNotSet");
        corePlaced = Tribes.getInstance().LanguageData.getString("CorePlaced");
        teleportingToTribeCoreShortly = Tribes.getInstance().LanguageData.getString("TeleportingToTribeCoreShortly");
        teleportingToTribeCoreCancelled = Tribes.getInstance().LanguageData.getString("TeleportingToTribeCoreCancelled");
        teleportingToTribeCore = Tribes.getInstance().LanguageData.getString("TeleportingToTribeCore");
        coreAlreadySet = Tribes.getInstance().LanguageData.getString("CoreAlreadySet");
        coreIsMaxLevel = Tribes.getInstance().LanguageData.getString("CoreIsMaxLevel");
        coreUpgradedToLevel = Tribes.getInstance().LanguageData.getString("CoreUpgradedToLevel");
        coreBroken = Tribes.getInstance().LanguageData.getString("CoreBroken");
        coreMustBeInTerritory = Tribes.getInstance().LanguageData.getString("CoreMustBeInTerritory");

        combatSameTribeVictim = Tribes.getInstance().LanguageData.getString("CombatSameTribeVictim");
        combatSameTribeAttacker = Tribes.getInstance().LanguageData.getString("CombatSameTribeAttacker");
        combatTruceVictim = Tribes.getInstance().LanguageData.getString("CombatTruceVictim");
        combatTruceAttacker = Tribes.getInstance().LanguageData.getString("CombatTruceAttacker");

        launcherToggleGrenade = Tribes.getInstance().LanguageData.getString("LauncherToggleGrenadeType");
        launcherNoAmmo = Tribes.getInstance().LanguageData.getString("LauncherHasNoAmmo");
        weaponsCommandList = Tribes.getInstance().LanguageData.getStringList("WeaponsCommandList");
    }

    @Deprecated
    public void addDefaults(Tribes m) {
        Tribes.getInstance().LanguageData.addDefault("CreatedNewTribe", "&7You have successfully created a new Tribe called &8{TRIBE}");

        Tribes.getInstance().LanguageData.addDefault("DifferentTribe", "&8{TARGET} &7is not in your Tribe!");
        Tribes.getInstance().LanguageData.addDefault("YouHaveNoTribe", "&7You don't have a Tribe!");

        Tribes.getInstance().LanguageData.addDefault("TruceYourTribe", "&7You can't truce with your own tribe!");
        Tribes.getInstance().LanguageData.addDefault("TruceSameTribe", "&7You can't truce with your own tribe!");
        Tribes.getInstance().LanguageData.addDefault("TruceHasNoTribe", "&8{TARGET} &7has no tribe!");
        Tribes.getInstance().LanguageData.addDefault("TruceTribeDoesntExist", "&7That tribe does't exist!");
        Tribes.getInstance().LanguageData.addDefault("TruceNoTribe", "&7You are not in a Tribe!");
        Tribes.getInstance().LanguageData.addDefault("TruceTribeA", "&8{PLAYER} &7has set your Tribes status with &8{TRIBE} &7to Truce.");
        Tribes.getInstance().LanguageData.addDefault("TruceTribeB", "&8{TRIBE} &7has set their Tribes status with your Tribe to Truce.");
        Tribes.getInstance().LanguageData.addDefault("TruceTribeC", "&7Your Tribe now has a Truce with &8{TRIBE}");

        Tribes.getInstance().LanguageData.addDefault("EnemyYourTribe", "&7You can't make enemies with your own Tribe!");
        Tribes.getInstance().LanguageData.addDefault("EnemySameTribe", "&7You can't make enemies with your own Tribe!");
        Tribes.getInstance().LanguageData.addDefault("EnemyHasNoTribe", "&8{TARGET} &7has no tribe!");
        Tribes.getInstance().LanguageData.addDefault("EnemyTribeDoesntExist", "&7That tribe does't exist!");
        Tribes.getInstance().LanguageData.addDefault("EnemyNoTribe", "&7You are not in a Tribe!");
        Tribes.getInstance().LanguageData.addDefault("EnemyTribeA", "&8{PLAYER} &7has set your Tribes status with &8{TRIBE} &7to Enemy.");
        Tribes.getInstance().LanguageData.addDefault("EnemyTribeB", "&8{TRIBE} &7has set their Tribes status with your Tribe to Enemy.");
        Tribes.getInstance().LanguageData.addDefault("EnemyTribeC", "&7Your Tribe now has a Enemy with &8{TRIBE}");

        Tribes.getInstance().LanguageData.addDefault("TargetAlreadyHasTribe", "&8{TARGET} &7already has a Tribe!");
        Tribes.getInstance().LanguageData.addDefault("SentInvite", "&8{PLAYER} &7has sent &8{TARGET} &7an invite.");
        Tribes.getInstance().LanguageData.addDefault("ReceivedInvite", "&8{PLAYER} &7has sent you an invite to join his tribe: &8{TRIBE}");
        Tribes.getInstance().LanguageData.addDefault("NoInvitesFrom", "&7You don't have any invites from &8{TRIBE}");
        Tribes.getInstance().LanguageData.addDefault("TribeDoesntExist", "&7That tribe does not exist!");

        Tribes.getInstance().LanguageData.addDefault("BroadcastToTribeFormat", "&f[{COLOR}{TRIBE}&f] &7{MESSAGE}");
        Tribes.getInstance().LanguageData.addDefault("PlayerJoinedTribe", "&8{PLAYER} &7has joined the Tribe!");
        Tribes.getInstance().LanguageData.addDefault("Promote", "&8{PLAYER} &7has promoted &8{TARGET} &7to Admin!");
        Tribes.getInstance().LanguageData.addDefault("Demote", "&8{PLAYER} &7has demoted &8{TARGET} &7to Member!");
        Tribes.getInstance().LanguageData.addDefault("NewOwner", "&8{PLAYER} &7has promoted &8{TARGET} &7to Owner!");
        Tribes.getInstance().LanguageData.addDefault("Kick", "&8{PLAYER} &7has kicked &8{TARGET} &7from the Tribe!");
        Tribes.getInstance().LanguageData.addDefault("Description", "&8{PLAYER} &7has set the tribe description to:");
        Tribes.getInstance().LanguageData.addDefault("Color", "&8{PLAYER} &7has set the tribe MAIN to {TRIBECOLOR}");
        Tribes.getInstance().LanguageData.addDefault("LeftTribe", "&8{PLAYER} &7has left the Tribe");
        Tribes.getInstance().LanguageData.addDefault("DisbandTribe", "&8{PLAYER} &7can't leave the Tribe until he sets a new Owner.");
        Tribes.getInstance().LanguageData.addDefault("ClaimedLand", "&8{PLAYER} &7has claimed land at &8{LOCATION}");
        Tribes.getInstance().LanguageData.addDefault("ReclaimedLand", "&8{PLAYER} &7has reclaimed land at &8{LOCATION}");
        Tribes.getInstance().LanguageData.addDefault("UnclaimedLand", "&8{PLAYER} &7has unclaimed our territory.");
        Tribes.getInstance().LanguageData.addDefault("CantClaimHere", "&7You can't claim land here!");

        // Tribe Show
        Tribes.getInstance().LanguageData.addDefault("TribeShow", "&f[{COLOR}{TRIBE}&f] &f- {COLOR}{LEVEL}<br>{COLOR}Wealth: &a${WEALTH}<br>{COLOR}{DESCRIPTION}<br>{COLOR}Owner: {OWNER}<br>{COLOR}Members: {MEMBERS}<br>{COLOR}Admins: {ADMINS}<br>{COLOR}Truces: {TRUCES}<br>{COLOR}Enemies: {ENEMIES}".split("<br>"));

        Tribes.getInstance().LanguageData.addDefault("RelicWait", "&7You can pickup this Relic again in &b{TIME}");

        Tribes.getInstance().LanguageData.addDefault("TopHeader", "&8Top Tribes");
        Tribes.getInstance().LanguageData.addDefault("Top", "&8{#} # {TRIBE}: &7{WEALTH}");

        Tribes.getInstance().LanguageData.addDefault("TribeCoreNotSet", "&7Tribe core has not been placed.");
        Tribes.getInstance().LanguageData.addDefault("CorePlaced", "&7Tribe core has been placed at &8{LOCATION}.");
        Tribes.getInstance().LanguageData.addDefault("TeleportingToTribeCore", "&7Teleporting to Tribe core.");
        Tribes.getInstance().LanguageData.addDefault("CoreAlreadySet", "&7Your Tribe core has already been placed.");
        Tribes.getInstance().LanguageData.addDefault("CoreIsMaxLevel", "&7Your Tribe core is Max level.");
        Tribes.getInstance().LanguageData.addDefault("CoreUpgradedToLevel", "&8{PLAYER} &7upgraded the Core to level &8{LEVEL}");
        Tribes.getInstance().LanguageData.addDefault("CoreBroken", "{COLOR}{TRIBE}&7's core has been broken!");
        Tribes.getInstance().LanguageData.addDefault("CoreMustBeInTerritory", "&7You must place the Core within your Tribe Territory.");

        Tribes.getInstance().LanguageData.addDefault("CombatSameTribeVictim", "&8{TARGET} &7attacked you, but you are in the same Tribe.");
        Tribes.getInstance().LanguageData.addDefault("CombatSameTribeAttacker", "&8{TARGET} &7is in your Tribe!");
        Tribes.getInstance().LanguageData.addDefault("CombatTruceVictim", "&8{TARGET} &7attacked you, but you are in a truce with their Tribe.");
        Tribes.getInstance().LanguageData.addDefault("CombatTruceAttacker", "&7You can't hurt &8{TARGET} &7since their Tribe has a truce with your Tribe.");

        Tribes.getInstance().LanguageData.addDefault("LauncherToggleGrenadeType", "&7Set priority to {GRENADE}'s");
        Tribes.getInstance().LanguageData.addDefault("LauncherHasNoAmmo", "&7You don't have enough grenades!");
        Tribes.getInstance().LanguageData.addDefault("WeaponsCommandList", "&7-- Weapons --<br>&7- All<br>&7- {C4}<br>&7- {GRENADE}<br>&7- {SPAWNERGRENADE}<br>&7- {STICKYGRENADE}<br>&7- {LAUNCHER}<br>&7- {CROSSBOW}".split("<br>"));

        Tribes.getInstance().LanguageData.options().copyDefaults(true);
        m.saveLanguageData();
    }
}
