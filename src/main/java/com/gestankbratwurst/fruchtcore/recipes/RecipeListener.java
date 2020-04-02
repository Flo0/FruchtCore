package com.gestankbratwurst.fruchtcore.recipes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Recipe;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 31.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class RecipeListener implements Listener {

  @EventHandler
  public void onPrepare(PrepareItemCraftEvent event) {
    Recipe recipe = event.getRecipe();
    if (recipe instanceof IRecipeExtender) {
      Player player = (Player) event.getView().getPlayer();
      IRecipeExtender extender = (IRecipeExtender) recipe;
      if (!extender.canExecute(player)) {
        CraftingInventory inv = event.getInventory();
        inv.setResult(null);
        extender.onFailure(player);
      } else {
        extender.onCraft(player);
      }
    }
  }

}
