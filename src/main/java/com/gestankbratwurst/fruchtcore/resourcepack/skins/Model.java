package com.gestankbratwurst.fruchtcore.resourcepack.skins;

import com.gestankbratwurst.fruchtcore.resourcepack.packing.BoxedFontChar;
import com.gestankbratwurst.fruchtcore.util.items.ItemBuilder;
import com.gestankbratwurst.fruchtcore.util.nbtapi.NBTItem;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.File;
import java.lang.reflect.Field;
import lombok.Getter;
import lombok.Setter;
import net.crytec.libs.protocol.skinclient.data.Skin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 24.11.2019
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public enum Model {

  BLACK_ARROW_DOWN(Material.STICK, 1000, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  BLACK_ARROW_LEFT(Material.STICK, 1001, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  BLACK_ARROW_RIGHT(Material.STICK, 1002, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  BLACK_ARROW_UP(Material.STICK, 1003, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  GREEN_CHECK(Material.STICK, 1004, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  RED_X(Material.STICK, 1005, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  COINPILE_TINY(Material.STICK, 1100, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_SMALL(Material.STICK, 1101, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_MEDIUM(Material.STICK, 1102, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_BIG(Material.STICK, 1103, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_HUGE(Material.STICK, 1104, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_BAR_SMALL(Material.STICK, 1105, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_BAR_MEDIUM(Material.STICK, 1106, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  COINPILE_BAR_BIG(Material.STICK, 1107, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, true),
  TREATED_BOW(Material.BOW, 1108, null, FontMeta.common(), new BoxedFontChar(), false, true),
  RESIN(Material.STICK, 5000, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  ROOTS(Material.STICK, 5001, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  WHITE_MUSHROOMS(Material.STICK, 5002, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  WILD_POLLEN(Material.STICK, 5003, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  COBBLE_POUCH(Material.STICK, 5004, ModelData.defaultHandheld(), FontMeta.common(), new BoxedFontChar(), false, false),
  LOG_POUCH(Material.STICK, 5005, ModelData.defaultHandheld(), FontMeta.common(), new BoxedFontChar(), false, false),
  LUCKY_CLOVER(Material.COOKIE, 5006, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SALT(Material.STICK, 5007, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  BUTCHER_KNIFE(Material.IRON_AXE, 5008, ModelData.defaultHandheld(), FontMeta.common(), new BoxedFontChar(), false, false),
  SURVIVAL_KNIFE(Material.STICK, 5009, ModelData.defaultHandheld(), FontMeta.common(), new BoxedFontChar(), false, false),
  FILLET(Material.BEEF, 5010, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  COOKED_FILLET(Material.COOKED_BEEF, 5011, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  LESSER_ARTIFACT_BONES(Material.STICK, 5012, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  LESSER_ARTIFACT_ADEM(Material.STICK, 5013, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  LESSER_ARTIFACT_KEHR(Material.STICK, 5014, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  LESSER_ARTIFACT_VULD(Material.STICK, 5015, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  HOME_STONE(Material.STICK, 5016, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_ENERGY_EMPTY(Material.STICK, 5017, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_ENERGY_FULL(Material.STICK, 5018, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_GEODE(Material.STICK, 5019, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_GRAVEL(Material.STICK, 5020, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_HAMMER(Material.STICK, 5021, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_SAND(Material.STICK, 5022, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_SIEVE(Material.STICK, 5023, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_STONE(Material.STICK, 5024, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_SIEVE_SELECTED(Material.STICK, 5025, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  SIEVE_HAMMER_SELECTED(Material.STICK, 5026, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  HARD_LEATHER(Material.LEATHER, 5027, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  CHAINS(Material.IRON_NUGGET, 5028, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  FLINT_ARROW(Material.ARROW, 5029, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  TREATED_STICK(Material.STICK, 5030, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  TREATED_IRON_ARROW(Material.ARROW, 5031, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  IRON_ARROW_HEAD(Material.IRON_NUGGET, 5032, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  GOLDEN_RING(Material.GOLD_NUGGET, 5033, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  GOLDEN_LOCKET(Material.GOLD_NUGGET, 5034, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false),
  GOLDEN_NECKLACE(Material.GOLD_NUGGET, 5035, ModelData.defaultGenerated(), FontMeta.common(), new BoxedFontChar(), false, false);

  Model(final Material baseMaterial, final int modelID, final ModelData modelData, final FontMeta fontMeta,
      final BoxedFontChar boxedFontChar, final boolean headEnabled, final boolean customModelDataEnabled) {
    this.baseMaterial = baseMaterial;
    this.modelID = modelID;
    this.modelData = modelData;
    this.fontMeta = fontMeta;
    this.boxedFontChar = boxedFontChar;
    headSkinEnabled = headEnabled;
    this.customModelDataEnabled = customModelDataEnabled;
  }

  @Getter
  private final Material baseMaterial;
  @Getter
  private final int modelID;
  @Getter
  private final ModelData modelData;
  @Getter
  private final FontMeta fontMeta;
  @Getter
  private final BoxedFontChar boxedFontChar;
  @Getter
  private final boolean headSkinEnabled;
  @Getter
  private final boolean customModelDataEnabled;
  @Getter
  @Setter
  private Skin skin;
  @Getter
  @Setter
  private File linkedImageFile;
  @Getter
  private GameProfile gameProfile;

  private ItemStack head;

  private ItemStack item;

  private void initProfile() {
    if (gameProfile == null && skin != null) {
      gameProfile = new GameProfile(skin.data.uuid, skin.name);
      gameProfile.getProperties().put("textures", new Property("textures", skin.data.texture.value, skin.data.texture.signature));
    }
  }

  public char getChar() {
    return boxedFontChar.getAsCharacter();
  }

  public ItemStack getItem() {
    if (item == null) {
      item = new ItemBuilder(baseMaterial)
          .modelData(modelID)
          .name(this.toString())
          .build();
      NBTItem nbt = new NBTItem(item);
      nbt.setString("Model", this.toString());
      item = nbt.getItem();
    }
    return item.clone();
  }

  public ItemStack getHead() {
    if (head != null) {
      return head.clone();
    }
    initProfile();
    final ItemStack newHead = new ItemStack(Material.PLAYER_HEAD);
    final SkullMeta headMeta = (SkullMeta) newHead.getItemMeta();
    Field profileField;

    try {
      profileField = headMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(headMeta, gameProfile);
    } catch (final NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
      e1.printStackTrace();
    }

    newHead.setItemMeta(headMeta);
    NBTItem nbt = new NBTItem(newHead);
    nbt.setString("Model", this.toString());
    head = nbt.getItem();
    return head.clone();
  }

}