package miu.sa.accountservice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    @Autowired
    private Environment env;

    public Boolean validateToken(String token) {
        final String secret = env.getProperty("S2S_ACCOUNT_SERVICE");
        return secret != null ? secret.equals(token) : false;
    }
}
