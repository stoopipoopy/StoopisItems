package yes.stoopipoopy.stoopisitems.items.abilitytest;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import yes.stoopipoopy.stoopisitems.CooldownManager;

import java.util.concurrent.TimeUnit;

public class ABILITYTEST implements Listener {
    private final CooldownManager cooldownManager = new CooldownManager();

    @EventHandler
    public void ABILITY_TEST(PlayerInteractEvent e) {
        //Player only command
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            System.out.println("testing 123 testing 123");
            long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(p.getUniqueId());
            if (TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= CooldownManager.DEFAULT_COOLDOWN) {
                p.sendMessage(ChatColor.GREEN + "Featured used!");
                cooldownManager.setCooldown(p.getUniqueId(), (int) System.currentTimeMillis());
            } else {
                p.sendMessage(ChatColor.RED.toString() + String.valueOf(CooldownManager.DEFAULT_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)) + " seconds before you can use this feature again.");
            }
        }
        }
    }




