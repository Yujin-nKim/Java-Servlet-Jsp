package com.nhnacademy.javaservletjsp.Chapter03.Controller_step3;

import com.nhnacademy.javaservletjsp.Chapter03.Gender;
import com.nhnacademy.javaservletjsp.Chapter03.Student;
import com.nhnacademy.javaservletjsp.Chapter03.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentUpdateController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("studentId");
        String name = req.getParameter("studentName");
        String gender = req.getParameter("gender");
        String age = req.getParameter("studentAge");

        int student_age = 0;
        try {
            student_age = Integer.parseInt(age);
        }catch (Exception e) {
            resp.sendError(400, "student age must be integer");
        }

        Student student = new Student(id, name, Gender.valueOf(gender), student_age);
        studentRepository.update(student);

        return "redirect:/student/view?id="+id;
    }
}
