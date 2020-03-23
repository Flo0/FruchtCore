package com.gestankbratwurst.fruchtcore.util.actionbar;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import com.gestankbratwurst.fruchtcore.tasks.TaskManager;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 23.03.2020
 *
 * LaLaLand-CorePlugin can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ActionBarManager {

  public ActionBarManager(final FruchtCore plugin) {
    boardMap = new Object2ObjectOpenHashMap<>();
    taskManager = plugin.getTaskManager();
    for (final Player player : Bukkit.getOnlinePlayers()) {
      init(player);
    }
    Bukkit.getPluginManager().registerEvents(new ActionBarListener(this), plugin);
    taskManager
        .runRepeatedBukkit(new ActionBarUpdateThread(this), 0L,
            ActionBarUpdateThread.UPDATE_PERIOD);
  }

  private final Object2ObjectOpenHashMap<Player, ActionBarBoard> boardMap;
  @Getter(AccessLevel.PROTECTED)
  private final TaskManager taskManager;

  public ActionBarBoard getBoard(final Player player) {
    return boardMap.get(player);
  }

  protected void init(final Player player) {
    boardMap.put(player, new ActionBarBoard(player.getUniqueId(), this));
  }

  protected void terminate(final Player player) {
    boardMap.remove(player);
  }

  public void showTo(final Player player) {
    player.sendActionBar(boardMap.get(player).getCurrentDisplay());
  }

  public void updateAndShow(final Player player) {
    final ActionBarBoard board = boardMap.get(player);
    board.update();
    player.sendActionBar(board.getCurrentDisplay());
  }

  protected void updateAndShowAll() {
    for (final Player player : Bukkit.getOnlinePlayers()) {
      updateAndShow(player);
    }
  }

  protected void showToAll() {
    for (final Player player : Bukkit.getOnlinePlayers()) {
      showTo(player);
    }
  }

  public void update(final Player player) {
    boardMap.get(player).update();
  }

  protected void updateAll() {
    for (final Player player : Bukkit.getOnlinePlayers()) {
      update(player);
    }
  }

}
