package com.nhnacademy.javaservletjsp.Chapter03.Servlet;

import com.nhnacademy.javaservletjsp.Chapter03.Gender;
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
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.info("StudentRegisterServlet | StudentRepository 초기화 완료");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo /student/register.jsp forward 하기
        //RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        //rd.forward(req, resp);
        //todo view attribute 설정 /student/register.jsp
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("studentId");
        String name = req.getParameter("studentName");
        String gender = req.getParameter("gender");
        String age = req.getParameter("studentAge");

        //todo null 체크
        if(Objects.isNull(id)) {
            resp.sendError(400, "id is needed");
        }
        if(Objects.isNull(name)) {
            resp.sendError(400, "student name is needed");
        }
        if(Objects.isNull(gender)) {
            resp.sendError(400, "student gender is needed");
        }
        if(Objects.isNull(req.getParameter("studentAge"))) {
            resp.sendError(400, "student age is needed");
        }
        int student_age = 0;
        try {
            student_age = Integer.parseInt(age);
        }catch (Exception e) {
            resp.sendError(400, "student age must be integer");
        }

        //todo save 구현
        Student student = new Student(id, name, Gender.valueOf(gender), student_age);
        studentRepository.save(student);
        log.info("새로운 student 등록 완료");

        //todo redirect /student/view?id=student1
        //resp.sendRedirect("/student/view?id="+id);
        //todo redirect view attribute 설정
        //req.setAttribute("view", "redirect:/student/view.do?id="+id);
        req.setAttribute("view", "redirect:/student/view.do?id="+id);
    }
}
