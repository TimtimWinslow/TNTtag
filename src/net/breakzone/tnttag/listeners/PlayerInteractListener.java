package net.breakzone.tnttag.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.breakzone.tnttag.files.Config;
import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.managers.SignManager;
import net.breakzone.tnttag.util.Message;
import net.breakzone.tnttag.util.Permissions;
import net.breakzone.tnttag.util.TNTTagSign;

public class PlayerInteractListener implements Listener {
  @EventHandler
  public void Place(PlayerInteractEvent event) {
    Action action = event.getAction();
    Player player = event.getPlayer();
    if (action == Action.RIGHT_CLICK_BLOCK) {
      if (event.getClickedBlock().getType() == Material.WALL_SIGN || event.getClickedBlock().getType() == Material.SIGN_POST) {
        TNTTagSign sign = SignManager.getManager().getSignAtLocation(event.getClickedBlock().getLocation());
        if (sign != null) {
          if ((SignManager.getManager()).tempSign.containsKey(player.getName())) {
            MessageManager.getInstance().sendMessage((CommandSender)event.getPlayer(), Messages.getMessage(Message.signAlreadyExists));
          } else if (player.hasPermission((new Permissions()).join) || !Config.getConfig().getBoolean("usepermissions")) {
            if (!ArenaManager.getManager().isInGame(player)) {
              ArenaManager.getManager().addPlayers(player, sign.getArena());
              return;
            } 
            MessageManager.getInstance().sendErrorMessage((CommandSender)player, Messages.getMessage(Message.leaveCurrentArena));
            return;
          } 
        } else if ((SignManager.getManager()).tempSign.containsKey(player.getName())) {
          event.setCancelled(true);
          Sign s = (Sign)event.getClickedBlock().getState();
          SignManager.getManager().addSign(event.getClickedBlock().getLocation(), (String)(SignManager.getManager()).tempSign.get(player.getName()), s);
          MessageManager.getInstance().sendMessage((CommandSender)event.getPlayer(), Messages.getMessage(Message.signCreated));
          (SignManager.getManager()).tempSign.remove(player.getName());
        } 
        return;
      } 
      if (ArenaManager.getManager().isInGame(player))
        event.setCancelled(true); 
    } 
  }
}
