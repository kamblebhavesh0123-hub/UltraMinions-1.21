package io.github.Leonardo0013YT.UltraMinions.managers;

import io.github.Leonardo0013YT.UltraMinions.Main;
import io.github.Leonardo0013YT.UltraMinions.addons.CMIAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.EssentialsAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.FactionUUIDAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.LandsAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.LuckPermsAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.MVdWPlaceholderAPIAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.MassiveFactionAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.PlaceholderAPIAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.PlayerPointsAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.PlotSquaredAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.PlotSquaredV5Addon;
import io.github.Leonardo0013YT.UltraMinions.addons.ResidenceAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.ShopGUIAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.TownyAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.VaultAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.holograms.HologramsAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.holograms.HolographicDisplaysAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.holograms.TrHologramAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.protections.GriefPreventionAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.protections.PreciousStonesAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.protections.ProtectionStonesAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.protections.RedProtectAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.protections.UltimateClaimsAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.protections.WorldGuardAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.skyblocks.ASkyBlockAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.skyblocks.AcidIslandAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.skyblocks.BentoBoxAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.skyblocks.FabledSkyBlockAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.skyblocks.IridiumSkyBlockAddon;
import io.github.Leonardo0013YT.UltraMinions.addons.skyblocks.SuperiorSkyBlockAddon;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerData;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerMinion;
import io.github.Leonardo0013YT.UltraMinions.interfaces.HologramAddon;
import io.github.Leonardo0013YT.UltraMinions.interfaces.PlaceholderAddon;
import io.github.Leonardo0013YT.UltraMinions.interfaces.ProtectionAddon;
import io.github.Leonardo0013YT.UltraMinions.interfaces.SellAddon;
import io.github.Leonardo0013YT.UltraMinions.tiers.Tier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scheduler.BukkitRunnable;

public class AddonManager {
   private Main plugin;
   private List<ProtectionAddon> protectionAddons = new ArrayList();
   private List<SellAddon> sellAddons = new ArrayList();
   private LuckPermsAddon lpa;
   private FabledSkyBlockAddon fsba;
   private SuperiorSkyBlockAddon ssba;
   private AcidIslandAddon aia;
   private ASkyBlockAddon asa;
   private BentoBoxAddon bba;
   private IridiumSkyBlockAddon irs;
   private PlotSquaredAddon psa;
   private PlotSquaredV5Addon psav5;
   private VaultAddon vault;
   private PlayerPointsAddon points;
   private HologramAddon ha;
   private TownyAddon towny;
   private PreciousStonesAddon pca;
   private ProtectionStonesAddon prsa;
   private PlaceholderAddon placeholder;
   private CMIAddon cmi;
   private boolean addon = false;

   public AddonManager(Main plugin) {
      this.plugin = plugin;
      this.reload();
   }

   public void reload() {
      if (this.plugin.getCfm().isMVdWPlaceholderAPI()) {
         if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
            this.placeholder = new MVdWPlaceholderAPIAddon();
            this.plugin.sendLogMessage("Hooked into §aMVdWPlaceholderAPI§e!");
         } else {
            this.plugin.getConfig().set("addons.MVdWPlaceholderAPI", false);
            this.plugin.saveConfig();
            this.plugin.getCm().reload();
         }
      }

