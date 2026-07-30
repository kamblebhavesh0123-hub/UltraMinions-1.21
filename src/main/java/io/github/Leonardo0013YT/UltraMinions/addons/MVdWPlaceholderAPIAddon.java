package io.github.Leonardo0013YT.UltraMinions.addons;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import io.github.Leonardo0013YT.UltraMinions.interfaces.PlaceholderAddon;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public class MVdWPlaceholderAPIAddon implements PlaceholderAddon {
   public String parsePlaceholders(Player p, String value) {
      return PlaceholderAPI.replacePlaceholders(p, value);
   }

   public List<String> parsePlaceholders(Player p, List<String> list) {
      List<String> replaced = new ArrayList();

      for(String s : list) {
         replaced.add(PlaceholderAPI.replacePlaceholders(p, s));
      }

      return replaced;
   }
}
