package com.gestankbratwurst.fruchtcore.items;

import com.gestankbratwurst.fruchtcore.util.Msg;
import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTCompound;
import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTItem;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.projectiles.ProjectileSource;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 08.04.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@AllArgsConstructor
public class ItemActionListener implements Listener {

  private final ItemActionManager itemActionManager;

  // TODO umschreiben zu ActionManager
  @EventHandler
  public void onShoot(EntityShootBowEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    Player player = (Player) event.getEntity();
    ItemStack item = event.getArrowItem();
    EntityEquipment equip = event.getEntity().getEquipment();
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemPdc = meta.getPersistentDataContainer();
    ArrowType arrowType = null;
    if (itemPdc.has(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING)) {
      arrowType = ArrowType.valueOf(itemPdc.get(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING));
      if (meta.hasEnchant(Enchantment.ARROW_INFINITE) && !arrowType.isInfinityUsable()) {
        event.setCancelled(true);
        Msg.error(player, "Bogen", "Dieser Pfeil kann nicht mit Infinity verwendet werden.");
        return;
      }
      event.getProjectile().getPersistentDataContainer()
          .set(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING, arrowType.toString());
    } else {
      event.getProjectile().getPersistentDataContainer()
          .set(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING, ArrowType.PRIMITIVE.toString());
    }

    if (equip != null) {
      ItemStack bow = equip.getItemInMainHand();
      if (bow.getType() != Material.AIR) {
        PersistentDataContainer pdc = bow.getItemMeta().getPersistentDataContainer();
        if (pdc.has(NameSpaceFactory.provide("BOW_TYPE"), PersistentDataType.STRING)) {
          BowType type = BowType.valueOf(pdc.get(NameSpaceFactory.provide("BOW_TYPE"), PersistentDataType.STRING));
          if (arrowType != null && !type.canUse(arrowType)) {
            event.setCancelled(true);
            Msg.send(player, "Bogen", "Diese Pfeile benötigen einen anderen Bogen.");
            return;
          }
          event.getProjectile().getVelocity().multiply(type.getSpeedScalar());
        }
      }
    }
  }

  // TODO umschreiben zu ActionManager
  @EventHandler
  public void onImpact(ProjectileHitEvent event) {
    if (event.getEntityType() != EntityType.ARROW) {
      return;
    }
    Projectile arrow = event.getEntity();
    PersistentDataContainer pdc = arrow.getPersistentDataContainer();
    if (pdc.has(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING)) {
      ArrowType type = ArrowType.valueOf(pdc.get(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING));
      if (!type.isPersistent()) {
        arrow.remove();
      }
    } else {
      arrow.remove();
    }
  }

  @EventHandler
  public void onPickup(EntityPickupItemEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    ItemStack stack = event.getItem().getItemStack();
    Material mat = stack.getType();
    if (mat != Material.ARROW) {
      return;
    }
    PersistentDataContainer pdc = stack.getItemMeta().getPersistentDataContainer();
    if (!pdc.has(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING)) {
      ItemStack arrow = ItemLibrary.FLINT_ARROW.getItem();
      arrow.setAmount(stack.getAmount());
      event.getItem().setItemStack(arrow);
    }
  }

  @EventHandler
  public void onOpen(InventoryOpenEvent event) {
    Inventory inv = event.getInventory();
    for (int index = 0; index < inv.getSize(); index++) {
      ItemStack item = inv.getItem(index);
      if (item != null && item.getType() == Material.ARROW) {
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        if (!pdc.has(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING)) {
          ItemStack arrow = ItemLibrary.FLINT_ARROW.getItem();
          arrow.setAmount(item.getAmount());
          inv.setItem(index, arrow);
        }
      }
    }
  }

  @EventHandler
  public void onKill(EntityDeathEvent event) {
    LivingEntity defender = event.getEntity();
    EntityDamageEvent lastDamageEvent = defender.getLastDamageCause();
    if (!(lastDamageEvent instanceof EntityDamageByEntityEvent)) {
      return;
    }
    EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) lastDamageEvent;
    Entity attackerEntity = damageEvent.getDamager();
    LivingEntity attacker;
    if (attackerEntity instanceof Projectile) {
      Projectile projectile = (Projectile) attackerEntity;
      ProjectileSource projectileSource = projectile.getShooter();
      if (projectileSource instanceof LivingEntity) {
        attacker = (LivingEntity) projectileSource;
      } else {
        return;
      }
    } else {
      attacker = (LivingEntity) attackerEntity;
    }

