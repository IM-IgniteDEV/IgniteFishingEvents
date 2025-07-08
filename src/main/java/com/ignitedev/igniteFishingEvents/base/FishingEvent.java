package com.ignitedev.igniteFishingEvents.base;

import com.ignitedev.aparecium.util.collection.WeightCollection;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Data
public class FishingEvent {

  private final String eventTitle;

  private final double actionChance;

  private final double itemDropChance;
  private final double crazyZombieChance;
  private final double waterHookChance;
  private final double superOreChance;
  private final boolean multipleActions;
  // weight - item
  private final Map<Integer, ItemStack> itemDrops;

  private final transient WeightCollection<ItemStack> weightedItemDrops;

  public FishingEvent(
      String eventTitle,
      double actionChance,
      double itemDropChance,
      double crazyZombieChance,
      double waterHookChance,
      double superOreChance,
      boolean multipleActions,
      Map<Integer, ItemStack> itemDrops) {
    this.eventTitle = eventTitle;
    this.actionChance = actionChance;
    this.itemDropChance = itemDropChance;
    this.crazyZombieChance = crazyZombieChance;
    this.waterHookChance = waterHookChance;
    this.superOreChance = superOreChance;
    this.multipleActions = multipleActions;
    this.itemDrops = itemDrops;
    this.weightedItemDrops = new WeightCollection<>();
    itemDrops.forEach(weightedItemDrops::add);
  }
}
