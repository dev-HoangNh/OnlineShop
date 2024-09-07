package controller;

import dao.AccountDao;
import entities.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(name = "registerController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("errorMessageRegister", null);
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = new Account(email, username, password);
        AccountDao accountDao = new AccountDao();
        HttpSession session = request.getSession();
        session.setAttribute("errorMessageRegister", null);
        try {
            boolean isDuplicateEmail = accountDao.checkDuplicateEmail(email);
            if (isDuplicateEmail) {
                // Forward the request to the register.jsp page with an error message
                session.setAttribute("errorMessageRegister", "Email already exists. Please choose a different email.");
                request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            } else {
                session.setAttribute("errorMessageRegister", null);
                accountDao.register(account);
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            session.setAttribute("errorMessageRegister", "Something wrong! Register failed.");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}
