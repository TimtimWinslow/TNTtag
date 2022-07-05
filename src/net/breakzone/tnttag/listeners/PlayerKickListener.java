package net.breakzone.tnttag.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import net.breakzone.tnttag.managers.ArenaManager;

public class PlayerKickListener implements Listener {
  @EventHandler
  public void Kick(PlayerKickEvent event) {
    if (ArenaManager.getManager().isInGame(event.getPlayer()))
      ArenaManager.getManager().removePlayer(event.getPlayer()); 
  }
}
