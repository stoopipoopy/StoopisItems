package yes.stoopipoopy.stoopisitems;


import it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GunManager implements Listener {
    public UUID playerID;
    private Integer totalAmmo;
    private Integer currentAmmo;
    public String itemID; /** Based off of lore elements **/
    public final HashMap<String, Integer> gunAmmoPair = new HashMap<>();

    public static final String PISTOLWHIPID = "Extremely Strong Frame!";
    public GunManager(UUID playerID, Integer totalAmmo, Integer currentAmmo, String itemID){
        this.playerID = playerID;
        this.totalAmmo = totalAmmo;
        this.currentAmmo = currentAmmo;
        this.itemID = itemID;
        this.gunAmmoPair.put(PISTOLWHIPID,6);
    }
    public Integer getTotalAmmo(UUID p){
        if (!ShotManager.playerAndGun.containsKey(p)) {
            System.out.println("total ammo get should have put the thing in the thing");
            ShotManager.playerAndGun.put(p, this);
        }
        return totalAmmo;

    }
    public void setTotalAmmo(Integer input){
        totalAmmo = input;
    }
    public Integer getCurrentAmmo(){
        return currentAmmo;
    }
    public void setCurrentAmmo(Integer input){
        currentAmmo = input;
    }

}
