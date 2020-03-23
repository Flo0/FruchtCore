package com.gestankbratwurst.fruchtcore.util.guis;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ExampleGUI implements GUIProvider {

  @Override
  public void init(GUIContext context) {
    context.setIcon(0, new ItemStack(Material.STICK), event -> {
      event.setCancelled(true);
      event.getWhoClicked().sendMessage("Click");
    });
  }

  @Override
  public InventoryType getType() {
    return InventoryType.CHEST;
  }

  @Override
  public int getSize() {
    return 18;
  }
}
