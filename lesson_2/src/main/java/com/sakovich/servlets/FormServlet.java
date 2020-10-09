package com.sakovich.servlets;


import com.sakovich.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        String name = request.getParameter("UserName");

        if (name == null) {
            if (UserDao.findByLogin(login, password) == 0) {
                getServletContext().getRequestDispatcher("/register.html").forward(request, response);
            } else if (UserDao.findByLogin(login, password) == 2){
                try {
                    request.setAttribute("login", login);
                    getServletContext().getRequestDispatcher("/hello.jsp").forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            } else if (UserDao.findByLogin(login, password) == 1) {
                response.sendRedirect(request.getContextPath() + "/index.html");
            }

        } else {
            if (UserDao.addAccount(login, password, name)) {
                request.setAttribute("login", login);
                getServletContext().getRequestDispatcher("/hello.jsp").forward(request, response);
            }
        }


    }
}
