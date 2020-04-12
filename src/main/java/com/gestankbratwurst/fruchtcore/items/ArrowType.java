package com.gestankbratwurst.fruchtcore.items;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 12.04.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@AllArgsConstructor
public enum ArrowType {

  PRIMITIVE(false, true, 6),
  IRON(false, true, 12),
  TREATED_IRON(true, true, 20);

  @Getter
  private final boolean persistent;
  @Getter
  private final boolean infinityUsable;
  @Getter
  private final int maxDamage;

}
