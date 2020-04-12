package com.gestankbratwurst.fruchtcore.items;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import com.gestankbratwurst.fruchtcore.resourcepack.skins.Model;
import com.gestankbratwurst.fruchtcore.util.common.UtilItem;
import com.gestankbratwurst.fruchtcore.util.items.ItemBuilder;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 07.04.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public enum ItemLibrary {

  RESIN() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.RESIN.getItem())
          .name("§fHarz")
          .build();
    }
  },
  ROOTS() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.ROOTS.getItem())
          .name("§fWurzeln")
          .build();
    }
  },
  WHITE_MUSHROOMS() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.WHITE_MUSHROOMS.getItem())
          .name("§fWeiße Pilze")
          .build();
    }
  },
  WILD_POLLEN() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.WILD_POLLEN.getItem())
          .name("§fWild Pollen")
          .build();
    }
  },
  COBBLE_POUCH() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack item = new ItemBuilder(Model.COBBLE_POUCH.getItem())
          .name("§aKleiner Stein Beutel")
          .lore("")
          .lore("§7Plaziere diesen Beutel auf")
          .lore("§7deinen ersten Platz im In-")
          .lore("§7ventar (Links, Oben)")
          .lore("§7Es werden automatisch alle")
          .lore("§7Cobble- und Cleanstones")
          .lore("§7in den Beutel aufgenommen,")
          .lore("§7bis er voll ist.")
          .lore("")
          .lore("§7Es können nicht mehrere Beutel")
          .lore("§7dieser Art gefüllt werden.")
          .build();
      return itemActionManager.addAction(ActionType.INTERACT, this.toString(), item);
    }
  },
  LOG_POUCH() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack item = new ItemBuilder(Model.LOG_POUCH.getItem())
          .name("§aKleiner Holz Beutel")
          .lore("")
          .lore("§7Plaziere diesen Beutel auf")
          .lore("§7deinen ersten Platz im In-")
          .lore("§7ventar (Links, Oben)")
          .lore("§7Es werden automatisch alle")
          .lore("§7rohen Holzarten in den")
          .lore("§7Beutel aufgenommen, bis er")
          .lore("§7voll ist.")
          .lore("")
          .lore("§7Es können nicht mehrere Beutel")
          .lore("§7dieser Art gefüllt werden.")
          .build();
      return itemActionManager.addAction(ActionType.INTERACT, this.toString(), item);
    }
  },
  LUCKY_CLOVER() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack item = new ItemBuilder(Model.LUCKY_CLOVER.getItem())
          .name("§aGlücks Klee")
          .lore("")
          .lore("§7Relativ seltene Pflanze.")
          .lore("§7Gibt etwas Glück beim")
          .lore("§7verzehren.")
          .build();
      return itemActionManager.addAction(ActionType.CONSUME, this.toString(), item);
    }
  },
  STONE_SALT() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.SALT.getItem())
          .name("§fStein Salz")
          .build();
    }
  },
  BUTCHER_KNIFER() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack item = new ItemBuilder(Model.BUTCHER_KNIFE.getItem())
          .name("§aSchlachter Messer")
          .lore("")
          .lore("§7Du hast eine Chance")
          .lore("§7von §f33.3% §7doppelte")
          .lore("§7Drops von einem Nutz-")
          .lore("§7tier zu bekommen.")
          .lore("")
          .lore("§7Gilt nur für normale")
          .lore("§7Drops und kann nur von")
          .lore("§7Farmern verwendet werden.")
          .setEnchantable(false)
          .setAnvilReceiver(false)
          .setAnvilProvider(false)
          .build();
      ItemMeta meta = item.getItemMeta();
      meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
      meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
          new AttributeModifier(UUID.randomUUID(), "dmg", 12, Operation.ADD_NUMBER, EquipmentSlot.HAND));
      item.setItemMeta(meta);
      return itemActionManager.addAction(ActionType.KILL, this.toString(), item);
    }
  },
  SURVIVAL_KNIFE() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack item = new ItemBuilder(Model.SURVIVAL_KNIFE.getItem())
          .name("§fÜberlebens Messer")
          .lore("")
          .lore("§7Macht nur wenig Schaden,")
          .lore("§7ist aber dafür unzerstörbar.")
          .lore("")
          .lore("§7Rechtsklicke auf Blätter, um")
          .lore("§7Stöcke abzuschneiden.")
          .build();
      return itemActionManager.addAction(ActionType.INTERACT, this.toString(), item);
    }
  },
  FILLET() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.FILLET.getItem())
          .name("§aFillet")
          .lore("")
          .lore("§7Feines Stück Fleisch.")
          .lore("§7Gebraten stellt es ein")
          .lore("§7wenig Leben wieder her.")
          .build();
    }
  },
  COOKED_FILLET() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack item = new ItemBuilder(Model.COOKED_FILLET.getItem())
          .name("§aGebratenes Fillet")
          .lore("")
          .lore("§7Feines Stück Fleisch.")
          .lore("§7Stellt direkt ein wenig")
          .lore("§7Leben wieder her.")
          .build();
      return itemActionManager.addAction(ActionType.CONSUME, this.toString(), item);
    }
  },
  LESSER_ARTIFACT_BONES() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.LESSER_ARTIFACT_BONES.getItem())
          .name("§aNiederes Knochen Artefakt")
          .lore("")
          .lore("§7Versteinerte Knochen aus einer")
          .lore("§7längst vergessenen Zeit.")
          .build();
    }
  },
  LESSER_ARTIFACT_ADEM() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.LESSER_ARTIFACT_ADEM.getItem())
          .name("§aNiederes Artefakt: 'Adem'")
          .lore("")
          .lore("§7Die Inschriften auf diesem Artefakt")
          .lore("§7bilden das Zeichen 'Adem' ab.")
          .build();
    }
  },
  LESSER_ARTIFACT_KEHR() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.LESSER_ARTIFACT_KEHR.getItem())
          .name("§aNiederes Artefakt: 'Kehr'")
          .lore("")
          .lore("§7Die Inschriften auf diesem Artefakt")
          .lore("§7bilden das Zeichen 'Kehr' ab.")
          .build();
    }
  },
  LESSER_ARTIFACT_VULD() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.LESSER_ARTIFACT_KEHR.getItem())
          .name("§aNiederes Artefakt: 'Vuld'")
          .lore("")
          .lore("§7Die Inschriften auf diesem Artefakt")
          .lore("§7bilden das Zeichen 'Vuld' ab.")
          .build();
    }
  },
  HOME_STONE() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack itemStack = new ItemBuilder(Model.HOME_STONE.getItem())
          .name("§aHeimstein")
          .lore("")
          .lore("§7Kann alle §f20 Minuten§7 verwendet")
          .lore("§7werden, um dich zu deinem Spawn-")
          .lore("§7punkt zu teleportieren.")
          .build();
      return itemActionManager.addAction(ActionType.INTERACT, this.toString(), itemStack);
    }
  },
  SIEVE() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack itemStack = new ItemBuilder(Model.SIEVE_SIEVE.getItem())
          .name("§aSchürf Equipment")
          .lore("")
          .lore("§7Damit kannst du in Flüssen")
          .lore("§7und am Strand schürfen.")
          .lore("")
          .lore("§7Rechtsklicke auf Sand/Kies.")
          .lore("§7Muss unter Wasser und na-")
          .lore("§7türlich sein.")
          .build();
      return itemActionManager.addAction(ActionType.INTERACT, this.toString(), itemStack);
    }
  },
  HARD_LEATHER_HELMET() {
    @Override
    public ItemStack asItem() {
      ItemActionManager itemActionManager = FruchtCore.getInstance().getItemActionManager();
      ItemStack itemStack = new ItemBuilder(Material.LEATHER_HELMET)
          .name("§aGehärteter Leder Helm")
          .lore("")
          .lore("§7Etwas besser als Eisen aber")
          .lore("§7nicht so haltbar.")
          .lore("")
          .lore("§9+1 Fernkampfschaden")
          .build();
      ItemMeta meta = itemStack.getItemMeta();
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
      AttributeModifier armorMod = new AttributeModifier(UUID.randomUUID(), "HLH_ARMOR", 2.25, Operation.ADD_NUMBER, EquipmentSlot.HEAD);
      AttributeModifier toughnessMod = new AttributeModifier(UUID.randomUUID(), "HLH_TOUGH", 0.5, Operation.ADD_NUMBER, EquipmentSlot.HEAD);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessMod);
      itemStack.setItemMeta(meta);
      return itemActionManager.addAction(ActionType.ATTACK, "ONE_RANGED", itemStack);
    }
  },
  HARD_LEATHER_CHESTPLATE() {
    @Override
    public ItemStack asItem() {
      ItemStack itemStack = new ItemBuilder(Material.LEATHER_CHESTPLATE)
          .name("§aGehärteter Leder Harnisch")
          .lore("")
          .lore("§7Etwas besser als Eisen aber")
          .lore("§7nicht so haltbar.")
          .build();
      ItemMeta meta = itemStack.getItemMeta();
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
      AttributeModifier armorMod = new AttributeModifier(UUID.randomUUID(), "HLC_ARMOR", 6.5, Operation.ADD_NUMBER, EquipmentSlot.CHEST);
      AttributeModifier toughnessMod = new AttributeModifier(UUID.randomUUID(), "HLC_TOUGH", 1.5, Operation.ADD_NUMBER, EquipmentSlot.CHEST);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessMod);
      itemStack.setItemMeta(meta);
      return itemStack;
    }
  },
  HARD_LEATHER_LEGGINS() {
    @Override
    public ItemStack asItem() {
      ItemStack itemStack = new ItemBuilder(Material.LEATHER_LEGGINGS)
          .name("§aGehärteter Leder Beinschienen")
          .lore("")
          .lore("§7Etwas besser als Eisen aber")
          .lore("§7nicht so haltbar.")
          .build();
      ItemMeta meta = itemStack.getItemMeta();
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
      AttributeModifier armorMod = new AttributeModifier(UUID.randomUUID(), "HLL_ARMOR", 5.5, Operation.ADD_NUMBER, EquipmentSlot.LEGS);
      AttributeModifier toughnessMod = new AttributeModifier(UUID.randomUUID(), "HLL_TOUGH", 1.25, Operation.ADD_NUMBER, EquipmentSlot.LEGS);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessMod);
      itemStack.setItemMeta(meta);
      return itemStack;
    }
  },
  HARD_LEATHER_BOOTS() {
    @Override
    public ItemStack asItem() {
      ItemStack itemStack = new ItemBuilder(Material.LEATHER_BOOTS)
          .name("§aGehärteter Leder Stiefel")
          .lore("")
          .lore("§7Etwas besser als Eisen aber")
          .lore("§7nicht so haltbar.")
          .build();
      ItemMeta meta = itemStack.getItemMeta();
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
      meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
      AttributeModifier armorMod = new AttributeModifier(UUID.randomUUID(), "HLB_ARMOR", 5.5, Operation.ADD_NUMBER, EquipmentSlot.FEET);
      AttributeModifier toughnessMod = new AttributeModifier(UUID.randomUUID(), "HLB_TOUGH", 1.25, Operation.ADD_NUMBER, EquipmentSlot.FEET);
      AttributeModifier speedMod = new AttributeModifier(UUID.randomUUID(), "HLB_SPPED", 0.12, Operation.ADD_SCALAR, EquipmentSlot.FEET);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
      meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessMod);
      meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speedMod);
      itemStack.setItemMeta(meta);
      return itemStack;
    }
  },
  HARD_LEATHER() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.HARD_LEATHER.getItem())
          .name("§aGehärtetes Leder")
          .build();
    }
  },
  CHAINS() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.CHAINS.getItem())
          .name("§aKetten")
          .amount(3)
          .build();
    }
  },
  CHAIN_HELMET() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Material.CHAINMAIL_HELMET)
          .name("§aKetten Helm")
          .lore("")
          .lore("")
          .build();
    }
  },
  CHAIN_CHESTPLATE() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
          .name("§aKetten Helm")
          .lore("")
          .lore("")
          .build();
    }
  },
  CHAIN_LEGGINS() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
          .name("§aKetten Helm")
          .lore("")
          .lore("")
          .build();
    }
  },
  CHAIN_BOOTS() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Material.CHAINMAIL_BOOTS)
          .name("§aKetten Helm")
          .lore("")
          .lore("")
          .build();
    }
  },
  FLINT_ARROW() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.FLINT_ARROW.getItem())
          .name("§fPrimitiver Pfeil")
          .lore("")
          .lore("§7Wird beim Aufprall zer-")
          .lore("§7stört und kann nicht mehr")
          .lore("§7als §f6 §7Schaden verursachen.")
          .lore("")
          .lore("§7Kann nicht mit dem Infinity")
          .lore("§7Enchantment verwendet werden.")
          .addPersistentData("ARROW_TYPE", PersistentDataType.STRING, ArrowType.PRIMITIVE.toString())
          .build();
    }
  },
  IRON_ARROW() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Material.ARROW)
          .name("§fEisen Pfeil")
          .lore("")
          .lore("§7Wird beim Aufprall zerstört.")
          .lore("")
          .lore("§7Kann nicht mehr als §f12")
          .lore("§7Schaden verursachen.")
          .addPersistentData("ARROW_TYPE", PersistentDataType.STRING, ArrowType.IRON.toString())
          .build();
    }
  },
  TREATED_IRON_ARROW() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.TREATED_IRON_ARROW.getItem())
          .name("§aBehandelter Eisen Pfeil")
          .lore("")
          .lore("§7Kann nicht mehr als §f16")
          .lore("§7Schaden verursachen.")
          .lore("")
          .lore("§7Benötigt verbesserte Bögen")
          .addPersistentData("ARROW_TYPE", PersistentDataType.STRING, ArrowType.TREATED_IRON.toString())
          .build();
    }
  },
  TREATED_BOW() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.TREATED_BOW.getItem())
          .name("§aBehandelter Bogen")
          .lore("")
          .lore("§7Kann behandelte Pfeile schießen.")
          .lore("")
          .lore("§9+12% Pfeilgeschwindigkeit")
          .addPersistentData("BOW_TYPE", PersistentDataType.STRING, BowType.TREATED.toString())
          .build();
    }
  },
  IRON_ARROW_HEAD() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.IRON_ARROW_HEAD.getItem())
          .name("§fEisen Pfeilkopf")
          .build();
    }
  },
  GOLDEN_RING() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.GOLDEN_RING.getItem())
          .name("§aGoldener Ring")
          .lore("")
          .lore("§7Ein einfacher goldener")
          .lore("§7Ring, welcher verkauft oder")
          .lore("§7verzaubert werden kann.")
          .build();
    }
  },
  GOLDEN_NECKLACE() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.GOLDEN_RING.getItem())
          .name("§aGoldener Ring")
          .lore("")
          .lore("§7Ein einfacher goldener")
          .lore("§7Ring, welcher verkauft oder")
          .lore("§7verzaubert werden kann.")
          .build();
    }
  },
  GOLDEN_() {
    @Override
    public ItemStack asItem() {
      return new ItemBuilder(Model.GOLDEN_RING.getItem())
          .name("§aGoldener Ring")
          .lore("")
          .lore("§7Ein einfacher goldener")
          .lore("§7Ring, welcher verkauft oder")
          .lore("§7verzaubert werden kann.")
          .build();
    }
  };

  private ItemStack item = null;

  protected abstract ItemStack asItem();

  public ItemStack getItem() {
    if (item == null) {
      item = asItem();
    }
    return item.clone();
  }

}
