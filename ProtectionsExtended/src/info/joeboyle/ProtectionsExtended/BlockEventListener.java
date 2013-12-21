package info.joeboyle.ProtectionsExtended;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.entity.Player;
//import org.bukkit.event.block.Action;
//import org.bukkit.block.Block;
import org.bukkit.Bukkit;
//import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;

//import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
//import com.palmergames.bukkit.towny.object.TownyUniverse;
//import com.palmergames.bukkit.towny.object.Resident;
//import org.bukkit.Location;

public class BlockEventListener implements Listener{
	Player freett2 = Bukkit.getPlayer("freett2");
	@EventHandler
	public void onBlockEvent(EntityEvent event){
		freett2.sendMessage("Block " + event.getEntity() + " Interacted (" + event.getEventName() +".");
		
	}
	
	@EventHandler
	public void onEntityBreakBlock(EntityChangeBlockEvent event){
		freett2.sendMessage("Block " + event.getEntity() + " interacted w/ " + event.getBlock() + ".");
		
	}
	
}
