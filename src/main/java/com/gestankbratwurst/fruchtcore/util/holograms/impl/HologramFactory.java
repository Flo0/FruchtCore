package com.gestankbratwurst.fruchtcore.util.holograms.impl;

import com.gestankbratwurst.fruchtcore.util.holograms.AbstractHologram;
import com.gestankbratwurst.fruchtcore.util.holograms.AbstractHologramManager;
import com.gestankbratwurst.fruchtcore.util.holograms.IHologramFactory;
import java.util.UUID;
import java.util.function.Predicate;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HologramFactory implements IHologramFactory {

  @Override
  public Hologram supplyHologram(Location location, Predicate<Player> viewFilter, AbstractHologramManager manager, UUID uid) {
    return new Hologram(location, viewFilter, manager, uid);
  }

}
