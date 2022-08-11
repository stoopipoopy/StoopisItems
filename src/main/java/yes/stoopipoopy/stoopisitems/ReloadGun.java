package yes.stoopipoopy.stoopisitems;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        HashMap tmp = ShotManager.gunsAndAmmo.get(p.getUniqueId());

        HashMap<Integer, Integer> ammoLeftAndMax = tmp;
        HashMap<String, HashMap<Integer,Integer>> alsoTmp = new HashMap<>();
        tmp.put("Get name later", tmp);
        ShotManager.gunsAndAmmo.put(p.getUniqueId(),alsoTmp);
        return false;
    }
}
