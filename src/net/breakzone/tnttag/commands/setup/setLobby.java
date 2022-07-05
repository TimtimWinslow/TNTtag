package net.breakzone.tnttag.commands.setup;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.CreateArenaData;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class setLobby extends AbstractTagSetupCommands {
  public setLobby() {
    super("setlobby", Messages.getMessage(Message.setLobby), null, (new Permissions()).setLobby, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    CreateArenaData.setLobbyLocation(player);
  }
}
