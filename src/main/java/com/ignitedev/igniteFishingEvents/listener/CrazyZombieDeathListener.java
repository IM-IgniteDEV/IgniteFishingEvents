package com.ignitedev.igniteFishingEvents.listener;

import com.ignitedev.aparecium.util.text.TextUtility;
import com.ignitedev.igniteFishingEvents.IgniteFishingEvents;
import com.ignitedev.igniteFishingEvents.base.SuperOre;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.ignitedev.igniteFishingEvents.repository.SuperOreRepository;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;

@RequiredArgsConstructor
public class CrazyZombieDeathListener implements Listener {

  @Autowired private static FishingEventsConfiguration configuration;

  private final SuperOreRepository repository;
  private final IgniteFishingEvents plugin;

  @EventHandler
  public void onDeath(EntityDeathEvent event) {
    if (event.getEntity() instanceof Zombie entity) {
      if (entity.getCustomName() == null
          || !TextUtility.colorize(entity.getCustomName())
              .equalsIgnoreCase(TextUtility.colorize(configuration.getCrazyZombieName()))) {
        return;
      }
      Location location = entity.getLocation();

      if (location.getWorld() == null) {
        return;
      }
      Location oreSpawnLocation = location.getWorld().getHighestBlockAt(location).getLocation();
      EntityEquipment equipment = entity.getEquipment();

      if (equipment == null || equipment.getHelmet() == null) {
        return;
      }
      Material type = equipment.getHelmet().getType();

      if (!configuration.getSuperOreMaterials().contains(type)) {
        return;
      }
      new SuperOre(oreSpawnLocation, type).spawnOre(plugin, repository);
    }
  }
}
