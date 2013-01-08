package fr.jules_cesar.TheBox;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TheBox extends JavaPlugin implements Listener{

	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable(){

	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e){
		if(e.getInventory().getHolder() instanceof Chest){
            		Chest coffre = (Chest) e.getInventory().getHolder();
            		Location coffre_loc = coffre.getLocation();
            		if(coffre_loc.add(0, 1, 0).getBlock().getType() == Material.WALL_SIGN){
            			Sign panneau = (Sign) coffre_loc.getBlock().getState();
						String texte = panneau.getLine(0);
    					if(texte.equalsIgnoreCase("[letterbox]")) new L
    						texte = panneau.getLine(1);
    						if(!texte.equals(e.getPlayer().getName())){
    						joueurs.add(e.getPlayer().getName());
    				}
    			}
            }
        }
	}
}
