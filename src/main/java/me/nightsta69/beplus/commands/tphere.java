package me.nightsta69.beplus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Richard on 8/30/2015.
 */
public class tphere extends EcoCommand {

    public tphere() {
        super("tphere", "teleport to player", "<player>");

    }
    public void run(CommandSender sender, String[] args) {
        if(!sender.hasPermission("me.nightsta69.admin.tphere")) {
            return;
        }
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "[Admin] You did not enter enough args.");
            return;
        }
        Player p = (Player) sender;
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(ChatColor.RED + "[Admin] Could not find player " + args[0] + "!");
            return;
        }
        target.teleport(p.getLocation());
        target.sendMessage(ChatColor.RED + "[Admin] You have been summoned by " + p.getName() + "!");
    }
}

