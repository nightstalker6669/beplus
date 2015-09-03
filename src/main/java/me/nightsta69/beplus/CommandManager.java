package me.nightsta69.beplus;


import me.nightsta69.beplus.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Richard on 8/30/2015.
 */
public class CommandManager implements CommandExecutor {

    private ArrayList<EcoCommand> cmds = new ArrayList<EcoCommand>();
    private ArrayList<EcoCommand> admcmds = new ArrayList<EcoCommand>();

    public CommandManager() {
        cmds.add(new Add());
        cmds.add(new Remove());
        cmds.add(new Pay());
        admcmds.add(new tp());
        admcmds.add(new tphere());
        admcmds.add(new heal());
        admcmds.add(new proxiesend());

    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandlabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("money")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.GREEN + "Your Balance is $" + SettingsManager.getInstance().getBalance(p.getName()));
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
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!sender.hasPermission("me.nightsta69.admin")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
                    return false;
                }
                ArrayList<String> b = new ArrayList<String>(Arrays.asList(args));
                b.remove(0);

                for (EcoCommand c : admcmds) {
                    if (c.getName().equalsIgnoreCase(args[0])) {
                        try {c.run(sender, b.toArray(new String[b.size()]));}
                        catch (Exception e) {sender.sendMessage(ChatColor.RED + "An error has occurred"); e.printStackTrace(); }
                        return true;
                    }
                }
                sender.sendMessage(ChatColor.RED + "Invalid Command");
            }
        }
        return true;
    }
}
