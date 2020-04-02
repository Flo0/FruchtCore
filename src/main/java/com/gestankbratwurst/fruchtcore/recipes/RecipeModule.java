package com.gestankbratwurst.fruchtcore.recipes;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import org.bukkit.Bukkit;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 30.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class RecipeModule {

  public RecipeModule(FruchtCore plugin) {
    Bukkit.getPluginManager().registerEvents(new RecipeListener(), plugin);
  }

  public void registerRecipe(IRecipe recipe) {
    if (recipe instanceof IShapedCraftingRecipe) {
      registerShapedRecipe((IShapedCraftingRecipe) recipe);
    } else if (recipe instanceof IFurnaceRecipe) {
      registerFurnaceRecipe((IFurnaceRecipe) recipe);
    } else if (recipe instanceof IShapelessCraftingRecipe) {
      registerShapelessRecipe((IShapelessCraftingRecipe) recipe);
    }
  }

  private void registerShapedRecipe(IShapedCraftingRecipe shapedRecipe) {
    Bukkit.addRecipe(CustomShapedRecipe.of(shapedRecipe));
  }

  private void registerShapelessRecipe(IShapelessCraftingRecipe shapelessRecipe) {
    Bukkit.addRecipe(CustomShapelessRecipe.of(shapelessRecipe));
  }

  private void registerFurnaceRecipe(IFurnaceRecipe furnaceRecipe) {
    Bukkit.addRecipe(CustomFurnaceRecipe.of(furnaceRecipe));
  }

}
