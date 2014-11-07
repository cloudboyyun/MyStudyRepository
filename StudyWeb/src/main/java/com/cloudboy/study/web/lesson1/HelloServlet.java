package com.cloudboy.study.web.lesson1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	    name="HelloServlet",
	    urlPatterns={"/HelloServlet"},
	    loadOnStartup=1,
	    initParams={  
	            @WebInitParam(name="name",value="cloudboy"),  
	            @WebInitParam(name="age",value="20")
	    })  
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = -1451562973020646948L;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        ServletConfig config = getServletConfig();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Hello world" + "<br />");
        out.println(config.getInitParameter("name"));
        out.println("</body>");
        out.println("</html>");
    }
}
