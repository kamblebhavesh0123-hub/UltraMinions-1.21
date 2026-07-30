package io.github.Leonardo0013YT.UltraMinions.addons;

import com.plotsquared.core.plot.Plot;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlotSquaredV5Addon {
   public boolean isAllowedPlot(Player p, Location loc) {
      if (loc.getWorld() == null) {
         return false;
      } else {
         com.plotsquared.core.location.Location l = new com.plotsquared.core.location.Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getYaw(), loc.getPitch());
         Plot plot = Plot.getPlot(l);
         if (plot == null) {
            return false;
         } else {
            return plot.isAdded(p.getUniqueId()) || plot.isOwner(p.getUniqueId());
         }
      }
   }
}
