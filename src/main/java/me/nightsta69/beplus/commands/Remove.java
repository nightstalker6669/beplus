package me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by Richard on 8/30/2015.
 */
public class Remove extends EcoCommand {

    public Remove() {
        super("Remove", "Remove money from balance", "<player> <amount>");

    }

    public void run(CommandSender sender, String[] args) {
        if(!sender.hasPermission("me.nightsta69.eco.remove")) {
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
        SettingsManager.getInstance().removeBalance(name, amt);
        sender.sendMessage(ChatColor.GREEN + "Removed $" + amt + " from " + name + ". They now have $" + SettingsManager.getInstance().getBalance(name) +".");

    }
}
