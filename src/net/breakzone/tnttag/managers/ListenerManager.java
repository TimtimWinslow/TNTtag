package net.breakzone.tnttag.managers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.breakzone.tnttag.TNTtag;
import net.breakzone.tnttag.listeners.BlockBreakListener;
import net.breakzone.tnttag.listeners.DropItemListener;
import net.breakzone.tnttag.listeners.EntityDamageByEntityListener;
import net.breakzone.tnttag.listeners.EntityDamageListener;
import net.breakzone.tnttag.listeners.FoodLevelChangeListener;
import net.breakzone.tnttag.listeners.InventoryClickListener;
import net.breakzone.tnttag.listeners.PlayerCommandPreprocessListener;
import net.breakzone.tnttag.listeners.PlayerInteractListener;
import net.breakzone.tnttag.listeners.PlayerKickListener;
import net.breakzone.tnttag.listeners.PlayerQuitListener;

public class ListenerManager {
  public static void registerEvents(TNTtag plugin) {
    PluginManager pm = plugin.getServer().getPluginManager();
    pm.registerEvents((Listener)new BlockBreakListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new DropItemListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new EntityDamageByEntityListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new EntityDamageListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new FoodLevelChangeListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new InventoryClickListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new PlayerCommandPreprocessListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new PlayerInteractListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new PlayerQuitListener(), (Plugin)plugin);
    pm.registerEvents((Listener)new PlayerKickListener(), (Plugin)plugin);
  }
}
