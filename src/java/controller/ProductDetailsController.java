package controller;

import dao.CartDao;
import dao.ProductDao;
import entities.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(name = "productDetails", urlPatterns = {"/productDetails"})
public class ProductDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        int id = -1;
        if (request.getParameter("id") != null) {
            String raw_id = request.getParameter("id");
            id = Integer.parseInt(raw_id);
        }
        if (id > 0) {
            ProductDao productDao = new ProductDao();
            boolean checkIdExist = false;
            try {
                checkIdExist = productDao.checkProductIdExist(id);
                if (checkIdExist == false) {
                    response.sendRedirect("store");
                    return;
                } else {
                    session.setAttribute("productShowDetails", productDao.getProductByProductId(id));
                    session.setAttribute("youMayLike", productDao.getSixBestSellingProducts());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                response.sendRedirect("store");
                return;
            }
        }
        request.getRequestDispatcher("/views/productDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String raw_size = request.getParameter("sizeId");
        String raw_quantity = request.getParameter("quantity");
        String raw_product = request.getParameter("productId");
        CartDao cartDao = new CartDao();
        try {
            int sizeId = Integer.parseInt(raw_size);
            int quantity = Integer.parseInt(raw_quantity);
            int productId = Integer.parseInt(raw_product);
            Account account = (Account) session.getAttribute("account");
            
            int cartId = cartDao.createCart(account.getAccountId(), quantity, productId, sizeId);
            if (cartId > 0) {
                response.sendRedirect("cart");
                return;
            } else {
                throw new Exception("Something wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("store");
        }
    }
}

