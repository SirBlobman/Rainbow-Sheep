package com.github.sirblobman.rainbow.sheep;

import org.jetbrains.annotations.NotNull;

import org.bukkit.entity.Entity;

import net.kyori.adventure.text.Component;

public final class AdventureHelper {
    public static void setCustomName(@NotNull Entity entity, @NotNull String text, boolean visible) {
        Component component = Component.text(text);
        entity.customName(component);
        entity.setCustomNameVisible(visible);
    }
}
