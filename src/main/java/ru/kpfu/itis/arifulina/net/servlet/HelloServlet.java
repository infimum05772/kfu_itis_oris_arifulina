package ru.kpfu.itis.arifulina.net.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doResponse(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doResponse(req);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doResponse(req);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doResponse(req);
    }

    private void doResponse(HttpServletRequest req) throws IOException {
        System.out.println("'''START'''");
        System.out.println();

        System.out.println("PARAMETERS: ");
        req.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));
        System.out.println();

        String reqBody = req.getReader().lines().collect(Collectors.joining());
        System.out.println("REQUEST BODY: " + reqBody);
        System.out.println();

        System.out.println("REQUEST HEADERS:");
        Iterator<String> iter = req.getHeaderNames().asIterator();
        while (iter.hasNext()) {
            String headerName = iter.next();
            System.out.println(headerName + ": " + req.getHeader(headerName));
        }
        System.out.println();

        System.out.println("'''END'''");
    }
}
