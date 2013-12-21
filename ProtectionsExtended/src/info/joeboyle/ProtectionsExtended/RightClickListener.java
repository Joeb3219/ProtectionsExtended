package info.joeboyle.ProtectionsExtended;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.block.Block;
import org.bukkit.Bukkit;
//import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;

import com.sk89q.worldguard.bukkit.WGBukkit;

//import com.massivecraft.factions.*;
//import com.massivecraft.factions.Board;
//import com.massivecraft.factions.FLocation;
//import com.massivecraft.factions.Faction;
//import com.massivecraft.factions.Factions;

public class RightClickListener implements Listener{
	private PEConfig options = PEConfig.getInstance();
	boolean debug = options.debug;
	int debugLevel = options.debugLevel;
	boolean enableWorldGuard = options.enableWorldGuard;
	String modPack = options.modPack;
	String protectionsPlugin = options.protectionsPlugin;
	Material itemInHand = null;
	Player mcPlayer = null;
	Location playerPos, blockLoc = null;
	boolean blockClicked, rightClicked, leftClicked = false;
	Block targetBlock, clickedBlock = null;
	boolean violation, badTouch = false;
	int checkID = 0;
	boolean WorldGuardTriggered, FactionsTriggered, TownyTriggered  = false;
	String townName = null;
	Plugin ProtectionsExtended = Bukkit.getPluginManager().getPlugin("ProtectionsExtended");
	
	@EventHandler
	public void PlayerClicked(PlayerInteractEvent event){
		blockClicked = false;
		rightClicked = false;
		leftClicked = false;
		violation = false;
		mcPlayer = event.getPlayer();
		playerPos = mcPlayer.getLocation();
		itemInHand = event.getPlayer().getItemInHand().getType();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK){
			blockClicked = true;
			clickedBlock = event.getClickedBlock();
			//Check if the player is holding a wrench
		}
		
		if( event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR ){
			rightClicked = true;
			targetBlock = mcPlayer.getTargetBlock(null, 150);
		}
		
