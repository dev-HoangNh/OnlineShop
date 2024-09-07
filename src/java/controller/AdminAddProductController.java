package controller;

import dao.CategoryDao;
import dao.OriginDao;
import dao.ProductDao;
import entities.Account;
import entities.Category;
import entities.Origin;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet(name = "adminAddProduct", urlPatterns = {"/adminAddProduct"})
public class AdminAddProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getRole().equals("member")) {
            response.sendRedirect("login");
            return;
        }
        OriginDao originDao = new OriginDao();
        List<Origin> origins;
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categorys;
        try {
            origins = originDao.getAllOrigins();
            categorys = categoryDao.getAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
            origins = new ArrayList<Origin>();
            categorys = new ArrayList<Category>();
        }
        session.setAttribute("categories", categorys);
        session.setAttribute("origins", origins);
        request.getRequestDispatcher("/views/adminAddProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fruitName = request.getParameter("fruitName");
        String fruitcode = request.getParameter("fruitcode");
        String raw_status = request.getParameter("status");
        String raw_sale = request.getParameter("sale");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String raw_originID = request.getParameter("originID");
        String type = request.getParameter("type");
        String raw_categoryId = request.getParameter("categoryId");
        ProductDao productDao = new ProductDao();
        try {
            float sale = Float.parseFloat(raw_sale);
            int originId = Integer.parseInt(raw_originID);
            int categoryId = Integer.parseInt(raw_categoryId);
            boolean status = Boolean.parseBoolean(raw_status);
            Product product = new Product(fruitcode, status, originId, categoryId, type, fruitName, description, image, sale);
            int productId = productDao.createProduct(product);
            if (productId > 0) {
                response.sendRedirect("adminAddSize?id=" + productId);
                return;
            } else {
                throw new Exception("Something wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminAddProduct");
        }
    }
}
