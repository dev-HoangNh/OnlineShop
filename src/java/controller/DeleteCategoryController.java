package controller;

import dao.CategoryDao;
import dao.OriginDao;
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

@WebServlet(name = "deleteCategory", urlPatterns = {"/deleteCategory"})
public class DeleteCategoryController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(raw_id);
            CategoryDao categoryDao = new CategoryDao();
            categoryDao.deleteCategoryById(id);
            response.sendRedirect("adminAddCategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
