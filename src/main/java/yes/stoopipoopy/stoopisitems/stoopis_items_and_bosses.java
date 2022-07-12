package yes.stoopipoopy.stoopisitems;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import yes.stoopipoopy.stoopisitems.items.abilitytest.ABILITYTEST;
import yes.stoopipoopy.stoopisitems.items.gravitydie.CORE_DICE_GRAVITY;
import yes.stoopipoopy.stoopisitems.items.ragedie.CORE_DICE_RAGE;
import yes.stoopipoopy.stoopisitems.items.shiftdie.CORE_DICE_SHIFT;
import yes.stoopipoopy.stoopisitems.items.uproardie.CORE_DICE_UPROAR;
import yes.stoopipoopy.stoopisitems.items.warpdie.CORE_DICE_WARP;

public final class stoopis_items_and_bosses extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("poop");
        getServer().getPluginManager().registerEvents(new CORE_DICE_GRAVITY(), this);
        getServer().getPluginManager().registerEvents(new CORE_DICE_RAGE(), this);
        getServer().getPluginManager().registerEvents(new CORE_DICE_SHIFT(), this);
        getServer().getPluginManager().registerEvents(new CORE_DICE_UPROAR(), this);
        getServer().getPluginManager().registerEvents(new CORE_DICE_WARP(), this);
        getServer().getPluginManager().registerEvents(new ABILITYTEST(), this);
    }




}
