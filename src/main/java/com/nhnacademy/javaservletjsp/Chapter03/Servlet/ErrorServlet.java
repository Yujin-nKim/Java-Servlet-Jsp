package com.nhnacademy.javaservletjsp.Chapter03.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;

@WebServlet(name="errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));

        //todo exception_type
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));

        //todo message
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));

        //todo exception
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));

        //todo request_uri
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        //todo /error.jsp forward
        RequestDispatcher rd = req.getRequestDispatcher("/student/error.jsp");
        rd.forward(req, resp);

    }
}