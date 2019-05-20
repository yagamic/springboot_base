package com.yagamic.base.appliaction.web.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagamic.base.appliaction.web.spring.security.model.LoginResult;
import com.yagamic.base.domain.account.AccountService;
import com.yagamic.base.domain.model.User;
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
    @Autowired
    AccountService accountService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        User user = (User) authentication.getPrincipal();
        String currentAuthority = user.getAdmin() ? "admin" : "customer";

        LoginResult result = new LoginResult("ok", currentAuthority, request.getSession().getId());
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(mapper.writeValueAsString(result));
        out.flush();
        out.close();

    }
}