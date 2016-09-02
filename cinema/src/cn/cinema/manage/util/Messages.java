package cn.cinema.manage.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/*
	*读取properties工具类
*/
public class Messages {
	 private static final String BUNDLE_NAME = "init";   	
	    //messages.properties文件和Messages类在同一个包下,包名：com.xxx.cs.mm.service     
	      
	     private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);     
	      
	    public static String getString(String key) {     
	        try {     
	            return RESOURCE_BUNDLE.getString(key);     
	         } catch (MissingResourceException e) {     
	              return '!' + key + '!';     
	          }     
	     }  
	    
		public static void main(String[] args) 
		{
			System.out.println(getString("versionurl"));
		}
}


