/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblproducts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author visug
 */
public class TblProductsDAO implements Serializable {

    Map<String, Integer> products;

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void getProductIsExist(String title)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select quantity From tblCarts "
                        + "Where title=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, title);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    if (this.products == null) {
                        this.products = new HashMap<>();
                    }
                    products.put(title, quantity);
                    //System.out.println("List" + products.toString());
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insertProduct(String title, int quantity)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblCarts(title,quantity) "
                        + "Values(?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setInt(2, quantity);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateProduct(String title, int quantity)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update tblCarts Set quantity=? Where title=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, title);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
