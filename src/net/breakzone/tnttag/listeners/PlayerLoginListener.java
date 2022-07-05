package net.breakzone.tnttag.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import net.breakzone.tnttag.TNTtag;
import net.breakzone.tnttag.util.Permissions;

public class PlayerLoginListener {
  @EventHandler(priority = EventPriority.NORMAL)
  public void latePlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    if ((player.hasPermission((new Permissions()).all) || player.hasPermission((new Permissions()).update)) && 
      TNTtag.versionDiff) {
      player.sendMessage(ChatColor.GREEN + "An update is available: " + TNTtag.name + " Version " + TNTtag.version + ".");
      player.sendMessage(ChatColor.GREEN + "Downlaod it at:");
      player.sendMessage(ChatColor.GREEN + TNTtag.link);
    } 
  }
}
