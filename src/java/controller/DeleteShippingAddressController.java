package controller;

import dao.ShippingAddressDao;
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

@WebServlet(name = "deleteShippingAddress", urlPatterns = {"/deleteShippingAddress"})
public class DeleteShippingAddressController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(raw_id);
            ShippingAddressDao addressDao = new ShippingAddressDao();
            addressDao.deleteAddress(id);
            response.sendRedirect("shippingAddress");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
