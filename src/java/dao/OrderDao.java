/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Account;
import entities.AddressShip;
import entities.Order;
import entities.OrderDetails;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public int createOrder(Order order) throws SQLException {
        int orderId = -1;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO orders (total_price, order_date, order_time, account_id, address_id, description) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setFloat(1, order.getTotalPrice());
            pstmt.setDate(2, (Date) order.getOrderDate());
            pstmt.setTime(3, order.getOrderTime());
            pstmt.setInt(4, order.getAccountId());
            pstmt.setInt(5, order.getAddressId());
            pstmt.setString(6, order.getDescription());
            int rowsAffected = pstmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return orderId;
    }

    public List<Order> getAllOrdersByAccountId(int accountId) throws SQLException {
        List<Order> orders = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM orders WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, accountId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                float totalPrice = rs.getFloat("total_price");
                Date orderDate = rs.getDate("order_date");
                Time orderTime = rs.getTime("order_time");
                int addressId = rs.getInt("address_id");
                String description = rs.getString("description");

                Order order = new Order(orderId, totalPrice, orderDate, orderTime, accountId, addressId, description);
                ShippingAddressDao addressDao = new ShippingAddressDao();
                order.setAddressShip(addressDao.getAddressShipById(addressId));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return orders;
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orderList = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM orders";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                float totalPrice = rs.getFloat("total_price");
                Date orderDate = rs.getDate("order_date");
                Time orderTime = rs.getTime("order_time");
                int accountId = rs.getInt("account_id");
                int addressId = rs.getInt("address_id");
                String description = rs.getString("description");

                // Retrieve the associated address ship and account
                ShippingAddressDao addressDao = new ShippingAddressDao();
                AccountDao accountDao = new AccountDao();
                OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
                AddressShip addressShip = addressDao.getAddressShipById(addressId);
                Account account = accountDao.getAccountById(accountId);

                // Retrieve the order details for the current order
                List<OrderDetails> orderDetailsList = orderDetailsDao.getAllOrderDetailsByOrderId(orderId);

                // Create the Order object
                Order order = new Order(orderId, totalPrice, orderDate, orderTime, accountId, addressId, description);
                order.setAddressShip(addressShip);
                order.setOrderDetails(orderDetailsList);
                order.setAccount(account);

                // Add the order to the list
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return orderList;
    }

    public boolean deleteOrderByOrderId(int orderId) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM orders WHERE order_id = ?";
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
