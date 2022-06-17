import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class TextQuarzt4 {
    public static void main(String[] args) throws Exception {
        //定义调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义开始时间
        //Date startDate = DateBuilder.nextGivenSecondDate(null,2);
        Date startDate = DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND);
        //定义触发器
        SimpleTrigger simpleTrigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startAt(startDate)
                .build();
        //定义JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MailJob2.class).withIdentity("mailJob2","mailGroup2")
                .build();
        //插入调度器
        Date ft = scheduler.scheduleJob(jobDetail,simpleTrigger);

        System.out.println("当前时间是：" + new Date().toLocaleString());
        System.out.printf("%s 这个任务会在 %s 准时开始运行，累计运行%d次，间隔时间是%d毫秒%n", jobDetail.getKey(), ft.toLocaleString(), simpleTrigger.getRepeatCount()+1, simpleTrigger.getRepeatInterval());

        //启动调度器
        scheduler.start();
        //等待200秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(200000);
        scheduler.shutdown(true);
    }
}
