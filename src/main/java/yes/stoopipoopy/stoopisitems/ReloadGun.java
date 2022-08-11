package yes.stoopipoopy.stoopisitems;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ReloadGun implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command !");
            return false;
        }
        Player p = (Player) sender;
        p.sendMessage("Totally Reloaded :) trust");
        //ShotManager.playerAndGun.get(p.getUniqueId()).setCurrentAmmo(ShotManager.playerAndGun.get(p.getUniqueId()).getTotalAmmo());
        for(int i = 0; i < p.getInventory().getSize(); i++){
            ItemStack curItem = p.getInventory().getItem(i);
            try{
                if(curItem.getItemMeta().getLore() != null){
                    if(curItem.getItemMeta().getLore().toString().contains("Just a standard bullet shell")){
                        if(curItem.getAmount() >= 6){
                            p.sendMessage("More Than 6");
                            p.sendMessage(Integer.toString(ShotManager.playerAndGun.get(p.getUniqueId()).getCurrentAmmo()));
                            ShotManager.playerAndGun.get(p.getUniqueId()).setCurrentAmmo(ShotManager.playerAndGun.get(p.getUniqueId()).getCurrentAmmo() + 6);
                            curItem.setAmount(curItem.getAmount() - 6);
                            break;
                        }
                        else if(curItem.getAmount() < 6){
                            p.sendMessage("Less than 6");
                            p.sendMessage(Integer.toString(ShotManager.playerAndGun.get(p.getUniqueId()).getCurrentAmmo()));
                            ShotManager.playerAndGun.get(p.getUniqueId()).setCurrentAmmo(ShotManager.playerAndGun.get(p.getUniqueId()).getCurrentAmmo() + curItem.getAmount());
                            curItem.setAmount(0);
                            break;
                        }


                    }
                }
            }catch(NullPointerException e){

            }


            }


        return false;
    }
}
