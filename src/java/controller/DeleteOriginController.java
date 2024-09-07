package controller;

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

@WebServlet(name = "deleteOrigin", urlPatterns = {"/deleteOrigin"})
public class DeleteOriginController extends HttpServlet{
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(raw_id);
            OriginDao originDao = new OriginDao();
            originDao.deleteOriginById(id);
            response.sendRedirect("adminAddOrigin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
