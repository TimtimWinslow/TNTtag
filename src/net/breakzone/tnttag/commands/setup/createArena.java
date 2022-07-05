package net.breakzone.tnttag.commands.setup;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.CreateArenaData;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;

public class createArena extends AbstractTagSetupCommands {
  public createArena() {
    super("createarena", Messages.getMessage(Message.createArena), "<ArenaName>", (new Permissions()).createArena, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {
    Player player = (Player)sender;
    if (args.length == 0) {
      MessageManager.getInstance().sendInsuficientArgs(sender, "createarena", "<ArenaName>");
      return;
    } 
    String arenaName = args[0];
    if (!CreateArenaData.check(player)) {
      CreateArenaData.createArena(player.getName(), arenaName);
      MessageManager.getInstance().sendMessage(sender, Messages.getMessage(Message.arenaCreated).replace("{arena}", arenaName));
    } 
  }
}
