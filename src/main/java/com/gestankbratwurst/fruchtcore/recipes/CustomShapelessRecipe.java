package com.gestankbratwurst.fruchtcore.recipes;

import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 31.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class CustomShapelessRecipe extends ShapelessRecipe implements IRecipeExtender {

  protected static CustomShapelessRecipe of(IShapelessCraftingRecipe shapelessCraftingRecipe) {
    CustomShapelessRecipe recipe = new CustomShapelessRecipe(shapelessCraftingRecipe);
    for (RecipeChoice choice : shapelessCraftingRecipe.getChoices()) {
      recipe.addIngredient(choice);
    }
    return recipe;
  }

  private CustomShapelessRecipe(IShapelessCraftingRecipe shapelessCraftingRecipe) {
    super(NameSpaceFactory.provide(shapelessCraftingRecipe.getName()), shapelessCraftingRecipe.getResult());
    this.iShapelessCraftingRecipe = shapelessCraftingRecipe;
  }

  private final IShapelessCraftingRecipe iShapelessCraftingRecipe;

  @Override
  public void onCraft(Player player) {
    iShapelessCraftingRecipe.onCraft(player);
  }

  @Override
  public boolean canExecute(Player player) {
    return !(iShapelessCraftingRecipe instanceof IPlayerConditional) || ((IPlayerConditional) iShapelessCraftingRecipe).canExecute(player);
  }

  @Override
  public void onFailure(Player player) {
    ((IPlayerConditional) iShapelessCraftingRecipe).onFailure(player);
  }

}