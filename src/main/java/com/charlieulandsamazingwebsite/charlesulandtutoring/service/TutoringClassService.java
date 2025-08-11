package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.TutoringClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutoringClassService {
    @Autowired
    private TutoringClassRepository tutoringClassRepository;
    @Autowired
    private TutoringSessionService tutoringSessionService;
    @Autowired
    private EnrollmentService enrollmentService;

    @Transactional
    public void addTutoringClass(TutoringClass tutoringClass) {
        tutoringClassRepository.save(tutoringClass);
    }
    public TutoringClass findById(int id) {
        return tutoringClassRepository.findTutoringClassById(id);
    }


    public List<List<String>> getThisMonthsClasses() {
        LocalDate now = LocalDate.now();
        List<TutoringClass> thisMonthsClasses = tutoringClassRepository.getTutoringClassesByMonth(now.getMonth().getValue());
        List<List<String>> res = new ArrayList<>();
        for(TutoringClass tc : thisMonthsClasses) {
            List<String> classInfo = new ArrayList<>();
            classInfo.add(tc.getSubject());
            classInfo.add(tc.getDay() + " @ " + tc.getTimeXtoX());
            classInfo.add(tc.getDates(tutoringSessionService));
            classInfo.add("$" + tc.getPricePerHour()/100 + "/hour");
            int spotsLeft = tc.getTotalSpace() - enrollmentService.getUsersInClass(tc).size();
            classInfo.add("Spots left: " + spotsLeft);
            res.add(classInfo);
        }


        return res;
    }
}
