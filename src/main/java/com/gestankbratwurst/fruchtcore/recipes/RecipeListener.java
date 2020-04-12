package com.gestankbratwurst.fruchtcore.recipes;

import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
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
@AllArgsConstructor
public class RecipeListener implements Listener {

  private final RecipeModule recipeModule;

  @EventHandler
  protected void onCraft(CraftItemEvent event) {
    int timesCrafted = 1;
    ItemStack result = event.getCurrentItem();
    if (result == null || result.getType() == Material.AIR) {
      return;
    }

    Recipe recipe = event.getRecipe();
    NamespacedKey key;

    if (recipe instanceof Keyed) {
      key = ((Keyed) recipe).getKey();
    } else {
      return;
    }

    IRecipeExtender extender = recipeModule.getExtensionmap().get(key);
    if (extender == null) {
      return;
    }

    if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
      int lowest = 256;
      for (ItemStack ingredient : event.getInventory().getMatrix()) {
        if (ingredient != null) {
          if (ingredient.getAmount() < lowest) {
            lowest = ingredient.getAmount();
          }
        }
      }
      timesCrafted = lowest;
    }

    extender.onCraft((Player) event.getView().getPlayer(), timesCrafted);
  }

  @EventHandler
  public void onPrepare(PrepareItemCraftEvent event) {
    Recipe recipe = event.getRecipe();
    if (recipe == null) {
      return;
    }

    NamespacedKey key;

    if (recipe instanceof Keyed) {
      key = ((Keyed) recipe).getKey();
    } else {
      return;
    }

    IRecipeExtender extender = recipeModule.getExtensionmap().get(key);
    if (extender == null) {
      return;
    }

    Player player = (Player) event.getView().getPlayer();
    if (!extender.canExecute(player)) {
      CraftingInventory inv = event.getInventory();
      inv.setResult(null);
      extender.onFailure(player);
    }

  }

}
