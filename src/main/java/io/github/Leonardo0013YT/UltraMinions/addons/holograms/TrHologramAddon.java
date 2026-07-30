package io.github.Leonardo0013YT.UltraMinions.addons.holograms;

import io.github.Leonardo0013YT.UltraMinions.Main;
import io.github.Leonardo0013YT.UltraMinions.database.PlayerMinion;
import io.github.Leonardo0013YT.UltraMinions.interfaces.HologramAddon;
import java.util.HashMap;
import java.util.List;
import me.arasple.mc.trhologram.api.TrHologramAPI;
import me.arasple.mc.trhologram.hologram.Hologram;
import org.bukkit.Location;

public class TrHologramAddon implements HologramAddon {
   private HashMap<PlayerMinion, Hologram> holograms = new HashMap();

   public void createHologram(PlayerMinion id, Location spawn, List<String> lines) {
      Location loc = spawn.clone();
      Hologram h = TrHologramAPI.createHologram(Main.get(), String.valueOf(id), loc.clone().add((double)0.0F, 1.3 + (double)lines.size() * 0.3, (double)0.0F), lines);
      this.holograms.put(id, h);
   }

   public void deleteHologram(PlayerMinion id) {
      if (this.holograms.containsKey(id)) {
         ((Hologram)this.holograms.get(id)).delete();
         this.holograms.remove(id);
      }

   }

   public boolean hasHologram(PlayerMinion id) {
      return this.holograms.containsKey(id);
   }

   public void delete() {
      for(Hologram h : this.holograms.values()) {
         h.destroyAll();
         h.delete();
      }

      this.holograms.clear();
   }
}
