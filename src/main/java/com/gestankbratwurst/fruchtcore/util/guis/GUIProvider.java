package com.gestankbratwurst.fruchtcore.util.guis;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface GUIProvider {

  void init(GUIContext context);

  InventoryType getType();

  int getSize();

  default void handleBottomInventoryClick(InventoryClickEvent event) {
    event.setCancelled(true);
  }

}