      if (this.plugin.getCfm().isPlaceholdersAPI()) {
         if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.placeholder = new PlaceholderAPIAddon();
            this.plugin.sendLogMessage("Hooked into §aPlaceholderAPI§e!");
         } else {
            this.plugin.getConfig().set("addons.PlaceholderAPI", false);
            this.plugin.saveConfig();
            this.plugin.getCm().reload();
         }
      }

      if (this.plugin.getCfm().isShopguiplus()) {
         if (Bukkit.getPluginManager().isPluginEnabled("ShopGUIPlus")) {
            this.sellAddons.add(new ShopGUIAddon());
         } else {
            this.plugin.getConfig().set("addons.shopguiplus", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isCmi() || this.plugin.getCfm().isCmiholograms()) {
         if (Bukkit.getPluginManager().isPluginEnabled("CMI")) {
            CMIAddon cmi = new CMIAddon(this.plugin);
            if (this.plugin.getCfm().isCmi()) {
               this.sellAddons.add(cmi);
            }

            if (this.plugin.getCfm().isCmiholograms()) {
               this.cmi = cmi;
            }
         } else {
            this.plugin.getConfig().set("addons.cmi", false);
            this.plugin.getConfig().set("addons.cmiholograms", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isEssentials()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            this.sellAddons.add(new EssentialsAddon());
         } else {
            this.plugin.getConfig().set("addons.essentials", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isUltimateClaims()) {
         if (Bukkit.getPluginManager().isPluginEnabled("UltimateClaims")) {
            this.protectionAddons.add(new UltimateClaimsAddon());
         } else {
            this.plugin.getConfig().set("addons.UltimateClaims", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isLands()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Lands")) {
            this.protectionAddons.add(new LandsAddon());
         } else {
            this.plugin.getConfig().set("addons.lands", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isFactionsUUID()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Factions")) {
            this.protectionAddons.add(new FactionUUIDAddon());
         } else {
            this.plugin.getConfig().set("addons.factionsUUID", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isMassivefaction()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Factions")) {
            this.protectionAddons.add(new MassiveFactionAddon());
         } else {
            this.plugin.getConfig().set("addons.massivefaction", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isWorldguard()) {
         if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            this.protectionAddons.add(new WorldGuardAddon());
         } else {
            this.plugin.getConfig().set("addons.worldguard", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isGriefprevention()) {
         if (Bukkit.getPluginManager().isPluginEnabled("GriefPrevention")) {
            this.protectionAddons.add(new GriefPreventionAddon());
         } else {
            this.plugin.getConfig().set("addons.griefprevention", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isResidence()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Residence")) {
            this.protectionAddons.add(new ResidenceAddon());
         } else {
            this.plugin.getConfig().set("addons.residence", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isRedprotect()) {
         if (Bukkit.getPluginManager().isPluginEnabled("RedProtect")) {
            this.protectionAddons.add(new RedProtectAddon());
         } else {
            this.plugin.getConfig().set("addons.redprotect", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isPreciousstones()) {
         if (Bukkit.getPluginManager().isPluginEnabled("PreciousStones")) {
            this.pca = new PreciousStonesAddon();
         } else {
            this.plugin.getConfig().set("addons.preciousstones", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isProtectionstones()) {
         if (Bukkit.getPluginManager().isPluginEnabled("ProtectionStones")) {
            this.prsa = new ProtectionStonesAddon(this.plugin);
         } else {
            this.plugin.getConfig().set("addons.protectionstones", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isTowny()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Towny")) {
            this.towny = new TownyAddon();
         } else {
            this.plugin.getConfig().set("addons.towny", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isTrHologram()) {
         if (Bukkit.getPluginManager().isPluginEnabled("TrHologram")) {
            this.ha = new TrHologramAddon();
         } else {
            this.plugin.getConfig().set("addons.trHologram", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isHolograms()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Holograms")) {
            this.ha = new HologramsAddon(this.plugin);
         } else {
            this.plugin.getConfig().set("addons.holograms", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isHolographicdisplays()) {
         if (Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            this.ha = new HolographicDisplaysAddon(this.plugin);
         } else {
            this.plugin.getConfig().set("addons.holographicdisplays", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isPlotsquared()) {
         if (Bukkit.getPluginManager().isPluginEnabled("PlotSquared")) {
            this.psa = new PlotSquaredAddon();
         } else {
            this.plugin.getConfig().set("addons.plotsquared", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isPlotsquaredv5()) {
         if (Bukkit.getPluginManager().isPluginEnabled("PlotSquared")) {
            this.psav5 = new PlotSquaredV5Addon();
         } else {
            this.plugin.getConfig().set("addons.plotsquaredv5", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isSuperiorskyblock()) {
         if (Bukkit.getPluginManager().isPluginEnabled("SuperiorSkyblock2")) {
            this.ssba = new SuperiorSkyBlockAddon(this.plugin);
            Bukkit.getServer().getPluginManager().registerEvents(this.ssba, this.plugin);
            this.addon = true;
         } else {
            this.plugin.getConfig().set("addons.superiorskyblock", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isAcidisland()) {
         if (Bukkit.getPluginManager().isPluginEnabled("AcidIsland")) {
            this.aia = new AcidIslandAddon(this.plugin);
            Bukkit.getServer().getPluginManager().registerEvents(this.aia, this.plugin);
            this.addon = true;
         } else {
            this.plugin.getConfig().set("addons.acidisland", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isAskyblock()) {
         if (Bukkit.getPluginManager().isPluginEnabled("ASkyBlock")) {
            this.asa = new ASkyBlockAddon(this.plugin);
            Bukkit.getServer().getPluginManager().registerEvents(this.asa, this.plugin);
            this.addon = true;
         } else {
            this.plugin.getConfig().set("addons.askyblock", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isBentobox()) {
         if (Bukkit.getPluginManager().isPluginEnabled("BentoBox")) {
            this.bba = new BentoBoxAddon(this.plugin);
            Bukkit.getServer().getPluginManager().registerEvents(this.bba, this.plugin);
            this.addon = true;
         } else {
            this.plugin.getConfig().set("addons.bentobox", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isIridiumskyblock()) {
         if (Bukkit.getPluginManager().isPluginEnabled("IridiumSkyblock")) {
            this.irs = new IridiumSkyBlockAddon(this.plugin);
            Bukkit.getServer().getPluginManager().registerEvents(this.irs, this.plugin);
            this.addon = true;
         } else {
            this.plugin.getConfig().set("addons.iridiumskyblock", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isVault()) {
         if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            this.vault = new VaultAddon(this.plugin);
         } else {
            this.plugin.getConfig().set("addons.vault", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isPlayerpoints()) {
         if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            this.points = new PlayerPointsAddon();
         } else {
            this.plugin.getConfig().set("addons.playerpoints", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      if (this.plugin.getCfm().isLuckperms()) {
         if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            this.lpa = new LuckPermsAddon(this.plugin);
         } else {
            this.plugin.getConfig().set("addons.luckperms", false);
            this.plugin.saveConfig();
            this.plugin.getCfm().reload();
         }
      }

      (new BukkitRunnable() {
         public void run() {
            if (Bukkit.getPluginManager().isPluginEnabled("FabledSkyBlock")) {
               AddonManager.this.fsba = new FabledSkyBlockAddon(AddonManager.this.plugin);
               Bukkit.getServer().getPluginManager().registerEvents(AddonManager.this.fsba, AddonManager.this.plugin);
               AddonManager.this.addon = true;
            } else {
               AddonManager.this.plugin.getConfig().set("addons.fabledskyblock", false);
               AddonManager.this.plugin.saveConfig();
               AddonManager.this.plugin.getCfm().reload();
            }

         }
      }).runTaskLater(this.plugin, 100L);
   }

   public List<String> parsePlaceholders(Player p, List<String> text) {
      return this.placeholder != null ? this.placeholder.parsePlaceholders(p, text) : text;
   }

   public int getMaxPerType(Player p, String key) {
      int max = 0;

      for(PermissionAttachmentInfo attachmentInfo : p.getEffectivePermissions()) {
         String perm = attachmentInfo.getPermission();
         if (perm.startsWith("ultraminions.maxplace." + key)) {
            try {
               int d = Integer.parseInt(perm.replaceFirst("ultraminions.maxplace." + key, ""));
               if (d > max) {
                  max = d;
               }
            } catch (NumberFormatException var8) {
            }
         }
      }

      return max;
   }

   public PreciousStonesAddon getPca() {
      return this.pca;
   }

   public ProtectionStonesAddon getPrsa() {
      return this.prsa;
   }

   public TownyAddon getTowny() {
      return this.towny;
   }

   public boolean isProtect(Player p, Location loc) {
      boolean canBuild = false;

      for(ProtectionAddon pa : this.protectionAddons) {
         if (!pa.canBuild(p, loc)) {
            canBuild = true;
         }
      }

      return canBuild;
   }

   public boolean isPricePlugin() {
      return !this.sellAddons.isEmpty();
   }

   public double getPrice(Player p, ItemStack item) {
      double price = (double)0.0F;
      Iterator var5 = this.sellAddons.iterator();
      if (var5.hasNext()) {
         SellAddon sa = (SellAddon)var5.next();
         price = (double)sa.getPrice(p, item);
      }

      return price;
   }

   public boolean isAddon() {
      return this.addon;
   }

   public boolean hasPermission(Player p, String perm) {
      if (p.isOp()) {
         return true;
      } else {
         return this.lpa == null ? p.hasPermission(perm) : this.lpa.hasPermission(p, perm.toLowerCase());
      }
   }

   public Tier getTier(Player p) {
      PlayerData pd = PlayerData.getPlayerUUID(p.getUniqueId());
      return pd != null ? this.plugin.getTm().getTier(pd) : null;
   }

   public int getMaxMinion(Player p) {
      int maxSelected = 0;
      if (this.plugin.getCfm().isUnlockingTiers()) {
         PlayerData pd = PlayerData.getPlayerUUID(p.getUniqueId());
         if (pd != null) {
            maxSelected = this.plugin.getTm().getTier(pd).getMax();
         }
      }

      if (this.plugin.getCfm().isMaxMinionInData()) {
         PlayerData pd = PlayerData.getPlayerUUID(p.getUniqueId());
         if (pd != null && maxSelected < pd.getMaxMinion()) {
            maxSelected = pd.getMaxMinion();
         }
      }

      if (!p.isOp() && !p.hasPermission("minions.max.*")) {
         if (maxSelected < this.plugin.getCfm().getDefaultMaxMinion()) {
            maxSelected = this.plugin.getCfm().getDefaultMaxMinion();
         }

         for(PermissionAttachmentInfo attachmentInfo : p.getEffectivePermissions()) {
            String perm = attachmentInfo.getPermission();
            if (perm.startsWith("minions.max.")) {
               try {
                  int d = Integer.parseInt(perm.replaceFirst("minions.max.", ""));
                  if (d > maxSelected) {
                     maxSelected = d;
                  }
               } catch (NumberFormatException var7) {
                  return maxSelected;
               }
            }
         }

         return maxSelected;
      } else {
         return 999;
      }
   }

   public boolean isAllowedPlot(Player p, Location loc) {
      return this.psav5 != null ? this.psav5.isAllowedPlot(p, loc) : this.psa.isAllowedPlot(p, loc);
   }

   public boolean isStackable(Location b) {
      return this.fsba != null && b != null ? this.fsba.isStackable(b) : false;
   }

   public boolean checkMember(Player p) {
      if (this.fsba != null) {
         return this.fsba.checkMember(p);
      } else if (this.ssba != null) {
         return this.ssba.checkMember(p);
      } else if (this.asa != null) {
         return this.asa.checkMember(p);
      } else if (this.aia != null) {
         return this.aia.checkMember(p);
      } else if (this.bba != null) {
         return this.bba.checkMember(p);
      } else {
         return this.irs != null ? this.irs.checkMember(p) : false;
      }
   }

   public void addCoins(Player p, double amount) {
      if (this.plugin.getCfm().isVault()) {
         this.vault.addCoins(p, amount);
      } else if (this.plugin.getCfm().isPlayerpoints()) {
         this.points.addCoins(p, amount);
      }

   }

   public void removeCoins(Player p, double amount) {
      if (this.plugin.getCfm().isVault()) {
         this.vault.removeCoins(p, amount);
      } else if (this.plugin.getCfm().isPlayerpoints()) {
         this.points.removeCoins(p, amount);
      }

   }

   public double getCoins(Player p) {
      if (this.plugin.getCfm().isVault()) {
         return this.vault.getCoins(p);
      } else {
         return this.plugin.getCfm().isPlayerpoints() ? this.points.getCoins(p) : (double)0.0F;
      }
   }

   public void createHologram(PlayerMinion pm, Location spawn, List<String> lines) {
      if (this.hasHologramPlugin()) {
         if (this.ha != null) {
            this.ha.createHologram(pm, spawn, lines);
         }

         if (this.plugin.getCfm().isCmiholograms()) {
            this.cmi.createHologram(pm, spawn, lines);
         }

      }
   }

   public void deleteHologram(PlayerMinion pm) {
      if (this.hasHologramPlugin()) {
         if (this.ha != null) {
            this.ha.deleteHologram(pm);
         }

         if (this.plugin.getCfm().isCmiholograms()) {
            this.cmi.deleteHologram(pm);
         }

      }
   }

   public boolean hasHologram(PlayerMinion pm) {
      if (!this.hasHologramPlugin()) {
         return false;
      } else if (this.ha != null) {
         return this.ha.hasHologram(pm);
      } else {
         return this.plugin.getCfm().isCmiholograms() ? this.cmi.hasHologram(pm) : false;
      }
   }

   public void delete() {
      if (this.hasHologramPlugin()) {
         if (this.ha != null) {
            this.ha.delete();
         }

         if (this.plugin.getCfm().isCmiholograms()) {
            this.cmi.delete();
         }

      }
   }

   public boolean hasEconomyPlugin() {
      if (this.vault != null) {
         return true;
      } else {
         return this.points != null;
      }
   }

   public boolean hasHologramPlugin() {
      if (this.cmi != null) {
         return true;
      } else {
         return this.ha != null;
      }
   }
}
