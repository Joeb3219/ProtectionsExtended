package info.joeboyle.ProtectionsExtended;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Bukkit;

public class ItemReplacements {
	private PEConfig options = PEConfig.getInstance();
	boolean debug = options.debug;
	int debugLevel = options.debugLevel;
	String modPack = options.modPack;
	static public boolean scanStarted = false;
	private org.bukkit.Server _server = null;
	
	public void scan(org.bukkit.Server server){
		_server = server;
		int removalCount = 0;
		Player[] playerList = _server.getOnlinePlayers();
		for(Player mcPlayer : playerList){
			if(!mcPlayer.isOp()){
				PlayerInventory mcInv = mcPlayer.getInventory();
				java.util.ListIterator<ItemStack> iterator = mcInv.iterator();
				
				while(iterator.hasNext()){
					ItemStack scanItem = iterator.next();
					try{
						int itemId = scanItem.getTypeId();
						int itemData = scanItem.getData().getData();
						ItemStack baconGun = new ItemStack(Material.getMaterial(13457),1,(short)3);
						if( (itemId == 13457 && (itemData != 3 )) || (itemId == 13458 && (itemData != 3 )) || ((modPack.equalsIgnoreCase("ftb_ultimate") && itemId == 1396) || (modPack.equalsIgnoreCase("ftb_unleashed") && itemId == 13446)) ){
							if(((modPack.equalsIgnoreCase("ftb_ultimate") && itemId == 1396) || (modPack.equalsIgnoreCase("ftb_unleashed") && itemId == 13446)) && mcPlayer.hasPermission("pe.gravity.allow")){
								//Wow, you're allowed to have a gravity gun! Coolio!
							}else{
								mcInv.removeItem(scanItem);
								mcPlayer.updateInventory();
								mcInv.addItem(baconGun);
								mcPlayer.updateInventory();
								mcPlayer.sendMessage("You've been given a bacon gun instead!");
								removalCount++;
							}	
						}
					}catch(Exception e){}
				}
			}
		}
		if(debug&&debugLevel>1){Bukkit.getLogger().info("(PE): ItemReplacement scan ran; " + removalCount + " items replaced.");}
	}
}
