package com.charlieulandsamazingwebsite.charlesulandtutoring.model;

import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.TutoringSessionRepository;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.EmailService;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.EnrollmentService;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DailyEmailScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private TutoringSessionRepository tutoringSessionRepository;

    @Scheduled(cron = "0 0 8 * * ?", zone = "America/Chicago")
    @Scheduled(cron = "0 45 17 * * ?", zone = "America/Chicago")
    public void sendDailyEmails() throws IOException {

        Map<String, String> personalizationMap = new HashMap<>();
        List<TutoringSession> sessions = tutoringSessionRepository.findTutoringSessionsByMonthAndDay(LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        if(sessions == null || sessions.isEmpty()) {
            return;
        }
        personalizationMap.put("zoomLink", sessions.get(0).getZoomLink());
        personalizationMap.put("date", LocalDate.now().toString());
        personalizationMap.put("time", sessions.get(0).getTime() + " pm");
        personalizationMap.put("zoomPassword", sessions.get(0).getZoomPassword());
        personalizationMap.put("description", sessions.get(0).getDescription());
        // Example: hardcoded clients, but you can fetch from DB
        List<String> studentEmails = enrollmentService.getUsersWithAClassToday();
        Email from = new Email("classreminder@charlesulandtutoring.com", "Charles Uland");
        for (String client : studentEmails) {
            emailService.sendEmail(
                    new Email(client),
                    from,
                    "d-07186634273b474f956a3b1bbcc261d0",
                    personalizationMap
            );
        }
    }
}
