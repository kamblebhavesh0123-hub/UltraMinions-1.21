package io.github.Leonardo0013YT.UltraMinions.addons.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import io.github.Leonardo0013YT.UltraMinions.Main;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerMinion;
import io.github.Leonardo0013YT.UltraMinions.interfaces.HologramAddon;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;

public class HolographicDisplaysAddon implements HologramAddon {
   private HashMap<PlayerMinion, Hologram> holograms = new HashMap();
   private Main plugin;

   public HolographicDisplaysAddon(Main plugin) {
      this.plugin = plugin;
   }

   public void createHologram(PlayerMinion pm, Location spawn, List<String> lines) {
      if (this.plugin.getCfm().isHologramsSystem()) {
         Hologram h = HologramsAPI.createHologram(Main.get(), spawn.clone().add((double)0.0F, 1.4 + (double)lines.size() * 0.3, (double)0.0F));

         for(String l : lines) {
            h.appendTextLine(l);
         }

         this.holograms.put(pm, h);
      }
   }

   public void deleteHologram(PlayerMinion pm) {
      if (this.plugin.getCfm().isHologramsSystem()) {
         if (this.holograms.containsKey(pm)) {
            ((Hologram)this.holograms.get(pm)).delete();
            this.holograms.remove(pm);
         }

      }
   }

   public boolean hasHologram(PlayerMinion pm) {
      return !this.plugin.getCfm().isHologramsSystem() ? false : this.holograms.containsKey(pm);
   }

   public void delete() {
      if (this.plugin.getCfm().isHologramsSystem()) {
         this.holograms.values().forEach(Hologram::delete);
         this.holograms.clear();
      }
   }
}
