package net.breakzone.tnttag.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.FileManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagAdminCommands;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class reload extends AbstractTagAdminCommands {
  public reload() {
    super("reload", Messages.getMessage(Message.reload), null, (new Permissions()).reload, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    FileManager.getInstance().reloadConfig();
    for (Arena arena : Arena.arenaObjects) {
      arena.sendMessage(Messages.getMessage(Message.thereWasReload));
      ArenaManager.getManager().endArena(arena);
    } 
    MessageManager.getInstance().sendMessage(sender, ChatColor.GREEN + Messages.getMessage(Message.reloadComplete));
  }
}
