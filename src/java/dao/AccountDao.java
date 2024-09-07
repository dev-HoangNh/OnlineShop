/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import common.DBUtils;
import entities.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private ResultSet rs = null;

    public void register(Account account) throws SQLException {
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "INSERT INTO account (email, user_name, password, register_date, role) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, account.getEmail());
            pstmt.setString(2, account.getUserName());
            pstmt.setString(3, account.getPassword());
            pstmt.setDate(4, Date.valueOf(LocalDate.now()));
            pstmt.setString(5, "member");
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }
    }

    public Account login(String email, String password) throws SQLException {
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM account WHERE email = ? and password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setEmail(rs.getString(2));
                account.setUserName(rs.getString(3));
                account.setPassword(rs.getString(4));
                account.setAddress(rs.getString(5));
                account.setFullName(rs.getString(6));
                account.setPhoneNumber(rs.getString(7));
                account.setRole(rs.getString(8));
                account.setGender(rs.getString(9));
                account.setRegisterDate(rs.getDate(10));
                return account;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }
        return null;
    }

    public Account getAccountById(int accountId) throws SQLException {
        Account account = null;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM account WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, accountId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setEmail(rs.getString(2));
                account.setUserName(rs.getString(3));
                account.setPassword(rs.getString(4));
                account.setAddress(rs.getString(5));
                account.setFullName(rs.getString(6));
                account.setPhoneNumber(rs.getString(7));
                account.setRole(rs.getString(8));
                account.setGender(rs.getString(9));
                account.setRegisterDate(rs.getDate(10));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
    
    public Account getAccountByEmail(String email) throws SQLException {
        Account account = null;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM account WHERE email = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt(1));
                account.setEmail(rs.getString(2));
                account.setUserName(rs.getString(3));
                account.setPassword(rs.getString(4));
                account.setAddress(rs.getString(5));
                account.setFullName(rs.getString(6));
                account.setPhoneNumber(rs.getString(7));
                account.setRole(rs.getString(8));
                account.setGender(rs.getString(9));
                account.setRegisterDate(rs.getDate(10));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return account;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException {
        // Assuming you have a database connection established

        // Prepare the SQL query
        sql = "SELECT COUNT(*) FROM account WHERE email = ?";
        con = DBUtils.getInstance().getConnection();
        pstmt = con.prepareStatement(sql);
        try {
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return false;
    }

    public Account updateInfor(Account account) throws SQLException {
        sql = "UPDATE account SET user_name = ?, full_name = ?, phone_number = ?, gender = ?, address = ? WHERE email = ?";
        con = DBUtils.getInstance().getConnection();
        pstmt = con.prepareStatement(sql);
        try {
            pstmt.setString(1, account.getUserName());
            pstmt.setString(2, account.getFullName());
            pstmt.setString(3, account.getPhoneNumber());
            pstmt.setString(4, account.getGender());
            pstmt.setString(5, account.getAddress());
            pstmt.setString(6, account.getEmail());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }
        return null;
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try {
            con = DBUtils.getInstance().getConnection();
            sql = "SELECT * FROM account WHERE email <> 'admin@gmail.com'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int accountId = rs.getInt("account_id");
                String email = rs.getString("email");
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String fullName = rs.getString("full_name");
                String phoneNumber = rs.getString("phone_number");
                String role = rs.getString("role");
                String gender = rs.getString("gender");
                Date registerDate = rs.getDate("register_date");

                Account account = new Account(accountId, email, userName, password, address, fullName, phoneNumber, role, gender, registerDate);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(rs, pstmt, con);
        }

        return accounts;
    }

    public boolean updateAccountSetFullName(int accountId, String fullName) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "UPDATE account SET full_name = ? WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setInt(2, accountId);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.ReleaseJDBC(null, pstmt, con);
        }

        return success;
    }

    public boolean updateAccountSetPhoneNumber(int accountId, String phoneNumber) throws SQLException {
        boolean success = false;

        try {
            con = DBUtils.getInstance().getConnection();
            sql = "UPDATE account SET phone_number = ? WHERE account_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            pstmt.setInt(2, accountId);

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
