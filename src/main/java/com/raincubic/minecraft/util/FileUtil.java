package com.raincubic.minecraft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>IncludePermission</h3>
 * <p>文件操作类</p>
 *
 * @author : ChenhaoShen
 * @date : 2020-06-22 19:00
 **/
public class FileUtil {

	/**
	 * 遍历目录内容
	 * @param dirPath 目录路径
	 * @return List<String> fileList
	 */
	public static List<String> traverseDir(String dirPath) {
		List<String> fileList = new ArrayList<>();
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		for (File file: files) {
			if (!file.isDirectory()) {
				fileList.add(file.getAbsolutePath());
			}
		}
		return fileList;
	}

	/**
	 * 移动文件到指定位置
	 * @param sourceFilePath 原始文件位置
	 * @param destinationFilePath 目的文件位置
	 * @return
	 */
	public static boolean moveFile(String sourceFilePath, String destinationFilePath) {
		File srcFile = new File(sourceFilePath);
		File destFile = new File(destinationFilePath);
		if (!destFile.exists()) {
			//移动文件到目标位置
			srcFile.renameTo(destFile);
			//删除原文件
			srcFile.delete();
			return true;
		}
		return false;
	}

	/**
	 * 解析文件内容
	 * @param filePath 文件路径
	 * @return permissionList
	 */
	public static List<String> parsePermissionFile(String filePath) {
		List<String> permissionList = new ArrayList<>();
		try {
			File file = new File(filePath);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			permissionList.add(line);
			while (line != null) {
				line = br.readLine();
				if(!"".equals(line) && line != null){
					permissionList.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissionList;
	}

	/**
	 * 获取文件名称 (不含文件扩展名)
	 * @param filePath 文件路径
	 * @return fileName
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName().split("\\.")[0];
	}

}
