import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MailJob implements Job {
    //执行任务的唯一入口
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String email = detail.getJobDataMap().getString("email");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String now = sdf.format(new Date());

        System.out.printf("给邮件地址 %s 发出了一封定时邮件, 当前时间是: %s%n" ,email, now);
    }
}
