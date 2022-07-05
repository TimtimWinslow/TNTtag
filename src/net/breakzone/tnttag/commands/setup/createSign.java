package net.breakzone.tnttag.commands.setup;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.breakzone.tnttag.TNTtag;
import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.managers.SignManager;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.Arena;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class createSign extends AbstractTagSetupCommands {
  public createSign() {
    super("createsign", Messages.getMessage(Message.createSign), "<arena>", (new Permissions()).createSign, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    if (args.length == 0) {
      MessageManager.getInstance().sendInsuficientArgs(sender, "createsign", "<arena>");
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
    if (ArenaManager.getManager().getArena(arenaName) == null) {
      MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.arenaNotFound));
      StringBuilder arenaNames = new StringBuilder(Messages.getMessage(Message.availableArenas));
      int x = 0;
      for (Arena arena : Arena.arenaObjects) {
        x++;
        arenaNames.append(String.valueOf(arena.getName()) + ((x != Arena.arenaObjects.size()) ? ", " : "."));
      } 
      MessageManager.getInstance().sendMessage(sender, arenaNames.toString());
      return;
    } 
    (SignManager.getManager()).tempSign.put(player.getName(), arenaName);
    MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.clickToCreateSign).replace("{arena}", arenaName));
    addToTempList(player);
  }
  
  private void addToTempList(final Player player) {
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) TNTtag.main, new Runnable() {
          public void run() {
            if ((SignManager.getManager()).tempSign.containsKey(player.getName()))
              (SignManager.getManager()).tempSign.remove(player.getName()); 
          }
        },  1200L);
  }
}
