package com.shermservers.nblogin.config;

import com.shermservers.nblogin.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NamedConfig {
    protected Config config;
    protected String name;

    NamedConfig(Config config, String name) {
        this.config = config;
        this.name = name;
    }
}

enum ConfigType {
    YAML,
    NONE
}

class ConfigData {
    protected static List<String> YAMLExtensions = Arrays.asList(".yml", ".yaml");
    protected static List<String> AllExtensions = Utils.combine(YAMLExtensions);

    protected static ConfigType getConfigType(String extension) {
        if (YAMLExtensions.contains(extension)) return ConfigType.YAML;
        return ConfigType.NONE;
    }

    protected static List<String> getExtensions(ConfigType type) {
        switch (type) {
            case NONE -> { return null; }
            case YAML -> { return YAMLExtensions; }
        }
        return null;
    }

    protected static Config makeConfig(ConfigType type, String path) {
        switch (type) {
            case NONE -> { return null; }
            case YAML -> { return new YAMLConfig(path); }
        }
        return null;
    }
}

public class ConfigHandler {
    private static ConfigHandler s_Instance = null;
    private final List<NamedConfig> m_Configs;

    public ConfigHandler() {
        s_Instance = this;
        m_Configs = new ArrayList<NamedConfig>();
    }

    @NotNull
    public static ConfigHandler get() {
        return s_Instance;
    }

    @NotNull
    private String extractName(String path) {

        return Utils.removeSuffix(Utils.pop(path.split("/")), ConfigData.AllExtensions);
    }

    @Nullable
    private String extractExtension(String path) {
        String[] splitpath = path.split("\\.");
        if (splitpath.length > 1) {
            return Utils.pop(path.split("\\."));
        } else {
            return null;
        }
    }

    @NotNull
    public Config addConfig(Config config, String name) {
        this.m_Configs.add(new NamedConfig(config, name));
        return config;
    }

    @NotNull
    public Config addConfig(Config config) {
        return this.addConfig(config, this.extractName(config.getPath()));
    }

    @NotNull
    public Config makeConfig(String path, String name) {
        Config config = ConfigData.makeConfig(ConfigData.getConfigType(this.extractExtension(path)), path);
        return this.addConfig(config, name);
    }

    @NotNull
    public Config makeConfig(String path) {
        return this.makeConfig(path, this.extractName(path));
    }

    @Nullable
    private NamedConfig findNamedConfig(String name) {
        for (NamedConfig namedConfig : this.m_Configs) {
            if (namedConfig.name.equals(name)) return namedConfig;
        }
        return null;
    }

    @NotNull
    public Boolean exists(String name) {
        return this.findNamedConfig(name) != null;
    }

    @Nullable
    public Config getConfig(String name) {
        if (this.exists(name)) {
            return this.findNamedConfig(name).config;
        } else {
            return null;
        }
    }
}
