package com.util;



import java.io.File;

/**
 * 

* @Description: File操作工具类

* @author jaryur 

* @date 2014-12-25 上午11:33:30 

* @version V1.0
 */
public class FileUtil {
	
	
	/**
	 * 
	* @author jaryur 
	* @param @param dirName  
	* @date 2014-12-25 上午11:35:50
	 */
	public static void checkOrCreateDir(String dirName){
		File file=new File(dirName);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static Boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if(!dir.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
			return dir.getParentFile().mkdirs();		//不存在就全部创建
		}
		return false;
	}
	
	public static Double getFilesize(String filepath){
		File backupath=new File(filepath);
		return Double.valueOf(backupath.length())/1000.000;
		
	}
	
	 public static String getClassPath(){
		 String path=(String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../")
				 .replaceAll("file:/", "").replaceAll("%20"," ").trim();
		 if(path.indexOf(":") != 1){
			 path=File.separator+path;
		 }
		 return path;
	 }
}
