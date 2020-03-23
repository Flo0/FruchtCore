package com.gestankbratwurst.fruchtcore.util.guis;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 22.03.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class GUIContext {

  protected GUIContext() {
    this.iconMap = new Int2ObjectOpenHashMap<>();
  }

  @Getter(AccessLevel.PACKAGE)
  private final Int2ObjectMap<ClickableIcon> iconMap;
  @Getter(AccessLevel.PACKAGE)
  @Setter(AccessLevel.PACKAGE)
  private Consumer<InventoryClickEvent> bottomInvConsumer;

  public void setIcon(int position, ItemStack item, Consumer<InventoryClickEvent> eventConsumer) {
    iconMap.put(position, new ClickableIcon(item, eventConsumer));
  }

}