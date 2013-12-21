package info.joeboyle.ProtectionsExtended;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
//import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
//import org.bukkit.World;
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.Item;
//import java.util.List;
import org.bukkit.entity.Player;
//import org.bukkit.Location;


public class BlockBreakListener implements Listener{
	//private PEConfig options = PEConfig.getInstance();
	//boolean enableArcaneFix = options.enableArcaneFix;
	//boolean enableArcaneFix = true;
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		
		if(event.getBlock().getType() == Material.getMaterial(702) && event.getBlock().getState().getData().getData() == 40){
			Player mcPlayer = event.getPlayer();
			event.setCancelled(true);
			mcPlayer.sendMessage("You will be able to break these after tonight :)");
		}
	/*	
		if(enableArcaneFix){
    	if(event.getBlock().getType() == Material.getMaterial(1614)) {
    		event.setCancelled(true);
    		event.getBlock().getDrops().clear();
    		event.getBlock().setType(Material.AIR);
    		//Remove all drops within a radius of 3.
    		
    		int actualX=event.getBlock().getX();
    		int actualY=event.getBlock().getY();
    		int actualZ=event.getBlock().getZ();
    		int highX=actualX+3;
    		int highY=actualY+3;
    		int highZ=actualZ+3;
    		int lowX=actualX-3;
    		int lowY=actualY-3;
    		int lowZ=actualZ-3;

    	     World world = event.getBlock().getWorld();
    	     List<Entity> entList = world.getEntities();
    	      
    	     for(Entity current : entList){
    	    	double currentX=current.getLocation().getX();
    	    	double currentY=current.getLocation().getY();
    	    	double currentZ=current.getLocation().getZ();
    	    	if (current instanceof Item && ( (currentX > lowX || currentX < highX) && (currentY > lowY || currentY < highY) && (currentZ > lowZ || currentZ < highZ) ) ){//make sure we aren't deleting mobs/players
    	    	 	current.remove();
    	     	}
    	    }
    		//Now give them the actual block.
    		event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.getMaterial(1614),1));
    	}
		}*/
    }
}
