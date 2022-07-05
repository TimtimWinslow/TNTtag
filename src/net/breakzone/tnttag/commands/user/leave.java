package net.breakzone.tnttag.commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagCommands;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class leave extends AbstractTagCommands {
  public leave() {
    super("leave", Messages.getMessage(Message.leave), null, (new Permissions()).leave, true, "l");
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    if (ArenaManager.getManager().isInGame(player)) {
      ArenaManager.getManager().removePlayer(player);
    } else {
      MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.notInArena));
    } 
  }
}
