package info.joeboyle.ProtectionsExtended;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import info.joeboyle.ProtectionsExtended.PEConfig;
import info.joeboyle.ProtectionsExtended.RightClickListener;
import info.joeboyle.ProtectionsExtended.InventoryInteractions;
import info.joeboyle.ProtectionsExtended.BlockSpreadListener;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.Plugin;

public class ProtectionsExtended extends JavaPlugin{
	public PEConfig options;
	String Version = "1.3.0";
	public RightClickListener rightCon;
	public ItemReplacements _scan;
	public org.bukkit.Server _server = null;
	
	@Override
	public void onLoad(){
		saveDefaultConfig();
		PEConfig.createInstance(getConfig());
		_server = this.getServer();
		_scan = new ItemReplacements();
		super.onLoad();
	}
	
	@Override
	public void onEnable(){
		this.startReplacementScan();
		//this.removeRecipes();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new RightClickListener(),this);
		pm.registerEvents(new InventoryInteractions(),this);
		//pm.registerEvents(new ProjectileListeners(), this);
		//pm.registerEvents(new BlockSpreadListener(),this);
		//pm.registerEvents(new ProjectileHitListener(),this);
		pm.getPlugin("Towny");
		//pm.getPlugin("Factions");
		getWorldGuard();
		
		//super.onEnable();
	}
	
	@Override
	public void onDisable(){
		
		System.out.println("ProtectionsExtended Disabled");
		
	}
	
	private WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
	
	private void startReplacementScan(){
		if(ItemReplacements.scanStarted){return;}
		_server.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run() {
				_scan.scan(_server);
			}
		}, 100L, 150);
	
	ItemReplacements.scanStarted = true;
	}
	
	private Map<String,ItemStack> bannedItems = null;
	private List<Recipe> newRecipes = null;
	
	
	private void setRecipes(){
		if(bannedItems==null){
			bannedItems = new HashMap<String,ItemStack>();
			bannedItems.put("1053:-1", new ItemStack(Material.getMaterial(243),1));
			bannedItems.put("243:1", new ItemStack(Material.getMaterial(243),1));
			bannedItems.put("975:5", new ItemStack(Material.getMaterial(975),1,(short)2));
			bannedItems.put("19762:-1", new ItemStack(Material.getMaterial(19758),1));
			bannedItems.put("13361:-1", new ItemStack(Material.getMaterial(13393),1));
			bannedItems.put("13362:-1", new ItemStack(Material.getMaterial(13393),1));
		}
	}
	
	private void removeRecipes(){
		setRecipes();
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		while(recipes.hasNext()){
			try{
				Recipe item = recipes.next();
				if(item.getResult().getData()!=null){
					int itemId = item.getResult().getTypeId();
					int itemData = item.getResult().getData().getData();
					boolean noData = false;
					String bannedItemsKey = "";
					if(bannedItems.containsKey(itemId + ":-1")){bannedItemsKey = itemId + ":-1";}
					else if(bannedItems.containsKey(itemId + ":" + itemData)){bannedItemsKey = itemId + ":" + itemData;}
					if(bannedItems.containsKey(itemId + ":-1")||bannedItems.containsKey(itemId + ":" + itemData)){
						
						if(item instanceof ShapedRecipe){
							String oldShape[] = ((ShapedRecipe) item).getShape();
							Map<Character,ItemStack> oldGrid = ((ShapedRecipe) item).getIngredientMap();
							ShapedRecipe safeItem = new ShapedRecipe(bannedItems.get(bannedItemsKey));
							safeItem.shape(oldShape);
							for(Map.Entry<Character,ItemStack> entry : oldGrid.entrySet()){
								safeItem.setIngredient((char)entry.getKey(), entry.getValue().getData());
							}
							newRecipes.add(safeItem);
						}else{
							List<ItemStack> oldItems = ((ShapelessRecipe) item).getIngredientList();
							ShapelessRecipe safeItem = new ShapelessRecipe(bannedItems.get(bannedItemsKey));
							for(ItemStack entry : oldItems){
								safeItem.addIngredient(entry.getData());
							}
							newRecipes.add(safeItem);
						}
						recipes.remove();
					}
					
				}
			}catch(Exception e){e.printStackTrace();}
		}
		for(Recipe recipe : newRecipes){
			System.out.println("Recipe added!");
			Bukkit.getServer().addRecipe(recipe);
		}
		
	}
}
