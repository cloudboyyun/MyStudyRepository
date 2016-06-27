package com.cloudboy.cloudboyyun;

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
	            @WebInitParam(name="spaceName",value="cloudboy city"),  
	            @WebInitParam(name="copyRight",value="cloudboy.org")
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
        String name = request.getParameter("name");
        
        ServletConfig config = getServletConfig();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("Welcome to " + config.getInitParameter("spaceName") + "<br />");
        out.println("This space belongs to " + config.getInitParameter("copyRight") + "<br />");
        out.println("You are welcome " + name);
        out.println("</body>");
        out.println("</html>");
    }
}
