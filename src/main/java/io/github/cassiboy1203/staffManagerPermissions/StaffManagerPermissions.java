package io.github.cassiboy1203.staffManagerPermissions;

import com.google.inject.Guice;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffManagerPermissions extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        var injector = Guice.createInjector(new StaffManagerPermissionsFactory(this, getConfig()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
