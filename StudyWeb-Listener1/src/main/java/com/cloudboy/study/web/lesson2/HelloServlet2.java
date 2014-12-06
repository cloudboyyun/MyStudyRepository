package com.cloudboy.study.web.lesson2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	    name="HelloServlet2",
	    urlPatterns={"/HelloServlet2"},
	    loadOnStartup=2)  
public class HelloServlet2 extends HttpServlet {
    private static final long serialVersionUID = -1451562973020646948L;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        String name = request.getParameter("name");
        
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("This is lesson 2.");
        out.println("You are welcome " + name);
        out.println("</body>");
        out.println("</html>");
    }
}
