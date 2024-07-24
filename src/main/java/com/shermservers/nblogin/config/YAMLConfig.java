package com.shermservers.nblogin.config;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class YAMLConfig implements Config {
    private final String m_Path;
    private final File m_File;
    private final YamlConfiguration m_Config;

    //path relative to plugin config folder
    public YAMLConfig(String path) {
        this.m_Path = path;
        this.m_File = new File(path);
        this.m_Config = YamlConfiguration.loadConfiguration(this.m_File);
    }

    @Override
    public String getPath() {
        return this.m_Path;
    }

    @Override
    public YamlConfiguration getConfig() {
        return this.m_Config;
    }

    @Override
    public File getFile() {
        return this.m_File;
    }
}
