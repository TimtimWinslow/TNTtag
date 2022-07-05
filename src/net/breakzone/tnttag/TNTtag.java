package net.breakzone.tnttag;

import java.sql.Connection;
import java.util.logging.Logger;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.breakzone.metrics.Metrics;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.CommandManager;
import net.breakzone.tnttag.managers.FileManager;
import net.breakzone.tnttag.managers.ListenerManager;
import net.breakzone.tnttag.sql.MySQL;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Permissions;

public class TNTtag extends JavaPlugin {
  FileManager settings = FileManager.getInstance();
  
  Permissions perms = new Permissions();
  
  protected Logger log;
  
  public MySQL sql;
  
  public static TNTtag main;
  
  public static boolean versionDiff = false;
  
  public static boolean update = false;
  
  public static String name;
  
  public static String version;
  
  public static String link;
  
  Connection connection;
  String host, database, username, password;
  int port;
  
  @Override
  public void onEnable() {
      Metrics metrics = new Metrics(this);

    this.log = getLogger();
    main = this;
    this.settings.setup((Plugin)this);
    this.perms.loadPermissions(this);
    ListenerManager.registerEvents(this);
    getCommand("tnttag").setExecutor((CommandExecutor)new CommandManager());
    getCommand("tag").setExecutor((CommandExecutor)new CommandManager());
    this.log.info("Has Been Enabled!");

  }
  
  public void onDisable() {
    main = null;
    this.log = getLogger();
    this.settings.saveConfig();
    for (Arena arena : Arena.arenaObjects) {
      arena.sendMessage("There was a reload");
      ArenaManager.getManager().endArena(arena);
    } 
    this.perms.unloadPermissions(this);
    this.log.info("Has Been Disabled!");

  }
}
