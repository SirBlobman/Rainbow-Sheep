package com.github.sirblobman.rainbow.sheep;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.PluginManager;

import com.github.sirblobman.api.nms.EntityHandler;

public final class ListenerRainbowSheep implements Listener {
    private final RainbowSheepPlugin plugin;

    public ListenerRainbowSheep(RainbowSheepPlugin plugin) {
        this.plugin = Objects.requireNonNull(plugin, "plugin must not be null!");
    }

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this, this.plugin);
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        rename(entity);
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpawn(CreatureSpawnEvent e) {
        Entity entity = e.getEntity();
        rename(entity);
    }

    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpawn(ChunkLoadEvent e) {
        Chunk chunk = e.getChunk();
        Entity[] entityArray = chunk.getEntities();
        for(Entity entity : entityArray) rename(entity);
    }

    private void rename(Entity entity) {
        if(entity instanceof Sheep) {
            Sheep sheep = (Sheep) entity;
            rename(sheep);
        }
    }

    private void rename(Sheep sheep) {
        try {
            AdventureHelper.setCustomName(sheep, "jeb_", false);
        } catch(ClassCastException | NoClassDefFoundError ex) {
            try {
                EntityHandler entityHandler = this.plugin.getEntityHandler();
                entityHandler.setCustomNameTextOnly(sheep, "jeb_", false);
            } catch(Exception ex2) {
                sheep.setCustomName("jeb_");
                sheep.setCustomNameVisible(false);
            }
        }
    }
}
