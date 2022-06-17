import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TextQuarzt2 {
    public static void main(String[] args) throws Exception {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()
                .withIntervalInSeconds(2).withRepeatCount(10))
                .build();
        //定义一个JobDetail
        JobDetail jobDetail = JobBuilder.newJob(ExceptionJob.class)//要干活的类
                .withIdentity("exceptionJob1","exceptionGroup1")//定义名称和分组
                .build();
        //调度器中添加此job
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        scheduler.start();

        //等待20s等调度器执行完关闭
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
