package com.raincubic.minecraft.event;

import com.raincubic.minecraft.util.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 * <h3>IncludePermission</h3>
 * <p>导入权限事件类</p>
 *
 * @author : ChenhaoShen
 * @date : 2020-06-22 18:56
 **/
public class ImportEvent {

	/**
	 * 导入权限
	 * 	TODO: ChenhaoShen 2020-06-22 在Windows环境下的服务器启动时会锁定权限文件,导致文件不可写
	 * @param sender CommandSender
	 * @param permissionDirPath 权限文件路径
	 * @param pluginPath 插件文件夹
	 * @return
	 */
	public boolean includePermission(CommandSender sender, String permissionDirPath, String pluginPath) {
			//获取权限文件列表
			List<String> fileList = FileUtil.traverseDir(permissionDirPath);
			//获取每一个文件的内容
			for (int i = 0; i < fileList.size(); i++) {
				//获取文件名(文件名=权限组名称)
				String permissionGroup = FileUtil.getFileName(fileList.get(i));
				//获取权限列表
				List<String> permissionList = FileUtil.parsePermissionFile(fileList.get(i));
				//解析每一个权限
				for (int j = 0; j < permissionList.size(); j++) {
					//执行控制台命令来添加权限
					String command = "lp group " + permissionGroup + " permission set " + permissionList.get(j);
					Bukkit.dispatchCommand(sender, command);
				}
				//TODO: ChenhaoShen 2020-06-22 这什么鬼东西？怎么不生效???
				//移动文件到已添加目录
				//FileUtil.moveFile(fileList.get(i), pluginPath + File.separator + "done" + File.separator + permissionGroup);
			}
			return true;
	}

}
