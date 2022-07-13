// 
// Decompiled by Procyon v0.5.36
// 

package yes.stoopipoopy.stoopisitems;

import java.util.Iterator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import java.util.List;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener
{
    long timeLeft;
    long curTime;
    private HashMap<UUID, Long> killCooldown;
    private HashMap<UUID, Long> magmaCooldown;
    private HashMap<UUID, Long> magmaPillarCooldown;
    private int killDiceCooldownTime;
    private int magmaDiceCooldownTime;
    private int magmaPillarCooldownTime;

    public main() {
        this.timeLeft = 0L;
        this.curTime = 0L;
        this.killCooldown = new HashMap<UUID, Long>();
        this.magmaCooldown = new HashMap<UUID, Long>();
        this.magmaPillarCooldown = new HashMap<UUID, Long>();
        this.killDiceCooldownTime = 60;
        this.magmaDiceCooldownTime = 120;
        this.magmaPillarCooldownTime = 20;
    }

    public void onEnable() {
        System.out.println("poop");
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
    }

    @EventHandler
    @Finished
    public void KILL_DICE(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack heldItem = e.getItem();
        final ItemMeta itemMeta = heldItem.getItemMeta();
        if (itemMeta.hasLore()) {
            final List lore = itemMeta.getLore();
            String attunements = (String) lore.get(lore.size() - 1);
            char[] Attunements = attunements.toCharArray();
            attunements = " ";
            String alsoAttunements = " ";
            for (int i = 2; i < Attunements.length; ++i) {
                attunements += Attunements[i];
                alsoAttunements += Attunements[i];
            }
            if (alsoAttunements != " Unattuneable") {
                Attunements = null;
                Attunements = attunements.toCharArray();
                String maxAttunements = "";
                maxAttunements += Attunements[Attunements.length - 1];
            }
            String IDLORE = (String) lore.get(0);
            final char[] IDARRAY = IDLORE.toCharArray();
            IDLORE = " ";
            for (int j = 2; j < IDARRAY.length; ++j) {
                IDLORE += IDARRAY[j];
            }
            System.out.println("|" + IDLORE + "|");
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && IDLORE.equals(" Those moronic developers thought they could hide this from me?")) {
                if (this.killCooldown.containsKey(p.getUniqueId())) {
                    final long secondsLeft = this.killCooldown.get(p.getUniqueId()) / 1000L + this.killDiceCooldownTime - System.currentTimeMillis() / 1000L;
                    if (secondsLeft > 0L) {
                        p.sendMessage("seconds left: " + String.valueOf(secondsLeft));
                    }
                    else {
                        this.killCooldown.remove(p.getUniqueId());
                    }
                }
                else {
                    p.sendMessage("do thing");
                    p.setHealth(0.0);
                    this.killCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    public void MAGMA_DICE(final PlayerInteractEvent e) throws InterruptedException {
        final Player p = e.getPlayer();
        final ItemStack heldItem = e.getItem();
        final ItemMeta itemMeta = heldItem.getItemMeta();
        if (itemMeta.hasLore()) {
            final List lore = itemMeta.getLore();
            String attunements = (String) lore.get(lore.size() - 1);
            char[] Attunements = attunements.toCharArray();
            attunements = " ";
            String alsoAttunements = " ";
            for (int i = 2; i < Attunements.length; ++i) {
                attunements += Attunements[i];
                alsoAttunements += Attunements[i];
            }
            if (alsoAttunements != " Unattuneable") {
                Attunements = null;
                Attunements = attunements.toCharArray();
                String maxAttunements = "";
                maxAttunements += Attunements[Attunements.length - 1];
                attunements = "0";
            }
            String IDLORE = (String) lore.get(0);
            final char[] IDARRAY = IDLORE.toCharArray();
            IDLORE = " ";
            for (int j = 2; j < IDARRAY.length; ++j) {
                IDLORE += IDARRAY[j];
            }
            System.out.println("|" + IDLORE + "|");
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && IDLORE.equals(" Hellfire.")) {
                if (this.magmaCooldown.containsKey(p.getUniqueId())) {
                    final long secondsLeft = this.magmaCooldown.get(p.getUniqueId()) / 1000L + this.magmaDiceCooldownTime - System.currentTimeMillis() / 1000L;
                    if (secondsLeft > 0L) {
                        p.sendMessage("seconds left: " + String.valueOf(secondsLeft));
                    }
                    else {
                        this.magmaCooldown.remove(p.getUniqueId());
                    }
                }
                else {
                    p.sendMessage("do thing");
                    final World world = p.getWorld();
                    final ArmorStand center = (ArmorStand)world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                    final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    final String command = "particle flame " + center.getLocation().getBlockX() + " " + center.getLocation().getBlockY() + " " + center.getLocation().getBlockZ() + " 2 80 2 0 500 force";
                    this.magmaPillarCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                    while (true) {
                        Thread.sleep(100L);
                        final List<Entity> nearbyEntites = (List<Entity>)world.getNearbyEntities(center.getLocation(), 2.0, 100.0, 2.0);
                        final List<LivingEntity> nearbyLiveEntities = new ArrayList<LivingEntity>();
                        for (final Entity entity : nearbyEntites) {
                            if (entity.isDead()) {
                                continue;
                            }
                            nearbyLiveEntities.add((LivingEntity)entity);
                        }
                        for (final LivingEntity entity2 : nearbyLiveEntities) {
                            entity2.damage((double)(500 + 100 * Integer.valueOf(attunements)));
                        }
                        if (!this.magmaPillarCooldown.containsKey(p.getUniqueId())) {
                            break;
                        }
                        final long secondsLeft2 = this.magmaPillarCooldown.get(p.getUniqueId()) / 1000L + this.magmaPillarCooldownTime - System.currentTimeMillis() / 1000L;
                        if (secondsLeft2 > 0L) {
                            Bukkit.dispatchCommand((CommandSender)console, command);
                        }
                        else {
                            this.magmaPillarCooldown.remove(p.getUniqueId());
                        }
                    }
                    this.magmaPillarCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                    System.out.println(this.magmaPillarCooldown);
                    this.magmaCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }
}