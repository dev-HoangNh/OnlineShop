package controller;

import dao.ProductDao;
import dao.SizeDao;
import entities.Account;
import entities.Product;
import entities.Size;
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
@WebServlet(name = "adminAddSize", urlPatterns = {"/adminAddSize"})
public class AdminAddSizeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getRole().equals("member")) {
            response.sendRedirect("login");
            return;
        }
        List<Product> products;
        ProductDao productDao = new ProductDao();
        try {
            products = productDao.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            products = new ArrayList<Product>();
        }
        session.setAttribute("products", products);
        if (request.getParameter("id") != null) {
            String raw_product_id = request.getParameter("id");
            int productId = Integer.parseInt(raw_product_id);
            boolean isProductIdExist = false;
            try {
                isProductIdExist = productDao.findProductId(productId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isProductIdExist) {
                SizeDao sizeDao = new SizeDao();
                List<Size> sizes;
                try {
                    sizes = sizeDao.getAllSizesByProductId(productId);
                } catch (Exception e) {
                    e.printStackTrace();
                    sizes = new ArrayList<Size>();
                }
                session.setAttribute("sizes", sizes);
            } else {
                response.sendRedirect("adminAddSize");
                return;
            }
        }
        request.getRequestDispatcher("/views/adminAddSize.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sizeName = request.getParameter("size");
        String raw_price = request.getParameter("price");
        String raw_id = request.getParameter("id");
        int id;
        float price;
        SizeDao sizeDao = new SizeDao();

        try {
            if (raw_price == null || raw_price.isEmpty() || raw_id == null || raw_id.isEmpty()) {
                throw new Exception("Invalid price or id value");
            }

            price = Float.parseFloat(raw_price);
            id = Integer.parseInt(raw_id);
            Size size = new Size(id, price, sizeName);
            boolean isCreate = sizeDao.createSize(size);

            if (isCreate) {
                response.sendRedirect("adminAddSize?id=" + id);
                return;
            } else {
                throw new Exception("Something went wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminAddSize");
        }
    }

}
