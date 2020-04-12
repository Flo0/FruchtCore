package com.gestankbratwurst.fruchtcore.recipes;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

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
    extensionmap = new Object2ObjectOpenHashMap<>();
    Bukkit.getPluginManager().registerEvents(new RecipeListener(this), plugin);
  }

  @Getter(AccessLevel.PACKAGE)
  private final Map<NamespacedKey, IRecipeExtender> extensionmap;

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
    CustomShapedRecipe recipe = CustomShapedRecipe.of(shapedRecipe);
    extensionmap.put(recipe.getKey(), recipe);
    Bukkit.addRecipe(recipe);
  }

  private void registerShapelessRecipe(IShapelessCraftingRecipe shapelessRecipe) {
    CustomShapelessRecipe recipe = CustomShapelessRecipe.of(shapelessRecipe);
    extensionmap.put(recipe.getKey(), recipe);
    Bukkit.addRecipe(recipe);
  }

  private void registerFurnaceRecipe(IFurnaceRecipe furnaceRecipe) {
    CustomFurnaceRecipe recipe = CustomFurnaceRecipe.of(furnaceRecipe);
    extensionmap.put(recipe.getKey(), recipe);
    Bukkit.addRecipe(recipe);
  }

}
