package controller;

import dao.CategoryDao;
import dao.OriginDao;
import dao.ProductDao;
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
@WebServlet(name = "storeController", urlPatterns = {"/store"})
public class StoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1; // Default page is 1
        int itemsPerPage = 3; // Number of items to display per page

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        HttpSession session = request.getSession();
        String type = null;
        if (request.getParameter("type") != null) {
            type = request.getParameter("type");
        }
        ProductDao productDao = new ProductDao();
        List<Product> products;
        List<Category> categories;
        List<Origin> origins;
        CategoryDao categoryDao = new CategoryDao();
        OriginDao originDao = new OriginDao();
        int categoryId = 0;
        int originId = 0;
        float min = -1, max = -1;
        int sort = 0;
        if (request.getParameter("category") != null) {
            String raw_category = request.getParameter("category");
            categoryId = Integer.parseInt(raw_category);
        }
        if (request.getParameter("origin") != null) {
            String raw_origin = request.getParameter("origin");
            originId = Integer.parseInt(raw_origin);
        }
        if (request.getParameter("min") != null && request.getParameter("max") != null) {
            String raw_min = request.getParameter("min");
            String raw_max = request.getParameter("max");
            min = Float.parseFloat(raw_min);
            max = Float.parseFloat(raw_max);
        }
        if (request.getParameter("sort") != null) {
            String raw_sort = request.getParameter("sort");
            sort = Integer.parseInt(raw_sort);
            if (sort < 1 || sort > 5) {
                response.sendRedirect("store");
                return;
            }
        }
        int totalProduct = 0;
        try {
            if (categoryId > 0) {
                products = productDao.getProductByCategoryId(categoryId);
            } else if (originId > 0) {
                products = productDao.getProductByOriginId(originId);
            } else if (min > -1 && max > -1) {
                products = productDao.getAllProductByPriceRange(min, max);
            } else if (sort == 1) {
                products = productDao.getAllProductSortedByPrice(true);
            } else if (sort == 2) {
                products = productDao.getAllProductSortedByPrice(false);
            } else if (sort == 3) {
                products = productDao.getAllProductSortedByName(true);
            } else if (sort == 4) {
                products = productDao.getAllProductSortedByName(false);
            } else if (sort == 5) {
                products = productDao.getAllBestSellingProducts();
            } else {
                products = productDao.getAllProductByTypePagin(type, currentPage, itemsPerPage);
            }
            totalProduct = productDao.getAllProductByType(type).size();
            categories = categoryDao.getAllCategory();
            origins = originDao.getAllOrigins();
            session.setAttribute("totalProductSize", productDao.getAllProducts().size());
        } catch (Exception e) {
            e.printStackTrace();
            products = new ArrayList<>();
            categories = new ArrayList<>();
            origins = new ArrayList<>();
        }
        
        int totalItems = totalProduct; // Total number of items before pagination and filtering
        int totalPageCount = (int) Math.ceil((double) totalItems / itemsPerPage); // Total number of pages

        // Set the pagination attributes in the session
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("totalPageCount", totalPageCount);
        session.setAttribute("storeProducts", products);
        session.setAttribute("storeCategories", categories);
        session.setAttribute("storeOrigins", origins);
        request.getRequestDispatcher("/views/store.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search.isEmpty()) {
            response.sendRedirect("store");
            return;
        }
        HttpSession session = request.getSession();
        ProductDao productDao = new ProductDao();
        List<Product> products;
        List<Category> categorys;
        List<Origin> origins;
        CategoryDao categoryDao = new CategoryDao();
        OriginDao originDao = new OriginDao();
        try {
            products = productDao.searchProductByName(search);
            categorys = categoryDao.getAllCategory();
            origins = originDao.getAllOrigins();
        } catch (Exception e) {
            e.printStackTrace();
            products = new ArrayList<Product>();
            categorys = new ArrayList<Category>();
            origins = new ArrayList<Origin>();
        }
        session.setAttribute("storeProducts", products);
        session.setAttribute("storeCategories", categorys);
        session.setAttribute("storeOrigins", origins);
        request.getRequestDispatcher("/views/store.jsp").forward(request, response);
    }
}
