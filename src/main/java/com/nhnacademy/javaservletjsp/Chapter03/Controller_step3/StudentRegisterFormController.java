package com.nhnacademy.javaservletjsp.Chapter03.Controller_step3;

import com.nhnacademy.javaservletjsp.Chapter03.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentRegisterFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
