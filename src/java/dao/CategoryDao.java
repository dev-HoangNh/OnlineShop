/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public boolean createCategory(String name) throws SQLException {
        boolean success = false;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO category (name) VALUES (?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }

    public List<Category> getAllCategory() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM category";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int categoryId = rs.getInt("id");
                String categoryName = rs.getString("name");

                Category category = new Category(categoryId, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return categories;
    }

    public boolean deleteCategoryById(int categoryId) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM category WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, categoryId);

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

