package com.shermservers.nblogin.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public interface Config {
    public String getPath();

    public YamlConfiguration getConfig();

    public File getFile();
}
