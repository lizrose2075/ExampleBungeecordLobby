package com.elizabeth.bungeetestlobby;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class NavigatorGui {

	public NavigatorGui(Player player) {
		
		/*
		 * The Localized name is just the name of the bungeecord server it will send the player to
		   These localized names correspond the ones in my bungeecord's config.yml
		 */
		//CREATE THE GUI
		Inventory gui = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Navigator" );
		
		ItemStack playerHead = customHead(player);
		gui.setItem(0, playerHead);
		
		ItemStack buildBattle = new ItemStack(Material.BRICK);
		ItemMeta bbMeta = buildBattle.getItemMeta();
		bbMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Build Battle");
		bbMeta.setLocalizedName("BuildBattle");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Put your creative building to the test!");
		lore.add("Click to join!");
		bbMeta.setLore(lore);
		buildBattle.setItemMeta(bbMeta);
		gui.setItem(1, buildBattle);
		
		ItemStack skyWars = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta swMeta = skyWars.getItemMeta();
		swMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 1, true);
		swMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sky Wars");
		swMeta.setLocalizedName("SkyWars");
		lore.clear();
		lore.add("Test out your pvp 600 blocks high!");
		lore.add("Click to join!");
		skyWars.setItemMeta(swMeta);
		gui.setItem(2, skyWars);
		
		player.openInventory(gui);
	}
	
	//SETS THE PLAYERS HEAD BECAUSE IT FELT A BIT BARE TO ME HAHA
	@SuppressWarnings("deprecation")
	private ItemStack customHead(Player player) {
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setDisplayName(player.getName());
		skullMeta.setLocalizedName(null);
		skullMeta.setOwner(player.getName());
		skull.setItemMeta(skullMeta);
		return skull;
	}
}
