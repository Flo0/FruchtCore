package com.gestankbratwurst.fruchtcore.items;

import java.util.EnumSet;
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
public enum BowType {

  NORMAL(1.0, EnumSet.of(ArrowType.PRIMITIVE, ArrowType.IRON)),
  TREATED(1.12, EnumSet.of(ArrowType.PRIMITIVE, ArrowType.IRON, ArrowType.TREATED_IRON));

  @Getter
  private final double speedScalar;
  private final EnumSet<ArrowType> usableArrowTypes;

  public boolean canUse(ArrowType arrowType) {
    return usableArrowTypes.contains(arrowType);
  }

}
