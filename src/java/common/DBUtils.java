/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    private static DBUtils instance;
    private Connection con;

    private DBUtils() {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=fruit;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "123";

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static DBUtils getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBUtils();
        }
        return instance;
    }

    public static void ReleaseJDBC(ResultSet rs, Statement stm, Connection con) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

}
