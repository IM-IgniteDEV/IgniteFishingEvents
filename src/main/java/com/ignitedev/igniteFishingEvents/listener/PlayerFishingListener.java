package com.ignitedev.igniteFishingEvents.listener;

import com.ignitedev.igniteFishingEvents.base.FishingEvent;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.ignitedev.igniteFishingEvents.event.CustomFishingRewardEvent;
import com.ignitedev.igniteFishingEvents.event.WaterHookEvent;
import com.ignitedev.igniteFishingEvents.util.CrazyZombieUtility;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerFishingListener implements Listener {

  @Autowired private static FishingEventsConfiguration configuration;

  private final ThreadLocalRandom random = ThreadLocalRandom.current();
  private final PluginManager pluginManager = Bukkit.getPluginManager();

  @EventHandler
  public void onFishing(PlayerFishEvent event) {
    if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH || event.getCaught() == null) {
      return;
    }
    Player player = event.getPlayer();
    Entity caught = event.getCaught();
    Location location = caught.getLocation();
    World world = location.getWorld();

    if (world == null) {
      return;
    }
    FishingEvent fishingEvent =
        configuration.getFishingEvents().get(LocalDateTime.now().getDayOfWeek());

    if (fishingEvent == null) {
      return;
    }
    if (random.nextDouble() > fishingEvent.getActionChance()) {
      return;
    }
    processFishingReward(player, fishingEvent);

    if (fishingEvent.isMultipleActions()) {
      processCrazyZombie(player, location, world, fishingEvent);
      processWaterHook(player, fishingEvent);
    }
  }

  private void processFishingReward(Player player, FishingEvent fishingEvent) {
    if (random.nextDouble() <= fishingEvent.getItemDropChance()) {
      ItemStack reward = fishingEvent.getWeightedItemDrops().next();
      player.getInventory().addItem(reward);
      pluginManager.callEvent(new CustomFishingRewardEvent(player, reward));
    }
  }

  private void processCrazyZombie(
      Player player, Location location, World world, FishingEvent fishingEvent) {
    if (random.nextDouble() <= fishingEvent.getCrazyZombieChance()) {
      Vector direction = player.getLocation().toVector().subtract(location.toVector());
      Vector velocity = direction.multiply(configuration.getCrazyZombieHookPower());

      CrazyZombieUtility.spawnZombie(player, location, world, velocity);
      player.playSound(player.getLocation(), configuration.getCrazyZombieSpawnSound(), 1, 1);
    }
  }

  private void processWaterHook(Player player, FishingEvent fishingEvent) {
    if (random.nextDouble() <= fishingEvent.getWaterHookChance()) {
      Vector velocity =
          player.getLocation().getDirection().multiply(configuration.getWaterHookPower());
      player.setVelocity(velocity);
      pluginManager.callEvent(new WaterHookEvent(player));
    }
  }
}
