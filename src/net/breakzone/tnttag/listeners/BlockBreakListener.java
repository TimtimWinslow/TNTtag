package net.breakzone.tnttag.listeners;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.breakzone.tnttag.files.GameData;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.managers.SignManager;
import net.breakzone.tnttag.util.Permissions;
import net.breakzone.tnttag.util.TNTTagSign;

public class BlockBreakListener implements Listener {
  @EventHandler
  public void Break(BlockBreakEvent event) {
    Player player = event.getPlayer();
    if (ArenaManager.getManager().isInGame(player)) {
      event.setCancelled(true);
    } else if (SignManager.getManager().getSignAtLocation(event.getBlock().getLocation()) != null) {
      if (event.getPlayer().hasPermission((new Permissions()).deleteSign)) {
        FileConfiguration fc = GameData.getGameData();
        fc.set("signs." + SignManager.getManager().getSignAtLocation(event.getBlock().getLocation()).getId(), null);
        TNTTagSign.signs.remove(SignManager.getManager().getSignAtLocation(event.getBlock().getLocation()));
        MessageManager.getInstance().sendMessage((CommandSender)event.getPlayer(), "Sign successfully removed.");
      } else {
        event.setCancelled(true);
        MessageManager.getInstance().sendErrorMessage((CommandSender)event.getPlayer(), "You do not have permission to remove a TNT Tag sign.");
      } 
    } 
  }
}
