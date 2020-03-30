package com.gestankbratwurst.fruchtcore.resourcepack;

import com.gestankbratwurst.fruchtcore.FruchtCore;
import com.gestankbratwurst.fruchtcore.resourcepack.distribution.ResourcepackListener;
import com.gestankbratwurst.fruchtcore.resourcepack.distribution.ResourcepackManager;
import com.gestankbratwurst.fruchtcore.resourcepack.packing.ResourcepackAssembler;
import com.gestankbratwurst.fruchtcore.resourcepack.skins.Model;
import com.gestankbratwurst.fruchtcore.resourcepack.skins.ModelItemCommand;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;

public class ResourcepackModule {

  private ResourcepackManager resourcepackManager;

  public String getModuleName() {
    return "ResourcepackModule";
  }

  public void enable(final FruchtCore plugin) {
    try {
      new ResourcepackAssembler(plugin).zipResourcepack();
    } catch (IOException e) {
      e.printStackTrace();
    }
    plugin.getCommandManager().registerCommand(new ModelItemCommand());
    plugin.getCommandManager()
        .getCommandCompletions()
        .registerStaticCompletion("ModelItem",
            ImmutableList.copyOf(Arrays.stream(Model.values()).map(Enum::toString).collect(Collectors.toList())));
    resourcepackManager = new ResourcepackManager(plugin);
    Bukkit.getPluginManager().registerEvents(new ResourcepackListener(plugin, resourcepackManager), plugin);
  }

  public void disable(final FruchtCore plugin) {
    resourcepackManager.shutdown();
  }

}
