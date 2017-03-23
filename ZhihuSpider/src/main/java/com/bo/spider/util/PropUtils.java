package com.bo.spider.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropUtils {

	static Properties prop = new Properties();
	/**
	 * 这种方式不太适合map reduce ,要取消. 1,map,reduce 路径找不到 2,static 得执行N遍
	 * */
	static {
		PropUtils
				.init(System.getProperty("user.dir") + File.separator + "conf");
		// + File.separator + "conf.properties"
	}

	public static void init(String path) {
		loadProByPath(path);
	}

	public static boolean checkParam() {
		String[] parameters = { "log.source.dir", "log.dest.dir",
				"fs.default.name" };
		for (String param : parameters) {
			if (get(param) == null || get(param).length() == 0) {
				return false;
			}
		}

		return true;
	}

	public static String get(String id) {
		return prop.getProperty(id);
	}

	/**
	 * @Title: loadProByPath
	 * @Description: 加载path路径下所有properties文件 或直接加载当前文件
	 */
	public static void loadProByPath(String path) {
		File file = new File(path);
		try {
			if (file.isDirectory()) {
				Pattern p = Pattern.compile("\\.properties");
				File[] files = file.listFiles();
				for (File f : files) {
					Matcher matcher = p.matcher(f.getName());
					if (matcher.find()) {
						prop.load(new FileInputStream(f));
					}
				}
			} else {
				prop.load(new FileInputStream(file));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		System.out.println(PropUtils.get("defaultName"));
	}
}
