package com.raincubic.minecraft;

import com.raincubic.minecraft.event.ImportEvent;
import com.raincubic.minecraft.event.InitialEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * <h3>IncludePermission</h3>
 * <p>插件主类</p>
 *
 * @author : ChenhaoShen
 * @date : 2020-06-22 18:52
 **/
public class Main extends JavaPlugin {

	/**
	 * 获取插件文件夹
	 */
	public String pluginPath = getDataFolder().getAbsolutePath();

	@Override
	public void onEnable() {
		InitialEvent.createFolder(pluginPath);
		getLogger().info("IncludePermission已加载");
	}

	@Override
	public void onDisable() {
		getLogger().info("IncludePermission已卸载");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ImportEvent importEvent = new ImportEvent();
		return importEvent.includePermission(sender, pluginPath + File.separator + "permissions", pluginPath );
	}
}
