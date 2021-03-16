package com.elizabeth.bungeetestlobby;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class PlayerListener implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		setLobbyInv(e.getPlayer());
		
	}
	
	
	private void setLobbyInv(Player player) {
		
		//SET A NEW NAVIGATOR WHEN THE PLAYER JOINS
		ItemStack navigator = new ItemStack(Material.COMPASS);
		ItemMeta navMeta = navigator.getItemMeta();
		navMeta.setDisplayName(ChatColor.AQUA + "Navigator");
		navigator.setItemMeta(navMeta);
		
		player.getInventory().setItem(0, navigator);
	}

	
	
}
