package com.candy.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(value="/image/*", initParams = {
        @WebInitParam(name="ROOT_DIR", value="D:\\devstudy.net\\im-storage\\")
})
public class ShowPictureServlet extends HttpServlet {
    private String rootDir;
    @Override
    public void init() throws ServletException {
        rootDir = getServletConfig().getInitParameter("ROOT_DIR");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        String[] parts = req.getRequestURI().split("/");
        String fileName = parts[parts.length-1];
        File file = new File(rootDir + fileName);
        if(file.exists()) {
            try(InputStream in = new BufferedInputStream(new FileInputStream(file));
                OutputStream out = new BufferedOutputStream(resp.getOutputStream())) {
                int data = -1;
                while((data = in.read()) != -1) {
                    out.write(data);
                }
                out.flush();
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}