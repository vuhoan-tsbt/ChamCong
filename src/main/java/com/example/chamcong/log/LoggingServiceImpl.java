package com.example.chamcong.log;

import com.example.chamcong.utils.DateUtils;
import com.example.chamcong.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("REQUEST ");
        stringBuilder.append(DateUtils.getDate());
        stringBuilder.append("  UnixId=").append(MDC.get("UnixId"));
        stringBuilder.append("  method=[  ").append(httpServletRequest.getMethod()).append("  ] ");
        stringBuilder.append("  path=[ ").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("       ").append("body=[" + JsonUtils.objectToJson(body) + "]");
        log.info(stringBuilder.toString());
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RESPONSE ");
        stringBuilder.append(DateUtils.getDate());
        stringBuilder.append("  UnixId=").append(MDC.get("UnixId"));
        stringBuilder.append("  method =[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("  path = [").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("       ").append("body=[" + JsonUtils.objectToJson(body) + "]");
        log.info(stringBuilder.toString());
    }
}
