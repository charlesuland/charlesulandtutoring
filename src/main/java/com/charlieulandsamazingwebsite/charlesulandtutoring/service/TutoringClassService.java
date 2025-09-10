package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
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
            classInfo.add(tc.getFontAwesomeClass());
            classInfo.add(tc.getSubject());
            classInfo.add(tc.getDay() + " @ " + tc.getTimeXtoX() + " CST");
            classInfo.add(tc.getDates(tutoringSessionService));
            classInfo.add("$" + tc.getPricePerHour() + "/hour");
            int spotsLeft = tc.getTotalSpace() - enrollmentService.getUsersInClass(tc).size() > 0 ? tc.getTotalSpace() - enrollmentService.getUsersInClass(tc).size() : 1;
            classInfo.add("Spots left: " + spotsLeft);
            classInfo.add(tutoringSessionService.getSessionDescriptions(tc));
            classInfo.add(String.valueOf(tc.getId()));
            res.add(classInfo);
        }


        return res;
    }

    public int getTotalPrice(TutoringClass tc) {
        List<TutoringSession> sessions = tutoringSessionService.getSessionsFromClass(tc);
        int cnt = 0;
        LocalDate now = LocalDate.now();
        for(TutoringSession sesh : sessions) {
            if(sesh.getDay() >= now.getDayOfMonth()) {
                cnt++;
            }
        }

        return tc.getPricePerHour() * cnt * tc.getDuration();
    }
}
