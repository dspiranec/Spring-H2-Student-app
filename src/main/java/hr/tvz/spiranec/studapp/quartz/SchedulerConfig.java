package hr.tvz.spiranec.studapp.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail StudentJobDetail() {
        return
                JobBuilder.newJob(StudentJob.class).withIdentity("StudentJob").storeDurably().build();
    }

    @Bean
    public Trigger objavaJobTrigger() {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3600).repeatForever();

        return TriggerBuilder.newTrigger().forJob(StudentJobDetail())
                .withIdentity("StudentJobTrigger").withSchedule(scheduleBuilder).build();
    }
}