    EntityEquipment equipment = attacker.getEquipment();
    if (equipment == null) {
      return;
    }
    for (ItemStack armorItem : equipment.getArmorContents()) {
      NBTItem nbt = new NBTItem(armorItem);
      NBTCompound compound = nbt.getCompound("ITEM_ACTIONS");
      if (compound != null) {
        if (compound.hasKey(ActionType.KILL.toString())) {
          for (String id : compound.getStringList(ActionType.KILL.toString())) {
            itemActionManager.killMap.get(id).accept(attacker, event);
          }
        }
      }
    }
    NBTItem nbt = new NBTItem(equipment.getItemInMainHand());
    NBTCompound compound = nbt.getCompound("ITEM_ACTIONS");
    if (compound != null) {
      if (compound.hasKey(ActionType.KILL.toString())) {
        for (String id : compound.getStringList(ActionType.KILL.toString())) {
          itemActionManager.killMap.get(id).accept(attacker, event);
        }
      }
    }
  }

  @EventHandler
  public void onEat(PlayerItemConsumeEvent event) {
    NBTItem nbt = new NBTItem(event.getItem());
    NBTCompound compound = nbt.getCompound("ITEM_ACTIONS");
    if (compound != null) {
      if (compound.hasKey(ActionType.CONSUME.toString())) {
        for (String id : compound.getStringList(ActionType.CONSUME.toString())) {
          itemActionManager.consumerMap.get(id).accept(event);
        }
      }
    }
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    ItemStack item = event.getItem();
    if (item != null) {
      NBTItem nbt = new NBTItem(item);
      NBTCompound compound = nbt.getCompound("ITEM_ACTIONS");
      if (compound != null) {
        if (compound.hasKey(ActionType.INTERACT.toString())) {
          for (String id : compound.getStringList(ActionType.INTERACT.toString())) {
            itemActionManager.interactMap.get(id).accept(event);
          }
        }
      }
    }
  }

  @EventHandler
  public void onDamage(EntityDamageByEntityEvent event) {
    Entity attackerEntity = event.getDamager();
    Entity defenderEntity = event.getEntity();

    if (attackerEntity instanceof Projectile) {
      Projectile projectile = (Projectile) attackerEntity;

      PersistentDataContainer pdc = projectile.getPersistentDataContainer();
      // TODO umschreiben zu ActionManager
      if (!pdc.has(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING)) {
        ArrowType type = ArrowType.valueOf(pdc.get(NameSpaceFactory.provide("ARROW_TYPE"), PersistentDataType.STRING));
        if (event.getDamage() > type.getMaxDamage()) {
          event.setDamage(type.getMaxDamage());
        }
        if (!type.isPersistent()) {
          projectile.remove();
        }
      } else {
        projectile.remove();
      }

      ProjectileSource source = projectile.getShooter();
      if (source instanceof Entity) {
        attackerEntity = (Entity) source;
      }
    }

    if (attackerEntity instanceof LivingEntity) {
      LivingEntity attacker = (LivingEntity) attackerEntity;
      EntityEquipment equipment = attacker.getEquipment();
      if (equipment != null) {
        NBTItem nbt = new NBTItem(equipment.getItemInMainHand());
        NBTCompound compound = nbt.getCompound("ITEM_ACTIONS");
        if (compound != null) {
          if (compound.hasKey(ActionType.ATTACK.toString())) {
            for (String attackID : compound.getStringList(ActionType.ATTACK.toString())) {
              itemActionManager.attackerMap.get(attackID).accept(event);
            }
          }
        }
        for (ItemStack armorItem : equipment.getArmorContents()) {
          if (armorItem != null) {
            NBTItem nbtArmor = new NBTItem(armorItem);
            NBTCompound armorCompound = nbtArmor.getCompound("ITEM_ACTIONS");
            if (armorCompound != null) {
              if (armorCompound.hasKey(ActionType.ATTACK.toString())) {
                for (String attackID : armorCompound.getStringList(ActionType.ATTACK.toString())) {
                  itemActionManager.attackerMap.get(attackID).accept(event);
                }
              }
            }
          }
        }
      }
    }

    if (defenderEntity instanceof LivingEntity) {
      LivingEntity defender = (LivingEntity) defenderEntity;
      EntityEquipment equipment = defender.getEquipment();
      if (equipment != null) {
        NBTItem nbt = new NBTItem(equipment.getItemInMainHand());
        NBTCompound compound = nbt.getCompound("ITEM_ACTIONS");
        if (compound != null) {
          if (compound.hasKey(ActionType.DEFEND.toString())) {
            for (String attackID : compound.getStringList(ActionType.DEFEND.toString())) {
              itemActionManager.defenderMap.get(attackID).accept(event);
            }
          }
        }
        for (ItemStack armorItem : equipment.getArmorContents()) {
          if (armorItem != null) {
            NBTItem nbtArmor = new NBTItem(armorItem);
            NBTCompound armorCompound = nbtArmor.getCompound("ITEM_ACTIONS");
            if (armorCompound != null) {
              if (armorCompound.hasKey(ActionType.DEFEND.toString())) {
                for (String attackID : armorCompound.getStringList(ActionType.DEFEND.toString())) {
                  itemActionManager.defenderMap.get(attackID).accept(event);
                }
              }
            }
          }
        }
      }
    }

  }

}