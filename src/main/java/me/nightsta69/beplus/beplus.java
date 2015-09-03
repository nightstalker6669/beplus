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

        CommandManager cm = new CommandManager();
        getCommand("money").setExecutor(cm);
        getCommand("admin").setExecutor(cm);
        getCommand("spawn").setExecutor(cm);
        Bukkit.getPluginManager().registerEvents(new VotifierConnector(), this);

        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            Bukkit.getServer().getServicesManager().register(Economy.class, new VaultConnector(), this, ServicePriority.Highest);
        }
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }


}
