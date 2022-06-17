import module.BaseJob;
import utils.QuartzUtil;

public class MainApp {
    public static void main(String[] args) {
        MailJobListener mailJobListener =  new MailJobListener();
        BaseJob baseJob = new BaseJob();
        baseJob.setJobClass(MailJob2.class);
        baseJob.setJobListener(mailJobListener);
        baseJob.setJobName("mailJob2");
        baseJob.setJobGroupName("jobGroup2");
        baseJob.setTriggerName("trigger1");
        baseJob.setTriggerGroupName("group1");
        baseJob.setCronTime("0/2 * * * * ?");
        QuartzUtil quartzUtil = new QuartzUtil();
        quartzUtil.hadleCronTrigger(baseJob);
    }
}
