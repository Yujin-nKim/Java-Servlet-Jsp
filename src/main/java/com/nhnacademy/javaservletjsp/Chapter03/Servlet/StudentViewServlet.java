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
import java.util.Objects;

@Slf4j
@WebServlet(name="studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.info("StudentViewServlet | StudentRepository 초기화 완료");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        //todo id null check
        if(Objects.isNull(id)) {
            resp.sendError(400, "id is null");
        }

        //todo student 조회
        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student", student);

        //todo /student/view.jsp <-- forward
        //RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
        //rd.forward(req, resp);
        //todo view attribute 설정 - /student/view.jsp
        req.setAttribute("view", "/student/view.jsp");
    }
}
