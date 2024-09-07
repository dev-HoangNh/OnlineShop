/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Account;
import entities.AddressShip;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShippingAddressDao {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public List<AddressShip> getListAddressShip(Account a) throws SQLException {
        List<AddressShip> list = new ArrayList<AddressShip>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM address_ship WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, a.getAccountId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                AddressShip addressShip = new AddressShip();
                addressShip.setAddressId(rs.getInt(1));
                addressShip.setAccountId(rs.getInt(2));
                addressShip.setAddressName(rs.getString(3));
                list.add(addressShip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }
        return list;
    }

    public boolean createAddress(AddressShip address) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO address_ship (account_id, address_name) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, address.getAccountId());
            pstmt.setString(2, address.getAddressName());

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }

    public int createAddressReturnId(AddressShip address) throws SQLException {
        int addressId = -1;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO address_ship (account_id, address_name) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, address.getAccountId());
            pstmt.setString(2, address.getAddressName());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    addressId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return addressId;
    }

    public boolean deleteAddress(int addressId) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "DELETE FROM address_ship WHERE address_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, addressId);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }

    public AddressShip getAddressShipById(int addressId) throws SQLException {
        AddressShip addressShip = null;
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM address_ship WHERE address_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, addressId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int accountId = rs.getInt("account_id");
                String addressName = rs.getString("address_name");

                addressShip = new AddressShip(addressId, accountId, addressName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressShip;
    }
}
