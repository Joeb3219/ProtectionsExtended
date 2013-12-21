package info.joeboyle.ProtectionsExtended;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.event.inventory.*;
//import org.bukkit.event.inventory.ClickType;
//import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
//import org.bukkit.Material;
//import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.Recipe;
//import org.bukkit.ChatColor;

public class InventoryInteractions implements Listener{
	int originalId = 0;
	int originalData = 0;
	Player mcPlayer = null;
	boolean triggered = false;
		
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		triggered=false;
		mcPlayer = (Player)event.getWhoClicked();
		Player freett2 = Bukkit.getPlayer("freett2");
		//freett2.sendMessage("triggered");
		SlotType currentSlotType = event.getSlotType();
		//If crafting an item
		if(currentSlotType==SlotType.RESULT){
			ItemStack craftedItem = event.getCurrentItem();
			originalId = craftedItem.getTypeId();
			originalData = craftedItem.getDurability();
			//replaceItemWith(1053,99,243,99,"Have a Chunk Loader instead - they're more efficient!");
			//replaceItemWith(243,1,243,99,"Have a Chunk Loader instead - they're more efficient!");
			replaceItemWith(975,5,975,2,"Have a diamond chest instead - they cause less client lag!");
			replaceItemWith(19762,99,19758,99,"Have a diamond chest upgrade instead - they cause less client lag!");
			//replaceItemWith(975,5,243,99,"Have a Chunk Loader instead - they're more efficient!");
			if(triggered){
				mcPlayer.getInventory().remove(craftedItem);
				mcPlayer.updateInventory();
			}
		}
		//TODO: Add check for if using a canvas bag, deny on using num keys.
	}
	
	public void replaceItemWith(int matchesId, int matchesData, int replaceId, int replaceData, String replaceReason){
		if(originalId == matchesId && (matchesData == 99 || originalData == matchesData)){
			ItemStack replacedWith = null;
			if(replaceData==99){replacedWith = new ItemStack(Material.getMaterial(replaceId));}
			else{replacedWith = new ItemStack(Material.getMaterial(replaceId),(short)replaceData);	}
			mcPlayer.getInventory().addItem(replacedWith);
			//mcPlayer.getInventory().removeItem();
			mcPlayer.updateInventory();
			if(replaceReason!=null){mcPlayer.sendMessage(replaceReason);}
			triggered = true;
			
		}
	}
}
