package module;

import org.quartz.JobDataMap;

import java.io.Serializable;

public class BaseJob implements Serializable {
    private String jobName;   //任务名称
    private String jobGroupName;  //任务组名称
    private String triggerName; //触发器名称
    private String triggerGroupName;//触发器组名称
    private String cronTime;  //crond格式时间
    private JobDataMap jobDataMap;  //附带参数
    private  int time;  //时间间隔 秒
    private  int  count; //执行多少次
    private Class jobClass; //执行job的类
    private Object jobListener;

    public Object getJobListener() {
        return jobListener;
    }

    public void setJobListener(Object jobListener) {
        this.jobListener = jobListener;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public String getCronTime() {
        return cronTime;
    }

    public void setCronTime(String cronTime) {
        this.cronTime = cronTime;
    }

    public JobDataMap getJobDataMap() {
        if(jobDataMap==null){
            jobDataMap = new JobDataMap();
        }
        return jobDataMap;
    }

    public void setJobDataMap(JobDataMap jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Class getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class jobClass) {
        this.jobClass = jobClass;
    }
}
