package net.breakzone.tnttag.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagAdminCommands;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class forceStart extends AbstractTagAdminCommands {
  public forceStart() {
    super("forcestart", Messages.getMessage(Message.forceStart), null, (new Permissions()).forceStart, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    if (args.length == 0) {
      MessageManager.getInstance().sendInsuficientArgs(sender, "forcestart", "<ArenaName>");
      StringBuilder arenaNames = new StringBuilder(Messages.getMessage(Message.availableArenas));
      int x = 0;
      for (Arena arena : Arena.arenaObjects) {
        x++;
        arenaNames.append(String.valueOf(arena.getName()) + ((x != Arena.arenaObjects.size()) ? ", " : "."));
      } 
      MessageManager.getInstance().sendMessage(sender, arenaNames.toString());
      return;
    } 
    String arenaName = args[0];
    if (ArenaManager.getManager().getArena(arenaName) != null) {
      ArenaManager.getManager().forceStartArena(arenaName, player);
    } else {
      MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.invalidArena));
      StringBuilder arenaNames = new StringBuilder(Messages.getMessage(Message.availableArenas));
      int x = 0;
      for (Arena arena : Arena.arenaObjects) {
        x++;
        arenaNames.append(String.valueOf(arena.getName()) + ((x != Arena.arenaObjects.size()) ? ", " : "."));
      } 
      MessageManager.getInstance().sendMessage(sender, arenaNames.toString());
    } 
  }
}
