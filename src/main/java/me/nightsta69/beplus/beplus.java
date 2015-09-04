package me.nightsta69.beplus;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Created by Richard on 8/30/2015.
 */
public class beplus extends JavaPlugin {



    public void onDisable(){

    }


    public void onEnable(){

        SettingsManager.getInstance().setup(this);
        Bukkit.getServer().getLogger().info("[Economy] Checking for vault.");
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            Bukkit.getServer().getLogger().info("[Economy] Vault found, registering Economy.");
            Bukkit.getServer().getServicesManager().register(Economy.class, new VaultConnector(), this, ServicePriority.Highest);
        } else {

        }
        Bukkit.getServer().getLogger().info("[Votifier] Checking for Votifier.");
        if (Bukkit.getServer().getPluginManager().getPlugin("Votifier") != null) {
            Bukkit.getServer().getLogger().info("[Votifier] Votifier found, Registering Votifier Events.");
            Bukkit.getPluginManager().registerEvents(new VotifierConnector(), this);
        } else {
            Bukkit.getServer().getLogger().severe("[Economy] Votifier not found, please install Votifier!");
        }
        CommandManager cm = new CommandManager();
        Bukkit.getServer().getLogger().info("[Economy] Register Economy Commands.");
        getCommand("money").setExecutor(cm);
        Bukkit.getServer().getLogger().info("[Admin] Register Admin Commands.");
        getCommand("admin").setExecutor(cm);
        Bukkit.getServer().getLogger().info("[Spawn] Register Spawn Commands.");
        getCommand("spawn").setExecutor(cm);
        Bukkit.getServer().getLogger().info("[Warp] Register Warp Commands.");
        getCommand("warp").setExecutor(cm);
        Bukkit.getServer().getLogger().info("[BungeeCord] Registering Outgoing BungeeCord Channel.");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }


}
