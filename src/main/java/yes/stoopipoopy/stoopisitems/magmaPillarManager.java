package yes.stoopipoopy.stoopisitems;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class magmaPillarManager extends BukkitRunnable {
    public boolean doBreak = false;
    public boolean sendParticles = false;
    World world;
    ArmorStand center;
    String attunements;
    HashMap<UUID, Long> magmaPillarCooldown;
    Player p;
    PlayerInteractEvent e;
    int magmaPillarCooldownTime;
    ConsoleCommandSender console;
    String command;
    public final main plugin;
    public magmaPillarManager(World world, ArmorStand center, String attunements, HashMap<UUID, Long> magmaPillarCooldown, Player p , PlayerInteractEvent e, int magmaPillarCooldownTime, ConsoleCommandSender console, String command, main plugin){
        this.world = world;
        this.center = center;
        this.attunements = attunements;
        this.magmaPillarCooldown = magmaPillarCooldown;
        this.p = p;
        this.e = e;
        this.magmaPillarCooldownTime = magmaPillarCooldownTime;
        this.console = console;
        this.command = command;
        this.plugin = plugin;
    }
    public void run(){
        new BukkitRunnable(){
            @Override
            public void run() {

                while(true){

                    List<Entity> nearbyEntites = (List<Entity>) world.getNearbyEntities(center.getLocation(), 2, 100 , 2);
                    List<LivingEntity> nearbyLiveEntities = new ArrayList<>();
                    for(Entity entity : nearbyEntites){
                        if(entity.isDead()){
                            ;
                        }
                        else{
                            nearbyLiveEntities.add((LivingEntity) entity);
                        }


                    }
                    for(LivingEntity entity : nearbyLiveEntities){
                        entity.damage(500 + (100 * Integer.valueOf(attunements)));
                    }
                    if(magmaPillarCooldown.containsKey(p.getUniqueId())){

                        long secondsLeft = ((magmaPillarCooldown.get(p.getUniqueId()) / 1000) + magmaPillarCooldownTime) - (System.currentTimeMillis() / 1000);
                        if (secondsLeft > 0) {
                            /** idk if this works**/
                            System.out.println("dispach command");
                            p.getServer().dispatchCommand(p.getServer().getConsoleSender(), command);


                        } else {
                            sendParticles = false;

                            magmaPillarCooldown.remove(p.getUniqueId());
                        }
                    }else{
                        doBreak = true;

                        magmaPillarCooldown.put(p.getUniqueId(), System.currentTimeMillis());
                        System.out.println(magmaPillarCooldown);
                        break;
                    }
                }


            }


        }.runTask(this.plugin);




    }
}
