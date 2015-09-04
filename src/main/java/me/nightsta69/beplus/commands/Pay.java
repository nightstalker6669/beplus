package me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Richard on 8/30/2015.
 */
public class Pay extends EcoCommand {

    public Pay() {
        super("Pay", "pay player from your balance", "<player> <amount>");

    }

    public void run(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "You did not enter enough args." );
            return;
        }
        Player p = (Player) sender;
        String receiver = args[0];
        String name = p.getName();
        Double amt;

        try { amt = Double.parseDouble(args[1]);}
        catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Invalid Number");
            return;
        }
        if (!SettingsManager.getInstance().payBalance(name, receiver, amt)) {
           sender.sendMessage(ChatColor.RED +  "Insufficient Funds!");
            return;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        sender.sendMessage(ChatColor.GREEN + "You paid $" + amt + " to " + receiver + ". You now have $" + SettingsManager.getInstance().getBalance(name) +".");
        target.sendMessage(ChatColor.GREEN + p.getName() + "paid you " + amt +".");


    }
}
