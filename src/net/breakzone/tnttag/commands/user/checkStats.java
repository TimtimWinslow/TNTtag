package net.breakzone.tnttag.commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.util.AbstractTagCommands;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class checkStats extends AbstractTagCommands {
  public checkStats() {
    super("checkstats", Messages.getMessage(Message.checkStats), null, (new Permissions()).checkStats, true, "stats");
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    int money = getPlayerData().getInt(String.valueOf(player.getName()) + ".money");
    int tags = getPlayerData().getInt(String.valueOf(player.getName()) + ".tags");
    int taggeds = getPlayerData().getInt(String.valueOf(player.getName()) + ".taggeds");
    int wins = getPlayerData().getInt(String.valueOf(player.getName()) + ".wins");
    sendMessage(sender, Messages.getMessage(Message.stats).replace("{money}", (new StringBuilder(String.valueOf(money))).toString()).replace("{tags}", (new StringBuilder(String.valueOf(tags))).toString()).replace("{taggeds}", (new StringBuilder(String.valueOf(taggeds))).toString()).replace("{wins}", (new StringBuilder(String.valueOf(wins))).toString()));
  }
}
