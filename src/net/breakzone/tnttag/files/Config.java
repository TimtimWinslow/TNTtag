package net.breakzone.tnttag.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
  private static FileConfiguration config = null;
  
  private static File configFile = null;
  
  public enum PlayerType {
    Player, TNT;
  }
  
  static String[] broadcastTimes = new String[] { 
      "1", "2", "3", "4", "5", "10", "15", "20", "30", "60", 
      "120" };
  
  static String[] commands = new String[] { "time" };
  
  public static void load() {
    config = getConfig();
    config.options().header(
        "############################################################\n# +------------------------------------------------------+ #\n# |                TNT Tag Configuration                 | #\n# +------------------------------------------------------+ #\n############################################################");
    config.addDefault("BroadcastTimes", Arrays.asList(broadcastTimes));
    config.addDefault("AllowedCommands", Arrays.asList(commands));
    config.addDefault("checkforupdates", Boolean.valueOf(true));
    config.addDefault("Speed.Players", Integer.valueOf(2));
    config.addDefault("Speed.TNTs", Integer.valueOf(4));
    config.addDefault("minplayers", Integer.valueOf(12));
    config.addDefault("maxplayers", Integer.valueOf(24));
    config.addDefault("usepermissions", Boolean.valueOf(false));
    config.addDefault("mysql", Boolean.valueOf(false));
    getConfig().options().copyDefaults(true);
    save();
  }
  
  public static void reload() {
    if (configFile == null)
      configFile = new File("plugins/TNTTag/config.yml"); 
    config = (FileConfiguration)YamlConfiguration.loadConfiguration(configFile);
  }
  
  public static FileConfiguration getConfig() {
    if (config == null)
      reload(); 
    return config;
  }
  
  public static void save() {
    if (config == null || configFile == null)
      return; 
    try {
      config.save(configFile);
    } catch (IOException ex) {
      Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save configFile to " + configFile, ex);
    } 
  }
  
  public static Integer getSpeed(PlayerType type) {
    int speed = 1;
    switch (type) {
      case Player:
        speed = getConfig().getInt("Speed.Players") - 1;
        break;
      case TNT:
        speed = getConfig().getInt("Speed.TNTs") - 1;
        break;
    } 
    return Integer.valueOf(speed);
  }
  
  public static boolean checkforupdate() {
    return getConfig().getBoolean("checkforupdates");
  }
  public static boolean mysql() {return getConfig().getBoolean("mysq;");
  }
  
  public static String getLineNumber(Integer i) {
    int i1 = i.intValue() + 1;
    return getConfig().getString("signs.line" + i1);
  }
}
