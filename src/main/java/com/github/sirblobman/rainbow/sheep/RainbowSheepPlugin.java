package com.github.sirblobman.rainbow.sheep;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.sirblobman.api.nms.EntityHandler;
import com.github.sirblobman.api.nms.MultiVersionHandler;

public final class RainbowSheepPlugin extends JavaPlugin {
    private final EntityHandler entityHandler;

    public RainbowSheepPlugin() {
        MultiVersionHandler multiVersionHandler = new MultiVersionHandler(this);
        this.entityHandler = multiVersionHandler.getEntityHandler();
    }

    @Override
    public void onEnable() {
        new ListenerRainbowSheep(this).register();
    }

    public EntityHandler getEntityHandler() {
        return this.entityHandler;
    }
}
