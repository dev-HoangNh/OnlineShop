/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Origin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OriginDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public boolean createOrigin(String name) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO origin (name) VALUES (?)";
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

    public List<Origin> getAllOrigins() throws SQLException {
        List<Origin> origins = new ArrayList<>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM origin";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Origin origin = new Origin(id, name);
                origins.add(origin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return origins;
    }

    public boolean deleteOriginById(int id) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM origin WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }
    
    public Origin getOriginById(int originId) throws SQLException {
        Origin origin = null;
        try {
            con = DBUtils.getInstance().getConnection();
            String sql = "SELECT * FROM origin WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, originId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String originName = rs.getString("name");
                origin = new Origin(originId, originName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return origin;
    }
}
