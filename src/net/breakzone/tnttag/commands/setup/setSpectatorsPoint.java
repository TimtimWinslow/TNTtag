package net.breakzone.tnttag.commands.setup;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.CreateArenaData;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class setSpectatorsPoint extends AbstractTagSetupCommands {
  public setSpectatorsPoint() {
    super("setspec", Messages.getMessage(Message.setSpectatorsPoint), null, (new Permissions()).setSpec, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    CreateArenaData.setSpectatorsLocation(player);
  }
}
