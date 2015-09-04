package me.nightsta69.beplus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Richard on 8/30/2015.
 */
public class heal extends EcoCommand {

    public heal() {
        super("heal", "heal self or player", "<player>");

    }
    public void run(CommandSender sender, String[] args) {
        if(!sender.hasPermission("me.nightsta69.admin.heal")) {
            return;
        }
        if (args.length < 1) {
            Player p = (Player) sender;
            p.setHealth(20);
            p.sendMessage(ChatColor.GREEN + "[Admin] You have been healed!");
            return;
        }
        Player p = (Player) sender;
        Player target = Bukkit.getServer().getPlayer(args[0]);
        String uuid = target.getUniqueId().toString();
        Player fromUUID = Bukkit.getServer().getPlayer(UUID.fromString(uuid));

        if (fromUUID == null) {
            p.sendMessage(ChatColor.RED + "[Admin] Could not find player " + args[0] + "!");
            return;
        }
        fromUUID.setHealth(20);
        p.sendMessage(ChatColor.GREEN + "[Admin] You healed " + fromUUID.getName() + "!");
        fromUUID.sendMessage(ChatColor.GREEN + "[Admin] You have been healed by " + p.getName());
    }
}

