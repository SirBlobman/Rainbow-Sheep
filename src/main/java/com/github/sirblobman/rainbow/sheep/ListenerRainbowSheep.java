package com.github.sirblobman.rainbow.sheep;

import org.jetbrains.annotations.NotNull;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import com.github.sirblobman.api.nms.EntityHandler;
import com.github.sirblobman.api.plugin.listener.PluginListener;

public final class ListenerRainbowSheep extends PluginListener<RainbowSheepPlugin> {
    public ListenerRainbowSheep(@NotNull RainbowSheepPlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        rename(entity);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSpawn(CreatureSpawnEvent e) {
        Entity entity = e.getEntity();
        rename(entity);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSpawn(ChunkLoadEvent e) {
        Chunk chunk = e.getChunk();
        Entity[] entityArray = chunk.getEntities();
        for (Entity entity : entityArray) {
            rename(entity);
        }
    }

    private void rename(@NotNull Entity entity) {
        if (entity instanceof Sheep) {
            Sheep sheep = (Sheep) entity;
            rename(sheep);
        }
    }

    private void rename(@NotNull Sheep sheep) {
        try {
            AdventureHelper.setCustomName(sheep, "jeb_", false);
        } catch (ClassCastException | NoClassDefFoundError ex) {
            try {
                RainbowSheepPlugin plugin = getPlugin();
                EntityHandler entityHandler = plugin.getEntityHandler();
                entityHandler.setCustomNameTextOnly(sheep, "jeb_", false);
            } catch (Exception ex2) {
                sheep.setCustomName("jeb_");
                sheep.setCustomNameVisible(false);
            }
        }
    }
}
