package com.gestankbratwurst.fruchtcore.items;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import com.gestankbratwurst.fruchtcore.util.Msg;
import com.gestankbratwurst.fruchtcore.util.actionbar.ActionBarBoard.Section;
import com.gestankbratwurst.fruchtcore.util.actionbar.ActionBarManager;
import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
import com.gestankbratwurst.fruchtcore.util.common.UtilPlayer;
import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTCompound;
import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTItem;
import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTStringList;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 08.04.2020
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ItemActionManager {

  private static final EnumSet<Material> LEAVES = EnumSet.copyOf(Arrays
      .stream(Material.values())
      .filter(m -> m.toString().contains("LEAVES") && !m.toString().contains("LEGACY"))
      .collect(Collectors.toList()));

  public ItemActionManager(FruchtCore plugin) {
    this.random = ThreadLocalRandom.current();
    Bukkit.getPluginManager().registerEvents(new ItemActionListener(this), plugin);
    consumerMap = new Object2ObjectOpenHashMap<>();
    attackerMap = new Object2ObjectOpenHashMap<>();
    interactMap = new Object2ObjectOpenHashMap<>();
    defenderMap = new Object2ObjectOpenHashMap<>();
    killMap = new Object2ObjectOpenHashMap<>();
    init();
  }

  protected final Map<String, Consumer<PlayerItemConsumeEvent>> consumerMap;
  protected final Map<String, Consumer<EntityDamageByEntityEvent>> attackerMap;
  protected final Map<String, Consumer<EntityDamageByEntityEvent>> defenderMap;
  protected final Map<String, Consumer<PlayerInteractEvent>> interactMap;
  protected final Map<String, BiConsumer<LivingEntity, EntityDeathEvent>> killMap;
  private final ThreadLocalRandom random;

  public ItemStack addAction(ActionType type, String actionID, ItemStack item) {
    NBTItem nbt = new NBTItem(item);
    NBTCompound actionsCompound = nbt.getCompound("ITEM_ACTIONS");
    if (actionsCompound == null) {
      actionsCompound = nbt.createCompound("ITEM_ACTIONS");
    }

    if (actionsCompound.hasKey(type.toString())) {
      actionsCompound.getStringList(type.toString()).add(actionID);
    } else {
      NBTStringList list = new NBTStringList();
      list.add(actionID);
      actionsCompound.addStringList(type.toString(), list);
    }

    return nbt.getItem();
  }

  public void registerKillAction(String id, BiConsumer<LivingEntity, EntityDeathEvent> killEventConsumer) {
    killMap.put(id, killEventConsumer);
  }

  public void registerConsumeAction(String id, Consumer<PlayerItemConsumeEvent> consumeEventConsumer) {
    consumerMap.put(id, consumeEventConsumer);
  }

  public void registerAttackAction(String id, Consumer<EntityDamageByEntityEvent> entityEventConsumer) {
    attackerMap.put(id, entityEventConsumer);
  }

  public void registerDefendAction(String id, Consumer<EntityDamageByEntityEvent> entityEventConsumer) {
    defenderMap.put(id, entityEventConsumer);
  }

  public void registerInteractAction(String id, Consumer<PlayerInteractEvent> interactEventConsumer) {
    interactMap.put(id, interactEventConsumer);
  }

  private void init() {
    this.consumerMap.put(ItemLibrary.LUCKY_CLOVER.toString(),
        event -> event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 2400, 0)));
    this.interactMap.put(ItemLibrary.SURVIVAL_KNIFE.toString(), event -> {
      Block block = event.getClickedBlock();
      if (block != null && LEAVES.contains(block.getType())) {
        BlockBreakEvent breakEvent = new BlockBreakEvent(block, event.getPlayer());
        if (breakEvent.callEvent()) {
          UtilPlayer.playSound(event.getPlayer(), Sound.ENTITY_SHEEP_SHEAR, 0.8F, 1.25F);
          block.setType(Material.AIR);
          if (random.nextBoolean() && random.nextBoolean()) {
            ItemStack drop = new ItemStack(Material.STICK);
            if (random.nextBoolean()) {
              drop.add();
            }
            block.getWorld().dropItemNaturally(block.getLocation(), drop);
          }
        }
      }
    });
    this.consumerMap
        .put(ItemLibrary.COOKED_FILLET.toString(), e -> e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0)));
  }

}