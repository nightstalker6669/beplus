package me.nightsta69.beplus;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

/**
 * Created by Richard on 8/30/2015.
 */
public class VotifierConnector implements Listener{
    @EventHandler
    public void onPlayerVote(VotifierEvent e) {
        Vote v = e.getVote();
        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + v.getUsername() + " voted on " + v.getServiceName() + "!");
        Player p = Bukkit.getServer().getPlayer(UUID.fromString(v.getUsername()));
        if (p == null) {
            return;
        }

        double amnt = 20;
        SettingsManager.getInstance().addBalance(v.getUsername(), amnt);
    }
}
