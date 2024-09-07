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
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

@WebServlet(name = "adminViewMember", urlPatterns = {"/adminViewMember"})
public class AdminViewMemberController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getRole().equals("member")) {
            response.sendRedirect("login");
            return;
        }
         AccountDao accountDao = new AccountDao();
         List<Account> accounts;
         try {
             accounts = accountDao.getAllAccounts();
             session.setAttribute("listAccounts", accounts);
         } catch (Exception e) {
             e.printStackTrace();
             accounts = new ArrayList<Account>();
         }
        request.getRequestDispatcher("/views/adminViewMember.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
