package com.gestankbratwurst.fruchtcore;

import co.aikar.commands.PaperCommandManager;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.gestankbratwurst.fruchtcore.items.ItemActionManager;
import com.gestankbratwurst.fruchtcore.recipes.RecipeModule;
import com.gestankbratwurst.fruchtcore.resourcepack.ResourcepackModule;
import com.gestankbratwurst.fruchtcore.resourcepack.skins.Model;
import com.gestankbratwurst.fruchtcore.tasks.TaskManager;
import com.gestankbratwurst.fruchtcore.util.Msg;
import com.gestankbratwurst.fruchtcore.util.UtilModule;
import com.gestankbratwurst.fruchtcore.util.common.UtilBlock;
import com.gestankbratwurst.fruchtcore.util.items.display.ItemDisplayCompiler;
import lombok.Getter;
import lombok.Setter;
import net.crytec.inventoryapi.InventoryAPI;
import net.minecraft.server.v1_15_R1.EntityLiving;
import net.minecraft.server.v1_15_R1.EntityThrownTrident;
import net.minecraft.server.v1_15_R1.ItemStack;
import net.minecraft.server.v1_15_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

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
  @Getter
  private RecipeModule recipeModule;
  @Getter
  private ItemActionManager itemActionManager;

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
    this.recipeModule = new RecipeModule(this);
    this.itemActionManager = new ItemActionManager(this);

    utilModule.enable(this);
    resourcepackModule.enable(this);

  }

  @Override
  public void onDisable() {
    resourcepackModule.disable(this);
    UtilBlock.terminate(this);
  }

}