package com.candy.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//http://localhost:8080/admin?param1=admin&param2=admin
@WebServlet(value="/admin", initParams = {@WebInitParam(name="IP", value="0:0:0:0:0:1"),
        @WebInitParam(name = "ACCESSKEY",value = "330"),
        @WebInitParam(name = "login",value = "admin"), @WebInitParam(name = "password",value = "adin")
})

public class AdminServlet extends HttpServlet {
    private String ip;
    private String accesskey;
    private String login;
    private String password;
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public AdminServlet() {
    }

    @Override
    public void init() throws ServletException {
        ip = getServletConfig().getInitParameter("IP");
        accesskey = getServletConfig().getInitParameter("ACCESSKEY");
        login = getServletConfig().getInitParameter("login");
        password = getServletConfig().getInitParameter("password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String param1 = req.getParameter("param1");
        String param2 = req.getParameter("param2");
        if (ip.equals(req.getRemoteAddr())) {
            System.out.println("Login via ip:" + req.getRemoteAddr());
            out.println("OK");
        } else if (req.getHeaders("via").equals(accesskey)) {
            System.out.println("Login via accessKey:" + req.getHeader("via"));
            out.println("OK");
        } else if (param1.equals(login) && param2.equals(password)) {
            System.out.println("Login via login and password:" + login + " " + password);
            out.println("OK");
        } else {out.println("Failed");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("Login via ip:" + req.getRemoteAddr());
            System.out.println("Login via accessKey:" + req.getHeader("via"));
            System.out.println("Login via login and password:" + login + " " + password);
        }
    }
}

