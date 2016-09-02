package cn.cinema.manage.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 获取当前的日期
 * @author jiyk
 *
 */
public class DateUtil {
	
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static String getDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
	
	
	public String getDate_YYMMDD()
    {	    
        SimpleDateFormat     sdf=new   SimpleDateFormat( "yyyy-MM-dd   HH:mm:ss ");  
        String   test="";
        try{ 
        Date   now   =   new   Date(); 
          test   =   sdf.format(now).toString(); 
        } 
        catch(Exception   e){ 
                return   null; 
        }   
      
        return test.substring(0, 11);
    }
	public static void main(String args[])
	{
		DateUtil date=new DateUtil();
		System.out.println(getDate());
	}
	public String getDate__YYMMDD(int increment)
	{
		 SimpleDateFormat     sdf=new   SimpleDateFormat( "yyyy-MM-dd   HH:mm:ss ");  
	        String   test="";
	        try{ 
	        Date   now   =   new   Date(); 
	          test   =   sdf.format(now).toString(); 
	        } 
	        catch(Exception   e){ 
	                return   null; 
	        }   
	        String year=test.substring(0, 4);
	        int yearNum =Integer.parseInt(year);
	        yearNum+=increment;
	        year=yearNum+"";
	        String remainder=test.substring(4, 11);
	        year+=remainder;
	        test=year;
	        return test;
	        
	}
}
