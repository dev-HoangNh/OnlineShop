package controller;

import dao.OrderDao;
import entities.Account;
import entities.Order;
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
@WebServlet(name = "userViewOrder", urlPatterns = {"/viewOrder"})
public class UserViewOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        List<Order> orders;
        OrderDao orderDao = new OrderDao();
        try {
            orders = orderDao.getAllOrdersByAccountId(account.getAccountId());
        } catch (Exception e) {
            e.printStackTrace();
            orders = new ArrayList<Order>();
        }
        session.setAttribute("listOrderAccount", orders);
        request.getRequestDispatcher("/views/userViewOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
