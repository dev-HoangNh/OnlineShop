package controller;

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

@WebServlet(name = "deleteSize", urlPatterns = {"/deleteSize"})
public class DeleteSizeController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        String raw_product_id = request.getParameter("productId");
        int id, productId;
        try {
            id = Integer.parseInt(raw_id);
            productId = Integer.parseInt(raw_product_id);
            SizeDao sizeDao = new SizeDao();
            sizeDao.deleteSizeById(id);
            response.sendRedirect("adminAddSize?id=" + productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
