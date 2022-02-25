package com.lucas.specterlooting;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Comando implements Listener, CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandSender s = sender;

		if (label.equalsIgnoreCase("givelivro")) {
			if (!s.hasPermission("specterlooting.admin")) {
				s.sendMessage("§cVocê não tem permissão para fazer isso.");
				return true;
			}

			if (args.length != 3) {
				s.sendMessage("§cUtilize: /givelivro <Player> <Tipo> <Nivel>");
				return true;
			}
			if (isInt(args[2]) == false) {
				s.sendMessage("§cNivel inválido");
				return true;
			}
			if (Bukkit.getPlayer(args[0]) == null) {
				s.sendMessage("§cPlayer inválido");
				return true;
			}
			Player t = Bukkit.getPlayer(args[0]);
			if (args[1].equalsIgnoreCase("pilhagem")) {

				ItemStack loot = new ItemStack(Material.ENCHANTED_BOOK);
				ItemMeta lootmeta = loot.getItemMeta();
				lootmeta.setDisplayName("§eLivro Encantado");
				ArrayList<String> loreloot = new ArrayList<String>();
				loreloot.add("§9Combate");
				loreloot.add("§7Pilhagem " + args[2]);
				lootmeta.setLore(loreloot);
				lootmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				loot.setItemMeta(lootmeta);
				t.getInventory().addItem(loot);
				s.sendMessage("§aLivro enviado com sucesso");
			} else if (args[1].equalsIgnoreCase("fortuna")) {

				ItemStack loot = new ItemStack(Material.ENCHANTED_BOOK);
				ItemMeta lootmeta = loot.getItemMeta();
				lootmeta.setDisplayName("§eLivro Encantado");
				ArrayList<String> loreloot = new ArrayList<String>();
				loreloot.add("§9Ferramentas");
				loreloot.add("§7Fortuna " + args[2]);
				lootmeta.setLore(loreloot);
				lootmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				loot.setItemMeta(lootmeta);
				t.getInventory().addItem(loot);
				s.sendMessage("§aLivro enviado com sucesso");
			} else {
				s.sendMessage("§cTipo inv§lido");
				return true;
			}
		} else if (label.equalsIgnoreCase("stacklivros")) {
			if (!(sender instanceof Player))
				return true;
			Player p = (Player) sender;
			if (args.length != 1) {
				p.sendMessage("§cUtilize: /stacklivros <pilhagem/fortuna>");
				return true;
			}
			if (args[0].equalsIgnoreCase("pilhagem")) {
				Integer i = 0;
				for (ItemStack item : p.getInventory().getContents()) {
					if (item != null) {
						if (item.hasItemMeta()) {
							if (item.getItemMeta().hasLore()) {
								if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§eLivro Encantado")
										&& item.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {

									if (item.getItemMeta().getLore().get(1).contains("Pilhagem")) {
										i = i + Integer.valueOf(
												item.getItemMeta().getLore().get(1).replace("§7Pilhagem ", ""));
										p.getInventory().remove(item);
									}
								}
							}
						}
					}
				}
				if (i != 0) {
					ItemStack loot = new ItemStack(Material.ENCHANTED_BOOK);
					ItemMeta lootmeta = loot.getItemMeta();
					lootmeta.setDisplayName("§eLivro Encantado");
					ArrayList<String> loreloot = new ArrayList<String>();
					loreloot.add("§9Combate");
					loreloot.add("§7Pilhagem " + i);
					lootmeta.setLore(loreloot);
					lootmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
					loot.setItemMeta(lootmeta);
					p.getInventory().addItem(loot);
				} else {
					p.sendMessage("§cVocê não possui livros de looting para stackar");
				}
			} else if (args[0].equalsIgnoreCase("fortuna")) {
				Integer i = 0;
				for (ItemStack item : p.getInventory().getContents()) {
					if (item != null) {
						if (item.hasItemMeta()) {
							if (item.getItemMeta().hasLore()) {
								if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§eLivro Encantado")
										&& item.getItemMeta().hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {

									if (item.getItemMeta().getLore().get(1).contains("Fortuna")) {
										i = i + Integer
												.valueOf(item.getItemMeta().getLore().get(1).replace("§7Fortuna ", ""));
										p.getInventory().remove(item);
									}
								}
							}
						}
					}
				}
				if (i != 0) {
					ItemStack loot = new ItemStack(Material.ENCHANTED_BOOK);
					ItemMeta lootmeta = loot.getItemMeta();
					lootmeta.setDisplayName("§eLivro Encantado");
					ArrayList<String> loreloot = new ArrayList<String>();
					loreloot.add("§9Ferramentas");
					loreloot.add("§7Fortuna " + i);
					lootmeta.setLore(loreloot);
					lootmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
					loot.setItemMeta(lootmeta);
					p.getInventory().addItem(loot);
				} else {
					p.sendMessage("§cVocê não possui livros de looting para stackar");
				}
			} else {
				p.sendMessage("§cUtilize: /stacklivros <pilhagem/fortuna>");
				return true;
			}
		}
		return false;
	}

	public static boolean isInt(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
