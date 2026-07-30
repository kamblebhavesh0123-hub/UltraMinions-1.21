package io.github.Leonardo0013YT.UltraMinions.addons.skyblocks;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.api.event.island.IslandCreateEvent;
import com.songoda.skyblock.api.event.island.IslandDeleteEvent;
import com.songoda.skyblock.api.island.Island;
import com.songoda.skyblock.api.island.IslandEnvironment;
import com.songoda.skyblock.api.island.IslandManager;
import com.songoda.skyblock.api.island.IslandRole;
import com.songoda.skyblock.api.island.IslandWorld;
import com.songoda.skyblock.stackable.StackableManager;
import io.github.Leonardo0013YT.UltraMinions.Main;
import java.util.ArrayList;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FabledSkyBlockAddon implements Listener {
   private Main plugin;

   public FabledSkyBlockAddon(Main plugin) {
      this.plugin = plugin;
   }

   public boolean checkMember(Player p) {
      IslandManager sb = SkyBlockAPI.getIslandManager();
      Island is = sb.getIslandAtLocation(p.getLocation());
      if (is == null) {
         return false;
      } else {
         IslandRole r = is.getRole(p);
         if (r != null) {
            return r.equals(IslandRole.MEMBER) || r.equals(IslandRole.OWNER) || r.equals(IslandRole.COOP) || r.equals(IslandRole.OPERATOR) || is.getCoopPlayers().containsKey(p.getUniqueId()) || is.getOwnerUUID().equals(p.getUniqueId());
         } else {
            return is.getCoopPlayers().containsKey(p.getUniqueId()) || is.getOwnerUUID().equals(p.getUniqueId());
         }
      }
   }

   public boolean isStackable(Location b) {
      StackableManager stackableManager = SkyBlockAPI.getImplementation().getStackableManager();
      return stackableManager.isStacked(b);
   }

   @EventHandler
   public void onCreate(IslandCreateEvent e) {
      Player p = e.getPlayer();
      if (this.plugin.getCfm().isAutoMinionEnabled()) {
         Island island = e.getIsland();
         Location miLoc = island.getLocation(IslandWorld.OVERWORLD, IslandEnvironment.ISLAND).clone().add(this.plugin.getCfm().getAddX(), this.plugin.getCfm().getAddY(), this.plugin.getCfm().getAddZ());
         this.plugin.getMm().createIslandMinion(p, miLoc);
      }
   }

   @EventHandler
   public void onDelete(IslandDeleteEvent e) {
      Island island = e.getIsland();
      Location loc = island.getLocation(IslandWorld.OVERWORLD, IslandEnvironment.ISLAND);
      int size = island.getSize() / 2;
      int minX = loc.getBlockX() - size;
      int maxZ = loc.getBlockZ() - size;
      byte y = 0;
      ArrayList<Chunk> chunks = new ArrayList();

      for(int x = minX; x <= minX + island.getSize(); x += 16) {
         for(int z = maxZ; z <= maxZ + island.getSize(); z += 16) {
            Chunk c = loc.getWorld().getBlockAt(x, y, z).getChunk();
            if (!chunks.contains(c)) {
               chunks.add(c);
            }
         }
      }

      for(Chunk c : chunks) {
         for(Entity ent : c.getEntities()) {
            if (ent instanceof ArmorStand) {
               this.plugin.getMm().removeIslandMinion(ent);
            }
         }
      }

   }
}
