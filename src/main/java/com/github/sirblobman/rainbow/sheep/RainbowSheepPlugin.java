package com.github.sirblobman.rainbow.sheep;

import org.jetbrains.annotations.NotNull;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.sirblobman.api.core.CorePlugin;
import com.github.sirblobman.api.nms.EntityHandler;
import com.github.sirblobman.api.nms.MultiVersionHandler;
import com.github.sirblobman.api.plugin.ConfigurablePlugin;
import com.github.sirblobman.api.update.SpigotUpdateManager;

public final class RainbowSheepPlugin extends ConfigurablePlugin {
    @Override
    public void onLoad() {
        // Empty Method
    }

    @Override
    public void onEnable() {
        registerListeners();
        registerUpdateChecker();
    }

    @Override
    public void onDisable() {
        // Empty Method
    }

    public @NotNull EntityHandler getEntityHandler() {
        MultiVersionHandler multiVersionHandler = getMultiVersionHandler();
        return multiVersionHandler.getEntityHandler();
    }

    private void registerListeners() {
        new ListenerRainbowSheep(this).register();
    }

    private void registerUpdateChecker() {
        CorePlugin corePlugin = JavaPlugin.getPlugin(CorePlugin.class);
        SpigotUpdateManager updateManager = corePlugin.getSpigotUpdateManager();
        updateManager.addResource(this, 77075L);
    }
}
