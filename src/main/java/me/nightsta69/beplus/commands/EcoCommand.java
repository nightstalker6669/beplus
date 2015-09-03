package me.nightsta69.beplus.commands;

import org.bukkit.command.CommandSender;

/**
 * Created by Richard on 8/30/2015.
 */
public abstract class EcoCommand {
    private String name, desc, args;
    public EcoCommand(String name, String desc, String args) {
        this.name = name;
        this.desc = desc;
        this.args = args;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public String getArgs() {
        return args;
    }

    public abstract void run(CommandSender sender, String[] args);
}
