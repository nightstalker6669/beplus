package me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Richard on 9/3/2015.
 */
public class removewarp extends EcoCommand {
    public removewarp() {
        super("removewarp", "remove warp", "<location>");
    }

    public void run(CommandSender sender, String[] args) {
        Plugin config = SettingsManager.getInstance().getPlugin();
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[Warp] You can not run this command as Console.");
            return;
        }
        if (!sender.hasPermission("me.nightsta69.admin.setwarp")) {
            sender.sendMessage(ChatColor.RED + "[Warp] You do not have permission to do this!");
            return;
        }
        if (!config.getConfig().isSet("warp." + args[0])) {
            sender.sendMessage("[Warp] " + args[0] + " does not exist!");
            return;
        }
        config.getConfig().set("warp." + args[0], null);
        config.saveConfig();
        sender.sendMessage(ChatColor.GREEN + "[Warp] warp location " + args[0] + " has been removed!");
    }
}
