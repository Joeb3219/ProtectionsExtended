package info.joeboyle.ProtectionsExtended;

import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
//import org.bukkit.Material;
//import org.bukkit.block.Block;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.sk89q.worldguard.bukkit.WGBukkit;

public class ProjectileHitListener implements Listener{
	private PEConfig options = PEConfig.getInstance();
	boolean debug = options.debug;
	boolean enableWorldGuard = options.enableWorldGuard;
	String modPack = options.modPack;
	String protectionsPlugin = options.protectionsPlugin;
	short itemUsed = 0;
	Player mcPlayer = null;
	Entity projectile = null;
	boolean violation = false;
	
	public boolean canBuild(Player realPlayer, Location loc){
		boolean triggered = false;
		if(enableWorldGuard){
			if(!realPlayer.isOp() && !WGBukkit.getPlugin().canBuild(realPlayer, loc)){triggered=true;}
		}
		if(protectionsPlugin.equalsIgnoreCase("towny")){
			String blockTown = TownyUniverse.getTownName(loc);
			Resident player;
			String playerTown=null;
				try {
					player = TownyUniverse.getDataSource().getResident(realPlayer.getName());
				 playerTown = player.getTown().getName();
				} catch (NotRegisteredException e) {}
				//Compare towns, do nothing if null town/OP user.
				if(blockTown != playerTown && blockTown != null && !mcPlayer.isOp()){triggered=true;}
		}
		if(protectionsPlugin.equalsIgnoreCase("factions")){
			
			//Factions name = new Factions();
			
		}
		
		
		if(triggered){return false;}
		else{return true;}
	}
	
	public boolean canBuildScan(Player realPlayer,Location loc, int radius){
		boolean trigger = false;
		int playerX = loc.getBlockX();
		int playerY = loc.getBlockY();
		int playerZ = loc.getBlockZ();
		int playerXNegative = playerX - radius;
		int playerYNegative = playerY - radius;
		int playerZNegative = playerZ - radius;
		
		for (int scanX = playerXNegative; scanX <= (playerX + radius); scanX++) {
			for (int scanY = playerYNegative; scanY <= (playerY + radius); scanY++) {
				for (int scanZ = playerZNegative; scanZ <= (playerZ + radius); scanZ++) {
						
					Location scanLoc = new Location(realPlayer.getWorld(), scanX, scanY, scanZ);
					if(!canBuild(realPlayer,scanLoc)){trigger=true;break;}
					
				}
			}
		}
		if(trigger){return false;}else{return true;}
	}
	
	public void addCancelledProjectile(String packName, int itemID, String itemName, int scanRadius){
		if(modPack.equalsIgnoreCase("ftb_ultimate") && itemID == itemUsed){
			if(!canBuildScan(mcPlayer,projectile.getLocation(),scanRadius)){violation=true;}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event){
		violation=false;
		projectile = event.getEntity();
		itemUsed = projectile.getType().getTypeId();
		if(event.getEntity().getShooter() instanceof Player){
			mcPlayer = (Player)event.getEntity().getShooter();
			addCancelledProjectile("ftb_ultimate",30215,"Dynamite",4);
			
			if(violation){projectile.remove();}
		}
	}
}
