package controller;

import dao.CartDao;
import entities.Account;
import entities.Cart;
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

@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class CartController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
         CartDao cartDao = new CartDao();
         List<Cart> carts;
         try {
             carts = cartDao.getAllCartByAccountId(account.getAccountId());
         } catch (Exception e) {
             e.printStackTrace();
             carts = new ArrayList<Cart>();
         }
        session.setAttribute("cartProducts", carts);
        float total = 0;
        for(Cart cart : carts){
            float price = cart.getSize().getPrice() - cart.getSize().getPrice() * cart.getProduct().getSale() / 100;
            total += price * cart.getQuantity();
        }
        session.setAttribute("totalPrice", total);
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