		//Add ftb_ultimate items"
		if(modPack.equalsIgnoreCase("ftb_ultimate")){
			addCancelledItem("ftb_ultimate",30183,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",30140,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",13436,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",4470,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",4062,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",6257,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",30477,99,"Vajra",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",9263,99,"Wrath Igniter",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_ultimate",13457,99,"Portal Gun",true,2,true,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",13458,99,"Portal Gun",true,2,true,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25263,0,"Alumentum",true,8,true,false,true,false,false,0);
			//addCancelledItem(String packName, int itemID, int itemData, String itemName, boolean scanNeeded, int scanRadius, boolean lineOfSight, boolean clickBlockCheck, boolean rightClickCheck, boolean leftClickCheck);
			addCancelledItem("ftb_ultimate",27261,99,"Crumble Horn",true,6,false,false,true,false,true,3);
			addCancelledItem("ftb_ultimate",4464,99,"Vibration Catalyst",true,2,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",27547,99,"Destruction Catalyst",true,4,false,false,true,false,false,0);
			//addCancelledItem("ftb_ultimate",25030,99,"PowerTool",true,4,true,false,true,false,true,3);
			addCancelledItem("ftb_ultimate",25283,99,"Wand of Equal Trade",true,3,true,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25284,99,"Wand of Excavation",true,3,true,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",30208,99,"Mining Laser",true,8,true,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",30131,99,"CF Sprayer",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",9261,99,"Wand of Cooling",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25256,99,"Wand of Apprentice",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25257,99,"Wand of Adept",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25258,99,"Wand of Thaumaturge",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25259,99,"Wand of Fire",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25260,99,"Wand of Lightning",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25283,99,"Wand of Equal Trade",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25284,99,"Wand of Excavation",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",25294,99,"Wand of Frost",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_ultimate",27526,99,"Hunter's Handgun",true,7,true,false,true,false,false,0);
		}
		//Add items to ftb_unleashed:
		if(modPack.equalsIgnoreCase("ftb_unleashed")){
			addCancelledItem("ftb_unleashed",4370,99,"Quartz Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",5263,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",6294,99,"Force Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",11363,99,"Multi Tool",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",19362,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",30140,99,"Electrical Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",19362,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",30183,99,"Wrench",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",25268,99,"Portable Hole",true,3,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",30477,99,"Vajra",false,0,false,true,false,false,false,0);
			addCancelledItem("ftb_unleashed",13457,99,"Portal Gun",true,2,true,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",13458,99,"Portal Gun",true,2,true,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25263,0,"Alumentum",true,8,true,false,true,false,false,0);
			//addCancelledItem(String packName, int itemID, int itemData, String itemName, boolean scanNeeded, int scanRadius, boolean lineOfSight, boolean clickBlockCheck, boolean rightClickCheck, boolean leftClickCheck);
			addCancelledItem("ftb_unleashed",28019,99,"Crumble Horn",true,6,false,false,true,false,true,3);
			addCancelledItem("ftb_unleashed",4364,99,"Vibration Catalyst",true,2,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",27547,99,"Destruction Catalyst",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25031,99,"PowerTool",true,4,true,false,true,false,true,3);
			addCancelledItem("ftb_unleashed",30208,99,"Mining Laser",true,8,true,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",30131,99,"CF Sprayer",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",9261,99,"Wand of Cooling",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25256,99,"Wand of Apprentice",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25257,99,"Wand of Adept",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25258,99,"Wand of Thaumaturge",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25259,99,"Wand of Fire",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25260,99,"Wand of Lightning",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25283,99,"Wand of Equal Trade",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25284,99,"Wand of Excavation",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",25294,99,"Wand of Frost",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",10259,99,"Builder's Wand",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",10260,99,"Creative Builder's Wand",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",21406,99,"Wand of the Tinkerer",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",21410,99,"Wand of Dislocation",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",21416,99,"Wand of Uprising",true,4,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",27526,99,"Hunter's Handgun",true,7,true,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",4363,99,"Entropy Accelerator",true,2,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",4372,99,"Matter Cannon",true,7,true,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",27002,99,"Minium Stone",false,0,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",21413,99,"Infernal Bracelet",false,0,false,false,true,false,false,0);
			addCancelledItem("ftb_unleashed",21413,99,"Infernal Bracelet",false,0,false,false,true,false,false,0);
		}
		
		
		
		//Violation ticker
		if(violation || ( blockClicked && denyTouch(clickedBlock) && !canBuild(mcPlayer,clickedBlock.getLocation()) ) ){
			event.setCancelled(true);
			if(debug && debugLevel>0){
				String debugLine = "(PE): Player " + mcPlayer.getName() + " attempted to use " + itemInHand.getId();
				if(TownyTriggered && townName != null){
					debugLine = debugLine + " in town " + townName;
				}
				else if(WorldGuardTriggered){debugLine = debugLine + " in region";}
				if(blockLoc != null){debugLine = debugLine + " (Location: " + blockLoc.getX() + ", " + blockLoc.getY() + ", " + blockLoc.getZ() + ")";}
				else if(playerPos!=null){debugLine = debugLine + " (Location: " + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";}
				Bukkit.getLogger().info(debugLine);
			}
		
		}
	}
	
	public boolean canBuild(Player realPlayer, Location loc){
		boolean triggered = false;
		if(enableWorldGuard){
			if(!realPlayer.isOp() && !WGBukkit.getPlugin().canBuild(realPlayer, loc)){WorldGuardTriggered=true;triggered=true;}
		}
		if(protectionsPlugin.equalsIgnoreCase("towny")){
			String blockTown = TownyUniverse.getTownName(loc);
			townName = blockTown;
			Resident player;
			String playerTown=null;
				try {
					player = TownyUniverse.getDataSource().getResident(realPlayer.getName());
				 playerTown = player.getTown().getName();
				} catch (NotRegisteredException e) {}
				//Compare towns, do nothing if null town/OP user.
				if(blockTown != playerTown && blockTown != null && !mcPlayer.isOp()){TownyTriggered=true;triggered=true;}
		}
		/*if(protectionsPlugin.equalsIgnoreCase("factions")){
				FLocation blockFactionLocation = new FLocation(loc);
				Faction blockFaction = Board.getFactionAt(blockFactionLocation);
				FPlayer mcFPlayer = FPlayers.i.get(mcPlayer);
				Faction playerFaction = mcFPlayer.getFaction();
				Player freett2 = Bukkit.getPlayer("freett2");
				freett2.sendMessage(blockFaction + "::" + playerFaction);
			
			if(blockFaction != playerFaction && blockFaction!=null && !mcPlayer.isOp()){FactionsTriggered=true;triggered=true;}
		}*/
		
		
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
	
	public void slotChange(int seconds){
			Bukkit.getServer().getScheduler().runTaskLater(ProtectionsExtended, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					if(mcPlayer.getItemInHand().getTypeId()==checkID){
						int currentSlot = mcPlayer.getInventory().getHeldItemSlot();
							ItemStack currentItem = mcPlayer.getInventory().getItem(currentSlot);
							mcPlayer.getInventory().removeItem(currentItem);
							mcPlayer.updateInventory();
							mcPlayer.getInventory().setItem(currentSlot,currentItem);
							mcPlayer.updateInventory();
					}
				}
			}, ((long)seconds * 20));
	}
	
	public boolean denyTouch(Block realBlock){
		if(modPack.equalsIgnoreCase("ftb_ultimate")){
			int blockID = realBlock.getTypeId();
			int blockData = realBlock.getState().getData().getData();
			//Check if ME, deny.
			if( blockID == 2510 || blockID == 2511 ){return true;}
			//Check if barrel, deny.
			else if( blockID == 1000 && blockData == 5 ){return true;}
			//Check if reappearing block, allow
			else if( blockID == 1522 ){return false;}
			//Check if chest, allow.
			else if( blockID == 54){return false;}
			//Check if advanced monitor, allow.
			else if( blockID == 1226 && blockData == 12 ){return false;}
			//Check if Trade-O-Mat, allow.
			else if( blockID == 623 && blockData == 1 ){return false;}
			//Check if sign, allow
			else if( blockID == 323){return false;}
			//Check if button, Enchantment Table, allow.
			else if ( blockID == 77 || blockID == 116 ){return false;}
			
			else{return true;}
		}
		else if(modPack.equalsIgnoreCase("ftb_unleashed")){
			int blockID = realBlock.getTypeId();
			int blockData = realBlock.getState().getData().getData();
			//Check if ME, deny.
			if( blockID == 901 || blockID == 900 ){return true;}
			//Check if barrel, deny.
			else if( blockID == 1000 && blockData == 5 ){return true;}
			//Check if reappearing block, allow
			else if( blockID == 2181 ){return false;}
			//Check if chest, allow.
			else if( blockID == 54){return false;}
			//Check if advanced monitor, allow.
			else if( blockID == 1226 && blockData == 12 ){return false;}
			//Check if Trade-O-Mat, allow.
			else if( blockID == 225 && blockData == 1 ){return false;}
			//Check if sign, allow
			else if( blockID == 323){return false;}
			//Check if button, Enchantment Table, allow.
			else if ( blockID == 77 || blockID == 116 ){return false;}
			
			else{return true;}
		}
		else{return true;}
	}
	/*
	 * AddCancelledItem arguments in order:
	 * ModPack name, item ID, item Data, item Name
	 * Enable area scan (player)? radius?
	 * Enable line of sight scan? (Will scan target block w/ radius of area scan, disables area scan).
	 * Check on trigger of RIGHT_CLICK_BLOCK || LEFT_CLICK_BLOCK ?
	 * Check on trigger of RIGHT_CLICK_BLOCK || RIGHT_CLICK_AIR ?
	 * Check on trigger of LEFT_CLICK_BLOCK || LEFT_CLICK_AIR ?
	 * Disable the event after x seconds? Seconds?
	 */
	public void addCancelledItem(String packName, int itemID, int itemData, String itemName, boolean scanNeeded, int scanRadius, boolean lineOfSight, boolean clickBlockCheck, boolean rightClickCheck, boolean leftClickCheck, boolean changeSlot,int seconds){
		ItemStack rawMat = new ItemStack(itemInHand);
		int realItemData = rawMat.getDurability();
		int realID = mcPlayer.getItemInHand().getTypeId();
		if(/*modPack.equalsIgnoreCase(packName) &&*/ realID == itemID && (itemData == 99 || itemData == realItemData)){
			if(clickBlockCheck && blockClicked){
				blockLoc = clickedBlock.getLocation();
				if(!canBuild(mcPlayer,clickedBlock.getLocation())){violation=true;}
			}
			if(rightClickCheck && rightClicked){
				if(lineOfSight){
					if(targetBlock.getType()!=Material.AIR){
						blockLoc = targetBlock.getLocation();
						if(!canBuildScan(mcPlayer, targetBlock.getLocation(), scanRadius)){violation=true;}
					}
					else{violation=true;}
				}
				else if(scanNeeded){
					if(!canBuildScan(mcPlayer, playerPos, scanRadius)){violation=true;}
				}
				else{
					if(!canBuild(mcPlayer,playerPos)){violation = true;}
				}
				if(changeSlot){checkID = itemID;slotChange(seconds);}
			}
			if(leftClickCheck && leftClicked){
				
			}
		}
	}
	
}