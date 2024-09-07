/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public int createCart(int accountId, int quantity, int productId, int sizeId) throws SQLException {
        int cartId = -1;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO cart (account_id, quantity, product_id, size_id) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, productId);
            pstmt.setInt(4, sizeId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    cartId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }
        return cartId;
    }

    public List<Cart> getAllCartByAccountId(int accountId) throws SQLException {
        List<Cart> carts = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM cart WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, accountId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int cartId = rs.getInt("cart_id");
                int quantity = rs.getInt("quantity");
                int productId = rs.getInt("product_id");
                int sizeId = rs.getInt("size_id");

                // Create a Cart object
                Cart cart = new Cart(cartId, accountId, quantity, productId, sizeId);
                ProductDao productDao = new ProductDao();
                SizeDao sizeDao = new SizeDao();
                cart.setProduct(productDao.getProductByProductId(productId));
                cart.setSize(sizeDao.getSizeById(sizeId));
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return carts;
    }

    public boolean deleteCartById(int cartId) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM cart WHERE cart_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, cartId);
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

    public boolean deleteAllCartsByAccountId(int accountId) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM cart WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, accountId);
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

}
