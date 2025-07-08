package com.ignitedev.igniteFishingEvents.listener;

import com.ignitedev.aparecium.util.EntityUtility;
import com.ignitedev.igniteFishingEvents.base.FishingEvent;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.ignitedev.igniteFishingEvents.util.CrazyZombieUtility;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerFishingListener implements Listener {

  @Autowired private static FishingEventsConfiguration configuration;

  private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

  @EventHandler
  public void onFishing(PlayerFishEvent event) {
    if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
      return;
    }
    Entity caught = event.getCaught();

    if (caught == null) {
      return;
    }
    FishingEvent fishingEvent =
        configuration.getFishingEvents().get(LocalDateTime.now().getDayOfWeek());

    if (!(ThreadLocalRandom.current().nextDouble() <= fishingEvent.getActionChance())) {
      return;
    }
    Location location = caught.getLocation();
    World world = location.getWorld();
    Player player = event.getPlayer();

    if (world == null) {
      return;
    }
    if (ThreadLocalRandom.current().nextDouble() <= fishingEvent.getItemDropChance()) {
      ItemStack randomItem = fishingEvent.getWeightedItemDrops().next();

      player.getInventory().addItem(randomItem);

      if (!fishingEvent.isMultipleActions()) {
        return;
      }
    }
    if (threadLocalRandom.nextDouble() <= fishingEvent.getCrazyZombieChance()) {
      Vector from = new Vector(location.getX(), location.getY(), location.getZ());
      Vector to =
          new Vector(
              player.getLocation().getX(),
              player.getLocation().getY(),
              player.getLocation().getZ());
      Vector waterToPlayer = to.subtract(from);

      CrazyZombieUtility.spawnZombie(
          location, world, waterToPlayer.multiply(configuration.getCrazyZombieHookPower()));
      player.playSound(player.getLocation(), configuration.getCrazyZombieSpawnSound(), 1, 1);

      if (!fishingEvent.isMultipleActions()) {
        return;
      }
    }
    if (ThreadLocalRandom.current().nextDouble() <= fishingEvent.getWaterHookChance()) {
      player.setVelocity(
          player.getLocation().getDirection().multiply(configuration.getWaterHookPower()));
    }
  }
}
