package com.gestankbratwurst.fruchtcore.resourcepack.skins;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 25.11.2019
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@Data
@AllArgsConstructor
public class ModelData {

  private final String modelParent;
  private final JsonObject displayJson;
  private final JsonObject elementsJson;

  public static ModelData of(final String modelParent, final JsonObject displayJson, final JsonObject elementsJson) {
    return new ModelData(modelParent, displayJson, elementsJson);
  }

  public static ModelData defaultGenerated() {
    return ModelData.of("item/generated", null, null);
  }

  public static ModelData defaultHandheld() {
    return ModelData.of("item/handheld", null, null);
  }

}
