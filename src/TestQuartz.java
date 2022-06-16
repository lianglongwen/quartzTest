import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {
    public static void main(String[] args) throws Exception {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义一个触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")//定义触发器的名称和所属触发器的分组
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()
                .withIntervalInSeconds(2)//每隔2s执行一次
                .withRepeatCount(10))//一共执行11次，第一次不执行
                .build();
        //定义一个JobDetail任务
        JobDetail jobDetail = JobBuilder.newJob(MailJob.class)//执行要干活的MailJob
                .withIdentity("mailjob1", "mailgroup") //定义任务名称和所属任务的分组
                .usingJobData("email","1426007012@qq.com")//定义参数
                .build();
        //调度加入这个job
        scheduler.scheduleJob(jobDetail, trigger);
        //启动任务
        scheduler.start();

        //等待20秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(20000);
        scheduler.shutdown(true);

    }
}
