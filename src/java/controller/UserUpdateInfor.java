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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(name = "userUpdateInfor", urlPatterns = {"/updateInfor"})
public class UserUpdateInfor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        request.getRequestDispatcher("/views/userUpdateInfor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String fullName = request.getParameter("fullName");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        account.setUserName(username);
        account.setFullName(fullName);
        account.setPhoneNumber(phoneNumber);
        account.setGender(gender);
        account.setAddress(address);
        AccountDao accountDao = new AccountDao();
        try {
            Account updateAccount = accountDao.updateInfor(account);
            account = accountDao.getAccountByEmail(updateAccount.getEmail());
            if (updateAccount != null) {
                session.setAttribute("account", account);
                response.sendRedirect("viewInfor");
            }else{
                throw new Exception("Something wrong!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("updateInfor");
        }
    }
}
