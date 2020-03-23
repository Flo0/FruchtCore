package com.gestankbratwurst.fruchtcore.util.guis;

import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ClickableIcon {

  @Getter(AccessLevel.PROTECTED)
  private final ItemStack item;
  private final Consumer<InventoryClickEvent> eventConsumer;

  protected void handleEvent(InventoryClickEvent event) {
    eventConsumer.accept(event);
  }

}
