package com.elizabeth.bungeetestlobby;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class NavigatorListener implements Listener, PluginMessageListener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	//CHECKS THE ACTION AND IF THE CLICKED ITEM IS A NAVIGATOR
	//ON A LARGE SERVER THIS ISNT A PARTICULARLY EFFICIENT WAY
	public void clickNavigator(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK ||e.getAction() == Action.RIGHT_CLICK_AIR ) {
			if(player.getItemInHand() != null && player.getItemInHand().getType() == Material.COMPASS) {
				ItemStack item = player.getItemInHand();
				ItemMeta im = item.getItemMeta();
				if(im.getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Navigator")){
					//show navigator
					new NavigatorGui(player);
				}
			}
		}
	}
	
	//LISTENS TO WHAT THE PLAYER IS CLICKING IN THE INVENTORY
	//ON A LARGE SERVER THIS ISNT A PARTICULARLY EFFICIENT WAY
	@EventHandler
	public void navigatorClickEvent(InventoryClickEvent e) {
		
		Player player = (Player) e.getWhoClicked();
			
		if(e.getView().getTitle().contains("Navigator") && e.getRawSlot() <= 9 && e.getCurrentItem() != null) {
			
			ItemStack item = e.getCurrentItem();
			ItemMeta meta = item.getItemMeta();
			
			if(!meta.getLocalizedName().equals(null)) {
				
				String serverName = meta.getLocalizedName();
				
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(b);
				try {
					//COMMAND TO CONNECT A PLAYER
					out.writeUTF("Connect");
					//SERVER NAME (the item in the gui's localized name - see NavigatorGui.java)
					out.writeUTF(serverName);
				}catch(IOException exception) {
					Bukkit.getLogger().info("The server could not be found, please check the navigator class");
				}
				//SEND THE ACTUAL COMMAND
				player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
			}
			
			e.setCancelled(true);
			player.closeInventory();
		}
	}
	
	//RETURNS IF ANY OTHER NON-BUNGEE PLUGIN MESSAGES ARE SENT
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if(!channel.equals("BungeeCord")) {return;}
		
	}
	
}
