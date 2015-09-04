package me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Richard on 9/3/2015.
 */
public class setwarp extends EcoCommand {
    public setwarp() {
        super("setwarp", "set warp", "<location>");
    }

    public void run(CommandSender sender, String[] args) {
        Plugin config = SettingsManager.getInstance().getPlugin();
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[Warp] You can not run this command as Console.");
            return;
        }
        Player p = (Player) sender;

        if (!sender.hasPermission("me.nightsta69.admin.setwarp")) {
            sender.sendMessage(ChatColor.RED + "[Warp] You do not have permission to do this!");
            return;
        }
        config.getConfig().set("warp." + args[0] + ".world", p.getLocation().getWorld().getName());
        config.getConfig().set("warp." + args[0] + ".x", p.getLocation().getX());
        config.getConfig().set("warp." + args[0] + ".y", p.getLocation().getY());
        config.getConfig().set("warp." + args[0] + ".z", p.getLocation().getZ());
        config.getConfig().set("warp." + args[0] + ".yaw", p.getLocation().getYaw());
        config.getConfig().set("warp." + args[0] + ".pitch", p.getLocation().getPitch());
        config.saveConfig();
        sender.sendMessage(ChatColor.GREEN + "[Warp] warp location " + args[0] + " has been set!");
    }
}
