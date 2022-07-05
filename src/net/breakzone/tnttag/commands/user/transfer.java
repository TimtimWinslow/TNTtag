package net.breakzone.tnttag.commands.user;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagCommands;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class transfer extends AbstractTagCommands {
  int amount;
  
  public transfer() {
    super("transfer", Messages.getMessage(Message.transfer), "coins <player> <amount>", (new Permissions()).transferCoins, true, "pay");
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    if (args.length == 2) {
      String playerName = args[1];
      if (getPlayerData().getString(playerName) != null) {
        Player player = (Player)sender;
        try {
          this.amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
          MessageManager.getInstance().sendErrorMessage(sender, Messages.getMessage(Message.invalidNumber));
          return;
        } 
        if (getPlayerData().getInt(String.valueOf(player.getName()) + ".money") >= this.amount) {
          int giverMoney = getPlayerData().getInt(String.valueOf(player.getName()) + ".money");
          int recieverMoney = getPlayerData().getInt(String.valueOf(playerName) + ".money");
          getPlayerData().set(playerName, Integer.valueOf(recieverMoney + this.amount));
          getPlayerData().set(playerName, Integer.valueOf(giverMoney - this.amount));
          sendMessage(sender, ChatColor.GREEN + "Successfully transfered " + this.amount + " to " + playerName + ".");
        } 
      } else {
        MessageManager.getInstance().sendErrorMessage(sender, String.valueOf(playerName) + " does not exist!");
      } 
    } else {
      MessageManager.getInstance().sendInsuficientArgs(sender, "transfer", "coins <player> <amount>");
    } 
  }
}
