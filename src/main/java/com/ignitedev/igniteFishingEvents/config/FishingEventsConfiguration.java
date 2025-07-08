package com.ignitedev.igniteFishingEvents.config;

import com.ignitedev.igniteFishingEvents.base.FishingEvent;
import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Comment;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration("fishing-events.json")
@Getter
@SuppressWarnings("FieldMayBeFinal")
public class FishingEventsConfiguration extends Config {

  @Comment("The prefix for all messages sent by the plugin")
  private String prefix = "&7[&bFishingEvents&7] &6";

  private String newEventDay = "A new fishing event has started: {EVENT_NAME}!";

  /** Global Events configuration */
  @Comment("Possible spawned super ores")
  private List<Material> superOreMaterials = List.of(Material.DIAMOND_ORE, Material.EMERALD_ORE);

  @Comment("Particles when super ore is spawned!")
  private Particle superOreParticle = Particle.CLOUD;

  @Comment("Minimal amount of dropped resources on breaking super ore")
  private int superOreMinDropAmount = 3;

  @Comment("Maximal amount of dropped resources on breaking super ore")
  private int superOreMaxDropAmount = 10;

  @Comment("Power of hook # support minus values like -0.1")
  private double waterHookPower = 0.1;

  @Comment("Hour of the day when the event starts (0-23)")
  private int newEventHour = 1;

  /** Crazy Zombie global configuration */
  private String crazyZombieName = "&bLittle Fast Boyy";

  @Comment("Crazy Zombie applied potion effects")
  private List<PotionEffect> crazyZombiePotionEffects =
      List.of(
          new PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 20 * 60, 2),
          new PotionEffect(PotionEffectType.STRENGTH, 20 * 60, 1));

  @Comment("Sound played when the Crazy Zombie spawns")
  private Sound crazyZombieSpawnSound = Sound.ENTITY_ENDER_DRAGON_DEATH;

  @Comment("Power of hook # support minus values like -0.1")
  private double crazyZombieHookPower = 0.1;

  @Comment(
      "Item drops are in weight system, one of the items is randomly selected depending on weight of items, "
          + "more weight = more chances, in this example diamond has 1 weight and dirt has 5."
          + " Action chance percent determinate if any plugin action gonna be performed, set 1.0 to allow other actions to take a chance")
  private Map<DayOfWeek, FishingEvent> fishingEvents =
      Map.of(
          DayOfWeek.MONDAY,
          new FishingEvent(
              "Day 1 - Zombie day",
              1.0,
              0.5,
              0.9,
              0.1,
              0.9,
              true,
              Map.of(1, new ItemStack(Material.DIAMOND), 5, new ItemStack(Material.DIRT))),
          DayOfWeek.TUESDAY,
          new FishingEvent(
              "Day 2 - Diamond day!",
              1.0,
              0.8,
              0.1,
              0.1,
              0.1,
              true,
              Map.of(10, new ItemStack(Material.DIAMOND), 5, new ItemStack(Material.DIRT))),
          DayOfWeek.WEDNESDAY,
          new FishingEvent(
              "Day 3 - Normal day!",
              0.5,
              0.1,
              0.1,
              0.1,
              0.1,
              true,
              Map.of(1, new ItemStack(Material.DIAMOND), 5, new ItemStack(Material.DIRT))),
          DayOfWeek.THURSDAY,
          new FishingEvent(
              "Day 4 - Mega day!",
              1.0,
              1.0,
              1.0,
              1.0,
              1.0,
              true,
              Map.of(10, new ItemStack(Material.DIAMOND), 5, new ItemStack(Material.DIRT))),
          DayOfWeek.FRIDAY,
          new FishingEvent(
              "Day 5 - Weekend Start!",
              0.2,
              1.0,
              1.0,
              1.0,
              1.0,
              true,
              Map.of(100, new ItemStack(Material.DIAMOND, 10), 5, new ItemStack(Material.DIRT))),
          DayOfWeek.SATURDAY,
          new FishingEvent("Day 6 - No event day!", 0, 1.0, 1.0, 1.0, 1.0, true, new HashMap<>()),
          DayOfWeek.SUNDAY,
          new FishingEvent("Day 7 - No event day!", 0, 1.0, 1.0, 1.0, 1.0, true, new HashMap<>()));
}
