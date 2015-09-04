package me.nightsta69.beplus;

import me.nightsta69.beplus.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.bukkit.Bukkit.getServer;

/**
 * Created by Richard on 8/30/2015.
 */
public class CommandManager implements CommandExecutor {
    Plugin config = SettingsManager.getInstance().getPlugin();
    private ArrayList<EcoCommand> cmds = new ArrayList<EcoCommand>();
    private ArrayList<EcoCommand> admcmds = new ArrayList<EcoCommand>();
    private ArrayList<EcoCommand> spawncmds = new ArrayList<EcoCommand>();
    private ArrayList<EcoCommand> warpcmds = new ArrayList<EcoCommand>();
    public CommandManager() {
        cmds.add(new Add());
        cmds.add(new Remove());
        cmds.add(new Pay());
        admcmds.add(new tp());
        admcmds.add(new tphere());
        admcmds.add(new heal());
        admcmds.add(new proxiesend());
        spawncmds.add(new setspawn());
        warpcmds.add(new setwarp());
        warpcmds.add(new removewarp());

    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandlabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("money")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN + "[Economy]" + "Your Balance is $" + SettingsManager.getInstance().getBalance(p.getName()));
                }
                return true;
            }

            ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
            a.remove(0);

            for (EcoCommand c : cmds) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    try {c.run(sender, a.toArray(new String[a.size()]));}
                    catch (Exception e) {sender.sendMessage(ChatColor.RED + "An error has occurred"); e.printStackTrace(); }
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "Invalid Command");
        }
        if (cmd.getName().equalsIgnoreCase("admin")) {
                if (!sender.hasPermission("me.nightsta69.admin")) {
                    sender.sendMessage(ChatColor.RED + "[Admin]" + "You don't have permission to use this command");
                    return false;
                }
            for (EcoCommand c : admcmds) {
                sender.sendMessage("/admin " + c.getName() + " " + c.getArgs() + " - " + c.getDescription());
            }
                ArrayList<String> b = new ArrayList<String>(Arrays.asList(args));
                b.remove(0);

                for (EcoCommand c : admcmds) {
                    if (c.getName().equalsIgnoreCase(args[0])) {
                        try {
                            c.run(sender, b.toArray(new String[b.size()]));
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + "[Admin]" + "An error has occurred");
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
            sender.sendMessage(ChatColor.RED + "[Admin]" + "Invalid Command");

        }
        if (cmd.getName().equalsIgnoreCase("spawn")) {

            Plugin config = SettingsManager.getInstance().getPlugin();

            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "[Spawn]" + "You can not run this command as Console.");
                    return true;
                }
                if (config.getConfig().getConfigurationSection("spawn") == null) {
                    getServer().getLogger().info("[Spawn] The spawn config is null!");
                    return true;
                }
                Player p = (Player) sender;
                //Bukkit.getServer().getLogger().info("sending " + p.getName() + " to spawn location!");
                World w = getServer().getWorld(config.getConfig().getString("spawn.world"));
                double x = config.getConfig().getDouble("spawn.x");
                double y = config.getConfig().getDouble("spawn.y");
                double z = config.getConfig().getDouble("spawn.z");
                float yaw = config.getConfig().getInt("spawn.yaw");
                float pitch = config.getConfig().getInt("spawn.pitch");
                //Bukkit.getServer().getLogger().info("world:" + w + " X:" + x + " Y:" + y + " z:" + z + " yaw:" + yaw + " pitch:" + pitch);
                Location location = new Location(w, x, y, z, yaw, pitch);
                p.teleport(location);
                return true;
            }
            if (Bukkit.getServer().getPlayer(args[0]) != null) {
                if (!sender.hasPermission("me.nightsta69.admin.spawnother")) {
                    sender.sendMessage(ChatColor.RED + "[Spawn] You don't have permission to use this command");
                    return false;
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                World w = getServer().getWorld(config.getConfig().getString("spawn.world"));
                double x = config.getConfig().getDouble("spawn.x");
                double y = config.getConfig().getDouble("spawn.y");
                double z = config.getConfig().getDouble("spawn.z");
                float yaw = config.getConfig().getInt("spawn.yaw");
                float pitch = config.getConfig().getInt("spawn.pitch");
                //Bukkit.getServer().getLogger().info("world:" + w + " X:" + x + " Y:" + y + " z:" + z + " yaw:" + yaw + " pitch:" + pitch);
                Location location = new Location(w, x, y, z, yaw, pitch);
                target.sendMessage(ChatColor.GREEN + "[Spawn] You have been sent to spawn by " + sender.getName());
                target.teleport(location);
                return true;
            }

            ArrayList<String> b = new ArrayList<String>(Arrays.asList(args));
            b.remove(0);

            for (EcoCommand c : spawncmds) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    try {
                        c.run(sender, b.toArray(new String[b.size()]));
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "[Spawn] An error has occurred");
                        e.printStackTrace();
                    }
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "[Spawn] Invalid Command");
            return false;
        }
        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.GREEN + "[Warp] Current list of warps:");
                Set<String> warps = config.getConfig().getConfigurationSection("warp").getKeys(false);
                for (String c : warps) {
                    sender.sendMessage(ChatColor.GREEN + " - " + c);
                }
                return true;
            }

            if (args.length == 1) {
                Player p = (Player) sender;
                World w = getServer().getWorld(config.getConfig().getString("warp." + args[0] + ".world"));
                double x = config.getConfig().getDouble("warp." + args[0] + ".x");
                double y = config.getConfig().getDouble("warp." + args[0] + ".y");
                double z = config.getConfig().getDouble("warp." + args[0] + ".z");
                float yaw = config.getConfig().getInt("warp." + args[0] + ".yaw");
                float pitch = config.getConfig().getInt("warp." + args[0] + ".pitch");
                Location location = new Location(w, x, y, z, yaw, pitch);
                p.teleport(location);
                return true;
            }

            ArrayList<String> b = new ArrayList<String>(Arrays.asList(args));
            b.remove(0);

            for (EcoCommand c : warpcmds) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    try {
                        c.run(sender, b.toArray(new String[b.size()]));
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "[Warp] An error has occurred");
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.RED + "[BEPlus] Invalid Command");
        return false;
    }
}
