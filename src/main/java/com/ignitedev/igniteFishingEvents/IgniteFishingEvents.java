package com.ignitedev.igniteFishingEvents;

import com.ignitedev.igniteFishingEvents.listener.CrazyZombieDeathListener;
import com.ignitedev.igniteFishingEvents.listener.CrazyZombieProtectListener;
import com.ignitedev.igniteFishingEvents.listener.PlayerFishingListener;
import com.ignitedev.igniteFishingEvents.listener.SuperOreBreakListener;
import com.ignitedev.igniteFishingEvents.repository.SuperOreRepository;
import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class IgniteFishingEvents extends JavaPlugin {

  @Override
  public void onEnable() {
    SimpleJSONConfig.INSTANCE.register(this);

    SuperOreRepository superOreRepository = new SuperOreRepository();

    loadListeners(Bukkit.getPluginManager(), superOreRepository);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  private void loadListeners(PluginManager pluginManager, SuperOreRepository repository) {
    pluginManager.registerEvents(new CrazyZombieDeathListener(repository, this), this);
    pluginManager.registerEvents(new CrazyZombieProtectListener(), this);
    pluginManager.registerEvents(new PlayerFishingListener(), this);
    pluginManager.registerEvents(new SuperOreBreakListener(repository), this);
  }
}
