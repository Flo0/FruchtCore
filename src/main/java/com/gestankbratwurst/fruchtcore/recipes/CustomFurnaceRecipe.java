package com.gestankbratwurst.fruchtcore.recipes;

import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
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
public class CustomFurnaceRecipe extends FurnaceRecipe {

  protected static CustomFurnaceRecipe of(IFurnaceRecipe furnaceRecipe) {
    return new CustomFurnaceRecipe(furnaceRecipe);
  }

  private CustomFurnaceRecipe(IFurnaceRecipe furnaceRecipe) {
    super(NameSpaceFactory.provide(furnaceRecipe.getName()),
        furnaceRecipe.getResult(),
        furnaceRecipe.getInput(),
        furnaceRecipe.getExp(),
        furnaceRecipe.getCookingTime());
  }
}
