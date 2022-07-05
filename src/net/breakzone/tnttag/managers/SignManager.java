package net.breakzone.tnttag.managers;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;

import net.breakzone.tnttag.files.Signs;
import net.breakzone.tnttag.util.TNTTagSign;

public class SignManager {
  public HashMap<String, String> tempSign = new HashMap<>();
  
  public static SignManager manager = new SignManager();
  
  public static SignManager getManager() {
    return manager;
  }
  
  public void updateSigns(String arena) {
    for (TNTTagSign sign : TNTTagSign.signs)
      sign.update(); 
  }
  
  public TNTTagSign getSignAtLocation(Location location) {
    for (TNTTagSign sign : TNTTagSign.signs) {
      if (sign.getSignLocation().equals(location))
        return sign; 
    } 
    return null;
  }
  
  public String getArenaOnSign(Location location) {
    for (TNTTagSign sign : TNTTagSign.signs) {
      if (sign.getSignLocation().equals(location))
        return sign.getArena(); 
    } 
    return null;
  }
  
  public void addSign(Location location, String arena, Sign sign) {
    FileConfiguration fc = Signs.getSignsData();
    int id = getAvailableSignId();
    fc.set("signs." + id, null);
    String path = "signs." + id + ".";
    int x = location.getBlockX(), y = location.getBlockY(), z = location.getBlockZ();
    String world = location.getWorld().getName();
    fc.set(String.valueOf(path) + "world", world);
    fc.set(String.valueOf(path) + "x", Integer.valueOf(x));
    fc.set(String.valueOf(path) + "y", Integer.valueOf(y));
    fc.set(String.valueOf(path) + "z", Integer.valueOf(z));
    fc.set(String.valueOf(path) + "line.1", sign.getLine(0));
    fc.set(String.valueOf(path) + "line.2", sign.getLine(1));
    fc.set(String.valueOf(path) + "line.3", sign.getLine(2));
    fc.set(String.valueOf(path) + "line.4", sign.getLine(3));
    fc.set(String.valueOf(path) + "arena", arena);
    Signs.save();
  }
  
  public void loadSigns() {
    FileConfiguration fc = Signs.getSignsData();
    if (fc.getString("signs") != null)
      for (String id : fc.getConfigurationSection("signs").getKeys(false)) {
        World world = Bukkit.getWorld(fc.getString("signs." + id + ".world"));
        int x = fc.getInt("signs." + id + ".x");
        int y = fc.getInt("signs." + id + ".y");
        int z = fc.getInt("signs." + id + ".z");
        Location location = new Location(world, x, y, z);
        String arena = fc.getString("signs." + id + ".arena");
        String line1 = fc.getString("signs." + id + ".line.1");
        String line2 = fc.getString("signs." + id + ".line.2");
        String line3 = fc.getString("signs." + id + ".line.3");
        String line4 = fc.getString("signs." + id + ".line.4");
      }  
  }
  
  private int getAvailableSignId() {
    ArrayList<Integer> signIds = new ArrayList<>();
    for (TNTTagSign tagSign : TNTTagSign.signs)
      signIds.add(tagSign.getId()); 
    for (int i = 0; i < 99999; i++) {
      if (!signIds.contains(Integer.valueOf(i)))
        return i; 
    } 
    return 0;
  }
}
