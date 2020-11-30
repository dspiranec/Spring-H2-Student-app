package hr.tvz.spiranec.studapp.quartz;

import hr.tvz.spiranec.studapp.dto.StudentDTO;
import hr.tvz.spiranec.studapp.services.StudentService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;


public class StudentJob extends QuartzJobBean {

    @Autowired
    private StudentService studentService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<StudentDTO> students;
        students = studentService.findAll();

        System.out.println("Ovo su trenutno upisani studenti:");

        System.out.println("----------------------------------");

        for (StudentDTO student : students) {
            System.out.println(student.getJmbag() + " - " + student.getFirstName() + " " + student.getLastName());
        }

        System.out.println("----------------------------------");
    }


}
