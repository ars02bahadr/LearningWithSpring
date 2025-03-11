package com.mycompany.learning.Infrastructure.Mail;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class SendGridService {

 // application.properties dosyasından API key al
    private String sendGridApiKey="YOUR_API_KEY";

    public String sendEmail(String fromEmail, String fromName, String toEmail, String toName, String subject, String plainText, String htmlContent) {
        if (sendGridApiKey == null || sendGridApiKey.isEmpty()) {
            return "SendGrid API Key is not configured.";
        }

        Email from = new Email(fromEmail, fromName);
        Email to = new Email(toEmail, toName);
        Content content = new Content("text/html", htmlContent);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            return "Email sent successfully. Status code: " + response.getStatusCode();
        } catch (IOException ex) {
            return "Error while sending email: " + ex.getMessage();
        }
    }
}

