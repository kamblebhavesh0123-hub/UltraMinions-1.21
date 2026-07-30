package io.github.Leonardo0013YT.UltraMinions.addons.protections;

import com.songoda.ultimateclaims.UltimateClaims;
import com.songoda.ultimateclaims.claim.Claim;
import io.github.Leonardo0013YT.UltraMinions.interfaces.ProtectionAddon;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class UltimateClaimsAddon implements ProtectionAddon {
   public boolean canBuild(Player p, Location loc) {
      Claim claim = UltimateClaims.getInstance().getClaimManager().getClaim(loc.getChunk());
      return claim != null ? claim.isOwnerOrMember(p) : true;
   }

   public boolean canBuild(Player p, Block b) {
      Claim claim = UltimateClaims.getInstance().getClaimManager().getClaim(b.getLocation().getChunk());
      return claim != null ? claim.isOwnerOrMember(p) : true;
   }
}
