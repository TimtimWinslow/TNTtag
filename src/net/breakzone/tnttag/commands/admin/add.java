package net.breakzone.tnttag.commands.admin;

import org.bukkit.command.CommandSender;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagAdminCommands;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class add extends AbstractTagAdminCommands {
  int amount;
  
  public add() {
    super("add", Messages.getMessage(Message.add), "<coins|wins|tags|taggeds> <player>", (new Permissions()).add, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    if (args.length != 0 && args.length != 1) {
      if (getPlayerData().getString(args[0]) != null) {
        int coins, wins, tags, taggeds;
        String player = args[0];
        try {
          this.amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.invalidNumber));
          return;
        } 
        String str1;
        switch ((str1 = args[1]).hashCode()) {
          case -1548436345:
            if (!str1.equals("taggeds"))
              break; 
            taggeds = getPlayerData().getInt(String.valueOf(player) + ".taggeds");
            getPlayerData().set(String.valueOf(player) + ".taggeds", Integer.valueOf(taggeds - this.amount));
            MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.nowHasTaggeds).replace("{amount}", (new StringBuilder(String.valueOf(getPlayerData().getInt(String.valueOf(player) + ".taggeds")))).toString()).replace("{player}", player));
            return;
          case 3552281:
            if (!str1.equals("tags"))
              break; 
            tags = getPlayerData().getInt(String.valueOf(player) + ".tags");
            getPlayerData().set(String.valueOf(player) + ".tags", Integer.valueOf(tags + this.amount));
            MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.nowHasTags).replace("{amount}", (new StringBuilder(String.valueOf(getPlayerData().getInt(String.valueOf(player) + ".tags")))).toString()).replace("{player}", player));
            return;
          case 3649559:
            if (!str1.equals("wins"))
              break; 
            wins = getPlayerData().getInt(String.valueOf(player) + ".wins");
            getPlayerData().set(String.valueOf(player) + ".wins", Integer.valueOf(wins + this.amount));
            MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.nowHasWins).replace("{amount}", (new StringBuilder(String.valueOf(getPlayerData().getInt(String.valueOf(player) + ".wins")))).toString()).replace("{player}", player));
            return;
          case 94839810:
            if (!str1.equals("coins"))
              break; 
            coins = getPlayerData().getInt(String.valueOf(player) + ".money");
            if (this.amount <= coins) {
              getPlayerData().set(String.valueOf(player) + ".money", Integer.valueOf(coins + this.amount));
              MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.nowHasCoins).replace("{amount}", (new StringBuilder(String.valueOf(getPlayerData().getInt(String.valueOf(player) + ".money")))).toString()).replace("{player}", player));
            } else {
              MessageManager.getInstance().sendErrorMessage(sender, "Amount (" + this.amount + ") is bigger than " + player + "'s coins (" + coins + ")");
            } 
            return;
        } 
        MessageManager.getInstance().sendInvalidArgs(sender, "add", "<coins|wins|tags|taggeds> <player>");
      } else {
        MessageManager.getInstance().sendErrorMessage(sender, "Player " + args[0] + " could not be find.");
      } 
    } else {
      MessageManager.getInstance().sendInsuficientArgs(sender, "add", "<coins|wins|tags|taggeds> <player>");
    } 
  }
}
