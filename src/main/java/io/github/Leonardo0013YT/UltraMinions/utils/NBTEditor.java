package io.github.Leonardo0013YT.UltraMinions.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class NBTEditor {

    private static Plugin plugin;

    public static void init(Plugin instance) {
        plugin = instance;
    }

    private static NamespacedKey getKey(String key) {
        return new NamespacedKey(plugin, key.toLowerCase());
    }

    // --- STRING ---
    public static ItemStack setString(ItemStack item, String key, String value) {
        if (item == null || item.getType().isAir()) return item;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(getKey(key), PersistentDataType.STRING, value);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static String getString(ItemStack item, String key) {
        if (item == null || !item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.get(getKey(key), PersistentDataType.STRING);
    }

    // --- INTEGER ---
    public static ItemStack setInt(ItemStack item, String key, int value) {
        if (item == null || item.getType().isAir()) return item;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(getKey(key), PersistentDataType.INTEGER, value);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static int getInt(ItemStack item, String key) {
        if (item == null || !item.hasItemMeta()) return 0;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return 0;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        Integer val = pdc.get(getKey(key), PersistentDataType.INTEGER);
        return val != null ? val : 0;
    }

    // --- BOOLEAN ---
    public static ItemStack setBoolean(ItemStack item, String key, boolean value) {
        if (item == null || item.getType().isAir()) return item;
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(getKey(key), PersistentDataType.BOOLEAN, value);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static boolean getBoolean(ItemStack item, String key) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        Boolean val = pdc.get(getKey(key), PersistentDataType.BOOLEAN);
        return val != null && val;
    }

    // --- CHECK KEY ---
    public static boolean contains(ItemStack item, String key) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;
        return meta.getPersistentDataContainer().has(getKey(key));
    }
                                                  }
          
