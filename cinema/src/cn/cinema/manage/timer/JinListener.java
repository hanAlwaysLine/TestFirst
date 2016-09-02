package cn.cinema.manage.timer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JinListener implements ServletContextListener {
//Fields
private Log logger = LogFactory.getLog(this.getClass()); //日志
private static Timer timer;
private static JinTask task = new JinTask();


public void contextDestroyed(ServletContextEvent arg0) {
   logger.debug("调用contextDestroyed方法");
   if (timer != null) {   
            timer.cancel();   
            logger.debug("Timer Canceled");   
        }   
   //timer.schedule(task, 0, 100);
   logger.debug("contextDestroyed方法执行完成");
}


public void contextInitialized(ServletContextEvent arg0) {
   logger.debug("调用contextInitialized方法");
        try {   
            
            timer = new Timer(true);   
            GregorianCalendar now = new GregorianCalendar();   
            //每天9:22执行   Calendar.DAY_OF_YEAR(一年中第一天的值为 1)
            //HOUR_OF_DAY( 用于 24 小时制时钟)
            //WEEK_OF_YEAR(第一个星期为1)
            now.set(Calendar.HOUR_OF_DAY, 9);
            now.set(Calendar.MINUTE, 22);
            now.set(Calendar.SECOND, 0);
            timer.schedule(task,0,300000);
        } catch (Exception e) {   
            e.printStackTrace();   
            logger.error("Unable to initialize Schedule.");     
        } 
   logger.debug("contextInitialized方法执行完成");

}
}