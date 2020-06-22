package com.raincubic.minecraft.event;

import java.io.File;
import java.util.logging.Logger;

/**
 * <h3>IncludePermission</h3>
 * <p>初始化事件</p>
 *
 * @author : ChenhaoShen
 * @date : 2020-06-22 19:53
 **/
public class InitialEvent {
	Logger logger = Logger.getLogger(this.getClass().getName());
	public static void createFolder(String dataFolderPath) {
		File pluginFolder = new File(dataFolderPath);
		File permissionFolder = new File(dataFolderPath + File.separator + "permissions");
		File doneFolder = new File(dataFolderPath + File.separator + "done");
		if (!pluginFolder.exists()) {
			pluginFolder.mkdirs();
			permissionFolder.mkdirs();
			doneFolder.mkdirs();
		} else if (!permissionFolder.exists()) {
			permissionFolder.mkdirs();
		} else if (!doneFolder.exists()) {
			doneFolder.mkdirs();
		}
	}
}
