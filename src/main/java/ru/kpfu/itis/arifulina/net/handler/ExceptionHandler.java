package ru.kpfu.itis.arifulina.net.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handle")
public class ExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleException(req, resp);
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");

        request.setAttribute("statusCode", code);
        request.setAttribute("uri", uri == null ? "" : uri);
        if (code == 500) {
            request.setAttribute("message", throwable.getMessage());
        }

        request.getRequestDispatcher("exception.ftl").forward(request, response);
    }
}
