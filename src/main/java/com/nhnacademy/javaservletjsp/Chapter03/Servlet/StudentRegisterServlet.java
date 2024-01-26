package com.nhnacademy.javaservletjsp.Chapter03.Servlet;

import com.nhnacademy.javaservletjsp.Chapter03.Student;
import com.nhnacademy.javaservletjsp.Chapter03.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(name="studentListServlet", urlPatterns = "/student/list")
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.info("Servlet | StudentRepository 초기화 완료");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // student list 구하기
        List<Student> studentList = studentRepository.getStudents();

        req.setAttribute("studentList", studentList);

        // /student/list.jsp <- forward 하기
        RequestDispatcher rd = req.getRequestDispatcher("/student/list.jsp");
        rd.forward(req, resp);
    }
}
