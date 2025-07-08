package com.ignitedev.igniteFishingEvents.util;

import com.ignitedev.aparecium.util.RandomUtility;
import com.ignitedev.aparecium.util.collection.RandomSelector;
import com.ignitedev.aparecium.util.text.TextUtility;
import com.ignitedev.igniteFishingEvents.base.FishingEvent;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.ignitedev.igniteFishingEvents.event.CrazyZombieSpawnEvent;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class CrazyZombieUtility {

  @Autowired private static FishingEventsConfiguration configuration;

  private static final ThreadLocalRandom random = ThreadLocalRandom.current();

  private final PluginManager pluginManager = Bukkit.getPluginManager();

  public void spawnZombie(Player causePlayer, Location location, World world, Vector reverseVelocity) {

    Zombie babyZombie = ((Zombie) world.spawnEntity(location, EntityType.ZOMBIE));
    EntityEquipment equipment = babyZombie.getEquipment();
    List<PotionEffect> potionEffects = configuration.getCrazyZombiePotionEffects();

    babyZombie.setBaby();
    babyZombie.setVelocity(reverseVelocity);
    babyZombie.setCustomName(TextUtility.colorize(configuration.getCrazyZombieName()));
    babyZombie.setCustomNameVisible(true);
    babyZombie.setConversionTime(10000);
    babyZombie.addPotionEffects(potionEffects);

    pluginManager.callEvent(new CrazyZombieSpawnEvent(causePlayer, babyZombie));

    if (equipment == null) {
      return;
    }
    FishingEvent fishingEvent =
        configuration.getFishingEvents().get(LocalDateTime.now().getDayOfWeek());

    if (random.nextDouble() <= fishingEvent.getSuperOreChance()) {
      equipment.setHelmetDropChance(0);
      // we do not drop the helmet because we are spawning super ore
      equipment.setHelmet(
          new ItemStack(RandomSelector.uniform(configuration.getSuperOreMaterials()).next(random)));
    }
  }
}
