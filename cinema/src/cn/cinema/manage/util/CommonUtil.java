package cn.cinema.manage.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public  class CommonUtil {
	
	
	   public static   InputStream String2InputStream(String str){
		   ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
		   return stream;
		}
}
