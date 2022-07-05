package net.breakzone.tnttag.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Message;

public class MessageManager {
  static MessageManager instance = new MessageManager();
  
  String prefix = ChatColor.WHITE + "[" + ChatColor.RED + "TNT Tag" + ChatColor.WHITE + "]: ";
  
  public static MessageManager getInstance() {
    return instance;
  }
  
  public void sendErrorMessage(CommandSender sender, String errormsg) {
    sender.sendMessage(ChatColor.DARK_RED + "Error:" + ChatColor.RED + " " + errormsg);
  }
  
  public void sendInsuficientArgs(CommandSender sender, String command, String args) {
    sendErrorMessage(sender, "Insufficient args.");
    sender.sendMessage(ChatColor.RED + "Usage: /tag " + command + " " + args);
  }
  
  public void sendInvalidArgs(CommandSender sender, String command, String args) {
    sendErrorMessage(sender, "Invalid args.");
    sender.sendMessage(ChatColor.RED + "Usage: /tag " + command + " " + args);
  }
  
  public void sendMessage(CommandSender sender, String s) {
    sender.sendMessage(String.valueOf(this.prefix) + ChatColor.GRAY + s);
  }
  
  public void sendInGamePlayersMessage(String s, Arena arena) {
    arena.sendMessage(s);
  }
  
  public void sendWinMessage(Player player2, String s, Arena arena) {
    for (String p : arena.getPlayers()) {
      Player player = Bukkit.getPlayer(p);
      player.sendMessage(Messages.getMessage(Message.lineBreak));
      player.sendMessage(Messages.getMessage(Message.winMessage).replace("{player}", (new StringBuilder(String.valueOf(s))).toString()));
      player.sendMessage(Messages.getMessage(Message.lineBreak));
    } 
    player2.sendMessage(Messages.getMessage(Message.lineBreak));
    player2.sendMessage(Messages.getMessage(Message.winMessage).replace("{player}", (new StringBuilder(String.valueOf(s))).toString()));
    player2.sendMessage(Messages.getMessage(Message.lineBreak));
  }
  
  public void isConsole(CommandSender sender) {
    sender.sendMessage(String.valueOf(this.prefix) + ChatColor.GRAY + "This Command Can Only Be Done By In-Game Players");
  }
  
  public void noperm(CommandSender sender) {
    sender.sendMessage(String.valueOf(this.prefix) + ChatColor.GRAY + "You do not have permission to perform this command!");
  }
  
  public void sendNoPrefixMessage(CommandSender sender, String s) {
    sender.sendMessage(ChatColor.GRAY + s);
  }
}
