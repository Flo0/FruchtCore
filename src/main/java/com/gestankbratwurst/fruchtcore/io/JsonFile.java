package com.gestankbratwurst.fruchtcore.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class JsonFile implements IONode {

  protected JsonFile(File file) {
    this.gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    this.file = file;
    this.jsonObject = new JsonObject();
  }

  private final Gson gson;
  @Getter(AccessLevel.PACKAGE)
  private final File file;
  @Getter
  private JsonObject jsonObject;

  @Override
  public void save() {

    if (!file.exists()) {
      try {
        if (!file.createNewFile()) {
          return;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
      bufferedWriter.write(gson.toJson(jsonObject));
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void load() {

    if (file.exists()) {
      try {
        this.jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

  }

}
