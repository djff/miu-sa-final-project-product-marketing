package miu.sa.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import miu.sa.notificationservice.model.EmailTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailSenderService {

    @Value("${email.from}")
    private String emailFrom;

    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(EmailTemplate template) {
        log.info("sendSimpleEmail Request => " + template);
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(emailFrom);
            msg.setTo(template.getSendTo());
            msg.setText(template.getBody());
            msg.setSubject(template.getSubject());
            mailSender.send(msg);
            log.info("Mail Sent...");
        } catch (MailException e) {
            log.error("sendSimpleEmail Exception => " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendSimpleEmailWithHtml(EmailTemplate template) {
        log.info("sendSimpleEmailWithHtml Request => " + template);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(emailFrom);
            helper.setTo(template.getSendTo());
            // use the true flag to indicate the text included is HTML
            helper.setText(template.getBody(), true);
            helper.setSubject(template.getSubject());
            mailSender.send(message);
            log.info("Mail Sent...");
        } catch (Exception e) {
            log.error("sendSimpleEmailWithHtml Exception => " + e.getMessage());
            e.printStackTrace();
        }
    }

}
