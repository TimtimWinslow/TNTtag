package net.breakzone.tnttag.listeners;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.FileManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.Message;

public class PlayerCommandPreprocessListener implements Listener {
  @EventHandler
  private void blockcommand(PlayerCommandPreprocessEvent event) {
    List<String> cmds = FileManager.getInstance().getConfig().getStringList("AllowedCommands");
    cmds.add("tag");
    cmds.add("tnttag");
    String cmdPerformed = event.getMessage().toLowerCase();
    if (ArenaManager.getManager().isInGame(event.getPlayer())) {
      for (String command : cmds) {
        if (cmdPerformed.startsWith("/" + command))
          return; 
      } 
      event.setCancelled(true);
      MessageManager.getInstance().sendErrorMessage((CommandSender)event.getPlayer(), Messages.getMessage(Message.commandError).replace("{command}", cmdPerformed));
    } 
  }
}
