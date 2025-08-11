package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.AppUser;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Enrollment;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<AppUser> getUsersInClass(TutoringClass tc) {
        List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsByTutoringClass(tc);
        List<AppUser> res = new ArrayList<>();
        for(Enrollment e : enrollments) {
            res.add(e.getUser());
        }

        return res;
    }
}
