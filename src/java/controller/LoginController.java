package controller;

import dao.AccountDao;
import dao.CartDao;
import entities.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("errorMessageLogin", null);
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccountDao accountDao = new AccountDao();
        HttpSession session = request.getSession();
        session.setAttribute("errorMessageLogin", null);
        try {
            Account account = accountDao.login(email, password);
            if (account == null) {
                session.setAttribute("errorMessageLogin", "Something wrong! Login failed.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            } else {
                session.setAttribute("errorMessageLogin", null);
                session.setAttribute("account", account);
                CartDao cartDao = new CartDao();
                session.setAttribute("cartProducts", cartDao.getAllCartByAccountId(account.getAccountId()));
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessageLogin", "Something wrong! Login failed.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
