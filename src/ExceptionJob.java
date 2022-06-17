import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ExceptionJob implements Job {
    private static int i = 3;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        int i = 5;
        //估计触发异常
        try{
            System.out.println(100/i--);
        }catch (Exception e){
            System.out.println("发生了异常，取消这个Job 对应的所有调度");
            JobExecutionException je = new JobExecutionException(e);
            je.setUnscheduleAllTriggers(true);
            throw je;
        }
    }
}
