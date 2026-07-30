package io.github.Leonardo0013YT.UltraMinions.addons;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.entity.Player;

public class PlayerPointsAddon {
   private PlayerPointsAPI pointsAPI = ((PlayerPoints)PlayerPoints.getPlugin(PlayerPoints.class)).getAPI();

   public void addCoins(Player p, double amount) {
      if (this.pointsAPI != null) {
         this.pointsAPI.give(p.getUniqueId(), (int)amount);
      }

   }

   public void removeCoins(Player p, double amount) {
      if (this.pointsAPI != null) {
         this.pointsAPI.take(p.getUniqueId(), (int)amount);
      }

   }

   public double getCoins(Player p) {
      return (double)this.pointsAPI.look(p.getUniqueId());
   }
}
