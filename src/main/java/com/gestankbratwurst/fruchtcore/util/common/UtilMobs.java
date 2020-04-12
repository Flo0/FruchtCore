package com.gestankbratwurst.fruchtcore.util.common;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.plugin.java.JavaPlugin;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 08.04.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class UtilMobs implements Listener {

  private static final String DOMESTICATION_TAG = "DOMESTIC";

  public static void init(JavaPlugin plugin) {
    Bukkit.getPluginManager().registerEvents(new UtilMobs(plugin), plugin);
  }

  public static boolean isDomesticated(LivingEntity entity) {
    return entity.getScoreboardTags().contains(DOMESTICATION_TAG);
  }

  private UtilMobs(JavaPlugin plugin) {

  }

  private final Set<SpawnReason> domesticSpawnReasons = ImmutableSet.of(
      SpawnReason.BREEDING,
      SpawnReason.DISPENSE_EGG,
      SpawnReason.EGG);

  @EventHandler
  public void onSpawn(CreatureSpawnEvent event) {
    if (domesticSpawnReasons.contains(event.getSpawnReason())) {
      event.getEntity().getScoreboardTags().add(DOMESTICATION_TAG);
    }
  }

  @EventHandler
  public void onBreed(EntityBreedEvent event) {
    if (event.getBreeder() == null) {
      return;
    }
    event.getMother().getScoreboardTags().add(DOMESTICATION_TAG);
    event.getFather().getScoreboardTags().add(DOMESTICATION_TAG);
  }

  @EventHandler
  public void onTame(EntityTameEvent event) {
    event.getEntity().getScoreboardTags().add(DOMESTICATION_TAG);
  }

}
