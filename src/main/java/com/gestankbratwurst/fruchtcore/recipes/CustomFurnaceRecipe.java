package com.gestankbratwurst.fruchtcore.recipes;

import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 31.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class CustomFurnaceRecipe extends FurnaceRecipe implements IRecipeExtender {

  protected static CustomFurnaceRecipe of(IFurnaceRecipe furnaceRecipe) {
    return new CustomFurnaceRecipe(furnaceRecipe);
  }

  private CustomFurnaceRecipe(IFurnaceRecipe furnaceRecipe) {
    super(NameSpaceFactory.provide(furnaceRecipe.getName()),
        furnaceRecipe.getResult(),
        furnaceRecipe.getInput(),
        furnaceRecipe.getExp(),
        furnaceRecipe.getCookingTime());
    this.recipe = furnaceRecipe;
  }

  private final IRecipe recipe;

  @Override
  public void onCraft(Player player, int amount) {

  }

  @Override
  public boolean canExecute(Player player) {
    return true;
  }

  @Override
  public void onFailure(Player player) {

  }

  @Override
  public String[] getDescription(Player player) {
    return recipe.getDescription(player);
  }

  @Override
  public String getDisplayName(Player player) {
    return recipe.getDisplayName(player);
  }
}
