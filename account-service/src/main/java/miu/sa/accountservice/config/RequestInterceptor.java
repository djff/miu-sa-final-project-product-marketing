package miu.sa.accountservice.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import miu.sa.accountservice.config.security.TokenUtil;
import miu.sa.accountservice.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtil util;
    private Gson g = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //add validation code
        String token = request.getHeader("TOKEN") != null ? request.getHeader("TOKEN") : "";
        if (util.validateToken(token))
            return true;
        //false
        ResponseModel r = new ResponseModel();
        r.setSuccess(false);
        r.setMessage("Invalid service token");
        r.setData(new JsonObject());

        PrintWriter out = response.getWriter();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(g.toJson(r));
        out.flush();
        return false;
    }
}
