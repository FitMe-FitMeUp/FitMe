package com.ootd.fitme.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class MDCLoggingInterceptor implements HandlerInterceptor {

    public static final String MDC_REQUEST_ID = "requestId";
    public static final String MDC_CLIENT_IP = "clientIp";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String requestId = MDC.get(MDC_REQUEST_ID);
        String clientIp = MDC.get(MDC_CLIENT_IP);

        log.info("Request: method {} uri {} query {} request-id {} client-id {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                requestId,
                clientIp
        );

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
    }
}
