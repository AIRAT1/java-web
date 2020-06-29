package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getServerPort());//порт
        System.out.println(request.getParameter("myParameter"));//получение значения параметна если таковой присутствует
        HttpSession session = request.getSession();//получение сессии
        String path = request.getServletPath();
        if ("/".equals(path)) {
            request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
        } else if ("/welcome".equals(path)) {
            request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
