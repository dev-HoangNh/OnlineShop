package controller;

import dao.AccountDao;
import dao.ShippingAddressDao;
import entities.Account;
import entities.AddressShip;
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
@WebServlet(name = "userShippingAddress", urlPatterns = {"/shippingAddress"})
public class UserShippingAddress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        ShippingAddressDao addressDao = new ShippingAddressDao();
        List<AddressShip> addressShips;
        try {
            addressShips = addressDao.getListAddressShip(account);
        } catch (Exception e) {
            e.printStackTrace();
            addressShips = new ArrayList<AddressShip>();
        }
        session.setAttribute("addressShips", addressShips);
        request.getRequestDispatcher("/views/userShippingAddress.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String addressName = request.getParameter("addressName");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ShippingAddressDao addressDao = new ShippingAddressDao();
        try {
            AddressShip address = new AddressShip(account.getAccountId(), addressName);
            boolean isUpdate = addressDao.createAddress(address);
            if (isUpdate) {
                response.sendRedirect("shippingAddress");
                return;
            }else{
                throw new Exception("Something wrong!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("shippingAddress");
        }
    }
}
