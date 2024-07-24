package com.shermservers.nblogin;

import com.shermservers.nblogin.config.ConfigHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class NBlogin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigHandler configHandler = new ConfigHandler();
        configHandler.makeConfig("somepath/another/my/config.yml");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
