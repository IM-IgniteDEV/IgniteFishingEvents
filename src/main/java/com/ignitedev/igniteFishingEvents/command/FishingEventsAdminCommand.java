package com.ignitedev.igniteFishingEvents.command;

import com.ignitedev.aparecium.acf.BaseCommand;
import com.ignitedev.aparecium.acf.annotation.*;
import com.ignitedev.aparecium.util.MessageUtility;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.DayOfWeek;

@CommandAlias("fishingeventsadmin|fishingevents|feadmin|fea")
@CommandPermission("ignitefishingevents.admin")
@RequiredArgsConstructor
public class FishingEventsAdminCommand extends BaseCommand {

  @Autowired private static FishingEventsConfiguration configuration;

  @HelpCommand
  @Default
  @CommandPermission("ignitefishingevents.admin.help")
  public void onHelp(Player player) {
    MessageUtility.send(
        player,
        configuration.getPrefix()
            + "Usage: /fe add <weight> <day for example: MONDAY> - &8(write held item in config)");
    MessageUtility.send(
        player, configuration.getPrefix() + "Usage: /fe reload - reloads the plugin");
  }

  @Subcommand("list")
  @CommandPermission("ignitefishingevents.admin.list")
  public void onList(Player player) {
    configuration
        .getFishingEvents()
        .forEach(
            (key, value) -> {
              MessageUtility.send(player, configuration.getPrefix() + "Day: " + key.toString());
              MessageUtility.send(player, "Title: " + value.getEventTitle());
              MessageUtility.send(player, "ActionChance: " + value.getActionChance());
              MessageUtility.send(player, "CrazyZombie: " + value.getCrazyZombieChance());
              MessageUtility.send(player, "ItemDrop: " + value.getItemDropChance());
              MessageUtility.send(player, "SuperOre: " + value.getSuperOreChance());
              MessageUtility.send(player, "WaterHook: " + value.getWaterHookChance());
              MessageUtility.send(player, "Items: " + value.getItemDrops().size());
            });
  }

  @Subcommand("add")
  @CommandPermission("ignitefishingevents.admin.add")
  @Syntax("<weight> <day>")
  public void onAdd(Player player, int weight, DayOfWeek day) {
    ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

    if (itemInMainHand.getType().isAir()) {
      return;
    }
    configuration.getFishingEvents().get(day).getItemDrops().put(weight, itemInMainHand);
    MessageUtility.send(player, configuration.getPrefix() + "Item added!");
    onReload(player);
  }

  @Subcommand("reload")
  @CommandPermission("ignitefishingevents.admin.reload")
  public void onReload(Player player) {
    MessageUtility.send(player, configuration.getPrefix() + "Reloaded!");
    configuration.reload();
  }
}
