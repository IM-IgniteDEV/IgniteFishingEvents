package com.ignitedev.igniteFishingEvents.base;

import com.ignitedev.aparecium.util.MathUtility;
import com.ignitedev.igniteFishingEvents.IgniteFishingEvents;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.ignitedev.igniteFishingEvents.repository.SuperOreRepository;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import lombok.Data;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

@Data
public class SuperOre {

  @Autowired private static FishingEventsConfiguration configuration;

  private final Location location;
  private final Material type;
  private final int min;
  private final int max;

  private int particleTaskId;

  public void spawnOre(IgniteFishingEvents plugin, SuperOreRepository repository) {
    World world = this.location.getWorld();

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
  }

  public void deleteOre(SuperOreRepository repository) {
    World world = this.location.getWorld();

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
  }
}
