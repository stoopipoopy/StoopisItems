package yes.stoopipoopy.stoopisitems;

import it.unimi.dsi.fastutil.Hash;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;


public class ShotManager extends Thread implements Listener {
    public static Integer maxAmmo = 6;
    public static Integer ammoLeft = maxAmmo;

    public static HashMap<UUID,HashMap<String, HashMap<Integer,Integer>>> gunsAndAmmo = new HashMap<>();
    @EventHandler
    public void GENERIC_SHOT(final PlayerInteractEvent e){
        Player p = e.getPlayer();

        // change later; for test

        ammoLeft -= 1;
        if(ammoLeft < 1){
            p.sendMessage("No ammo haha haha haha hahah ahaha ok ill stop");
        }
        else{
            String toPrint = showAmmo();
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(toPrint));
            p.sendMessage("you shot gun :)");
            // (X / X / X / X / 0 / 0) 4/6 ammo
        }

    }
    public String showAmmo(){
        StringBuilder ammoShower = new StringBuilder();
        ammoShower.append("(");
        for(int i = 0; i < maxAmmo; i++){
            if(i > ammoLeft){
                ammoShower.append("0");
            }
            else{
                ammoShower.append("X");
            }
            if(i != maxAmmo - 1){
                ammoShower.append(" / ");
            }


        }
        ammoShower.append(")");
        return ammoShower.toString();
    }
}
