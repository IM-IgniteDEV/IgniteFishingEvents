package com.ignitedev.igniteFishingEvents.task;

import com.ignitedev.aparecium.util.text.TextUtility;
import com.ignitedev.igniteFishingEvents.config.FishingEventsConfiguration;
import com.twodevsstudio.simplejsonconfig.interfaces.Autowired;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class DailyEventTask {

  @Autowired private static FishingEventsConfiguration configuration;

  public DailyEventTask() {
    Calendar calendar = Calendar.getInstance();
    Timer timer = new Timer();

    calendar.set(Calendar.HOUR_OF_DAY, configuration.getNewEventHour());
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    timer.schedule(
        new DailyTask(), calendar.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
  }

  @RequiredArgsConstructor
  static class DailyTask extends TimerTask {

    @Override
    public void run() {
      DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();

      Bukkit.broadcastMessage(
          TextUtility.colorize(
              configuration.getPrefix()
                  + configuration
                      .getNewEventDay()
                      .replace(
                          "{EVENT_NAME}",
                          configuration.getFishingEvents().get(dayOfWeek).getEventTitle())));
    }
  }
}
