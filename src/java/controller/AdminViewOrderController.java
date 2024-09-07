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

@WebServlet(name = "adminViewOrder", urlPatterns = {"/adminViewOrder"})
public class AdminViewOrderController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getRole().equals("member")) {
            response.sendRedirect("login");
            return;
        }
        List<Order> orders;
        OrderDao orderDao = new OrderDao();
        try {
            orders = orderDao.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            orders = new ArrayList<Order>();
        }
        session.setAttribute("listOrderAdmin", orders);
        request.getRequestDispatcher("/views/adminViewOrder.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
