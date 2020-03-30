package com.gestankbratwurst.fruchtcore.resourcepack.skins;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.gestankbratwurst.fruchtcore.util.Msg;
import org.bukkit.entity.Player;

/*******************************************************
 * Copyright (C) Gestankbratwurst suotokka@gmail.com
 *
 * This file is part of FruchtCore and was created at the 24.11.2019
 *
 * FruchtCore can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
@CommandAlias("model")
@CommandPermission("admin")
public class ModelItemCommand extends BaseCommand {

  @Default
  public void onCommand(final Player sender) {
    Msg.send(sender, "Resourcepack", "Benutze '/modelitem get <ModelItem>' um ein ModelItem zu erhalten.");
  }

  @Subcommand("asitem")
  @CommandCompletion("@ModelItem")
  public void onGetCommand(final Player sender, final Model model) {
    sender.getInventory().addItem(model.getItem());
    final String modelName = Msg.elem(model.toString());
    Msg.send(sender, "Resourcepack", "Du hast ein ModelItem erhalten: " + modelName);
  }

  @Subcommand("ashead")
  @CommandCompletion("@ModelItem")
  public void onGetSkillCommand(final Player sender, final Model model) {
    sender.getInventory().addItem(model.getHead());
    final String modelName = Msg.elem(model.toString());
    Msg.send(sender, "Resourcepack", "Du hast einen ModelItem Kopf erhalten: " + modelName);
  }

  @Subcommand("tell")
  @CommandCompletion("@ModelItem")
  public void onTellCommand(final Player sender, final Model model) {
    Msg.send(sender, "Resourcepack", "Model: " + model.getChar());
  }

}
