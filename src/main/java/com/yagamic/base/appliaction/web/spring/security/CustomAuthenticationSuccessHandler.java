package com.yagamic.base.appliaction.web.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagamic.base.appliaction.web.spring.security.model.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RedisOperationsSessionRepository sessionRepository;




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {



        LoginResult result = new LoginResult("", request.getSession().getId());
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(mapper.writeValueAsString(result));
        out.flush();
        out.close();

    }
}