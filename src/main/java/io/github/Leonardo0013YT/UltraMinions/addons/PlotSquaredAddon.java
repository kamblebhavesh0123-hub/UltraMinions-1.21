package io.github.Leonardo0013YT.UltraMinions.addons;

import com.github.intellectualsites.plotsquared.plot.object.Plot;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlotSquaredAddon {
   public boolean isAllowedPlot(Player p, Location loc) {
      if (loc.getWorld() == null) {
         return false;
      } else {
         com.github.intellectualsites.plotsquared.plot.object.Location l = new com.github.intellectualsites.plotsquared.plot.object.Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getYaw(), loc.getPitch());
         Plot plot = Plot.getPlot(l);
         if (plot == null) {
            return false;
         } else {
            return plot.isAdded(p.getUniqueId()) || plot.isOwner(p.getUniqueId());
         }
      }
   }
}
