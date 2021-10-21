package miu.sa.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    private String sendTo;
    private String subject;
    private String body;
}
