package controller;

import dao.OrderDao;
import dao.OrderDetailsDao;
import dao.OriginDao;
import dao.SizeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

@WebServlet(name = "deleteOrder", urlPatterns = {"/deleteOrder"})
public class DeleteOrderController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(raw_id);
            OrderDao orderDao = new OrderDao();
            OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
            
            orderDetailsDao.deleteOrderDetailsByOrderId(id);
            orderDao.deleteOrderByOrderId(id);
            response.sendRedirect("viewOrder");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
