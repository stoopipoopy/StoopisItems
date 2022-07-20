// 
// Decompiled by Procyon v0.5.36
// 

package yes.stoopipoopy.stoopisitems;

import java.util.*;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import sun.awt.image.ImageAccessException;

import static java.lang.Thread.sleep;

public final class main extends JavaPlugin implements Listener
{
    long timeLeft;
    long curTime;
    private HashMap<UUID, Long> killCooldown;
    private HashMap<UUID, Long> magmaCooldown;
    private HashMap<UUID, Long> magmaPillarCooldown;
    private HashMap<UUID, Long> rageComboCooldown;
    private HashMap<UUID, Long> rageCooldown;
    private int killDiceCooldownTime;
    private int rageComboCooldownTime;
    private int magmaDiceCooldownTime;
    private int magmaPillarCooldownTime;
    private int rageDiceCooldownTime;

    private int rageComboLimit;


    public main() {
        this.timeLeft = 0L;
        this.curTime = 0L;
        this.killCooldown = new HashMap<UUID, Long>();
        this.magmaCooldown = new HashMap<UUID, Long>();
        this.magmaPillarCooldown = new HashMap<UUID, Long>();
        this.rageCooldown = new HashMap<UUID,Long>();
        this.rageComboCooldown = new HashMap<UUID, Long>();
        this.killDiceCooldownTime = 60;
        this.magmaDiceCooldownTime = 120;
        this.magmaPillarCooldownTime = 20;
        this.rageDiceCooldownTime = 180;
        this.rageComboCooldownTime = 1;
        this.rageComboLimit = 400 /** percent **/;
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
    @WorkingOn
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
                    p.sendMessage("do thing(fire)");
                    final World world = p.getWorld();
                    final ArmorStand center = (ArmorStand)world.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
                    final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    final String command = "particle flame " + center.getLocation().getBlockX() + " " + center.getLocation().getBlockY() + " " + center.getLocation().getBlockZ() + " 2 80 2 0 10 force";
                    this.magmaPillarCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                    magmaPillarManager MagmaPillarManager = new magmaPillarManager(world, center, attunements, magmaPillarCooldown, p, e, magmaPillarCooldownTime, console,command,this);
                 //   MagmaPillarManager.runTaskAsynchronously(this);
                    String finalAttunements1 = attunements;
                    new BukkitRunnable(){
                        @Override
                        public void run() {

                            while(true){
                                try {
                                    sleep(500);
                                } catch (InterruptedException ex) {
                                    p.sendMessage("shit the sleep wont work oh god ahhhhh turufjelaielidniegnid *dies*");
                                }
                                List<Entity> nearbyEntites = (List) world.getNearbyEntities(center.getLocation(), 2, 80 , 2);
                                List<LivingEntity> nearbyLiveEntities = new ArrayList<>();
                                for(Entity entity : nearbyEntites){
                                    if(entity.isDead()){
                                        continue;
                                    }
                                    else{
                                        nearbyLiveEntities.add((LivingEntity) entity);
                                    }


                                }
                                for(LivingEntity entity : nearbyLiveEntities){
                                    try{
                                        entity.damage(500 + (100 * Integer.valueOf(finalAttunements1)));
                                    } catch(NumberFormatException e){
                                        System.out.println("Failed to damage nearby entity!");
                                    }

                                }
                                if(magmaPillarCooldown.containsKey(p.getUniqueId())){

                                    long secondsLeft = ((magmaPillarCooldown.get(p.getUniqueId()) / 1000) + magmaPillarCooldownTime) - (System.currentTimeMillis() / 1000);
                                    if (secondsLeft > 0) {
                                        /** idk if this works**/
                                        p.getServer().dispatchCommand(p.getServer().getConsoleSender(), command);


                                    } else {
                                        magmaPillarCooldown.remove(p.getUniqueId());
                                    }
                                }else{


                                    magmaPillarCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                                    System.out.println(magmaPillarCooldown);
                                    break;
                                }
                            }


                        }


                    }.runTaskLater(this,0);
                  //  Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
                        // Code, no need to do the run() method, just directly type the code
                  //      Bukkit.getServer().dispatchCommand(console, command);
                  //  }, 0 /*Delay before starting in ticks*/, 20 /*Delay between runs in ticks*/);
                    String finalAttunements = attunements;

                    this.magmaPillarCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                    System.out.println(this.magmaPillarCooldown);
                    this.magmaCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    @WorkingOn
    public void GRAVITY_DICE(final PlayerInteractEvent e) throws  InterruptedException{
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
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && IDLORE.equals(" \"I've always wanted to fly!\"")) {
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
                    p.sendMessage("do thing(gravity)");
                    List<Entity> nearbyEntites = (List<Entity>) p.getWorld().getNearbyEntities(p.getLocation(), 10, 100 , 10);
                    List<LivingEntity> nearbyLiveEntities = new ArrayList<>();
                    PotionEffect levitate = new PotionEffect(PotionEffectType.LEVITATION, 10 + (5 * Integer.valueOf(attunements)), 1, true, false, true);
                    for(Entity entity : nearbyEntites){
                        if(entity.isDead()){
                            continue;
                        }
                        else{
                            nearbyLiveEntities.add((LivingEntity) entity);
                        }


                    }
                    for(LivingEntity entity : nearbyLiveEntities){
                        if(entity.getType() != EntityType.PLAYER){
                            entity.addPotionEffects((Collection<PotionEffect>) levitate);
                        }

                    }
                    this.killCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    @WorkingOn
    public void RAGE_DICE(final PlayerInteractEvent e) throws InterruptedException {
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
            if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && IDLORE.equals(" Level 18 spell.")) {
                if (this.rageCooldown.containsKey(p.getUniqueId())) {
                    final long secondsLeft = this.rageCooldown.get(p.getUniqueId()) / 1000L + this.rageDiceCooldownTime - System.currentTimeMillis() / 1000L;
                    if (secondsLeft > 0L) {
                        p.sendMessage("seconds left: " + String.valueOf(secondsLeft));
                    }
                    else {
                        this.rageCooldown.remove(p.getUniqueId());
                    }
                }
                else {
                    p.sendMessage("do thing (rage)");
                    new BukkitRunnable(){
                        @EventHandler
                        public void COMBO(EntityDamageByEntityEvent e){
                            if(e.getDamager().getClass() == Player.class){

                            }



                        }
                        public void run(){
                            while(true){
                                if (rageComboCooldown.containsKey(p.getUniqueId())) {
                                    final long secondsLeft = rageComboCooldown.get(p.getUniqueId()) / 1000L + killDiceCooldownTime - System.currentTimeMillis() / 1000L;
                                    if (secondsLeft > 0L) {
                                        p.sendMessage("seconds left: " + String.valueOf(secondsLeft));
                                    }
                                    else {
                                        rageComboCooldown.remove(p.getUniqueId());
                                    }
                                }
                                else {
                                    p.sendMessage("do thing (rage)");
                                    rageManager RageManager = new rageManager();
                                    RageManager.start();
                                    rageComboCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                                }
                            }
                        }
                    }.runTaskLater(this,0);
                    this.killCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }

}