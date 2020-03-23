package com.gestankbratwurst.fruchtcore.util.guis;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class GUIManager {

  private static final Map<Inventory, GUIContext> INVENTORY_GUI_CONTEXT_MAP = new Object2ObjectOpenHashMap<>();

  public static void openGUI(Player player, GUIProvider inventoryProvider) {

    Inventory inventory;
    InventoryType type = inventoryProvider.getType();

    if (type == InventoryType.CHEST) {
      inventory = Bukkit.createInventory(null, inventoryProvider.getSize());
    } else {
      inventory = Bukkit.createInventory(null, type);
    }
    GUIContext context = new GUIContext();
    context.setBottomInvConsumer(inventoryProvider::handleBottomInventoryClick);
    inventoryProvider.init(context);
    Int2ObjectMap<ClickableIcon> iconMap = context.getIconMap();
    for (int i : iconMap.keySet()) {
      inventory.setItem(i, iconMap.get(i).getItem());
    }
    INVENTORY_GUI_CONTEXT_MAP.put(inventory, context);
    player.openInventory(inventory);
  }

  protected static void handleEvent(InventoryClickEvent event) {
    Inventory inventory = event.getInventory();
    if (INVENTORY_GUI_CONTEXT_MAP.containsKey(inventory)) {
      if (inventory == event.getClickedInventory()) {
        Int2ObjectMap<ClickableIcon> iconMap = INVENTORY_GUI_CONTEXT_MAP.get(inventory).getIconMap();
        int slotID = event.getSlot();
        ClickableIcon icon = iconMap.get(slotID);
        if (icon == null) {
          return;
        }
        icon.handleEvent(event);
      } else {
        INVENTORY_GUI_CONTEXT_MAP.get(inventory).getBottomInvConsumer().accept(event);
      }
    }
  }

  protected static void handleEvent(InventoryCloseEvent event) {
    INVENTORY_GUI_CONTEXT_MAP.remove(event.getInventory());
  }

}