package net.breakzone.tnttag.commands.setup;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.CreateArenaData;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class setArenaPoint extends AbstractTagSetupCommands {
  public setArenaPoint() {
    super("setarena", Messages.getMessage(Message.setArenaPoint), null, (new Permissions()).setArena, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    CreateArenaData.setArenaLocation(player);
  }
}
