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
public class voteremove extends EcoCommand {
    public voteremove() {
        super("voteremove", "remove voting site", "<site name>");
    }

    public void run(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "[Votifier] You did not enter enough args.");
            return;
        }
        Plugin config = SettingsManager.getInstance().getPlugin();
        if ((sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[Votifier] This is a console only command!");
            return;
        }

        config.getConfig().set("vote." + args[0], null);
        config.saveConfig();
        Bukkit.getServer().getLogger().info("[Votifier]" + args[0] + " removed!");
    }
}
