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
	Logger logger = Logger.getLogger(this.getClass().getName());

	public boolean includePermission(CommandSender sender, String permissionDirPath, String pluginPath) {

			//获取权限文件列表
			List<String> fileList = FileUtil.traverseDir(permissionDirPath);
			logger.info("已获取" + fileList.size() + "个权限组文件");
			logger.info(fileList.toString());
			//获取每一个文件的内容
			for (int i = 0; i < fileList.size(); i++) {
				//获取文件名(文件名=权限组名称)
				String permissionGroup = FileUtil.getFileName(fileList.get(i));
				//获取权限列表
				List<String> permissionList = FileUtil.parsePermissionFile(fileList.get(i));
				//解析每一个权限
				logger.info("解析到" + permissionGroup + "共" + permissionList.size() + "条权限数据");
				for (int j = 0; j < permissionList.size(); j++) {
					//执行控制台命令来添加权限
					String command = "lp group " + permissionGroup + " permission set " + permissionList.get(j);
					Bukkit.dispatchCommand(sender, command);
					logger.info("已添加" + permissionList.get(j) + "权限到" + permissionGroup);
				}
				//移动文件到已添加目录
				logger.info("移动"+ fileList.get(i) + "到" + pluginPath + File.separator + "done" + File.separator + permissionGroup);
				FileUtil.moveFile(fileList.get(i), pluginPath + File.separator + "done" + File.separator + permissionGroup);
				logger.info("已移动添加过的权限文件");
			}
			return true;
	}

}
