package com.gestankbratwurst.fruchtcore.recipes;

import java.util.Set;
import org.bukkit.inventory.RecipeChoice;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 31.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface IShapelessCraftingRecipe extends IRecipe {

  String getName();
  Set<RecipeChoice> getChoices();

}
