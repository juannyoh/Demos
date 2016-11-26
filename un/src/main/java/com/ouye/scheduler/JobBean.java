package com.ouye.scheduler;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobBean extends QuartzJobBean {
	
	private Logger LOGGER =Logger.getLogger(JobBean.class);

	@Override
	protected void executeInternal(JobExecutionContext jobexecutioncontext)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		LOGGER.info("jobbean:time");
	}

}
