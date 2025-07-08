package com.ignitedev.igniteFishingEvents.listener;

import com.ignitedev.aparecium.util.text.TextUtility;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CrazyZombieProtectListener implements Listener {

  @Autowired private static FishingEventsConfiguration configuration;

  @EventHandler
  public void onDamage(EntityDamageEvent event) {
    Entity entity = event.getEntity();

    if (!(entity instanceof Zombie)) {
      return;
    }
    EntityDamageEvent.DamageCause cause = event.getCause();

    if (cause == EntityDamageEvent.DamageCause.FALL
        || cause == EntityDamageEvent.DamageCause.FIRE_TICK
        || cause == EntityDamageEvent.DamageCause.DROWNING) {
      if (entity.getCustomName() != null
          && TextUtility.colorize(entity.getCustomName())
              .equalsIgnoreCase(TextUtility.colorize(configuration.getCrazyZombieName()))) {
        event.setCancelled(true);
      }
    }
  }
}
