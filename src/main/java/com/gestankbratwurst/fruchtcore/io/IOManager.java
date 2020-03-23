package com.gestankbratwurst.fruchtcore.io;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class IOManager {

  private static final String MAPPING_FILE = "IOMappings";
  private static final String INTERNAL_DATA_FOLDER = "internal";

  public IOManager(FruchtCore plugin) {

    File pluginFolder = plugin.getDataFolder();
    if (!pluginFolder.exists()) {
      pluginFolder.mkdirs();
    }

    this.pluginFolder = pluginFolder;

    this.jsonFileMap = new Object2ObjectOpenHashMap<>();
    this.ymlFileMap = new Object2ObjectOpenHashMap<>();

    this.internalDataFolder = new File(pluginFolder + File.separator + INTERNAL_DATA_FOLDER);
    if (!internalDataFolder.exists()) {
      internalDataFolder.mkdirs();
    }

    this.fileMappingsFile = new File(internalDataFolder, MAPPING_FILE);
  }

  private final File fileMappingsFile;
  private final File pluginFolder;
  private final File internalDataFolder;
  private final Map<String, JsonFile> jsonFileMap;
  private final Map<String, YmlFile> ymlFileMap;

  public JsonFile getOrCreateJsonFile(String fileName) {
    return this.getOrCreateJsonFile(new File(pluginFolder, fileName + ".json"));
  }

  public YmlFile getOrCreateYmlFile(String fileName) {
    return this.getOrCreateYmlFile(new File(pluginFolder, fileName + ".yml"));
  }

  private JsonFile getOrCreateJsonFile(File file) {
    String fileName = file.getName();
    JsonFile jsonFile = jsonFileMap.get(fileName);
    if (jsonFile == null) {
      jsonFile = new JsonFile(file);
      this.jsonFileMap.put(fileName, jsonFile);
    }
    return jsonFile;
  }

  private YmlFile getOrCreateYmlFile(File file) {
    String fileName = file.getName();
    YmlFile ymlFile = ymlFileMap.get(fileName);
    if (ymlFile == null) {
      ymlFile = new YmlFile(file);
      this.ymlFileMap.put(fileName, ymlFile);
    }
    return ymlFile;
  }

  public void saveMappings() {
    try {
      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(this.fileMappingsFile));
      StringBuilder builder = new StringBuilder();

      builder.append(jsonFileMap
          .values()
          .stream()
          .map(jsonFile -> jsonFile.getFile().getAbsolutePath())
          .collect(Collectors.joining("\n")));

      builder.append(ymlFileMap
          .values()
          .stream()
          .map(ymlFile -> ymlFile.getFile().getAbsolutePath())
          .collect(Collectors.joining("\n")));

      osw.write(builder.toString());
      osw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadMappings() {

    if (!fileMappingsFile.exists()) {
      return;
    }

    try {
      InputStreamReader isr = new InputStreamReader(new FileInputStream(fileMappingsFile));

      StringBuilder builder = new StringBuilder();
      int read;
      while ((read = isr.read()) != -1) {
        builder.append((char) read);
      }
      isr.close();

      String[] lines = builder.toString().split("\n");

      for (String filePath : lines) {
        if (filePath.endsWith(".json")) {
          this.getOrCreateJsonFile(new File(filePath));
        } else {
          this.getOrCreateYmlFile(new File(filePath));
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveAllFiles() {
    for (JsonFile jsonFile : jsonFileMap.values()) {
      jsonFile.save();
    }
    for (YmlFile ymlFile : ymlFileMap.values()) {
      ymlFile.save();
    }
  }

  public void loadAllFiles() {
    for (JsonFile jsonFile : jsonFileMap.values()) {
      jsonFile.load();
    }
    for (YmlFile ymlFile : ymlFileMap.values()) {
      ymlFile.load();
    }
  }

}
