/**
 * 
 */
package cn.cinema.manage.timer;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * TODO
 * 
 * @ClassName Featuretimer
 * @Description TODO
 * @author 汤凤欣
 * @date 2012-11-2
 */
public class FilmFeaturetimer {
	/**
	 * 日志类.
	 */
	private Logger logger = Logger.getLogger(FilmFeaturetimer.class);
	public void run() {
		logger.info("开始同步排期"+new Date());
		FilmFeatureUtil filmFeatureUtil = new FilmFeatureUtil();
		try {
			filmFeatureUtil.addFeature();
			logger.info("结束同步排期"+new Date());
		} catch (ParseException e) {
			logger.info("排期更新失败");
			logger.info(e);
			e.printStackTrace();
		}
	}
}
