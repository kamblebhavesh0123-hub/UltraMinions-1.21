package io.github.Leonardo0013YT.UltraMinions.addons;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import io.github.Leonardo0013YT.UltraMinions.Main;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerMinion;
import io.github.Leonardo0013YT.UltraMinions.interfaces.HologramAddon;
import io.github.Leonardo0013YT.UltraMinions.interfaces.SellAddon;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.Zrips.CMILib.Container.CMILocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CMIAddon implements SellAddon, HologramAddon {
   private HashMap<PlayerMinion, CMIHologram> holograms = new HashMap();
   private Main plugin;

   public CMIAddon(Main plugin) {
      this.plugin = plugin;
   }

   public float getPrice(Player p, ItemStack item) {
      ItemStack var3 = item.clone();
      var3.setAmount(1);
      return CMI.getInstance().getWorthManager().getWorth(var3).getSellPrice().floatValue() * (float)item.getAmount();
   }

   public void createHologram(PlayerMinion pm, Location spawn, List<String> lines) {
      if (this.plugin.getCfm().isHologramsSystem()) {
         CMIHologram h = new CMIHologram(UUID.randomUUID().toString(), new CMILocation(spawn.clone().add((double)0.0F, 1.7 + (double)lines.size() * 0.3, (double)0.0F)));

         for(String l : lines) {
            h.addLine(l);
         }

         CMI.getInstance().getHologramManager().addHologram(h);
         this.holograms.put(pm, h);
      }
   }

   public void deleteHologram(PlayerMinion pm) {
      if (this.plugin.getCfm().isHologramsSystem()) {
         if (this.holograms.containsKey(pm)) {
            CMIHologram h = (CMIHologram)this.holograms.get(pm);
            h.hide();
            h.remove();
            this.holograms.remove(pm);
         }

      }
   }

   public boolean hasHologram(PlayerMinion pm) {
      return !this.plugin.getCfm().isHologramsSystem() ? false : this.holograms.containsKey(pm);
   }

   public void delete() {
      if (this.plugin.getCfm().isHologramsSystem()) {
         this.holograms.values().forEach(CMIHologram::remove);
         this.holograms.clear();
      }
   }
        }
           
