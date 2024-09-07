package controller;

import dao.OriginDao;
import entities.Account;
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

@WebServlet(name = "adminAddOrigin", urlPatterns = {"/adminAddOrigin"})
public class AdminAddOriginController extends HttpServlet{
    
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
         try {
             origins = originDao.getAllOrigins();
         } catch (Exception e) {
             e.printStackTrace();
             origins = new ArrayList<Origin>();
         }
         session.setAttribute("origins", origins);
        request.getRequestDispatcher("/views/adminAddOrigin.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
         String originName = request.getParameter("origin");
         OriginDao originDao = new OriginDao();
         try {
            boolean isCreate = originDao.createOrigin(originName);
            if(isCreate) {
                response.sendRedirect("adminAddOrigin");
                return;
            }else{
                throw new Exception("Something wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("adminAddOrigin");
        }
    }
}
