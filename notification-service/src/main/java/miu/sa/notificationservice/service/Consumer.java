package miu.sa.notificationservice.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import miu.sa.notificationservice.model.EmailTemplate;
import miu.sa.notificationservice.utils.BasicUtil;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    private final EmailSenderService emailSenderService;

    public Consumer(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
    //consume topics and execute tasks
    @KafkaListener(topics="notification-topic", groupId="group_id")
    public void consumeFromOrder(String message) {
        log.info("Consumed message from Order service => " + message);
        JSONObject r = new JSONObject(message);
        EmailTemplate template = new EmailTemplate();
        template.setSubject(r.optString("subject"));
        template.setSendTo(r.optString("email"));
        template.setBody(BasicUtil.notifyCustomerBody(r.optString("message")));
        emailSenderService.sendSimpleEmailWithHtml(template);
    }

}
