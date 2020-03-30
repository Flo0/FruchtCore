package com.gestankbratwurst.fruchtcore.util;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import com.gestankbratwurst.fruchtcore.util.actionbar.ActionBarManager;
import com.gestankbratwurst.fruchtcore.util.common.BukkitTime;
import com.gestankbratwurst.fruchtcore.util.common.NameSpaceFactory;
import com.gestankbratwurst.fruchtcore.util.common.UtilBlock;
import com.gestankbratwurst.fruchtcore.util.common.UtilChunk;
import com.gestankbratwurst.fruchtcore.util.common.UtilPlayer;
import com.gestankbratwurst.fruchtcore.util.holograms.impl.HologramManager;
import com.gestankbratwurst.fruchtcore.util.holograms.impl.infobar.InfoBar;
import com.gestankbratwurst.fruchtcore.util.holograms.infobars.InfoBarManager;
import com.gestankbratwurst.fruchtcore.util.items.display.ItemDisplayCompiler;
import com.gestankbratwurst.fruchtcore.util.packets.adapter.ChunkTracker;
import com.gestankbratwurst.fruchtcore.util.packets.adapter.EntityTracker;
import lombok.Getter;
import net.crytec.libs.protocol.ProtocolAPI;
import net.crytec.libs.protocol.npc.NpcAPI;
import net.crytec.libs.protocol.skinclient.PlayerSkinManager;
import net.crytec.libs.protocol.tablist.TabListManager;
import net.crytec.libs.protocol.tablist.implementation.EmptyTablist;

public class UtilModule {

  @Getter
  private HologramManager hologramManager;
  @Getter
  private ActionBarManager actionBarManager;
  @Getter
  private InfoBarManager infoBarManager;
  @Getter
  private ProtocolAPI protocolAPI;
  @Getter
  private NpcAPI npcAPI;
  @Getter
  private TabListManager tabListManager;
  @Getter
  private PlayerSkinManager playerSkinManager;

  private EmptyTablist et;

  public void enable(final FruchtCore plugin) {
    BukkitTime.start(plugin);
    NameSpaceFactory.init(plugin);
    ChunkTracker.init(plugin, plugin.getProtocolManager());
    EntityTracker.init(plugin, plugin.getProtocolManager());
    UtilChunk.init(plugin);
    UtilPlayer.init(plugin);
    UtilBlock.init(plugin);
    plugin.setDisplayCompiler(new ItemDisplayCompiler(plugin));
    plugin.getProtocolManager().addPacketListener(plugin.getDisplayCompiler());
    hologramManager = new HologramManager(plugin);
    playerSkinManager = new PlayerSkinManager();
    plugin.getCommandManager().registerCommand(new TestCommand(hologramManager));
    actionBarManager = new ActionBarManager(plugin);
    infoBarManager = new InfoBarManager(plugin, (entity) -> new InfoBar(entity, infoBarManager));
    protocolAPI = new ProtocolAPI(plugin);
    npcAPI = new NpcAPI(plugin);
    tabListManager = new TabListManager(plugin, (p) -> et);
    et = new EmptyTablist(tabListManager);
  }

}
