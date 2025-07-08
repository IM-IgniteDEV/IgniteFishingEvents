package com.ignitedev.igniteFishingEvents.event;

import com.ignitedev.igniteFishingEvents.base.SuperOre;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
public class SuperOreDestroyEvent extends Event {
  private static final HandlerList HANDLER_LIST = new HandlerList();

  private final Player destroyer;
  private final SuperOre superOre;

  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLER_LIST;
  }
}
