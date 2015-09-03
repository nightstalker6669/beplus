package me.nightsta69.beplus.commands;

import me.nightsta69.beplus.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Richard on 9/3/2015.
 */
public class setspawn extends EcoCommand {
    public setspawn() {
        super("setspawn", "set spawn", "none");
    }
    public void run(CommandSender sender, String[] args) {
        Plugin config = SettingsManager.getInstance().getPlugin();
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You can not run this command as Console.");
            return;
        }
        Player p = (Player) sender;

            if(!sender.hasPermission("me.nightsta69.spawn.setspawn")){
                sender.sendMessage(ChatColor.RED + "You do not have permission to do this!");
                return;
            }
            config.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
            config.getConfig().set("spawn.x", p.getLocation().getX());
            config.getConfig().set("spawn.y", p.getLocation().getY());
            config.getConfig().set("spawn.z", p.getLocation().getZ());
            config.getConfig().set("spawn.yaw", p.getLocation().getYaw());
            config.getConfig().set("spawn.pitch", p.getLocation().getPitch());
            config.saveConfig();
            sender.sendMessage(ChatColor.GREEN + "Spawn has been set!");
            return;


    }
}
