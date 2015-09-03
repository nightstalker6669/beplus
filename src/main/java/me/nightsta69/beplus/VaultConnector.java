package me.nightsta69.beplus;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.List;

/**
 * Created by Richard on 8/30/2015.
 */
public class VaultConnector implements Economy{
    public boolean isEnabled() {
        return SettingsManager.getInstance().getPlugin().isEnabled();
    }

    public String getName() {
        return "BE+ Economy";
    }

    public boolean hasBankSupport() {
        return false;
    }

    public int fractionalDigits() {
        return 2;
    }

    public String format(double amnt) {
        return "$" + String.valueOf(amnt);
    }

    public String currencyNamePlural() {
        return "BusinessBucks";
    }

    public String currencyNameSingular() {
        return "BusinessBuck";
    }

    public boolean hasAccount(String s) {
        return false;
    }

    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    public boolean hasAccount(String s, String s1) {
        return false;
    }

    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }

    public double getBalance(String name) {
        return SettingsManager.getInstance().getBalance(name);
    }

    public double getBalance(OfflinePlayer offlinePlayer) {
        return 0;
    }

    public double getBalance(String name, String world) {
        return getBalance(name);
    }

    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return 0;
    }

    public boolean has(String player, double amnt) {
        return SettingsManager.getInstance().getBalance(player) >= amnt;
    }

    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return false;
    }

    public boolean has(String player, String world, double amnt) {
        return has(player, amnt);
    }

    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return false;
    }

    public EconomyResponse withdrawPlayer(String player, double amnt) {
        return new EconomyResponse(amnt, SettingsManager.getInstance().getBalance(player) - amnt, SettingsManager.getInstance().removeBalance(player, amnt) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "Insufficent Funds");
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        return null;
    }

    public EconomyResponse withdrawPlayer(String player, String world, double amnt) {
        return withdrawPlayer(player, amnt);
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, String receiver, double amnt) {
        return withdrawPlayer(player, amnt);
    }

    public EconomyResponse depositPlayer(String name, double amnt) {
        SettingsManager.getInstance().addBalance(name, amnt);
        return new EconomyResponse(amnt, SettingsManager.getInstance().getBalance(name), EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        return null;
    }

    public EconomyResponse depositPlayer(String name, String world, double amnt) {
        return depositPlayer(name, amnt);
    }

    public EconomyResponse depositPlayer(OfflinePlayer name, String receiver, double amnt) {
        return depositPlayer(name, amnt);
    }

    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    public EconomyResponse deleteBank(String s) {
        return null;
    }

    public EconomyResponse bankBalance(String s) {
        return null;
    }

    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    public List<String> getBanks() {
        return null;
    }

    public boolean createPlayerAccount(String name) {
        SettingsManager.getInstance().setBalance(name, 0);
        return true;
    }

    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    public boolean createPlayerAccount(String name, String world) {
        return createPlayerAccount(name);
    }

    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}
