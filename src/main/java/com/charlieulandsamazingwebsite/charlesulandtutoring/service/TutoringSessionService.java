package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.TutoringSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutoringSessionService {
    @Autowired
    private TutoringSessionRepository tutoringSessionRepository;

    public void addTutoringSession(TutoringSession ts) {
        tutoringSessionRepository.save(ts);
    }

    public Iterable<TutoringSession> allMeetings() {
        return tutoringSessionRepository.findAll();
    }

    public List<TutoringSession> getSessionsFromClass(TutoringClass tutoringClass) {
        return tutoringSessionRepository.findAllByTutoringClass(tutoringClass);
    }

    public void saveAll(List<TutoringSession> submittedSessions) {
        tutoringSessionRepository.saveAll(submittedSessions);
    }
    public TutoringSession findById(int id) {
        return tutoringSessionRepository.findById(id);
    }

    public void save(TutoringSession ts) {
        tutoringSessionRepository.save(ts);
    }

    public String getSessionDescriptions(TutoringClass tc) {
        List<TutoringSession> sessions = getSessionsFromClass(tc);
        String res = "";
        for(TutoringSession session : sessions) {
            res += session.getDescription() +"\n";
        }
        return res.substring(0, res.length()-1);
    }
}
