package controller;

import dao.AccountDao;
import dao.CartDao;
import dao.OrderDao;
import dao.OrderDetailsDao;
import dao.ShippingAddressDao;
import entities.Account;
import entities.AddressShip;
import entities.Cart;
import entities.Order;
import entities.OrderDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(name = "checkout", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        List<Cart> listCarts = (List<Cart>) session.getAttribute("cartProducts");
        if (listCarts.size() == 0) {
            response.sendRedirect("cart");
            return;
        }
        List<AddressShip> addressShips;
        ShippingAddressDao addressDao = new ShippingAddressDao();
        try {
            addressShips = addressDao.getListAddressShip(account);
        } catch (Exception e) {
            e.printStackTrace();
            addressShips = new ArrayList<AddressShip>();
        }
        session.setAttribute("addressCheckout", addressShips);
        request.getRequestDispatcher("/views/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        float totalPrice = (float) session.getAttribute("totalPrice");
        Account account = (Account) session.getAttribute("account");
        String fullname = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        if (address1.isEmpty() && address2.isEmpty()) {
            response.sendRedirect("checkout");
            return;
        }
        ShippingAddressDao addressDao = new ShippingAddressDao();
        int addressId = -1;
        if (Integer.parseInt(address1) > 0) {
            addressId = Integer.parseInt(address1);
        } else if (!address2.isEmpty()) {
            AddressShip address = new AddressShip(account.getAccountId(), address2);
            try {
                addressId = addressDao.createAddressReturnId(address);
            } catch (SQLException ex) {
                ex.printStackTrace();
                response.sendRedirect("checkout");
                return;
            }
        } else {
            response.sendRedirect("checkout");
            return;
        }
        OrderDao orderDao = new OrderDao();
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        AccountDao accountDao = new AccountDao();
        CartDao cartDao = new CartDao();
        int orderId = -1;
        try {
            Order order = new Order(totalPrice, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), account.getAccountId(), addressId, null);
            orderId = orderDao.createOrder(order);
            accountDao.updateAccountSetFullName(account.getAccountId(), fullname);
            accountDao.updateAccountSetPhoneNumber(account.getAccountId(), phoneNumber);
            account = accountDao.getAccountById(account.getAccountId());
            List<Cart> listCarts = (List<Cart>) session.getAttribute("cartProducts");
            for (Cart cart : listCarts) {
                float unitPrice = cart.getQuantity() * (cart.getSize().getPrice() - cart.getSize().getPrice() * cart.getProduct().getSale() / 100);
                OrderDetails details = new OrderDetails(orderId, cart.getProductId(), cart.getSizeId(), cart.getQuantity(), unitPrice);
                orderDetailsDao.createOrderDetails(details);
            }
            cartDao.deleteAllCartsByAccountId(account.getAccountId());
            session.setAttribute("cartProducts", cartDao.getAllCartByAccountId(account.getAccountId()));
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendRedirect("checkout");
            return;
        }
        session.setAttribute("account", account);
        response.sendRedirect("viewOrder");
    }
}
