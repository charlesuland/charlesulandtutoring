package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.TutoringSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
