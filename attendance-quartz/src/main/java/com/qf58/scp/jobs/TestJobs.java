package com.qf58.crm.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: weicaijia
 * Date: 2018/11/26 18:03
 * Time: 14:15
 */
public class TestJobs extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(TestJobs.class);


    public void testMethod() {
        System.out.println("执行");
    }





    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("-------------appointmentSms---------start-------------" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        testMethod();
    }


}
