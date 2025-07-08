package com.ignitedev.igniteFishingEvents.repository;

import com.ignitedev.igniteFishingEvents.base.SuperOre;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SuperOreRepository {

  @Getter private final Map<Location, SuperOre> spawnedSuperOres = new HashMap<>();

  public void cacheOre(SuperOre superOre) {
    spawnedSuperOres.put(superOre.getLocation(), superOre);
  }

  public void removeOre(Location location) {
    spawnedSuperOres.remove(location);
  }

  @Nullable
  public SuperOre getByLocation(Location location) {
    for (Map.Entry<Location, SuperOre> entry : spawnedSuperOres.entrySet()) {
      Location loc = entry.getKey();
      SuperOre superOre = entry.getValue();
      World world = loc.getWorld();

      if (world == null) {
        return null;
      }
      if (world.equals(location.getWorld())) {
        if (location.getBlockX() == loc.getBlockX()
            && location.getBlockY() == loc.getBlockY()
            && location.getBlockZ() == loc.getBlockZ()) {
          return superOre;
        }
      }
    }
    return null;
  }
}
