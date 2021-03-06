package me.nightsta69.beplus.commands;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.nightsta69.beplus.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Richard on 9/3/2015.
 */
public class proxiesend extends EcoCommand {

    public proxiesend() {

            super("proxiesend", "Send player to server","<player> <server>");
    }
    public void run(CommandSender sender, String[] args) {
        if (!sender.hasPermission("me.nightsta69.admin.proxiesend")) {
            return;
        }
        if (args.length < 2) {
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.RED + "You did not enter enough args.");
                return;
            } else {
                Bukkit.getServer().getLogger().info("You did not enter enough args.");
                return;
            }
        }
        Player ptarget = Bukkit.getServer().getPlayer(args[0]);
        if (ptarget == null) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(ChatColor.RED + "Could not find player " + args[0] + "!");
            } else {
                Bukkit.getServer().getLogger().info("Could not find player " + args[0] + "!");
                return;
            }
        }
        Player player = Bukkit.getServer().getPlayer(args[0]);
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(args[1]);
            player.sendPluginMessage(SettingsManager.getInstance().getPlugin(), "BungeeCord", out.toByteArray());
        }
}
