package com.gestankbratwurst.fruchtcore;

import co.aikar.commands.PaperCommandManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.gestankbratwurst.fruchtcore.io.IOManager;
import com.gestankbratwurst.fruchtcore.tasks.TaskManager;
import com.gestankbratwurst.fruchtcore.util.Msg;
import com.gestankbratwurst.fruchtcore.util.UtilModule;
import com.gestankbratwurst.fruchtcore.util.holograms.MovingHologram;
import com.gestankbratwurst.fruchtcore.util.holograms.impl.HologramManager;
import com.gestankbratwurst.fruchtcore.util.holograms.impl.HologramTextLine;
import com.gestankbratwurst.fruchtcore.util.items.display.ItemDisplayCompiler;
import lombok.Getter;
import lombok.Setter;
import net.crytec.inventoryapi.InventoryAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class FruchtCore extends JavaPlugin {

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

    Bukkit.getPluginManager().registerEvents(new Listener() {

      private final HologramManager hologramManager = utilModule.getHologramManager();
      private final int ticksAlive = 60;
      private final Vector direction = new Vector(0D, 0.15D, 0D);

      private final String[] lines = new String[]{"§aLine", "§cLine", "§eLine"};

      @EventHandler
      public void handleSneak(BlockBreakEvent event) {

        Location loc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
        MovingHologram moving = hologramManager.createMovingHologram(loc, direction, ticksAlive);
        moving.getHologram().appendTextLine("");

        taskManager.runFixedTimesBukkitSync(new Runnable() {
          private int index = 0;

          @Override
          public void run() {
            ((HologramTextLine) moving.getHologram().getHologramLine(0)).update(lines[index++]);
            if (index == lines.length) {
              index = 0;
            }
          }
        }, 0L, 5L, 11);

      }

    }, this);

  }

  @Override
  public void onDisable() {
    ioManager.saveAllFiles();
    ioManager.saveMappings();
  }

}