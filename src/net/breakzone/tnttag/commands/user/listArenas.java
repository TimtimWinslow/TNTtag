package net.breakzone.tnttag.commands.user;

import org.bukkit.command.CommandSender;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagCommands;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class listArenas extends AbstractTagCommands {
  public listArenas() {
    super("listArenas", Messages.getMessage(Message.listArenas), null, (new Permissions()).join, true, "arenas");
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    StringBuilder arenaNames = new StringBuilder(Messages.getMessage(Message.availableArenas));
    int x = 0;
    for (Arena arena : Arena.arenaObjects) {
      x++;
      arenaNames.append(String.valueOf(arena.getName()) + ((x != Arena.arenaObjects.size()) ? ", " : "."));
    } 
    MessageManager.getInstance().sendMessage(sender, arenaNames.toString());
  }
}
