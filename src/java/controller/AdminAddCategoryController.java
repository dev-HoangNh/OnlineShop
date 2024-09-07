package controller;

import dao.CategoryDao;
import dao.OriginDao;
import entities.Account;
import entities.Category;
import entities.Origin;
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

@WebServlet(name = "adminAddCategory", urlPatterns = {"/adminAddCategory"})
public class AdminAddCategoryController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null || account.getRole().equals("member")) {
            response.sendRedirect("login");
            return;
        }
         CategoryDao categoryDao = new CategoryDao();
         List<Category> categorys;
         try {
             categorys = categoryDao.getAllCategory();
         } catch (Exception e) {
              e.printStackTrace();
              categorys = new ArrayList<Category>();
         }
         session.setAttribute("categories", categorys);
        request.getRequestDispatcher("/views/adminAddCategory.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("category");
        CategoryDao categoryDao = new CategoryDao();
        try {
            boolean isCreate = categoryDao.createCategory(categoryName);
            if (isCreate) {
                response.sendRedirect("adminAddCategory");
                return;
            } else {
                throw new Exception("Something wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminAddCategory");
        }
    }
}
