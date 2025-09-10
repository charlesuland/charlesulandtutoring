package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;



    public void sendEmail(Email to, Email from, String templateId, Map<String, String> personalizationMap) {



        Mail mail = new Mail();
        mail.setFrom(from);

        mail.setTemplateId(templateId);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        for (String key : personalizationMap.keySet()) {
            personalization.addDynamicTemplateData(key, personalizationMap.get(key));
        }
        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(sendgridApiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            // 🔍 Log the real SendGrid response
            System.out.println("SendGrid Response Status: " + response.getStatusCode());
            System.out.println("SendGrid Response Body: " + response.getBody());
            System.out.println("SendGrid Response Headers: " + response.getHeaders());

            if (response.getStatusCode() == 202) {
                System.out.println("✅ Email successfully queued by SendGrid");
            } else {
                System.out.println("⚠️ Email not accepted by SendGrid");
            }
        } catch (IOException e) {
            System.out.println("Email error");
        }
    }
}
