package com.lucas.specterlooting;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main m;

	@Override
	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("§7| §bSpecterLooting        §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §bVersão 1.0            §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §fStatus: §aLigado         §7|");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("");
		
		m = this;
		
		Bukkit.getPluginManager().registerEvents(new Comando(), this);
		Bukkit.getPluginManager().registerEvents(new Eventos(), this);
		getCommand("givelivro").setExecutor(new Comando());
		getCommand("stacklivros").setExecutor(new Comando());
	}
}
