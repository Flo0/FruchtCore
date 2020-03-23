package com.gestankbratwurst.fruchtcore.util.guis;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class GUIListener implements Listener {

  @EventHandler
  public void handleClick(InventoryClickEvent event) {
    GUIManager.handleEvent(event);
  }

  @EventHandler
  public void handleClose(InventoryCloseEvent event) {
    GUIManager.handleEvent(event);
  }

}
