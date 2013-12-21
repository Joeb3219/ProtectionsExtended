package info.joeboyle.ProtectionsExtended;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.block.Block;
//import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class BlockSpreadListener implements Listener{
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent event){
		Player freett2 = Bukkit.getPlayer("freett2");
		Block initBlock = event.getSource();
		Block newBlock = event.getBlock();
		
		freett2.sendMessage("BSE: " + initBlock + "::" + newBlock + "::" + event.getNewState());
	}
}
