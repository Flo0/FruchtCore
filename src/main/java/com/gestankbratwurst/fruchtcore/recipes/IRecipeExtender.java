package com.gestankbratwurst.fruchtcore.recipes;

import org.bukkit.entity.Player;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 31.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public interface IRecipeExtender {

  void onCraft(Player player);
  boolean canExecute(Player player);
  void onFailure(Player player);

}
