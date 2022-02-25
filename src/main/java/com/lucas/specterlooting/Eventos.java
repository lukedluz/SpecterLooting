package com.lucas.specterlooting;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Eventos implements Listener {

	@EventHandler
	public void onClick(final InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if ((e.getAction() == InventoryAction.SWAP_WITH_CURSOR)
				&& (e.getCursor().getType().equals(Material.ENCHANTED_BOOK))) {
			if (e.getCursor().hasItemMeta()) {
				if (e.getCursor().getItemMeta().hasLore()) {
					if (e.getCursor().getItemMeta().getDisplayName().equalsIgnoreCase("§eLivro Encantado")) {
						if (e.getCursor().getItemMeta().getLore().get(1).contains("Fortuna") && e.getCurrentItem().getType().toString().contains("AXE") && !e.getCurrentItem().getType().toString().contains("PICKAXE")) {
							if (!Comando.isInt(e.getCursor().getItemMeta().getLore().get(1).replace("§7Fortuna ", ""))) return;
							int nvl = Integer
									.valueOf(e.getCursor().getItemMeta().getLore().get(1).replace("§7Fortuna ", ""));
							if (e.getCurrentItem().hasItemMeta()) {
								if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
									Integer nv = e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
									if (nv >= 30000) {
										p.sendMessage("§cEsse item já chegou no limite de fortuna");
										return;
									}
									if (nvl + nv > 30000) {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_BLOCKS, 30000);
									} else {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_BLOCKS, nvl + nv);	
									}
								} else {
									if (nvl > 30000) {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_BLOCKS, 30000);
									} else {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_BLOCKS, nvl);
									}
								}
							} else {
								if (nvl > 30000) {
									EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_BLOCKS, 30000);
								} else {
									EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_BLOCKS, nvl);
								}
							}
							p.setItemOnCursor(new ItemStack(Material.AIR));
							e.setCancelled(true);
							return;
						} else if (e.getCursor().getItemMeta().getLore().get(1).contains("Pilhagem") && e.getCurrentItem().getType().toString().contains("SWORD")) {
							if (!Comando.isInt(e.getCursor().getItemMeta().getLore().get(1).replace("§7Pilhagem ", ""))) return;
							int nvl = Integer
									.valueOf(e.getCursor().getItemMeta().getLore().get(1).replace("§7Pilhagem ", ""));
							if (e.getCurrentItem().hasItemMeta()) {
								if (e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().contains("§eMatadora de Boss")) return;
								if (e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_MOBS)) {
									Integer nv = e.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS);
									if (nv >= 30000) {
										p.sendMessage("§cEsse item já chegou no limite de pilhagem");
										return;
									}
									if (nvl + nv > 30000) {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_MOBS, 30000);
									} else {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_MOBS, nvl + nv);	
									}
								} else {
									if (nvl > 30000) {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_MOBS, 30000);
									} else {
										EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_MOBS, nvl);
									}
								}
							} else {
								if (nvl > 30000) {
									EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_MOBS, 30000);
								} else {
									EnchantItem(e.getCurrentItem(), Enchantment.LOOT_BONUS_MOBS, nvl);
								}
							}
							p.setItemOnCursor(new ItemStack(Material.AIR));
							e.setCancelled(true);
							return;
						}
					}
				}
			}
		}
	}
	
	public static void EnchantItem(ItemStack item, Enchantment enchant, Integer nivel) {
		if (nivel != 0) {
			if (nivel > enchant.getMaxLevel()) {
				item.addUnsafeEnchantment(enchant, nivel);
			} else {
				item.addEnchantment(enchant, nivel);
			}
		}
	}
}
