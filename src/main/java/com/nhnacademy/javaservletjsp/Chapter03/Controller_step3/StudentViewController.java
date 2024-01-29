package com.nhnacademy.javaservletjsp.Chapter03.Controller_step3;

import com.nhnacademy.javaservletjsp.Chapter03.Student;
import com.nhnacademy.javaservletjsp.Chapter03.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentViewController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");

        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student", student);

        return "/student/view.jsp";
    }
}
