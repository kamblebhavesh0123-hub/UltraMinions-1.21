package io.github.Leonardo0013YT.UltraMinions.cmds;

import io.github.Leonardo0013YT.UltraMinions.Main;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerData;
import io.github.Leonardo0013YT.UltraMinions.fanciful.FancyMessage;
import io.github.Leonardo0013YT.UltraMinions.minions.Minion;
import io.github.Leonardo0013YT.UltraMinions.minions.skins.MinionSkin;
import io.github.Leonardo0013YT.UltraMinions.setup.SetupAutoSell;
import io.github.Leonardo0013YT.UltraMinions.setup.SetupAutoSmelt;
import io.github.Leonardo0013YT.UltraMinions.setup.SetupCompressor;
import io.github.Leonardo0013YT.UltraMinions.setup.SetupFuel;
import io.github.Leonardo0013YT.UltraMinions.upgrades.UpgradeAutoSell;
import io.github.Leonardo0013YT.UltraMinions.upgrades.UpgradeAutoSmelt;
import io.github.Leonardo0013YT.UltraMinions.upgrades.UpgradeCompressor;
import io.github.Leonardo0013YT.UltraMinions.upgrades.UpgradeFuel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetupCMD implements CommandExecutor, TabExecutor {
   private Main plugin;

   public SetupCMD(Main plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (sender instanceof Player p) {
         if (args.length < 1) {
            this.sendHelp(p);
            return true;
         }

         switch (args[0].toLowerCase()) {
            case "setmaxminion":
               if (args.length < 3) {
                  this.sendHelp(p);
                  return true;
               }

               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               Boolean x = this.executeMaxMinion(p, args);
               if (x != null) {
                  return x;
               }
            case "shop":
               if (!this.plugin.getCfm().isShopEnabled()) {
                  this.sendHelp(p);
                  return true;
               }

               this.plugin.getMem().getPages().put(p, 1);
               this.plugin.getMem().createShopMenu(p);
               break;
            case "forcesave":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               this.plugin.getDb().autoSave();
               p.sendMessage(this.plugin.getLang().get("messages.forceSaving"));
               break;
            case "setup":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               this.plugin.getSem().getPages().put(p.getUniqueId(), 1);
               this.plugin.getSem().createSetupMainMenu(p);
               break;
            case "food":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               this.plugin.getSem().getPages().put(p.getUniqueId(), 1);
               this.plugin.getSem().createSetupMainFoodMenu(p);
               break;
            case "autosell":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               if (this.plugin.getSm().isSetupAutoSell(p)) {
                  SetupAutoSell autoSell = this.plugin.getSm().getSetupAutoSell(p);
                  this.plugin.getSm().setSetupAutoSell(p, autoSell);
                  this.plugin.getSem().createSetupAutoSellMenu(p, autoSell);
                  return true;
               }

               if (args.length < 2) {
                  this.sendHelp(p);
                  return true;
               }

               String name = args[1];
               SetupAutoSell autoSell = new SetupAutoSell(p, name);
               this.plugin.getSm().setSetupAutoSell(p, autoSell);
               this.plugin.getSem().createSetupAutoSellMenu(p, autoSell);
               p.sendMessage(this.plugin.getLang().get("setup.autosell.create").replaceAll("<name>", name));
               break;
            case "autosmelt":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               if (this.plugin.getSm().isSetupAutoSmelt(p)) {
                  SetupAutoSmelt autoSmelt = this.plugin.getSm().getSetupAutoSmelt(p);
                  this.plugin.getSm().setSetupAutoSmelt(p, autoSmelt);
                  this.plugin.getSem().createSetupAutoSmeltMenu(p, autoSmelt);
                  return true;
               }

               if (args.length < 2) {
                  this.sendHelp(p);
                  return true;
               }

               String name2 = args[1];
               SetupAutoSmelt autoSmelt = new SetupAutoSmelt(p, name2);
               this.plugin.getSm().setSetupAutoSmelt(p, autoSmelt);
               this.plugin.getSem().createSetupAutoSmeltMenu(p, autoSmelt);
               p.sendMessage(this.plugin.getLang().get("setup.autosmelt.create").replaceAll("<name>", name2));
               break;
            case "compressor":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               if (this.plugin.getSm().isSetupCompressor(p)) {
                  SetupCompressor compressor = this.plugin.getSm().getSetupCompressor(p);
                  this.plugin.getSm().setSetupCompressor(p, compressor);
                  this.plugin.getSem().createSetupCompressorMenu(p, compressor);
                  return true;
               }

               if (args.length < 2) {
                  this.sendHelp(p);
                  return true;
               }

               String name3 = args[1];
               SetupCompressor compressor = new SetupCompressor(p, name3);
               this.plugin.getSm().setSetupCompressor(p, compressor);
               this.plugin.getSem().createSetupCompressorMenu(p, compressor);
               p.sendMessage(this.plugin.getLang().get("setup.compressor.create").replaceAll("<name>", name3));
               break;
            case "fuel":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               if (this.plugin.getSm().isSetupFuel(p)) {
                  SetupFuel fuel = this.plugin.getSm().getSetupFuel(p);
                  this.plugin.getSm().setSetupFuel(p, fuel);
                  this.plugin.getSem().createSetupFuelMenu(p, fuel);
                  return true;
               }

               if (args.length < 2) {
                  this.sendHelp(p);
                  return true;
               }

               String name4 = args[1];
               SetupFuel fuel = new SetupFuel(p, name4);
               this.plugin.getSm().setSetupFuel(p, fuel);
               this.plugin.getSem().createSetupFuelMenu(p, fuel);
               p.sendMessage(this.plugin.getLang().get("setup.fuel.create").replaceAll("<name>", name4));
               break;
            case "give":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               if (args.length < 5) {
                  this.sendHelp(p);
                  return true;
               }

               switch (args[1].toLowerCase()) {
                  case "minion":
                     String key = args[2];
                     if (!this.plugin.getMm().getMinions().containsKey(key)) {
                        p.sendMessage(this.plugin.getLang().get("messages.noMinion"));
                        return false;
                     }

                     Player on = Bukkit.getPlayer(args[3]);
                     if (on == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                        return false;
                     }

                     int amount;
                     try {
                        amount = Integer.parseInt(args[4]);
                     } catch (NumberFormatException var63) {
                        p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                        return false;
                     }

                     int level;
                     if (args.length > 5) {
                        try {
                           level = Integer.parseInt(args[5]);
                        } catch (NumberFormatException var62) {
                           p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                           return false;
                        }
                     } else {
                        level = 1;
                     }

                     Minion m = this.plugin.getMm().getMinion(key);
                     if (m.getMinionLevelByLevel(level) == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noExistLevel"));
                        return true;
                     }

                     ItemStack head = m.getMinionLevelByLevel(level).getMinionHead();

                     for(int i = 0; i < amount; ++i) {
                        on.getInventory().addItem(new ItemStack[]{head});
                     }

                     p.sendMessage(this.plugin.getLang().get("messages.giveMinion").replaceAll("<key>", key).replaceAll("<amount>", String.valueOf(amount)));
                     return false;
                  case "fuel":
                     String key2 = args[2];
                     if (!this.plugin.getUm().getFuel().containsKey(key2)) {
                        p.sendMessage(this.plugin.getLang().get("messages.noFuel"));
                        return false;
                     }

                     Player on2 = Bukkit.getPlayer(args[3]);
                     if (on2 == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                        return false;
                     }

                     int amount2;
                     try {
                        amount2 = Integer.parseInt(args[4]);
                     } catch (NumberFormatException var61) {
                        p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                        return false;
                     }

                     UpgradeFuel m2 = this.plugin.getUm().getFuel(key2);
                     ItemStack head2 = m2.getResult(false);

                     for(int i = 0; i < amount2; ++i) {
                        on2.getInventory().addItem(new ItemStack[]{head2});
                     }

                     p.sendMessage(this.plugin.getLang().get("messages.giveFuel").replaceAll("<key>", key2).replaceAll("<amount>", String.valueOf(amount2)));
                     return false;
                  case "autosell":
                     String key3 = args[2];
                     if (!this.plugin.getUm().getAutoSell().containsKey(key3)) {
                        p.sendMessage(this.plugin.getLang().get("messages.noAutoSell"));
                        return false;
                     }

                     Player on3 = Bukkit.getPlayer(args[3]);
                     if (on3 == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                        return false;
                     }

                     int amount3;
                     try {
                        amount3 = Integer.parseInt(args[4]);
                     } catch (NumberFormatException var60) {
                        p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                        return false;
                     }

                     UpgradeAutoSell uas = this.plugin.getUm().getAutoSell(key3);
                     ItemStack head3 = uas.getResult();

                     for(int i = 0; i < amount3; ++i) {
                        on3.getInventory().addItem(new ItemStack[]{head3});
                     }

                     p.sendMessage(this.plugin.getLang().get("messages.giveAutoSell").replaceAll("<key>", key3).replaceAll("<amount>", String.valueOf(amount3)));
                     return false;
                  case "autosmelt":
                     String key4 = args[2];
                     if (!this.plugin.getUm().getAutoSmelt().containsKey(key4)) {
                        p.sendMessage(this.plugin.getLang().get("messages.noAutoSmelt"));
                        return false;
                     }

                     Player on4 = Bukkit.getPlayer(args[3]);
                     if (on4 == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                        return false;
                     }

                     int amount4;
                     try {
                        amount4 = Integer.parseInt(args[4]);
                     } catch (NumberFormatException var59) {
                        p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                        return false;
                     }

                     UpgradeAutoSmelt usm = this.plugin.getUm().getAutoSmelt(key4);
                     ItemStack head4 = usm.getResult();

                     for(int i = 0; i < amount4; ++i) {
                        on4.getInventory().addItem(new ItemStack[]{head4});
                     }

                     p.sendMessage(this.plugin.getLang().get("messages.giveAutoSmelt").replaceAll("<key>", key4).replaceAll("<amount>", String.valueOf(amount4)));
                     return false;
                  case "compressor":
                     String key5 = args[2];
                     if (!this.plugin.getUm().getCompressor().containsKey(key5)) {
                        p.sendMessage(this.plugin.getLang().get("messages.noCompressor"));
                        return false;
                     }

                     Player on5 = Bukkit.getPlayer(args[3]);
                     if (on5 == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                        return false;
                     }

                     int amount5;
                     try {
                        amount5 = Integer.parseInt(args[4]);
                     } catch (NumberFormatException var58) {
                        p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                        return false;
                     }

                     UpgradeCompressor uc = this.plugin.getUm().getCompressor(key5);
                     ItemStack head5 = uc.getResult();

                     for(int i = 0; i < amount5; ++i) {
                        on5.getInventory().addItem(new ItemStack[]{head5});
                     }

                     p.sendMessage(this.plugin.getLang().get("messages.giveMinion").replaceAll("<key>", key5).replaceAll("<amount>", String.valueOf(amount5)));
                     return false;
                  case "skin":
                     String key6 = args[2];
                     if (!this.plugin.getSkm().getSkins().containsKey(key6)) {
                        p.sendMessage(this.plugin.getLang().get("messages.noCompressor"));
                        return false;
                     }

                     Player on6 = Bukkit.getPlayer(args[3]);
                     if (on6 == null) {
                        p.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                        return false;
                     }

                     int amount6;
                     try {
                        amount6 = Integer.parseInt(args[4]);
                     } catch (NumberFormatException var57) {
                        p.sendMessage(this.plugin.getLang().get("messages.noNumber"));
                        return false;
                     }

                     MinionSkin ms = (MinionSkin)this.plugin.getSkm().getSkins().get(key6);
                     ItemStack head6 = ms.getHead();

                     for(int i = 0; i < amount6; ++i) {
                        on6.getInventory().addItem(new ItemStack[]{head6});
                     }

                     p.sendMessage(this.plugin.getLang().get("messages.giveMinion").replaceAll("<key>", key6).replaceAll("<amount>", String.valueOf(amount6)));
                     return false;
                  default:
                     this.sendHelp(p);
                     return false;
               }
            case "reload":
               if (!p.hasPermission("ultraminions.admin")) {
                  p.sendMessage(this.plugin.getLang().get("messages.noPermission"));
                  return true;
               }

               this.plugin.reload();
               p.sendMessage(this.plugin.getLang().get("messages.reload"));
               break;
            default:
               this.sendHelp(p);
         }
      } else {
         if (args.length < 1) {
            this.sendHelp(sender);
            return true;
         }

         switch (args[0].toLowerCase()) {
            case "setmaxminion":
               if (args.length < 3) {
                  this.sendHelp(sender);
                  return true;
               }

               Boolean x = this.executeMaxMinion(sender, args);
               if (x != null) {
                  return x;
               }
            case "forcesave":
               this.plugin.getDb().autoSave();
               sender.sendMessage(this.plugin.getLang().get("messages.forceSaving"));
               break;
            case "setup":
            case "autosell":
            case "autosmelt":
            case "compressor":
            case "fuel":
               sender.sendMessage(this.plugin.getLang().get("messages.noConsole"));
               break;
            case "shop":
               if (!this.plugin.getCfm().isShopEnabled()) {
                  this.sendHelp(sender);
                  return true;
               }

               Player son = Bukkit.getPlayer(args[1]);
               if (son == null) {
                  sender.sendMessage(this.plugin.getLang().get("messages.noOnline"));
                  return false;
               }

               this.plugin.getMem().getPages().put(son, 1);
               this.plugin.getMem().createShopMenu(son);
               break;
            case "give":
               switch (args[1].toLowerCase()) {
                  case "minion":
                     if (args.length < 5) {
                        this.sendHelp(sender);
                        return true;
                     }

                     String key = args[2];
                     if (!this.plugin.getMm().getMinions().containsKey(key)) {
                        sender.sendMessage(this.plugin.getLang().get("messages.noMinion"));
                        return false;
                     }

                     Player on = Bukkit.getPlayer(args[3]);
                     if (on == null) {
                        sender.sendMess
