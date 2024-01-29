package com.nhnacademy.javaservletjsp.Chapter03.Servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;

@Slf4j
@WebServlet(name="frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 공통 처리 - 응답 content-type, character encoding 지정
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try{
            //실제 요청 처리할 servlet 결정
            String servletPath = resolveServlet(req.getServletPath());
            //실제 요청을 처리할 Servlet으로 요청을 전달하여 처리 결과를 include 시킴
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            //실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해줌
            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url:{}", view.substring(REDIRECT_PREFIX.length()+1));
                //todo 'redirect:'로 시작하면 redirect 처리
                System.out.println(view.substring(REDIRECT_PREFIX.length()+1));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));
            }else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 시킴
                rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch (Exception ex) {
            //todo 공통 error 처리 - ErrorServlet 참고해서 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));

            //todo forward - /error.jsp
            RequestDispatcher rd = req.getRequestDispatcher("/student/error.jsp");
            rd.forward(req, resp);
        }
    }

    private String resolveServlet(String servletPath) {
        String processingServlet = null;

        //todo 실행할 servlet 결정하기
        if("/student/list.do".equals(servletPath)) {
            processingServlet = "/student/list";
        } else if ("/student/register.do".equals(servletPath)) {
            processingServlet = "/student/register";
        } else if ("/student/delete.do".equals(servletPath)) {
            processingServlet = "/student/delete";
        } else if ("/student/update.do".equals(servletPath)) {
            processingServlet = "/student/update";
        } else if ("/student/view.do".equals(servletPath)) {
            processingServlet = "/student/view";
        } else if ("/error.do".equals(servletPath)) {
            processingServlet = "/error";
        }


        return processingServlet;
    }
}
