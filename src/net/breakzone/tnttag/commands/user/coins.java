package net.breakzone.tnttag.commands.user;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.util.AbstractTagCommands;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class coins extends AbstractTagCommands {
  public coins() {
    super("coins", Messages.getMessage(Message.coins), "", (new Permissions()).checkCoins, true, "money");
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    sendMessage(sender, Messages.getMessage(Message.checkCoins).replace("{amount}", getPlayerData().getString(player.getName())));
  }
}
