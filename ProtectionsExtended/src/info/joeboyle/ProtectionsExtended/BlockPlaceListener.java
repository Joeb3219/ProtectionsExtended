package info.joeboyle.ProtectionsExtended;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;

public class BlockPlaceListener implements Listener{
	private PEConfig options = PEConfig.getInstance();
	boolean debug = options.debug;
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		
		Block placedBlock = event.getBlock();
		if(placedBlock.getType() == Material.getMaterial(1614)){
			Player mcPlayer = event.getPlayer();
			mcPlayer.sendMessage("Warning: Breaking the Arcane Table removes items that it is holding.");
			if(debug){System.out.println("[ProtectionsExtended] " + mcPlayer + " placed Arcane Table");}
		}
	}
}
