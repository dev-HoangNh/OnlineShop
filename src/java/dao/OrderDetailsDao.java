/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public boolean createOrderDetails(OrderDetails orderDetails) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO order_details (order_id, product_id, size_id, quantity, unit_price) "
                    + "VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderDetails.getOrderId());
            pstmt.setInt(2, orderDetails.getProductId());
            pstmt.setInt(3, orderDetails.getSizeId());
            pstmt.setInt(4, orderDetails.getQuantity());
            pstmt.setFloat(5, orderDetails.getUnitPrice());
            int rowsAffected = pstmt.executeUpdate();

            // Check if any rows were affected
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return success;
    }

    public List<OrderDetails> getAllOrderDetailsByOrderId(int orderId) throws SQLException {
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM order_details WHERE order_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderDetailsId = rs.getInt("order_details_id");
                int productId = rs.getInt("product_id");
                int sizeId = rs.getInt("size_id");
                int quantity = rs.getInt("quantity");
                float unitPrice = rs.getFloat("unit_price");
                ProductDao productDao = new ProductDao();
                SizeDao sizeDao = new SizeDao();
                OrderDetails orderDetails = new OrderDetails(orderDetailsId, orderId, productId, sizeId, quantity, unitPrice);
                orderDetails.setProduct(productDao.getProductByProductId(productId));
                orderDetails.setSize(sizeDao.getSizeById(sizeId));
                orderDetailsList.add(orderDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetailsList;
    }

    public boolean deleteOrderDetailsByOrderId(int orderId) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM order_details WHERE order_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderId);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }
}
