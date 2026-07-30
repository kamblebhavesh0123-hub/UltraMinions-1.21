package io.github.Leonardo0013YT.UltraMinions.utils;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MinionUtils_1_17 {
   public static void damageBlock(Location l, int damage) {
      try {
         PacketPlayOutBlockBreakAnimation breaking = new PacketPlayOutBlockBreakAnimation(0, BlockPosition.b.b(l.getX(), l.getY(), l.getZ()), damage);

         for(Entity ent : l.getWorld().getNearbyEntities(l, (double)4.0F, (double)4.0F, (double)4.0F)) {
            if (ent instanceof Player p) {
               sendPacket(p, breaking);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public static void sendPacket(Player player, Packet<?> packet) {
      try {
         ((CraftPlayer)player).getHandle().b.sendPacket(packet);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
            }
