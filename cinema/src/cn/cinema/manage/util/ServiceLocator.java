package cn.cinema.manage.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cinema.manage.service.FeatureService;
import cn.cinema.manage.spring.BaseService;


public final class ServiceLocator
{


    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext-service.xml");

    private static BaseService bs = (BaseService) getService("baseService");
    
    private static FeatureService featureService = (FeatureService) getService("featureService");


    public static Object getService(String service)
    {
        return context.getBean(service);
    }


    public static BaseService getBaseService()
    {
        return bs;
    }
    
	public static FeatureService getFeatureService() {
		return featureService;
	}

}
