package me.nightsta69.beplus;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {

    private SettingsManager() { }

    private static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
        return instance;
    }

    private Plugin p;
    private FileConfiguration config;
    private File cfile;

    public void setup(Plugin p) {
        this.p = p;

        if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();

        cfile = new File(p.getDataFolder(), "money.yml");

        if (!cfile.exists()) {
            try { cfile.createNewFile(); }
            catch (Exception e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(cfile);
    }

    /*
     * money:
     *   pogostick29: 29
     *   blah: 67
     */
    public Double getBalance(String p) {
        return config.getDouble("money." + p.toLowerCase());
    }

    public void addBalance(String p, double amnt) {
        setBalance(p, getBalance(p) + amnt);
    }

    public boolean removeBalance(String p, double amnt) {
        if (getBalance(p) - amnt < 0) return false;
        setBalance(p, getBalance(p) - amnt);
        return true;
    }

    public boolean payBalance(String p, String r, double amnt) {
        if (getBalance(p) - amnt < 0) return false;
        setBalance(p, getBalance(p) - amnt);
        setBalance(r, getBalance(r) + amnt);
        return true;
    }
    public void setBalance(String p, double amnt) {
        config.set("money." + p.toLowerCase(), amnt);
        save();
    }

    private void save() {
        try { config.save(cfile); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public Plugin getPlugin() {
        return p;
    }
}