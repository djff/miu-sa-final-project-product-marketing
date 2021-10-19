package miu.sa.notificationservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class BasicUtil {

    public static void main(String[] args) throws Exception {
        System.out.println(notifyCustomerBody("formatted message"));
    }

    public static String notifyCustomerBody(String message) {
        String data = "";
        try {
            Resource resource = new ClassPathResource("email.html");
            InputStream inputStream = resource.getInputStream();

            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
//            log.info(data);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("********Oops Something went wrong **********" + e);
        }
        return data != null ? data.replace("[message]", message) : message;
    }

}
