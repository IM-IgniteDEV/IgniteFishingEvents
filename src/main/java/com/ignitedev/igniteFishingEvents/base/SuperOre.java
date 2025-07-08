package com.ignitedev.igniteFishingEvents.base;

import com.ignitedev.aparecium.util.MathUtility;
import com.ignitedev.igniteFishingEvents.IgniteFishingEvents;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.ignitedev.igniteFishingEvents.event.SuperOreDestroyEvent;
import com.ignitedev.igniteFishingEvents.event.SuperOreSpawnEvent;
import com.ignitedev.igniteFishingEvents.repository.SuperOreRepository;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import lombok.Data;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

@Data
public class SuperOre {

  @Autowired private static FishingEventsConfiguration configuration;

  private final Location location;
  private final Material type;

  private int particleTaskId;

  public void spawnOre(Zombie cause, IgniteFishingEvents plugin, SuperOreRepository repository) {
    World world = this.location.getWorld();
    PluginManager pluginManager = Bukkit.getPluginManager();

    if (world != null) {
      world.getBlockAt(this.location).setType(this.type);
    }
    this.particleTaskId =
        Bukkit.getScheduler()
            .runTaskTimerAsynchronously(
                plugin,
                () -> {
                  if (world == null) {
                    return;
                  }
                  world.spawnParticle(configuration.getSuperOreParticle(), this.location, 1);
                },
                10,
                10)
            .getTaskId();
    repository.cacheOre(this);
    pluginManager.callEvent(new SuperOreSpawnEvent(cause, this));
  }

  public void deleteOre(Player destroyer, SuperOreRepository repository) {
    World world = this.location.getWorld();
    PluginManager pluginManager = Bukkit.getPluginManager();

    Bukkit.getScheduler().cancelTask(this.particleTaskId);

    if (world == null) {
      return;
    }
    world.getBlockAt(this.location).setType(Material.AIR);
    world.dropItemNaturally(
        this.location,
        new ItemStack(
            this.type,
            MathUtility.getRandomNumber(
                configuration.getSuperOreMinDropAmount(),
                configuration.getSuperOreMaxDropAmount())));
    repository.removeOre(this.location);
    pluginManager.callEvent(new SuperOreDestroyEvent(destroyer, this));
  }
}
