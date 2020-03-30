package com.gestankbratwurst.fruchtcore;

import co.aikar.commands.PaperCommandManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.gestankbratwurst.fruchtcore.resourcepack.ResourcepackModule;
import com.gestankbratwurst.fruchtcore.tasks.TaskManager;
import com.gestankbratwurst.fruchtcore.util.Msg;
import com.gestankbratwurst.fruchtcore.util.UtilModule;
import com.gestankbratwurst.fruchtcore.util.common.UtilBlock;
import com.gestankbratwurst.fruchtcore.util.items.display.ItemDisplayCompiler;
import lombok.Getter;
import lombok.Setter;
import net.crytec.inventoryapi.InventoryAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class FruchtCore extends JavaPlugin {

  @Getter
  private static FruchtCore instance;

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
  @Getter
  private ResourcepackModule resourcepackModule;

  @Override
  public void onEnable() {
    instance = this;
    new InventoryAPI(this);
    Msg.init(this);
    this.utilModule = new UtilModule();
    this.commandManager = new PaperCommandManager(this);
    this.taskManager = new TaskManager(this);
    this.protocolManager = ProtocolLibrary.getProtocolManager();
    this.resourcepackModule = new ResourcepackModule();

    utilModule.enable(this);
    resourcepackModule.enable(this);
  }

  @Override
  public void onDisable() {
    resourcepackModule.disable(this);
    UtilBlock.terminate(this);
  }

}