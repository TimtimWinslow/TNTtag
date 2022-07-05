package net.breakzone.tnttag.managers;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.breakzone.tnttag.commands.admin.add;
import net.breakzone.tnttag.commands.admin.forceEnd;
import net.breakzone.tnttag.commands.admin.forceStart;
import net.breakzone.tnttag.commands.admin.reload;
import net.breakzone.tnttag.commands.admin.remove;
import net.breakzone.tnttag.commands.admin.resetStats;
import net.breakzone.tnttag.commands.setup.createArena;
import net.breakzone.tnttag.commands.setup.createSign;
import net.breakzone.tnttag.commands.setup.deleteArena;
import net.breakzone.tnttag.commands.setup.setArenaPoint;
import net.breakzone.tnttag.commands.setup.setLobby;
import net.breakzone.tnttag.commands.setup.setSpectatorsPoint;
import net.breakzone.tnttag.commands.user.checkStats;
import net.breakzone.tnttag.commands.user.coins;
import net.breakzone.tnttag.commands.user.join;
import net.breakzone.tnttag.commands.user.leave;
import net.breakzone.tnttag.commands.user.listArenas;
import net.breakzone.tnttag.commands.user.transfer;
import net.breakzone.tnttag.util.AbstractTagAdminCommands;
import net.breakzone.tnttag.util.AbstractTagCommands;
import net.breakzone.tnttag.util.AbstractTagSetupCommands;
import net.breakzone.tnttag.util.Permissions;

public class CommandManager implements CommandExecutor {
  private ArrayList<AbstractTagCommands> cmds = new ArrayList<>();
  
  private ArrayList<AbstractTagAdminCommands> adminCmds = new ArrayList<>();
  
  private ArrayList<AbstractTagSetupCommands> setupCmds = new ArrayList<>();
  
