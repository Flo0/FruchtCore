package com.gestankbratwurst.fruchtcore.util.items;

import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class NBTItemBuilder {

  public NBTItemBuilder(ItemStack item) {
    this.nbt = new NBTItem(item);
  }

  private NBTItem nbt;

  public ItemBuilder asBukkitBuilder() {
    return new ItemBuilder(this.nbt.getItem());
  }

  public NBTItem getNBTItem() {
    return this.nbt;
  }

  public ItemStack build() {
    return nbt.getItem();
  }
}
