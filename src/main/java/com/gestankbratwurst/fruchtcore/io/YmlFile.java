package com.gestankbratwurst.fruchtcore.io;

import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class YmlFile implements IONode {

  protected YmlFile(File file) {
    this.file = file;
    this.fileConfiguration = new YamlConfiguration();
  }

  @Getter(AccessLevel.PACKAGE)
  private final File file;
  @Getter
  private FileConfiguration fileConfiguration;

  @Override
  public void save() {
    try {
      this.fileConfiguration.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void load() {
    if (file.exists()) {
      this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
  }

}