package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.Student;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.StudentRepository;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.TutoringClassRepository;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.EmailService;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.EnrollmentService;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.OrderService;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringClassService;
import com.sendgrid.helpers.mail.objects.Email;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StripeController {
    @Autowired
    private TutoringClassService tutoringClassService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TutoringClassRepository tutoringClassRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmailService emailService;
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    @Value("${stripe.webhook.key}")
    private String stripeWebhookKey;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, Object>> createCheckoutSession(@RequestParam Long classId) throws StripeException {
        TutoringClass tClass = tutoringClassService.findById((Integer.parseInt(classId.toString())));

        Stripe.apiKey = stripeSecretKey; // Load from env in real use

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", List.of("card"));
        params.put("mode", "payment");

        // Success & cancel URLs (adjust to your domain)
        params.put("success_url", "/payment-success");
        params.put("cancel_url", "/payment-failure");

        // Product/price info
        params.put("line_items", List.of(Map.of(
                "price_data", Map.of(
                        "currency", "usd",
                        "unit_amount", tutoringClassService.getTotalPrice(tClass) * 100, // price in cents
                        "product_data", Map.of("name", tClass.getSubject())
                ),
                "quantity", 1
        )));

        // Attach your custom data
        params.put("metadata", Map.of(
                "classId", classId.toString()
        ));

        Session session = Session.create(params);

        return ResponseEntity.status(303)
                .header("Location", session.getUrl())
                .build();
    }

    @PostMapping("/api/stripe-webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
                                                      @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;


        try {
            // Pass the raw payload and signature to constructEvent
            event = Webhook.constructEvent(payload, sigHeader, stripeWebhookKey);
        } catch (SignatureVerificationException e) {
            // Log the error for debugging, but don't expose it to the client
            System.err.println("Webhook signature verification failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        // Handle the event
        if ("checkout.session.completed".equals(event.getType())) {
            try {
                // Extract session ID from the raw payload
                JSONObject json = new JSONObject(payload);
                String sessionId = json.getJSONObject("data").getJSONObject("object").getString("id");

                // Fetch full session from Stripe API with expanded fields
                Session session = Session.retrieve(
                        sessionId
                );

                // Now safely access metadata + email
                String classId = session.getMetadata().get("classId");
                String userEmail = session.getCustomerDetails() != null
                        ? session.getCustomerDetails().getEmail()
                        : null;
                Optional<Student> optionalStudent = studentRepository.findStudentByEmail(userEmail);
                Student student;
                if(optionalStudent.isPresent()) {
                    student = optionalStudent.get();
                } else {
                    student = new Student(userEmail);
                    studentRepository.save(student);
                }

                TutoringClass tutoringClass = tutoringClassRepository.findTutoringClassById(Integer.parseInt(classId));

                enrollmentService.addEnrollment(tutoringClass, student);

                orderService.addOrder(tutoringClass, student, session.getAmountTotal());
                emailService.sendEmail(new Email(userEmail), new Email("charlesuland@charlesulandtutoring.com", "Charles Uland"), "d-c3037470e3a84bf3af10ad296b69862b", Map.of("subject", tutoringClass.getSubject(), "day", tutoringClass.getDay() + "s", "time", tutoringClass.getTime() + tutoringClass.getTimeSuffix()));

            } catch (StripeException e) {

                return ResponseEntity.badRequest().body("Failed to retrieve session");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/payment-success")
    public String success(Model model) {
        model.addAttribute("pageTitle", "Success");
        model.addAttribute("view", "payment-success");
        model.addAttribute("pageStyles", "/css/payment.css");

        return "layout";

    }

    @GetMapping("/payment-failure")
    public String failure(Model model) {
        model.addAttribute("pageTitle", "Failure");
        model.addAttribute("view", "payment-failure");
        model.addAttribute("pageStyles", "/css/payment.css");

        return "layout";

    }


}
