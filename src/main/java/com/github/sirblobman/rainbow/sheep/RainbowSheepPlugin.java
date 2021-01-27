package com.github.sirblobman.rainbow.sheep;

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
import org.bukkit.plugin.java.JavaPlugin;

public class RainbowSheepPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(this, this);
    }
    
    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpawn(EntitySpawnEvent e) {
        Entity entity = e.getEntity();
        if(!(entity instanceof Sheep)) return;
        Sheep sheep = (Sheep) entity;
        rename(sheep);
    }
    
    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpawn(CreatureSpawnEvent e) {
        Entity entity = e.getEntity();
        if(!(entity instanceof Sheep)) return;
        Sheep sheep = (Sheep) entity;
        rename(sheep);
    }
    
    @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
    public void onSpawn(ChunkLoadEvent e) {
        Chunk chunk = e.getChunk();
        Entity[] entityArray = chunk.getEntities();
        for(Entity entity : entityArray) {
            if(!(entity instanceof Sheep)) continue;
            Sheep sheep = (Sheep) entity;
            rename(sheep);
        }
    }
    
    private void rename(Sheep sheep) {
        sheep.setCustomName("jeb_");
        sheep.setCustomNameVisible(false);
    }
}