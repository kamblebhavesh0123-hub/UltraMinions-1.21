package io.github.Leonardo0013YT.UltraMinions.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class MinionUtils_1_17 {

    /**
     * Applies a base64 texture directly to a Skull item on modern Paper (1.20.5+).
     */
    public static ItemStack getCustomHead(String base64Texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (base64Texture == null || base64Texture.isEmpty()) return head;

        SkullMeta meta = (SkullMeta) head.getItemMeta();
        if (meta != null) {
            PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
            profile.setProperty(new ProfileProperty("textures", base64Texture));
            meta.setPlayerProfile(profile);
            head.setItemMeta(meta);
        }
        return head;
    }

    /**
     * Attaches minion data tags using standard persistent data container logic.
     */
    public static ItemStack createMinionItem(ItemStack baseItem, String minionType, int level) {
        ItemStack item = NBTEditor.setString(baseItem, "minion_type", minionType);
        return NBTEditor.setInt(item, "minion_level", level);
    }

    public static String getMinionType(ItemStack item) {
        return NBTEditor.getString(item, "minion_type");
    }

    public static int getMinionLevel(ItemStack item) {
        return NBTEditor.getInt(item, "minion_level");
    }
}
