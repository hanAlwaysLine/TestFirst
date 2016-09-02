package cn.cinema.manage.action.listener;


import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import cn.cinema.manage.spring.BaseService;
import cn.cinema.manage.util.CPO;
import cn.cinema.manage.util.ServiceLocator;


/**
 * 监听
 * 
 * @author 
 * 
 */
public class ServiceListener implements ServletContextListener {
	private static BaseService bs = ServiceLocator.getBaseService();
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(ServiceListener.class);
	/*
	 * 定义定时器
	 */
	private Timer timer = new Timer();
	private Timer timer1 = new Timer();
	private Timer timer2 = new Timer();
	private Timer timer3 = new Timer();
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		// 服务器路径
		try {
			String serverUrl = event.getServletContext().getRealPath("/");
			CPO.serverUrl = serverUrl;
			CPO.serverUploadUrl = serverUrl+"upload\\";
		} catch (Exception e) {
			logger.error("获取服务器路径失败");
		}
	  try {
           CPO.webRootPath=getWebRootAbsolutePath();           
        } catch (Exception e) {
            logger.error("获取路径失败");
        }
        //同步排期
        try {
//			影院信息上报 定时发计划30分钟执行一次
//			logger.info("开始监听影院信息上报开始");
//			timer = new Timer(true);
		//	timer.schedule(new Cinemaltimer(), new Date(), 180*60 * 1000);
			//logger.info("开始监听影院信息上报结束");
			
			
        	//scheduleAtFixedRate  上次任务的执行时间+时间间隔   任务不会延后，但要考虑多线程并发的问题。因为当一个任务还没执行完时，下一个时间间隔任务又会启动，执行相同的任务
        	//schedule             上次任务的结束时间+时间间隔   阻塞式，任务会延后，等待前面的任务执行完，再执行延务后的任务
			//定时发计划30分钟执行一次
         	 // timer.schedule(new FilmFeaturetimer(), 0, 5 * 60 * 1000);
		} catch (Exception e) {
			logger.error("同步排期失败");
			e.printStackTrace();
		}
		try {
			//定时获取排期剩余座位数
	 	// timer1.schedule(new FeatureSeatStatetimer(), 0, 3 * 60 * 1000);
		} catch (Exception e) {
			logger.error("同步排期剩余座位数失败");
			e.printStackTrace();
		}
		
		try {
			//定时同步订单取票状态
	 	// timer2.schedule(new OrderStatustimer(), 0, 5 * 60 * 1000);
		} catch (Exception e) {
			logger.error("同步订单取票状态失败");
			e.printStackTrace();
		}
		try{
			//定时同步影厅数据
	  // 	timer3.schedule(new Halltimer(), 0, 180 * 60 * 1000);
		}catch(Exception e){
			logger.error("影厅数据同步失败");
			e.printStackTrace();
		}
	}
	 private String getWebRootAbsolutePath() {  
	        String path = null;  
	        String folderPath = ServiceListener.class.getProtectionDomain().getCodeSource()  
	                .getLocation().getPath(); 	      
	        if (folderPath.indexOf("WEB-INF") > 0) {  
	            path = folderPath.substring(0, folderPath  
	                    .indexOf("WEB-INF/classes"));  
	        }
	        logger.info("===================path="+path);
	        return path;  
	    }
	 
}
