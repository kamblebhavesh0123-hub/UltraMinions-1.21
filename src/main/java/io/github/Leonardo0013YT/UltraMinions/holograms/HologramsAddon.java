package io.github.Leonardo0013YT.UltraMinions.addons.holograms;

import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.HologramManager;
import com.sainttx.holograms.api.HologramPlugin;
import com.sainttx.holograms.api.line.HologramLine;
import com.sainttx.holograms.api.line.TextLine;
import io.github.Leonardo0013YT.UltraMinions.Main;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerMinion;
import io.github.Leonardo0013YT.UltraMinions.interfaces.HologramAddon;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramsAddon implements HologramAddon {
   private HashMap<PlayerMinion, Hologram> holograms = new HashMap();
   private HologramManager hologramManager = ((HologramPlugin)JavaPlugin.getPlugin(HologramPlugin.class)).getHologramManager();
   private Main plugin;

   public HologramsAddon(Main plugin) {
      this.plugin = plugin;
   }

   public void createHologram(PlayerMinion pm, Location spawn, List<String> lines) {
      if (this.plugin.getCfm().isHologramsSystem()) {
         Hologram h = new Hologram(pm.toString(), spawn.clone().add((double)0.0F, (double)1.0F + (double)lines.size() * 0.3, (double)0.0F), false);

         for(String l : lines) {
            HologramLine hl = new TextLine(h, l);
            h.addLine(hl);
         }

         h.spawn();
         this.hologramManager.addActiveHologram(h);
         this.holograms.put(pm, h);
      }
   }

   public void deleteHologram(PlayerMinion pm) {
      if (this.plugin.getCfm().isHologramsSystem()) {
         if (this.holograms.containsKey(pm)) {
            this.hologramManager.deleteHologram((Hologram)this.holograms.get(pm));
         }

      }
   }

   public boolean hasHologram(PlayerMinion pm) {
      return !this.plugin.getCfm().isHologramsSystem() ? false : this.holograms.containsKey(pm);
   }

   public void delete() {
      if (this.plugin.getCfm().isHologramsSystem()) {
         this.holograms.keySet().forEach(this::deleteHologram);
         this.holograms.clear();
      }
   }
                                              }
                                   