  public CommandManager() {
    this.cmds.add(new join());
    this.cmds.add(new leave());
    this.cmds.add(new coins());
    this.cmds.add(new checkStats());
    this.cmds.add(new transfer());
    this.cmds.add(new listArenas());
    this.adminCmds.add(new add());
    this.adminCmds.add(new remove());
    this.adminCmds.add(new resetStats());
    this.adminCmds.add(new forceEnd());
    this.adminCmds.add(new forceStart());
    this.adminCmds.add(new reload());
    this.setupCmds.add(new setLobby());
    this.setupCmds.add(new setSpectatorsPoint());
    this.setupCmds.add(new setArenaPoint());
    this.setupCmds.add(new createArena());
    this.setupCmds.add(new deleteArena());
    this.setupCmds.add(new createSign());
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if (sender instanceof org.bukkit.entity.Player)
      if (cmd.getName().equalsIgnoreCase("tnttag") || cmd.getName().equalsIgnoreCase("tag")) {
        if (args.length == 0) {
          showHelp(sender);
          return true;
        } 
        ArrayList<String> a = new ArrayList<>(Arrays.asList(args));
        a.remove(0);
        if (args[0].equalsIgnoreCase("admin")) {
          if (args.length != 1) {
            for (AbstractTagAdminCommands c : this.adminCmds) {
              if (c.getName().equalsIgnoreCase(args[1])) {
                if (c.usePermissions() ? (sender.hasPermission(c.getPermission()) || sender.hasPermission((new Permissions()).all)) : !c.usePermissions()) {
                  a.remove(0);
                  if (args.length != 1) {
                    try {
                      c.onCommand(sender, a.<String>toArray(new String[a.size()]));
                    } catch (Exception e) {
                      sender.sendMessage(ChatColor.RED + "An error has occurred.");
                      e.printStackTrace();
                    } 
                    return true;
                  } 
                  MessageManager.getInstance().sendInsuficientArgs(sender, c.getName(), c.getArgs());
                  continue;
                } 
                sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
                return true;
              } 
            } 
            MessageManager.getInstance().sendErrorMessage(sender, "Invalid Command!");
            return true;
          } 
          showAdminHelp(sender);
        } else if (args[0].equalsIgnoreCase("setup")) {
          if (args.length != 1) {
            for (AbstractTagSetupCommands c : this.setupCmds) {
              if (c.getName().equalsIgnoreCase(args[1])) {
                if (c.usePermissions() ? (sender.hasPermission(c.getPermission()) || sender.hasPermission((new Permissions()).all)) : !c.usePermissions()) {
                  a.remove(0);
                  if (args.length != 1) {
                    try {
                      c.onCommand(sender, a.<String>toArray(new String[a.size()]));
                    } catch (Exception e) {
                      sender.sendMessage(ChatColor.RED + "An error has occurred.");
                      e.printStackTrace();
                    } 
                    return true;
                  } 
                  MessageManager.getInstance().sendInsuficientArgs(sender, c.getName(), c.getArgs());
                  continue;
                } 
                sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
                return true;
              } 
            } 
            MessageManager.getInstance().sendErrorMessage(sender, "Invalid Command!");
            return true;
          } 
          showCreateHelp(sender);
        } else {
          for (AbstractTagCommands c : this.cmds) {
            if (c.getName().equalsIgnoreCase(args[0]) || c.getAlias().equalsIgnoreCase(args[0])) {
              if (c.usePermissions() ? (sender.hasPermission(c.getPermission()) || sender.hasPermission((new Permissions()).all)) : !c.usePermissions()) {
                if (args.length != 0) {
                  try {
                    c.onCommand(sender, a.<String>toArray(new String[a.size()]));
                  } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "An error has occurred.");
                    e.printStackTrace();
                  } 
                  return true;
                } 
                MessageManager.getInstance().sendInsuficientArgs(sender, c.getName(), c.getArgs());
                continue;
              } 
              sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
              return true;
            } 
          } 
          MessageManager.getInstance().sendErrorMessage(sender, "Invalid Command!");
          return true;
        } 
      } else {
        MessageManager.getInstance().isConsole(sender);
      }  
    return true;
  }
  
  public void showHelp(CommandSender sender) {
    sender.sendMessage(ChatColor.RED + "=================== " + ChatColor.WHITE + ChatColor.BOLD + "TNT Tag Help " + ChatColor.RED + "===================");
    for (AbstractTagCommands c : this.cmds)
      sender.sendMessage(ChatColor.WHITE + "   -   " + ChatColor.RED + "/tnttag " + c.getName() + ((c.getArgs() == null) ? " " : (" " + c.getArgs() + " ")) + ChatColor.WHITE + c.getDescription()); 
    sender.sendMessage(ChatColor.RED + "=====================================================");
  }
  
  public void showAdminHelp(CommandSender sender) {
    sender.sendMessage(ChatColor.RED + "================ " + ChatColor.WHITE + ChatColor.BOLD + "TNT Tag  Admin Help " + ChatColor.RED + "================");
    for (AbstractTagAdminCommands c : this.adminCmds)
      sender.sendMessage(ChatColor.WHITE + "   -   " + ChatColor.RED + "/tnttag admin " + c.getName() + ((c.getArgs() == null) ? " " : (" " + c.getArgs() + " ")) + ChatColor.WHITE + c.getDescription()); 
    sender.sendMessage(ChatColor.RED + "=====================================================");
  }
  
  public void showCreateHelp(CommandSender sender) {
    sender.sendMessage(ChatColor.RED + "=============== " + ChatColor.WHITE + ChatColor.BOLD + "TNT Tag Setup Help " + ChatColor.RED + "==================");
    for (AbstractTagSetupCommands c : this.setupCmds)
      sender.sendMessage(ChatColor.WHITE + "   -   " + ChatColor.RED + "/tnttag setup " + c.getName() + ((c.getArgs() == null) ? " " : (" " + c.getArgs() + " ")) + ChatColor.WHITE + c.getDescription()); 
    sender.sendMessage(ChatColor.RED + "=====================================================");
  }
}
