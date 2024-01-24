package com.nhnacademy.javaservletjsp.Chapter02.n3Listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CounterFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // CounterFilter를 이용해 모든 Servlet요청에 대해서 counter 1씩 증가한 다음
        // 변경된 counter값은 ServletContext에 저장함
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest,servletResponse);
        log.error("counter:{}",servletRequest.getServletContext().getAttribute("counter"));
    }
}
