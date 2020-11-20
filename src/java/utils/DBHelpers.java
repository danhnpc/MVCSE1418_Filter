/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author visug
 */
public class DBHelpers implements Serializable {

    public static Connection makeConnection()
            throws SQLException, NamingException {
        
        Context context = new InitialContext();
        Context tomCatCtx = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomCatCtx.lookup("SE1418DS");
        Connection con = ds.getConnection();
        return con;
        
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=ItemManagement;InstanceName=STEVEN/SQLEXPRESS";
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        return con;
    }
    
}
