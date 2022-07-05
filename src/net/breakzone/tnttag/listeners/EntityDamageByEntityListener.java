package net.breakzone.tnttag.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.breakzone.api.tnttag.PlayerTagEvent;
import net.breakzone.tnttag.files.Config;
import net.breakzone.tnttag.files.Messages;
import net.breakzone.tnttag.managers.ArenaManager;
import net.breakzone.tnttag.managers.FileManager;
import net.breakzone.tnttag.managers.MessageManager;
import net.breakzone.tnttag.util.FireworkEffectPlayer;
import net.breakzone.tnttag.util.Message;

public class EntityDamageByEntityListener implements Listener {
  FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
  
  @EventHandler(priority = EventPriority.NORMAL)
  public void dmg(EntityDamageByEntityEvent event) throws Exception {
    if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
      Player damager = (Player)event.getDamager();
      Player victim = (Player)event.getEntity();
      if (ArenaManager.getManager().isInGame(victim))
        if (ArenaManager.getManager().isTNT(damager)) {
          ArenaManager.getManager().addTNTPlayer(victim);
          ArenaManager.getManager().removeTNTPlayer(damager);
          MessageManager.getInstance().sendInGamePlayersMessage(Messages.getMessage(Message.playerIsIt).replace("{player}", victim.getName()), ArenaManager.get(victim));
          Bukkit.getServer().getPluginManager().callEvent((Event)new PlayerTagEvent(damager, victim));
          int tags = FileManager.getInstance().getPlayerData().getInt(String.valueOf(damager.getName()) + ".tags");
          int taggeds = FileManager.getInstance().getPlayerData().getInt(String.valueOf(victim.getName()) + ".taggeds");
          FileManager.getInstance().getPlayerData().set(String.valueOf(damager.getName()) + ".tags", Integer.valueOf(tags + 1));
          FileManager.getInstance().getPlayerData().set(String.valueOf(victim.getName()) + ".taggeds", Integer.valueOf(taggeds + 1));
          for (PotionEffect potionEffect : damager.getActivePotionEffects())
            damager.removePotionEffect(potionEffect.getType()); 
          for (PotionEffect potionEffect : victim.getActivePotionEffects())
            victim.removePotionEffect(potionEffect.getType()); 
          victim.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, Config.getSpeed(Config.PlayerType.TNT).intValue()));
          damager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, Config.getSpeed(Config.PlayerType.Player).intValue()));
          victim.getInventory().setHelmet(new ItemStack(Material.TNT, 1));
          damager.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
          damager.getInventory().setItem(0, new ItemStack(Material.AIR, 1));
          victim.getInventory().setItem(0, new ItemStack(Material.TNT, 1));
          FireworkEffect effect = FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.CREEPER).build();
          this.fplayer.playFirework(victim.getWorld(), victim.getLocation(), effect);
          victim.setHealth(20.0D);
        } else {
          victim.setHealth(20.0D);
        }  
    } 
  }
}
