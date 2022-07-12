package yes.stoopipoopy.stoopisitems;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("poop");
        getServer().getPluginManager().registerEvents(this, this);
    }

    long timeLeft = 0;
    long curTime = 0;
    private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
    private int cooldownTime = 60;

    @EventHandler
    public void ABILITY_TEST(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack heldItem = e.getItem();
        ItemMeta itemMeta = heldItem.getItemMeta();
        if (itemMeta.hasLore()) {
            p.sendMessage("has lore");
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && itemMeta.lore().equals("Those moronic developers thought they could hide this from me?")) {
                p.sendMessage("contains the lore ;)");

                if (cooldown.containsKey(p.getUniqueId())) {
                    long secondsLeft = ((cooldown.get(p.getUniqueId()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);

                    if (secondsLeft > 0) {
                        /** COOLDOWN -- DO NOT TOUCH **/
                        p.sendMessage("seconds left: " + String.valueOf(secondsLeft));

                    } else {
                        cooldown.remove(p.getUniqueId());
                    }


                } else {

                    /** PUT FUNCTIONALITY HERE **/
                    /**
                     * so theres gonna be like a
                     * check if the item meta contains a certain piece of lore (those quote things in dark gray) do later though im tired
                     *
                     */
                    p.sendMessage("do thing");
                    cooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
            }
        }

    }
}




