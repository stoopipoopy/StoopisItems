package yes.stoopipoopy.stoopisitems;

import it.unimi.dsi.fastutil.Hash;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.HashMap;
import java.util.UUID;


public class ShotManager extends Thread implements Listener {
    public static HashMap<UUID, GunManager> playerAndGun = new HashMap<>();

    @EventHandler
    public void GENERIC_SHOT(final PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(!(e.getItem().getItemMeta().getLore() == null)){
                if(e.getItem().getItemMeta().getLore().contains(GunManager.PISTOLWHIPID)){
                    Player p = e.getPlayer();
                    UUID userID = p.getUniqueId();
                    if(!(playerAndGun.containsKey(userID))){
                        p.sendMessage("johnathan kimball");
                        playerAndGun.put(userID,new GunManager(userID, 6, 6 , "g"));
                    }


                    // change later; for test


                    playerAndGun.put(userID,new GunManager(userID, playerAndGun.get(userID).getTotalAmmo(p.getUniqueId()), playerAndGun.get(userID).getCurrentAmmo() , "g"));
                    if(playerAndGun.get(userID).getCurrentAmmo() < 1){
                        p.sendMessage("No ammo haha haha haha hahah ahaha ok ill stop");
                    }
                    else{
                        playerAndGun.get(userID).setCurrentAmmo(playerAndGun.get(userID).getCurrentAmmo() - 1);

                        String toPrint = showAmmo(userID);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(toPrint));
                        p.sendMessage("you shot gun :)");
                        p.sendMessage(Integer.toString(playerAndGun.get(userID).getCurrentAmmo()));
                        p.sendMessage(Integer.toString(playerAndGun.get(userID).getTotalAmmo(p.getUniqueId())));
                        // (X / X / X / X / 0 / 0) 4/6 ammo
                    }
                }


            }

        }


    }

    public static String showAmmo(UUID userID){
        if(!playerAndGun.containsKey(userID)){
            playerAndGun.put(userID,new GunManager(userID, playerAndGun.get(userID).getTotalAmmo(userID), playerAndGun.get(userID).getCurrentAmmo() , "g"));
        }
        StringBuilder ammoShower = new StringBuilder();
        ammoShower.append("(");
        for(int i = 0; i < playerAndGun.get(userID).getTotalAmmo(userID); i++){
            if(i > playerAndGun.get(userID).getCurrentAmmo() - 1){
                ammoShower.append("0");
            }
            else{
                ammoShower.append("X");
            }
            if(i != playerAndGun.get(userID).getTotalAmmo(userID) - 1){
                ammoShower.append(" / ");
            }


        }
        ammoShower.append(")");
        return ammoShower.toString();
    }
    @EventHandler
    public void onHandChange(PlayerItemHeldEvent event) {

        Player p = event.getPlayer();
        String toPrint = showAmmo(p.getUniqueId());

        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(toPrint));
    }
}
