package com.gestankbratwurst.fruchtcore.recipes;

import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
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
public interface IShapedCraftingRecipe extends IRecipe {

  Char2ObjectMap<RecipeChoice> getIngredients();

  String[] getShape();

}
