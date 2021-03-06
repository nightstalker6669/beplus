package me.nightsta69.beplus;

import me.nightsta69.beplus.commands.*;
import mkremins.fanciful.FancyMessage;
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
    private ArrayList<EcoCommand> votecmds = new ArrayList<EcoCommand>();


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
        votecmds.add(new voteadd());
        votecmds.add(new voteremove());

    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandlabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("money")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN + "[Economy] " + "Your Balance is $" + SettingsManager.getInstance().getBalance(p.getName()));
                }
                return true;
            }
            if (args[0].equals("?")) {
                for (EcoCommand c : cmds) {
                    sender.sendMessage(ChatColor.GREEN + "[Economy] /money " + c.getName() + " " + c.getArgs() + " - " + c.getDescription());
                }
                return true;
            }

            ArrayList<String> a = new ArrayList<String>(Arrays.asList(args));
            a.remove(0);

            for (EcoCommand c : cmds) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    try {c.run(sender, a.toArray(new String[a.size()]));} catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "[Economy] An error has occurred");
                        e.printStackTrace();
                    }
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "[Economy] Invalid Command");
            return false;
        }
        if (cmd.getName().equalsIgnoreCase("admin")) {
                if (!sender.hasPermission("me.nightsta69.admin")) {
                    sender.sendMessage(ChatColor.RED + "[Admin]" + "You don't have permission to use this command");
                    return true;
                }
            if ((args.length == 0) || args[0].equals("?")) {
                for (EcoCommand c : admcmds) {
                    sender.sendMessage(ChatColor.GREEN + "[Admin] /admin " + c.getName() + " " + c.getArgs() + " - " + c.getDescription());
                }
                return true;
            }
                ArrayList<String> b = new ArrayList<String>(Arrays.asList(args));
                b.remove(0);

                for (EcoCommand c : admcmds) {
                    if (c.getName().equalsIgnoreCase(args[0])) {
                        try {
                            c.run(sender, b.toArray(new String[b.size()]));
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + "[Admin] " + "An error has occurred");
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
            sender.sendMessage(ChatColor.RED + "[Admin] " + "Invalid Command");
            return true;

        }
        if (cmd.getName().equalsIgnoreCase("spawn")) {

            Plugin config = SettingsManager.getInstance().getPlugin();

            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "[Spawn] " + "You can not run this command as Console.");
                    return true;
                }
                if (config.getConfig().getConfigurationSection("spawn") == null) {
                    getServer().getLogger().info("[Spawn] The spawn config is null!");
                    return true;
                }

                Player p = (Player) sender;
                World w = getServer().getWorld(config.getConfig().getString("spawn.world"));
                double x = config.getConfig().getDouble("spawn.x");
                double y = config.getConfig().getDouble("spawn.y");
                double z = config.getConfig().getDouble("spawn.z");
                float yaw = config.getConfig().getInt("spawn.yaw");
                float pitch = config.getConfig().getInt("spawn.pitch");
                Location location = new Location(w, x, y, z, yaw, pitch);
                p.teleport(location);
                return true;
            }
            if (args[0].equals("?")) {
                if (!sender.hasPermission("me.nightsta69.admin")) {
                    return true;
                }
                for (EcoCommand c : spawncmds) {
                    sender.sendMessage(ChatColor.GREEN + "[Spawn] /spawn " + c.getName() + " " + c.getArgs() + " - " + c.getDescription());
                }
                return true;
            }
            if (Bukkit.getServer().getPlayer(args[0]) != null) {
                if (!sender.hasPermission("me.nightsta69.admin.spawnother")) {
                    sender.sendMessage(ChatColor.RED + "[Spawn] You don't have permission to use this command");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "[Admin] Could not find player " + args[0] + "!");
                    return true;
                }
                World w = getServer().getWorld(config.getConfig().getString("spawn.world"));
                double x = config.getConfig().getDouble("spawn.x");
                double y = config.getConfig().getDouble("spawn.y");
                double z = config.getConfig().getDouble("spawn.z");
                float yaw = config.getConfig().getInt("spawn.yaw");
                float pitch = config.getConfig().getInt("spawn.pitch");
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
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("warp")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.GREEN + "[Warp] Current list of warps:");
                Set<String> warps = config.getConfig().getConfigurationSection("warp").getKeys(false);
                for (String c : warps) {
                    sender.sendMessage(ChatColor.GREEN + " - " + c);
                    return true;
                }
                return true;
            }
            if (args[0].equals("?")) {
                if (!sender.hasPermission("me.nightsta69.admin")) {
                    return true;
                }
                for (EcoCommand c : warpcmds) {
                    sender.sendMessage(ChatColor.GREEN + "[Warp] /warp " + c.getName() + " " + c.getArgs() + " - " + c.getDescription());
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
            sender.sendMessage(ChatColor.RED + "[Warp] Invalid Command");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("vote")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.GREEN + "[Votifier] Current list of Voting sites:");

                Set<String> votes = config.getConfig().getConfigurationSection("vote").getKeys(true);
                for (String c : votes) {
                    String b = config.getConfig().getString("vote." + c);
                    new FancyMessage(" - " + ChatColor.UNDERLINE + c)
                            .color(ChatColor.GOLD)
                            .link(b)
                            .tooltip(b)
                            .send(sender);
                }
                return true;
            }
            ArrayList<String> b = new ArrayList<String>(Arrays.asList(args));
            b.remove(0);

            for (EcoCommand c : votecmds) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    try {
                        c.run(sender, b.toArray(new String[b.size()]));
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "[Votifier] An error has occurred");
                        e.printStackTrace();
                    }
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "[Votifier] Invalid Command");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "[BEPlus] Invalid Command");
        return true;
    }
}
