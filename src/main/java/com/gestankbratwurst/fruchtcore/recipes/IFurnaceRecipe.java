package com.gestankbratwurst.fruchtcore.recipes;

import org.bukkit.inventory.RecipeChoice;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 30.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface IFurnaceRecipe extends IRecipe {

  RecipeChoice getInput();
  int getExp();
  int getCookingTime();

}
