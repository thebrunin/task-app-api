package com.task.api.config.logs.access;

import com.task.api.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
@Service
public class AccessLogInterceptor implements HandlerInterceptor {
    @Autowired
    private AccessLogRepository accessLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        request.setAttribute("init", LocalDateTime.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AccessLog log = new AccessLog();
        LocalDateTime init = (LocalDateTime) request.getAttribute("init");
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(init, now);

        log.setDate(new Date());
        log.setSecondsDuration(duration.getSeconds());
        log.setEndpoint(request.getServletPath());

        if(auth != null && auth.getPrincipal() != null)
        {
            User user = auth.getPrincipal() instanceof User ? (User) auth.getPrincipal() : null;
            if(user != null)
            {
                log.setUserId(user != null ? user.getId() : null);
            }
        }

        this.accessLogRepository.save(log);
    }
}
