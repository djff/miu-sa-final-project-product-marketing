package miu.sa.notificationservice;

import miu.sa.notificationservice.model.EmailTemplate;
import miu.sa.notificationservice.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class NotificationServiceApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    @Autowired
    private EmailSenderService emailService;

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);

    @Test
    public void shouldSendSingleMail() throws MessagingException, IOException {
        EmailTemplate mail = new EmailTemplate();
//        mail.setFrom("no-reply@memorynotfound.com");
        mail.setSendTo("edenajibade@gmail.com");
        mail.setSubject("Spring Mail Integration Testing with JUnit and GreenMail Example");
        mail.setBody("We show how to write Integration Tests using Spring and GreenMail.");

        emailService.sendSimpleEmail(mail);

        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertEquals(1, receivedMessages.length);

        MimeMessage current = receivedMessages[0];

        assertEquals(mail.getSubject(), current.getSubject());
        assertEquals(mail.getSendTo(), current.getAllRecipients()[0].toString());
        assertTrue(String.valueOf(current.getContent()).contains(mail.getBody()));

    }

}

