package com.gestankbratwurst.fruchtcore;

import co.aikar.commands.PaperCommandManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.gestankbratwurst.fruchtcore.io.IOManager;
import com.gestankbratwurst.fruchtcore.io.JsonFile;
import com.gestankbratwurst.fruchtcore.tasks.TaskManager;
import com.gestankbratwurst.fruchtcore.util.Msg;
import com.gestankbratwurst.fruchtcore.util.UtilModule;
import com.gestankbratwurst.fruchtcore.util.items.display.ItemDisplayCompiler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.Setter;
import net.crytec.inventoryapi.InventoryAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class FruchtCore extends JavaPlugin {

  @Getter
  private static FruchtCore instance;

  @Getter
  private IOManager ioManager;
  @Getter
  private ProtocolManager protocolManager;
  @Getter
  private TaskManager taskManager;
  @Getter
  @Setter
  private ItemDisplayCompiler displayCompiler;
  @Getter
  private PaperCommandManager commandManager;
  @Getter
  private UtilModule utilModule;

  @Override
  public void onEnable() {
    instance = this;
    new InventoryAPI(this);
    Msg.init(this);
    this.utilModule = new UtilModule();
    this.commandManager = new PaperCommandManager(this);
    this.taskManager = new TaskManager(this);
    this.ioManager = new IOManager(this);
    this.protocolManager = ProtocolLibrary.getProtocolManager();

    utilModule.enable(this);
    ioManager.loadMappings();
    ioManager.loadAllFiles();
  }

  @Override
  public void onDisable() {
    ioManager.saveAllFiles();
    ioManager.saveMappings();
  }

}