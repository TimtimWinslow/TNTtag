package net.breakzone.tnttag.commands.admin;

import org.bukkit.command.CommandSender;

import net.breakzone.tnttag.util.AbstractTagAdminCommands;
import net.breakzone.tnttag.util.Permissions;

public class resetStats extends AbstractTagAdminCommands {
  public resetStats() {
    super("resetstats", "Reset stats for a player.", "<player>", (new Permissions()).resetStats, true);
  }
  
  public void onCommand(CommandSender sender, String[] args) {}
}
