/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SizeDao {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public List<Size> getAllSizesByProductId(int productId) throws SQLException {
        List<Size> sizes = new ArrayList<>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM size WHERE product_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int sizeId = rs.getInt("size_id");
                float price = rs.getFloat("price");
                String sizeName = rs.getString("size_name");

                Size size = new Size(sizeId, productId, price, sizeName);
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return sizes;
    }

    public boolean createSize(Size size) throws SQLException {
        boolean success = false;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO size (product_id, price, size_name) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, size.getProductId());
            pstmt.setFloat(2, size.getPrice());
            pstmt.setString(3, size.getSizeName());

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }

    public boolean deleteSizeById(int sizeId) throws SQLException {
        boolean success = false;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM size WHERE size_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sizeId);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }
    
    public List<Size> getAllSizes(int productId) throws SQLException {
        List<Size> sizes = new ArrayList<>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM size WHERE product_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int sizeId = rs.getInt("size_id");
                float price = rs.getFloat("price");
                String sizeName = rs.getString("size_name");

                Size size = new Size(sizeId, productId, price, sizeName);
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }
    
    public Size getSizeById(int sizeid) throws SQLException {
        Size size = null;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM size WHERE size_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sizeid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int sizeId = rs.getInt("size_id");
                float price = rs.getFloat("price");
                String sizeName = rs.getString("size_name");
                int productId = rs.getInt("product_id");

                size = new Size(sizeId, productId, price, sizeName);
                return size;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }
}
