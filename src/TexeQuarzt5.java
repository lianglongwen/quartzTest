import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class TexeQuarzt5 {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Date startTime = DateBuilder.nextGivenSecondDate(null, 8);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();
        JobDetail jobDetail = JobBuilder.newJob(MailJob2.class).withIdentity("jobMail2","jobGroup2")
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("使用的Cron表达式是："+ trigger.getCronExpression());
        scheduler.start();
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
