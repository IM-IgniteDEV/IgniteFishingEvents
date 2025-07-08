# IgniteFishingEvents

Make fishing finally somehow funny and interesting with powerful events.

---

## Features:

* Almost everything configurable
* Create events per day of week or available every day!
* Crazy Zombie spawn Event ( Crazy zombies configurable)
* Hook by water Event
* Ore drop Event
* Drop Event
* Drop Reward as potion: command, experience or itemstack (in progress)
* Crazy zombies can drop ore after death

---

## Requirements

- Java 21+
- Spigot 1.8.8 - 1.21.4

---

## Commands

- `/feadmin` — Main Command with help message  
  **Permission:** `ignitefishingevents.admin`


- `/feadmin list` — List every day event  
  **Permission:** `ignitefishingevents.admin.list`


- `/feadmin add <weight> <day for example MONDAY>` — Add item from the main hand to specific day with specific weight  
  **Permission:** `ignitefishingevents.admin.add`


- `/feadmin reload` — Reloads plugin  
  **Permission:** `ignitefishingevents.admin.reload`

---

## Preview:

You can watch the preview video right [here](https://www.youtube.com/watch?v=b8I60z7VEn4)!  (Old Video, this is refreshed version but the concept is the same)

---

## Configuration:

```jsonc
{
  // The prefix for all messages sent by the plugin
  "prefix": "&7[&bFishingEvents&7] &6",

  "newEventDay": "A new fishing event has started: {EVENT_NAME}!",

  // Possible spawned super ores
  "superOreMaterials": ["DIAMOND_ORE", "EMERALD_ORE"],

  // Particles when super ore is spawned!
  "superOreParticle": "CLOUD",

  // Minimal amount of dropped resources on breaking super ore
  "superOreMinDropAmount": 3,

  // Maximal amount of dropped resources on breaking super ore
  "superOreMaxDropAmount": 10,

  // Power of hook # support minus values like -0.1
  "waterHookPower": 0.1,

  // Hour of the day when the event starts (0-23)
  "newEventHour": 1,

  "crazyZombieName": "&bLittle Fast Boyy",

  // Crazy Zombie applied potion effects
  "crazyZombiePotionEffects": [
    {
      "type": "SPEED",
      "duration": 1200,
      "amplifier": 2
    },
    {
      "type": "STRENGTH",
      "duration": 1200,
      "amplifier": 1
    }
  ],

  // Sound played when the Crazy Zombie spawns
  "crazyZombieSpawnSound": "ENTITY_ENDER_DRAGON_DEATH",

  // Power of hook # support minus values like -0.1
  "crazyZombieHookPower": 0.1,

  // Item drops are in weight system, one of the items is randomly selected depending on weight of items,
  // more weight = more chances. Action chance percent determinate if any plugin action gonna be performed,
  // set 1.0 to allow other actions to take a chance
  "fishingEvents": {
    "MONDAY": {
      "name": "Day 1 - Zombie day",
      "actionChance": 1.0,
      "crazyZombieChance": 0.5,
      "superOreChance": 0.9,
      "lightningChance": 0.1,
      "explosionChance": 0.9,
      "enabled": true,
      "items": {
        "1": {
          "v": 2730,
          "type": "DIAMOND"
        },
        "5": {
          "v": 2730,
          "type": "DIRT"
        }
      }
    },
    "TUESDAY": {
      "name": "Day 2 - Diamond day!",
      "actionChance": 1.0,
      "crazyZombieChance": 0.8,
      "superOreChance": 0.1,
      "lightningChance": 0.1,
      "explosionChance": 0.1,
      "enabled": true,
      "items": {
        "10": {
          "v": 2730,
          "type": "DIAMOND"
        },
        "5": {
          "v": 2730,
          "type": "DIRT"
        }
      }
    },
    "WEDNESDAY": {
      "name": "Day 3 - Normal day!",
      "actionChance": 0.5,
      "crazyZombieChance": 0.1,
      "superOreChance": 0.1,
      "lightningChance": 0.1,
      "explosionChance": 0.1,
      "enabled": true,
      "items": {
        "1": {
          "v": 2730,
          "type": "DIAMOND"
        },
        "5": {
          "v": 2730,
          "type": "DIRT"
        }
      }
    },
    "THURSDAY": {
      "name": "Day 4 - Mega day!",
      "actionChance": 1.0,
      "crazyZombieChance": 1.0,
      "superOreChance": 1.0,
      "lightningChance": 1.0,
      "explosionChance": 1.0,
      "enabled": true,
      "items": {
        "10": {
          "v": 2730,
          "type": "DIAMOND"
        },
        "5": {
          "v": 2730,
          "type": "DIRT"
        }
      }
    },
    "FRIDAY": {
      "name": "Day 5 - Weekend Start!",
      "actionChance": 0.2,
      "crazyZombieChance": 1.0,
      "superOreChance": 1.0,
      "lightningChance": 1.0,
      "explosionChance": 1.0,
      "enabled": true,
      "items": {
        "100": {
          "v": 2730,
          "type": "DIAMOND",
          "amount": 10
        },
        "5": {
          "v": 2730,
          "type": "DIRT"
        }
      }
    },
    "SATURDAY": {
      "name": "Day 6 - No event day!",
      "actionChance": 0.0,
      "crazyZombieChance": 1.0,
      "superOreChance": 1.0,
      "lightningChance": 1.0,
      "explosionChance": 1.0,
      "enabled": true,
      "items": {}
    },
    "SUNDAY": {
      "name": "Day 7 - No event day!",
      "actionChance": 0.0,
      "crazyZombieChance": 1.0,
      "superOreChance": 1.0,
      "lightningChance": 1.0,
      "explosionChance": 1.0,
      "enabled": true,
      "items": {}
    }
  }
}
```
---
## API Usage


---

## Available API Methods

---

## Events


---

## Todo

---


