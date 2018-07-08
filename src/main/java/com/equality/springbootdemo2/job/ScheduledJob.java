package com.equality.springbootdemo2.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author E-Quality
 *
 */
@Component
public class ScheduledJob {

	Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;
	
	/**
	 * fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
	 */
	@Scheduled(fixedDelay = 5000)
	public void testFixDelay() {
        logger.info("===fixedDelay: 第{}次执行方法", fixedDelayCount++);
    }
	
	/**
	 * fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
	 */
	@Scheduled(fixedRate = 5000)        
    public void testFixedRate() {
        logger.info("===fixedRate: 第{}次执行方法", fixedRateCount++);
    }

	/**
	 * initialDelay = 1000表示延迟1000ms执行第一次任务
	 */
    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void testInitialDelay() {
        logger.info("===initialDelay: 第{}次执行方法", initialDelayCount++);
    }

    /**
     * cron接受cron表达式，根据cron表达式确定定时规则
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void testCron() {
        logger.info("===cron: 第{}次执行方法", cronCount++);
    }
	
}
