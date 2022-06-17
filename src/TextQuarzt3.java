import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TextQuarzt3 {
    public static void main(String[] args) throws Exception {
        //定义调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .startNow()
                .build();
        //定义JobDetail
        JobDetail jobDetail = JobBuilder.newJob(StoppableJob.class)
                .withIdentity("stopJob1","stopGroup1")
                .build();
        //将job添加到调度器中
        scheduler.scheduleJob(jobDetail,trigger);
        //启动
        scheduler.start();
        //等待5s
        Thread.sleep(5000);
        //让调度停下来
        System.out.println("停止调度");
        scheduler.interrupt(jobDetail.getKey());

        //等待20s等调度器执行完关闭
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }
}
