package com.nhnacademy.javaservletjsp.Chapter03.Servlet;

import com.nhnacademy.javaservletjsp.Chapter03.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name="studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        studentRepository = (StudentRepository) servletConfig.getServletContext().getAttribute("studentRepository");
        log.info("StudentDeleteServlet | StudentRepository 초기화 완료");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo get parameter : id, id가 존재하지 않을 경우 throw new RuntimeException("...")
        String id = req.getParameter("id");

        if(Objects.isNull(studentRepository.getStudentById(id))) {
            throw new RuntimeException("아이디가 없습니다");
        }

        studentRepository.deleteById(id);

        //todo /student/list <-- redirect
        resp.sendRedirect("/student/list");
    }
}
