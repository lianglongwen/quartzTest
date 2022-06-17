package utils;

import module.BaseJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class QuartzUtil {
    /**
     * [简单任务调度:每次执行间隔为多少毫秒，执行多少次] <br>
     */
    public static void handleSimpleTrigger(BaseJob baseJob) {

        // 通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail job = JobBuilder.newJob(baseJob.getJobClass())
                    .withIdentity(baseJob.getJobName(),baseJob.getJobGroupName() )
                    .usingJobData(baseJob.getJobDataMap())
                    .build();
            // 定义调度触发规则
            //使用simpleTrigger规则
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity(baseJob.getTriggerName(),
                    baseJob.getJobGroupName())
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(baseJob.getTime()).withRepeatCount(baseJob.getCount()))
                    .startNow().build();

            //增加Job监听
            if(baseJob.getJobListener()!=null){
                Object obj= baseJob.getJobListener();
                KeyMatcher<JobKey> keyMatcher = KeyMatcher.keyEquals(job.getKey());
                scheduler.getListenerManager().addJobListener((JobListener) obj,keyMatcher);
            }

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job, trigger);
            // 启动调度
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    /**
     * [复杂任务调度：每天几点几分几秒定时执行任务]
     */
    public static void hadleCronTrigger(BaseJob baseJob) {
        // 通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail job = JobBuilder.newJob(baseJob.getJobClass())
                    .withIdentity(baseJob.getJobName(), baseJob.getJobGroupName())
                    .usingJobData(baseJob.getJobDataMap())
                    .build();
            // 定义调度触发规则
            //使用cornTrigger规则  每天18点30分
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity(baseJob.getTriggerName(), baseJob.getTriggerGroupName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(baseJob.getCronTime()))
                    .startNow().build();

            //增加Job监听
            if(baseJob.getJobListener()!=null){
                Object obj= baseJob.getJobListener();
                KeyMatcher<JobKey> keyMatcher = KeyMatcher.keyEquals(job.getKey());
                scheduler.getListenerManager().addJobListener((JobListener) obj,keyMatcher);
            }

            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job, trigger);
            // 启动调度
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

