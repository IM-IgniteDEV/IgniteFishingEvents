package com.ignitedev.igniteFishingEvents.repository;

import com.ignitedev.igniteFishingEvents.base.SuperOre;
import lombok.Getter;
import org.bukkit.Location;

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
}
