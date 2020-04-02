package com.gestankbratwurst.fruchtcore.recipes;

import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 30.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class CustomShapedRecipe extends ShapedRecipe implements IRecipeExtender {

  protected static CustomShapedRecipe of(IShapedCraftingRecipe craftingRecipe) {
    CustomShapedRecipe recipe = new CustomShapedRecipe(craftingRecipe);
    recipe.shape(craftingRecipe.getShape());
    Char2ObjectMap<RecipeChoice> choices = craftingRecipe.getIngredients();
    for (char key : choices.keySet()) {
      recipe.setIngredient(key, choices.get(key));
    }
    return recipe;
  }

  private CustomShapedRecipe(IShapedCraftingRecipe craftingRecipe) {
    super(NameSpaceFactory.provide(craftingRecipe.getName()), craftingRecipe.getResult());
    this.iShapedCraftingRecipe = craftingRecipe;
  }

  private final IShapedCraftingRecipe iShapedCraftingRecipe;

  @Override
  public void onCraft(Player player) {
    iShapedCraftingRecipe.onCraft(player);
  }

  @Override
  public boolean canExecute(Player player) {
    if (iShapedCraftingRecipe instanceof IPlayerConditional) {
      System.out.println("Check");
      return ((IPlayerConditional) iShapedCraftingRecipe).canExecute(player);
    }
    return true;
  }

  @Override
  public void onFailure(Player player) {
    ((IPlayerConditional) iShapedCraftingRecipe).onFailure(player);
  }

}
