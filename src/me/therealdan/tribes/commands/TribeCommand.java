package me.therealdan.tribes.commands;

import me.therealdan.tribes.Tribes;
import me.therealdan.tribes.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by Daniel on 24/08/2016.
 */
public class TribeCommand {

    public void command(Player player, String[] args) {
        boolean help = false;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("Create")) {
                if (!Tribes.getInstance().getTribeHandler().hasTribe(player))
                    create(player, args);
            } else if (args[0].equalsIgnoreCase("Join")) {
                if (!Tribes.getInstance().getTribeHandler().hasTribe(player))
                    join(player, args);
            } else if (args[0].equalsIgnoreCase("Show") || args[0].equalsIgnoreCase("S")) {
                show(player, args);
            } else if (args[0].equalsIgnoreCase("Leave")) {
                leave(player, args);
            } else if (args[0].equalsIgnoreCase("Disband")) {
                if (Tribes.getInstance().getTribeHandler().rank.isOwner(player) || player.hasPermission("tribes.commands.tribe.disband"))
                    disband(player, args);
            } else if (args[0].equalsIgnoreCase("Top")) {
                top(player, args);
            } else if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("C")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    color(player, args);
            } else if (args[0].equalsIgnoreCase("Invite") || args[0].equalsIgnoreCase("I")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    invite(player, args);
            } else if (args[0].equalsIgnoreCase("Promote") || args[0].equalsIgnoreCase("P")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    promote(player, args);
            } else if (args[0].equalsIgnoreCase("Demote")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    demote(player, args);
            } else if (args[0].equalsIgnoreCase("Kick") || args[0].equalsIgnoreCase("K")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    kick(player, args);
            } else if (args[0].equalsIgnoreCase("Description") || args[0].equalsIgnoreCase("Desc") || args[0].equalsIgnoreCase("D")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    description(player, args);
            } else if (args[0].equalsIgnoreCase("Truce")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    truce(player, args);
            } else if (args[0].equalsIgnoreCase("Enemy")) {
                if (Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player))
                    enemy(player, args);
            } else help = true;
        } else help = true;
        if (!help) return;

        // Tribe-less players help
        if (!Tribes.getInstance().getTribeHandler().hasTribe(player)) {
            player.sendMessage(Tribes.SECONDARY + "/tribes");
            player.sendMessage(Tribes.SECONDARY + "/tribes show [Tribe]:[player]");
            player.sendMessage(Tribes.SECONDARY + "/tribes create [Tribe]" + Tribes.MAIN + ": Create a new tribe");
            player.sendMessage(Tribes.SECONDARY + "/tribes join [Tribe]" + Tribes.MAIN + ": Join an existing tribe");
            return;
        }

        // Players who are in a tribe help
        if (!Tribes.getInstance().getTribeHandler().rank.isMemberPlus(player)) return;
        player.sendMessage(Tribes.SECONDARY + "/tribes" + Tribes.MAIN + ": Shows this help");
        player.sendMessage(Tribes.SECONDARY + "/tribes show" + Tribes.MAIN + ": Show your tribes info");
        player.sendMessage(Tribes.SECONDARY + "/tribes show [Tribe]" + Tribes.MAIN + ": Show a tribes info");
        player.sendMessage(Tribes.SECONDARY + "/tribes leave" + Tribes.MAIN + ": Leave your tribe");
        if (player.hasPermission("tribes.commands.tribe.disband")) {
            player.sendMessage(Tribes.SECONDARY + "/tribes disband [Tribe]" + Tribes.MAIN + ": Disband a Tribe.");
        } else if (Tribes.getInstance().getTribeHandler().rank.isOwner(player)) {
            player.sendMessage(Tribes.SECONDARY + "/tribes disband" + Tribes.MAIN + ": Disband your Tribe.");
        }
        player.sendMessage(Tribes.SECONDARY + "/tribes top" + Tribes.MAIN + ": View the top Tribes.");
        if (!Tribes.getInstance().getTribeHandler().rank.isAdminPlus(player)) return;
        player.sendMessage(Tribes.SECONDARY + "/tribes color" + Tribes.MAIN + ": Set your tribes color");
        player.sendMessage(Tribes.SECONDARY + "/tribes invite [player]" + Tribes.MAIN + ": Invite someone to your tribe");
        player.sendMessage(Tribes.SECONDARY + "/tribes promote [player]" + Tribes.MAIN + ": Promote a tribe member to admin");
        player.sendMessage(Tribes.SECONDARY + "/tribes demote [player]" + Tribes.MAIN + ": Demote a tribe admin to member");
        player.sendMessage(Tribes.SECONDARY + "/tribes kick [player]" + Tribes.MAIN + ": Kick a member from your tribe");
        player.sendMessage(Tribes.SECONDARY + "/tribes description [description]" + Tribes.MAIN + ": Set your tribes description");
        player.sendMessage(Tribes.SECONDARY + "/tribes truce [player:Tribe]" + Tribes.MAIN + ": Make truce with specified tribe");
        player.sendMessage(Tribes.SECONDARY + "/tribes enemy [player:Tribe]" + Tribes.MAIN + ": Make enemies with specified tribe");
        if (!Tribes.getInstance().getTribeHandler().rank.isOwner(player)) return;
    }

    private void create(Player player, String[] args) {
        if (args.length > 1) {
            String name = args[1];
            if (args.length > 2) {
                player.sendMessage(Tribes.MAIN + "Name can not contain spaces.");
                return;
            }
            for (String letter : name.toLowerCase().split("")) {
                if (!("abcdefghijklmnopqrstuvwxyz".contains(letter))) {
                    player.sendMessage(Tribes.MAIN + "Name can not contain " + Tribes.SECONDARY + letter);
                    return;
                }
            }
            if (name.length() > 10) {
                player.sendMessage(Tribes.MAIN + "Tribe name can have a maximum of 10 letters.");
                return;
            }
            if (Tribes.getInstance().getTribeHandler().tribeExists(name, false)) {
                player.sendMessage(Tribes.MAIN + "A tribe with that name already exists!");
                return;
            }
            Tribes.getInstance().getTribeHandler().newTribe(player, name);
            player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.createdNewTribe
                    .replace("{TRIBE}", name)));
        } else player.sendMessage(Tribes.MAIN + "/tribes Create [Name]");
    }

    private void join(Player player, String[] args) {
        if (args.length > 1) {
            if (Tribes.getInstance().getTribeHandler().tribeExists(args[1], false)) {
                String tribe = Tribes.getInstance().getTribeHandler().getTribe(args[1]);
                if (Tribes.getInstance().getTribeHandler().inviteHandler.hasInviteFrom(player, tribe)) {
                    Tribes.getInstance().getTribeHandler().inviteHandler.clearInvites(player);
                    Tribes.getInstance().getTribeHandler().setTribe(player, tribe, false);
                    Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.playerJoinedTribe.replace("{PLAYER}", player.getName()));
                } else player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.noInvitesFrom.replace("{TRIBE}", tribe)));
            } else if (Bukkit.getOnlinePlayers().toString().toLowerCase().contains(args[1].toLowerCase())) {
                try {
                    Player target = Bukkit.getPlayer(args[1]);
                    String tribe = Tribes.getInstance().getTribeHandler().getTribe(target);
                    if (Tribes.getInstance().getTribeHandler().inviteHandler.hasInviteFrom(player, tribe)) {
                        Tribes.getInstance().getTribeHandler().inviteHandler.clearInvites(player);
                        Tribes.getInstance().getTribeHandler().setTribe(player, tribe, false);
                        Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.playerJoinedTribe.replace("{PLAYER}", player.getName()));
                    } else
                        player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.noInvitesFrom.replace("{TRIBE}", target.getName())));
                } catch (Exception e) {
                    player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.tribeDoesntExist));
                }
            } else player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.tribeDoesntExist));
        } else player.sendMessage(Tribes.MAIN + "/tribes Join [Tribe]:[Player]");
    }

    private void show(Player player, String[] args) {
        if (args.length > 1) {
            if (Tribes.getInstance().getTribeHandler().tribeExists(args[1], false)) {
                Tribes.getInstance().getTribeHandler().showTribe(player, Tribes.getInstance().getTribeHandler().getTribe(args[1]));
                return;
            } else if (Bukkit.getOnlinePlayers().toString().toLowerCase().contains(args[1].toLowerCase())) {
                if (Tribes.getInstance().getTribeHandler().hasTribe(Bukkit.getPlayer(args[1]))) {
                    Tribes.getInstance().getTribeHandler().showTribe(player, Tribes.getInstance().getTribeHandler().getTribe(Bukkit.getPlayer(args[1])));
                } else {
                    player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.userHasNoTribe.replace("{PLAYER}", Bukkit.getPlayer(args[1]).getName())));
                }
                return;
            } else {
                player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.tribeDoesntExist));
                return;
            }
        }

        if (!Tribes.getInstance().getTribeHandler().hasTribe(player)) {
            player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.youHaveNoTribe));
            return;
        }

        Tribes.getInstance().getTribeHandler().showTribe(player, Tribes.getInstance().getTribeHandler().getTribe(player));
    }

    private void leave(Player player, String[] args) {
        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("Confirm")) {
                String tribe = Tribes.getInstance().getTribeHandler().getTribe(player);
                if (Tribes.getInstance().getTribeHandler().rank.isOwner(player)) {
                    if (Tribes.getInstance().getTribeHandler().relation.getEveryone(tribe).size() <= 1) {
                        Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.leftTribe
                                .replace("{PLAYER}", player.getName()));
                        Tribes.getInstance().getTribeHandler().rank.setNone(player);
                        Tribes.getInstance().getTribeHandler().setTribe(player, null, true);
                        Tribes.getInstance().getTribeHandler().removeTribe(tribe, true);
                    } else {
                        Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.disbandTribe
                                .replace("{PLAYER}", player.getName()));
                    }
                } else {
                    Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.leftTribe
                            .replace("{PLAYER}", player.getName()));
                    Tribes.getInstance().getTribeHandler().relation.removeMember(tribe, player.getName(), false);
                    Tribes.getInstance().getTribeHandler().relation.removeAdmin(tribe, player.getName(), false);
                    Tribes.getInstance().getTribeHandler().rank.setNone(player);
                    Tribes.getInstance().getTribeHandler().setTribe(player, null, true);
                    Tribes.getInstance().saveTribeData();
                }
            } else player.sendMessage(Tribes.MAIN + "/tribes Leave Confirm");
        } else player.sendMessage(Tribes.MAIN + "/tribes Leave Confirm");
    }

    private void disband(Player player, String[] args) {
        if (args.length > 1) {
            String tribe = args[1];
            if (Tribes.getInstance().getTribeHandler().tribeExists(tribe, false)) {
                Tribes.getInstance().getTribeHandler().disbandTribe(player, tribe, player.hasPermission("tribes.commands.tribe.disband"));
            } else player.sendMessage(Tribes.MAIN + "There is no Tribe with that name.");
        } else player.sendMessage(Tribes.MAIN + "/tribes Disband [Tribe]");
    }

    private void top(Player player, String[] args) {
        Tribes.getInstance().getTribeHandler().top.list(player);
    }

    private void color(Player player, String[] args) {
        Tribes.getInstance().getTribeHandler().colorhandler.open(player);
    }

    private void invite(Player player, String[] args) {
        if (args.length > 1) {
            Player target;
            try {
                target = Bukkit.getPlayer(args[1]);
                target.isOnline();
            } catch (Exception e) {
                player.sendMessage(Tribes.MAIN + "Invalid Player. Are they online?");
                return;
            }
            if (!Tribes.getInstance().getTribeHandler().hasTribe(target)) {
                Tribes.getInstance().getTribeHandler().inviteHandler.sendInvite(target, Tribes.getInstance().getTribeHandler().getTribe(player));
                Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().language.sentInvite
                        .replace("{PLAYER}", player.getName())
                        .replace("{TARGET}", target.getName()));
                target.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.receivedInvite
                        .replace("{TRIBE}", Tribes.getInstance().getTribeHandler().getTribe(player))
                        .replace("{PLAYER}", player.getName())
                        .replace("{TARGET}", target.getName())));
            } else player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.targetAlreadyHasTribe)
                    .replace("{PLAYER}", player.getName())
                    .replace("{TARGET}", target.getName()));
        } else player.sendMessage(Tribes.MAIN + "/tribes Invite [Player]");
    }

    private void promote(Player player, String[] args) {
        if (args.length > 1) {
            String tribe = Tribes.getInstance().getTribeHandler().getTribe(player);
            try {
                Player target = Bukkit.getPlayer(args[1]);
                if (!Tribes.getInstance().getTribeHandler().relation.sameTribe(player, target)) {
                    player.sendMessage(Util.getMessage(Tribes.getInstance().language.differentTribe
                            .replace("{PLAYER}", player.getName())
                            .replace("{TARGET}", target.getName())));
                    return;
                }
                if (Tribes.getInstance().getTribeHandler().rank.isOwner(target)) {
                    player.sendMessage(Tribes.MAIN + "You can't promote the Owner!");
                } else if (Tribes.getInstance().getTribeHandler().rank.isAdmin(target)) {
                    if (Tribes.getInstance().getTribeHandler().rank.isOwner(player)) {
                        Tribes.getInstance().getTribeHandler().rank.setAdmin(player);
                        Tribes.getInstance().getTribeHandler().rank.setOwner(target);
                        Tribes.getInstance().getTribeHandler().relation.addAdmin(tribe, player.getName());
                        Tribes.getInstance().getTribeHandler().relation.removeAdmin(tribe, target.getName(), false);
                        Tribes.getInstance().getTribeHandler().relation.removeMember(tribe, player.getName(), false);
                        Tribes.getInstance().getTribeHandler().relation.removeMember(tribe, target.getName(), false);
                        Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().language.newOwner
                                .replace("{PLAYER}", player.getName())
                                .replace("{TARGET}", target.getName()));
                        Tribes.getInstance().saveTribeData();
                    } else {
                        player.sendMessage(Tribes.SECONDARY + target.getName() + Tribes.MAIN + " is already an Admin!");
                    }
                } else {
                    Tribes.getInstance().getTribeHandler().rank.setAdmin(target);
                    Tribes.getInstance().getTribeHandler().relation.addAdmin(tribe, target.getName());
                    Tribes.getInstance().getTribeHandler().relation.removeMember(tribe, target.getName(), false);
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().language.promote
                            .replace("{PLAYER}", player.getName())
                            .replace("{TARGET}", target.getName()));
                    Tribes.getInstance().saveTribeData();
                }
            } catch (Exception e) {
                player.sendMessage(Tribes.MAIN + "Invalid player. Are they online?");
            }
        } else player.sendMessage(Tribes.MAIN + "Usage: /tribes promote [Player]");
    }

    private void demote(Player player, String[] args) {
        if (args.length > 1) {
            String tribe = Tribes.getInstance().getTribeHandler().getTribe(player);
            try {
                Player target = Bukkit.getPlayer(args[1]);
                if (!Tribes.getInstance().getTribeHandler().relation.sameTribe(player, target)) {
                    player.sendMessage(Util.getMessage(Tribes.getInstance().language.differentTribe
                            .replace("{PLAYER}", player.getName())
                            .replace("{TARGET}", target.getName())));
                    return;
                }
                if (Tribes.getInstance().getTribeHandler().rank.isOwner(target)) {
                    player.sendMessage(Tribes.MAIN + "You can't demote the Owner!");
                } else if (Tribes.getInstance().getTribeHandler().rank.isAdmin(target)) {
                    Tribes.getInstance().getTribeHandler().rank.setMember(target);
                    Tribes.getInstance().getTribeHandler().relation.addMember(tribe, target.getName());
                    Tribes.getInstance().getTribeHandler().relation.removeAdmin(tribe, target.getName(), true);
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().language.demote
                            .replace("{PLAYER}", player.getName())
                            .replace("{TARGET}", target.getName()));
                } else {
                    player.sendMessage(Tribes.SECONDARY + target.getName() + Tribes.MAIN + "Is already a Member.");
                }
            } catch (Exception e) {
                player.sendMessage(Tribes.MAIN + "Invalid player. Are they online?");
            }
        } else player.sendMessage(Tribes.MAIN + "Usage: /tribes demote [Player]");
    }

    private void kick(Player player, String[] args) {
        if (args.length > 1) {
            String tribe = Tribes.getInstance().getTribeHandler().getTribe(player);
            try {
                Player target = Bukkit.getPlayer(args[1]);
                if (!Tribes.getInstance().getTribeHandler().relation.sameTribe(player, target)) {
                    player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.differentTribe
                            .replace("{PLAYER}", player.getName())
                            .replace("{TARGET}", target.getName())));
                } else if (Tribes.getInstance().getTribeHandler().rank.isOwner(target)) {
                    player.sendMessage(Tribes.MAIN + "You can't kick the Owner!");
                } else if (player == target) {
                    player.sendMessage(Tribes.MAIN + "You can't kick yourself!");
                } else {
                    Tribes.getInstance().getTribeHandler().rank.setNone(target);
                    Tribes.getInstance().getTribeHandler().setTribe(target, tribe, true);
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().language.kick
                            .replace("{PLAYER}", player.getName())
                            .replace("{TARGET}", target.getName()));
                }
            } catch (Exception e) {
                player.sendMessage(Tribes.MAIN + "Invalid Player. Are they online?");
            }
        } else player.sendMessage(Tribes.MAIN + "/tribes Kick [Player]");
    }

    private void description(Player player, String[] args) {
        if (args.length > 1) {
            String description = "", tribe = Tribes.getInstance().getTribeHandler().getTribe(player);
            if (Tribes.getInstance().getTribeHandler().data.getTimeRemaining(tribe) >= 0) {
                player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.MAIN + "You can change your Tribe description in " +
                        Tribes.SECONDARY + (Tribes.getInstance().getTribeHandler().data.getTimeRemaining(tribe) / 1000) +
                        Tribes.MAIN + " seconds"));
                return;
            }
            Tribes.getInstance().TribeData.set("Tribes." + tribe + ".TimeStamp.Description", System.currentTimeMillis());
            Tribes.getInstance().saveTribeData();
            for (int i = 1; i < args.length; i++)
                description += " " + args[i];
            description = description.replaceFirst(" ", "");
            Tribes.getInstance().getTribeHandler().data.setDescription(tribe, description);
            Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().language.description
                    .replace("{PLAYER}", player.getName()));
            Tribes.getInstance().getTribeHandler().messageTribe(tribe, description);
        } else player.sendMessage(Tribes.MAIN + "/tribes Description [Description]");
    }

    private void enemy(Player player, String[] args) {
        if (Tribes.getInstance().getTribeHandler().hasTribe(player)) {
            if (args.length > 1) {
                String tribe;
                if (Tribes.getInstance().getTribeHandler().tribeExists(args[1], false)) {
                    tribe = Tribes.getInstance().getTribeHandler().getTribe(args[1]);
                    if (Tribes.getInstance().getTribeHandler().getTribe(player).equals(tribe)) {
                        player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyYourTribe));
                        return;
                    }
                } else if (Bukkit.getOnlinePlayers().toString().toLowerCase().contains(args[1].toLowerCase())) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (Tribes.getInstance().getTribeHandler().hasTribe(target)) {
                        if (Tribes.getInstance().getTribeHandler().relation.sameTribe(player, target)) {
                            player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemySameTribe));
                            return;
                        } else tribe = Tribes.getInstance().getTribeHandler().getTribe(target);
                    } else {
                        player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyHasNoTribe
                                .replace("{TARGET}", target.getName())));
                        return;
                    }
                } else {
                    player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyTribeDoesntExist));
                    return;
                }
                Tribes.getInstance().getTribeHandler().relation.setEnemy(Tribes.getInstance().getTribeHandler().getTribe(player), tribe);
                if (Tribes.getInstance().getTribeHandler().relation.isEnemy(Tribes.getInstance().getTribeHandler().getTribe(player), tribe)) {
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyTribeC
                            .replace("{TRIBE}", tribe)));
                    Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyTribeC
                            .replace("{TRIBE}", Tribes.getInstance().getTribeHandler().getTribe(player))));
                } else {
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyTribeA
                            .replace("{PLAYER}", player.getName())
                            .replace("{TRIBE}", tribe)));
                    Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyTribeB
                            .replace("{TRIBE}", Tribes.getInstance().getTribeHandler().getTribe(player))));
                }
            } else player.sendMessage(Tribes.MAIN + "/Enemy [Tribe:Player]");
        } else player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.enemyNoTribe));
    }

    private void truce(Player player, String[] args) {
        if (Tribes.getInstance().getTribeHandler().hasTribe(player)) {
            if (args.length > 1) {
                String tribe;
                if (Tribes.getInstance().getTribeHandler().tribeExists(args[1], false)) {
                    tribe = Tribes.getInstance().getTribeHandler().getTribe(args[1]);
                    if (Tribes.getInstance().getTribeHandler().getTribe(player).equals(tribe)) {
                        player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceYourTribe));
                        return;
                    }
                } else if (Bukkit.getOnlinePlayers().toString().toLowerCase().contains(args[1].toLowerCase())) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (Tribes.getInstance().getTribeHandler().hasTribe(target)) {
                        if (Tribes.getInstance().getTribeHandler().relation.sameTribe(player, target)) {
                            player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceSameTribe));
                            return;
                        } else tribe = Tribes.getInstance().getTribeHandler().getTribe(target);
                    } else {
                        player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceHasNoTribe
                                .replace("{TARGET}", target.getName())));
                        return;
                    }
                } else {
                    player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceTribeDoesntExist));
                    return;
                }
                Tribes.getInstance().getTribeHandler().relation.setTruce(Tribes.getInstance().getTribeHandler().getTribe(player), tribe);
                if (Tribes.getInstance().getTribeHandler().relation.isTruce(Tribes.getInstance().getTribeHandler().getTribe(player), tribe)) {
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceTribeC
                            .replace("{TRIBE}", tribe)));
                    Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceTribeC
                            .replace("{TRIBE}", Tribes.getInstance().getTribeHandler().getTribe(player))));
                } else {
                    Tribes.getInstance().getTribeHandler().messageTribe(Tribes.getInstance().getTribeHandler().getTribe(player), Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceTribeA
                            .replace("{PLAYER}", player.getName())
                            .replace("{TRIBE}", tribe)));
                    Tribes.getInstance().getTribeHandler().messageTribe(tribe, Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceTribeB
                            .replace("{TRIBE}", Tribes.getInstance().getTribeHandler().getTribe(player))));
                }
            } else player.sendMessage(Tribes.MAIN + "Usage: /tribes truce [Tribe:Player]");
        } else player.sendMessage(Tribes.getInstance().util.getMessage(Tribes.getInstance().language.truceNoTribe));
    }
}