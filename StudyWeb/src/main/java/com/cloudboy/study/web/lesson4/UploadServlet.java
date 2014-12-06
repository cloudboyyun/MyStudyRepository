package com.cloudboy.study.web.lesson4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet(name = "UploadServlet", urlPatterns = { "/upload" })
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = -2160631987199218642L;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("file");
        PrintWriter out = response.getWriter();
        out.println("File size：" + part.getSize() + "<br />");
        out.println("File Type：" + part.getContentType() + "<br />");
        out.println("name：" + request.getParameter("name") + "<br />");
        out.println(UploadUtil.getFileName(part) + "<br />");
        // 把文件写入/tmp/1.xxx
        part.write("/tmp/1." + UploadUtil.getFileType(part));
    }
} 