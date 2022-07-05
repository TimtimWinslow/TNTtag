package net.breakzone.tnttag.util;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;

public class CreateArenaData {
  private static HashMap<String, Location> lobbyLocation = new HashMap<>();
  
  private static HashMap<String, Location> arenaLocation = new HashMap<>();
  
  private static HashMap<String, Location> spectatorsLocation = new HashMap<>();
  
  public static void setArenaLocation(Player player) {
    arenaLocation.put(player.getName(), player.getLocation());
    MessageManager.getInstance().sendMessage((CommandSender)player, Messages.getMessage(Message.arenaTempSaved));
  }
  
  public static void setLobbyLocation(Player player) {
    lobbyLocation.put(player.getName(), player.getLocation());
    MessageManager.getInstance().sendMessage((CommandSender)player, Messages.getMessage(Message.lobbyTempSaved));
  }
  
  public static void setSpectatorsLocation(Player player) {
    spectatorsLocation.put(player.getName(), player.getLocation());
    MessageManager.getInstance().sendMessage((CommandSender)player, Messages.getMessage(Message.spectatorsTempSaved));
  }
  
  public static boolean check(Player player) {
    boolean b = false;
    if (lobbyLocation.get(player.getName()) == null) {
      if (!b)
        b = true; 
      MessageManager.getInstance().sendErrorMessage((CommandSender)player, Messages.getMessage(Message.lobbyMissing));
    } 
    if (arenaLocation.get(player.getName()) == null) {
      if (!b)
        b = true; 
      MessageManager.getInstance().sendErrorMessage((CommandSender)player, Messages.getMessage(Message.arenaMissing));
    } 
    if (spectatorsLocation.get(player.getName()) == null) {
      if (!b)
        b = true; 
      MessageManager.getInstance().sendErrorMessage((CommandSender)player, Messages.getMessage(Message.spectatorsMissing));
    } 
    return b;
  }
  
  public static void createArena(String player, String arenaName) {
    ArenaManager.getManager().createArena(arenaName, lobbyLocation.get(player), arenaLocation.get(player), spectatorsLocation.get(player));
    spectatorsLocation.remove(player);
    arenaLocation.remove(player);
    lobbyLocation.remove(player);
  }
}
