package me.nightsta69.beplus.me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Richard on 8/30/2015.
 */
public class Add extends EcoCommand {

    public Add() {
        super("Add", "Add money to balance", "<player> <amount>");

    }

    public void run(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        if(!sender.hasPermission("me.nightsta69.eco.add")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
            return;
        }
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "You did not enter enough args." );
            return;
        }
        String name = args[0];
        Double amt;
        try { amt = Double.parseDouble(args[1]);}
        catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Invalid Number");
            return;
        }
        SettingsManager.getInstance().addBalance(name, amt);
        Player target = Bukkit.getServer().getPlayer(args[0]);
        sender.sendMessage(ChatColor.GREEN + "Added $" + amt + " to " + name + ". They now have $" + SettingsManager.getInstance().getBalance(name) +".");
        target.sendMessage(ChatColor.GREEN + p.getName() + "gave you " + amt +".");

    }
}
