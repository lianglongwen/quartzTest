import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
//必须实现InterruptableJob
public class StoppableJob implements InterruptableJob {
    private boolean flag = false;
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("调度被叫停");
        flag=true;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        while (true){
            if(flag){
                break;
            }
            try{
                System.out.println("没隔1s进行检查，看看是否停止");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("持续工作中！");
        }
    }
}
