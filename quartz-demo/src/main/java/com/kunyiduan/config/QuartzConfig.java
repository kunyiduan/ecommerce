package com.kunyiduan.config;

import com.kunyiduan.Job.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob1", "myJobGroup1")
                //JobDataMap可以给任务execute传递参数
                .usingJobData("job_param", "job_param1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myTrigger1", "myTriggerGroup1")
                .usingJobData("job_trigger_param", "job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ? *"))
                .build();
    }
}
