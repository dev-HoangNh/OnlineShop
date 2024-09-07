/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDao;
import entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDao productDao = new ProductDao();
        List<Product> products;
        List<Product> topSelling;
        try {
            products = productDao.getFourProductsWithLargestSale();
            topSelling = productDao.getFourBestSellingProducts();
        } catch (Exception e) {
            products = new ArrayList<Product>();
            topSelling = new ArrayList<Product>();
            e.printStackTrace();
        }
        session.setAttribute("productFlashSale", products);
        session.setAttribute("productTopSelling", topSelling);
        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
