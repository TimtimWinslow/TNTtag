package net.breakzone.tnttag.commands.setup;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.breakzone.tnttag.TNTtag;
import net.breakzone.tnttag.files.GameData;
import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class deleteArena extends AbstractTagSetupCommands {
  public deleteArena() {
    super("deletearena", Messages.getMessage(Message.deleteArena), "<ArenaName|confirm>", (new Permissions()).deleteArena, true);
  }
  
  private static HashMap<String, String> strings = new HashMap<>();
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    if (args.length == 0) {
      MessageManager.getInstance().sendInsuficientArgs(sender, "deletearena", "<ArenaName>");
      StringBuilder arenaNames = new StringBuilder(Messages.getMessage(Message.availableArenas));
      int x = 0;
      for (Arena arena : Arena.arenaObjects) {
        x++;
        arenaNames.append(String.valueOf(arena.getName()) + ((x != Arena.arenaObjects.size()) ? ", " : "."));
      } 
      MessageManager.getInstance().sendMessage(sender, arenaNames.toString());
      return;
    } 
    if (args[0].equalsIgnoreCase("confirm")) {
      if (strings.get(player.getName()) != null) {
        FileConfiguration fc = GameData.getGameData();
        fc.set("arenas." + (String)strings.get(player.getName()), null);
        Arena.arenaObjects.remove(ArenaManager.getManager().getArena(strings.get(player.getName())));
        MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.arenaDeleted).replace("{arena}", strings.get(player.getName())));
        strings.remove(player.getName());
        return;
      } 
      MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.unspecifiedArena));
      return;
    } 
    String arenaName = args[0];
    if (ArenaManager.getManager().getArena(arenaName) != null) {
      MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.confirmationMessage));
      strings.put(player.getName(), arenaName);
      addPlayer(player.getName());
    } else {
      MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.invalidArena));
    } 
  }
  
  private void addPlayer(final String name) {
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) TNTtag.main, new Runnable() {
          public void run() {
            if (deleteArena.strings.get(name) != null)
              deleteArena.strings.remove(name); 
          }
        },  100L);
  }
}
