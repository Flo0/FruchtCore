package com.gestankbratwurst.fruchtcore.resourcepack.distribution;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import com.gestankbratwurst.fruchtcore.tasks.TaskManager;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 25.11.2019
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ResourcepackListener implements Listener {

  public ResourcepackListener(final FruchtCore plugin, final ResourcepackManager manager) {
    this.manager = manager;
    this.plugin = plugin;
    attempts = Sets.newHashSet();
    taskManager = plugin.getTaskManager();
  }

  private final ResourcepackManager manager;
  private final FruchtCore plugin;
  private final TaskManager taskManager;
  private final HashSet<UUID> attempts;

  @EventHandler(priority = EventPriority.HIGH)
  public void onJoin(final PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    taskManager.runBukkitSyncDelayed(() -> sendResourcepack(player), 20L);
  }

//  @EventHandler
//  public void resourceStatusEvent(final PlayerResourcePackStatusEvent event) {
//    final Player player = event.getPlayer();
//    final UUID id = player.getUniqueId();
//    final Status status = event.getStatus();
//    if (status == Status.SUCCESSFULLY_LOADED) {
//      return;
//    } else if (status == Status.FAILED_DOWNLOAD) {
//      if (attempts.contains(id)) {
//        attempts.remove(id);
//        player.kickPlayer("Das Resourcepack wurde nicht akzeptiert.");
//        return;
//      } else {
//        attempts.add(id);
//        plugin.getTaskManager().runBukkitSyncDelayed(() -> sendResourcepack(player), 60L);
//      }
//
//    } else if (status == Status.DECLINED) {
//      player.kickPlayer("Das Resourcepack wurde nicht akzeptiert.");
//    }
//  }

  private void sendResourcepack(final Player player) {
    player.setResourcePack(manager.getDownloadURL("serverpack.zip"), manager.getResourceHash());
  }
}