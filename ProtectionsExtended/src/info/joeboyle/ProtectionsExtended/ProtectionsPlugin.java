package info.joeboyle.ProtectionsExtended;

public class ProtectionsPlugin {
	private static ProtectionsPlugin _instance = null;
	private org.bukkit.plugin.PluginManager pm = null;
	//Public variables (accessed in main class)
	public static boolean pluginTownyFound = false;
	public static boolean pluginFactionsFound = false;
	public static boolean pluginWorldeditFound = false;
	
	public static ProtectionsPlugin createInstance(org.bukkit.plugin.PluginManager pm){
		if( _instance != null ) return _instance;
		_instance = new ProtectionsPlugin(pm);
		return _instance;
		
	}
	
	private ProtectionsPlugin(org.bukkit.plugin.PluginManager pm){
		this.pm = pm;
		checkProtections();
	}
	
	private void checkProtections(){
		if(pm.getPlugin("Towny")!=null){pluginTownyFound = true;}
		if(pm.getPlugin("Factions")!=null){pluginFactionsFound = true;}
		pullImports();
	}
	
	private void pullImports(){
		
	}
	
	public boolean factionsFound(){
		return pluginFactionsFound;
	}
	
	public boolean townyFound(){
		return pluginTownyFound;
	}
}