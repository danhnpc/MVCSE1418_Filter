/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblitems;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;

/**
 *
 * @author ASUS
 */
public class TblItemsDAO implements Serializable {

    private List<String> item;

    public List<String> getItem() {
        return item;
    }

    public void loadItem() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select title From tblItems";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                String title = "";
                while (rs.next()) {
                    title = rs.getString("title");
                    if (this.item == null) {
                        this.item = new ArrayList<>();
                    }
                    this.item.add(title);
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
}
