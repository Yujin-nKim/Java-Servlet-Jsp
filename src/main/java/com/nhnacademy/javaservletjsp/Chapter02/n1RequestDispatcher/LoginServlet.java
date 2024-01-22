package com.nhnacademy.javaservletjsp.Chapter02.n1RequestDispatcher;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

// day02/1.RequestDispatcher/실습01-로그인&로그아웃
@Slf4j
public class LoginServlet extends HttpServlet {
    private String initParamId;
    private String initParamPwd;

    @Override
    public void init(ServletConfig config) {
        // web.xml의 <servlet>-<init-param>의 paramter값을 가져와서 id와 password를 초기화
        initParamId = config.getInitParameter("id");
        initParamPwd = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // session이 있으면 가져오고 없으면 null
        HttpSession session = req.getSession(false);
        // 세션이 없으면 login.html로 이동
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
            resp.sendRedirect("/login2.html");
        // 세션이 있으면 로그인 성공이라는 텍스트와 세션에 있는 로그인한 아이디 출력
        }else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            try(PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                    out.println("<head>");
                        out.println("<meta charset='utf-8'>");
                    out.println("</head>");
                    out.println("<body>");
                        out.println("login success : id = " + session.getAttribute("id") + "<br/>");
                        out.println("<");
                    out.println("</body>");
                out.println("</html>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        // parameter로 전달된 id, password
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        // paramter로 전달된 id, password와 <init-param>에 설정된 아이디, 비밀번호 대조 확인
        if(initParamId.equals(id) && initParamPwd.equals(pwd)) {
            //session 있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();
            // 세션에 로그인한 아이디를 attribute로 등록
            session.setAttribute("id", id);
            // /login으로 이동
            resp.sendRedirect("/login2");
        // 틀린 경우 /login.html로 이동
        }else {
            // 로그인 실패시 response.sendRedirect() -> rd.forward()로 변경
            log.error("아이디/패스워드가 일치하지 않습니다.");
            //resp.sendRedirect("/login2.html");
            RequestDispatcher rd = req.getRequestDispatcher("/login2.html");
            rd.forward(req, resp);
            log.error("id : {}", id);
        }

    }
}
