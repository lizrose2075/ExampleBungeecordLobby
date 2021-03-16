package com.elizabeth.bungeetestlobby;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	private static Main instance;
	
	private static ArrayList<String> serverNames = new ArrayList<String>(
			Arrays.asList("BuildBattle","SkyWars"));
	
	@Override
	public void onEnable() {
		
		System.out.println("LobbyPlugin enabled successfully");
		
		Main.instance = this;
		
		//REGISTERS THE BUNGEECORD PLUGIN CHANNEL
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		//REGISTER THE CLASSES WITH LISTENERS
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new NavigatorListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getInstance() {return instance;}
	
	public static ArrayList<String> getServers() {return serverNames;}
}
