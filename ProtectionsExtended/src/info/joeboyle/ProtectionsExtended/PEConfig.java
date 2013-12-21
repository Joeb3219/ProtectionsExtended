package info.joeboyle.ProtectionsExtended;

public class PEConfig {
	private static PEConfig _instance = null;
	private org.bukkit.configuration.file.FileConfiguration config = null;
	
	//The vars being used
	public boolean debug = false;
	public int debugLevel = 0;
	public boolean enableWorldGuard = false;
	public String modPack = null;
	public String protectionsPlugin = null;
	
	
	public static PEConfig createInstance(org.bukkit.configuration.file.FileConfiguration config){
		if (_instance != null) return _instance;
		_instance = new PEConfig(config);
		return _instance;
	}
	
	public static PEConfig getInstance(){
		return _instance;
	}
	
	private PEConfig(org.bukkit.configuration.file.FileConfiguration config) {
		this.config = config;
		loadConfig();
	}
	
	private void loadConfig(){
		
		debug=config.getBoolean("enableDebug");
		debugLevel=config.getInt("debugLevel");
		enableWorldGuard=config.getBoolean("enableWorldGuard");
		modPack=config.getString("modPack");
		protectionsPlugin=config.getString("protectionsPlugin");
		
	}
}
