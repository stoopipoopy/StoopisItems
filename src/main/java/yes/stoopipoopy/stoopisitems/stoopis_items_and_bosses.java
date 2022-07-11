package yes.stoopipoopy.stoopisitems;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import yes.stoopipoopy.stoopisitems.items.CORE_DICE_GRAVITY;

public final class stoopis_items_and_bosses extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("poop");
        getServer().getPluginManager().registerEvents(new CORE_DICE_GRAVITY(), this);
    }




}
