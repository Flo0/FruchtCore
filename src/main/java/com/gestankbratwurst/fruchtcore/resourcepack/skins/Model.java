package com.gestankbratwurst.fruchtcore.resourcepack.skins;

import com.gestankbratwurst.fruchtcore.resourcepack.packing.BoxedFontChar;
import com.gestankbratwurst.fruchtcore.util.items.ItemBuilder;
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

  BLACK_ARROW_UP(Material.STICK, 1000, ModelData.common(), FontMeta.common(), new BoxedFontChar(), false, false);

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
      item = new ItemBuilder(baseMaterial).modelData(modelID).build();
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
    Field profileField = null;

    try {
      profileField = headMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(headMeta, gameProfile);
    } catch (final NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
      e1.printStackTrace();
    }

    newHead.setItemMeta(headMeta);
    head = newHead;
    return head.clone();
  }

}