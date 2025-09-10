package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Enrollment;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Student;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.EnrollmentRepository;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.TutoringClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private TutoringClassRepository tutoringClassRepository;
    @Autowired
    private TutoringSessionService tutoringSessionService;


    public List<Student> getUsersInClass(TutoringClass tc) {
        List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsByTutoringClass(tc);
        List<Student> res = new ArrayList<>();
        for(Enrollment e : enrollments) {
            res.add(e.getStudent());
        }

        return res;
    }


    public void addEnrollment(TutoringClass tutoringClass, Student student) {
        Enrollment thisEnrollment = new Enrollment();
        thisEnrollment.setTutoringClass(tutoringClass);

        thisEnrollment.setStudent(student);
        enrollmentRepository.save(thisEnrollment);

    }



    public List<String> getUsersWithAClassToday() {
        List<TutoringClass> monthClasses = tutoringClassRepository.getTutoringClassesByMonth(LocalDate.now().getMonthValue());
        List<Student> students = new ArrayList<>();
        for (TutoringClass tc : monthClasses) {
            List<TutoringSession> sesh = tutoringSessionService.getSessionsFromClass(tc);
            for(TutoringSession session : sesh) {
                if (session.getDay() == LocalDate.now().getDayOfMonth()) {
                    students.addAll(this.getUsersInClass(tc));
                }
            }
        }
        List<String> res = new ArrayList<>();
        for(Student student : students) {
            res.add(student.getEmail());
        }
        return res;
    }
}
