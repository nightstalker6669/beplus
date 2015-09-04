package me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Richard on 9/3/2015.
 */
public class voteadd extends EcoCommand {
    public voteadd() {
        super("voteadd", "add voting site", "<site name> <address>");
    }

    public void run(CommandSender sender, String[] args) {
        Plugin config = SettingsManager.getInstance().getPlugin();
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "[Votifier] You did not enter enough args.");
            return;
        }

        if ((sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[Votifier] This is a console only command!");
            return;
        }
        config.getConfig().set("vote." + args[0], args[1]);
        config.saveConfig();
        Bukkit.getServer().getLogger().info("[Votifier]" + args[0] + " with address " + args[1] + " added!");
    }
}
