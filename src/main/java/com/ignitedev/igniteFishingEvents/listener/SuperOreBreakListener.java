package com.ignitedev.igniteFishingEvents.listener;

import com.ignitedev.igniteFishingEvents.base.SuperOre;
import com.ignitedev.igniteFishingEvents.repository.SuperOreRepository;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class SuperOreBreakListener implements Listener {

  private final SuperOreRepository repository;

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Location location = event.getBlock().getLocation();
    SuperOre superOre = repository.getByLocation(location);

    if (superOre == null) {
      return;
    }
    event.setCancelled(true);
    superOre.deleteOre(event.getPlayer(), repository);
  }
}
