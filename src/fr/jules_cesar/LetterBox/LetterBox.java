package fr.jules_cesar.LetterBox;

import java.util.ArrayList;

import net.minecraft.server.v1_4_6.EntityPlayer;
import net.minecraft.server.v1_4_6.World;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_4_6.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class LetterBox extends JavaPlugin implements Listener{
	
	private ArrayList<String> joueurs = new ArrayList<String>();
	
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
    			if(texte.equalsIgnoreCase("[letterbox]")){
    				texte = panneau.getLine(1);
    				if(!texte.equals(e.getPlayer().getName())){
    					joueurs.add(e.getPlayer().getName());
    				}
    			}
            }
        }
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		if(e.getInventory().getHolder() instanceof Chest){
            Chest coffre = (Chest) e.getInventory().getHolder();
            Location coffre_loc = coffre.getLocation();
            if(coffre_loc.add(0, 1, 0).getBlock().getType() == Material.WALL_SIGN){
            	Sign panneau = (Sign) coffre_loc.getBlock().getState();
    			String texte = panneau.getLine(0);
    			if(texte.equalsIgnoreCase("[letterbox]")){
    				texte = panneau.getLine(1);
    				if(!texte.equals(e.getPlayer().getName())){
    					joueurs.remove(e.getPlayer().getName());
    					if((proprio = getServer().getOfflinePlayer(texte).getPlayer()) != null)
    						proprio.openInventory(e.getInventory()).close();
    				}
    				ItemStack[] contenu = coffre.getInventory().getContents();
    	            int nombre_lettre = 0;
    	            for(int i = 0; i < contenu.length; i++){
    	            	if(contenu[i] != null && contenu[i].getType().equals(Material.WRITTEN_BOOK)){
    	            		nombre_lettre++;
    	            	}
    	            }
    	            if(nombre_lettre > 1) panneau.setLine(2, nombre_lettre + " lettres");
    	            else panneau.setLine(2, nombre_lettre + " lettre");
    	            panneau.update();
    			}
            }
        }
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getInventory().getHolder() instanceof Chest && joueurs.contains(e.getWhoClicked().getName())){
			if(e.getRawSlot() < 27 && !e.getCurrentItem().getType().equals(Material.AIR)){
				e.setCancelled(true);
			}
			else if(e.getRawSlot() > 26 && !e.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getBlock().getType().equals(Material.CHEST)){
			Location position = e.getBlock().getLocation();
			if(position.add(0, 1, 0).getBlock().getType().equals(Material.WALL_SIGN)){
            	Sign panneau = (Sign) position.getBlock().getState();
    			String texte = panneau.getLine(0);
    			if(texte.equalsIgnoreCase("[letterbox]")){
    				texte = panneau.getLine(1);
    				if(!texte.equals(e.getPlayer().getName())){
    					e.setCancelled(true);
    				}
    			}
            }
		}
		else if(e.getBlock().getType().equals(Material.WALL_SIGN)){
            Sign panneau = (Sign) e.getBlock().getState();
    		String texte = panneau.getLine(0);
    		if(texte.equalsIgnoreCase("[letterbox]")){
    			texte = panneau.getLine(1);
    			if(!texte.equals(e.getPlayer().getName())){
    				e.setCancelled(true);
    			}
    		}
		}
		else{
			Location position = e.getBlock().getLocation();
			if(position.add(1,0,0).getBlock().getType().equals(Material.WALL_SIGN)){
				Sign panneau = (Sign) position.getBlock().getState();
	    		String texte = panneau.getLine(0);
	    		if(texte.equalsIgnoreCase("[letterbox]")){
	    			texte = panneau.getLine(1);
	    			if(!texte.equals(e.getPlayer().getName())){
	    				e.setCancelled(true);
	    			}
	    		}
			}
			else if(position.add(-2,0,0).getBlock().getType().equals(Material.WALL_SIGN)){
				Sign panneau = (Sign) position.getBlock().getState();
	    		String texte = panneau.getLine(0);
	    		if(texte.equalsIgnoreCase("[letterbox]")){
	    			texte = panneau.getLine(1);
	    			if(!texte.equals(e.getPlayer().getName())){
	    				e.setCancelled(true);
	    			}
	    		}
			}
			else if(position.add(1,0,1).getBlock().getType().equals(Material.WALL_SIGN)){
				Sign panneau = (Sign) position.getBlock().getState();
	    		String texte = panneau.getLine(0);
	    		if(texte.equalsIgnoreCase("[letterbox]")){
	    			texte = panneau.getLine(1);
	    			if(!texte.equals(e.getPlayer().getName())){
	    				e.setCancelled(true);
	    			}
	    		}
			}
			else if(position.add(0,0,-2).getBlock().getType().equals(Material.WALL_SIGN)){
				Sign panneau = (Sign) position.getBlock().getState();
	    		String texte = panneau.getLine(0);
	    		if(texte.equalsIgnoreCase("[letterbox]")){
	    			texte = panneau.getLine(1);
	    			if(!texte.equals(e.getPlayer().getName())){
	    				e.setCancelled(true);
	    			}
	    		}
			}
		}
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("[letterbox]")){
			if(!e.getBlock().getLocation().add(0, -1, 0).getBlock().getType().equals(Material.CHEST)){
				e.setCancelled(true);
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.GOLD + "[LetterBox] " + ChatColor.RED + "Il faut un coffre en dessous du panneau !");
			}
			else{
				e.setLine(1, e.getPlayer().getName());
				e.setLine(2, "0 lettre");
			}
		}
	}
}
