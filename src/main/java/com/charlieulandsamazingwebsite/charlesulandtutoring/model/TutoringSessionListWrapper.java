package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import java.util.ArrayList;
import java.util.List;

public class TutoringSessionListWrapper {
    private List<TutoringSession> sessions;

    public TutoringSessionListWrapper() {
        this.sessions = new ArrayList<>();
    }

    public TutoringSessionListWrapper(List<TutoringSession> sessions) {
        this.sessions = sessions;
    }

    public List<TutoringSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TutoringSession> sessions) {
        this.sessions = sessions;
    }
}
