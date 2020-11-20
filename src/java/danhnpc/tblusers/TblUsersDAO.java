/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblusers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelpers;


public class TblUsersDAO implements Serializable{
    
    //private TblUsersDTO user;
    private String fullname;

    public String getFullname() {
        return fullname;
    }
    
    private boolean role;

    public boolean isRole() {
        return role;
    }
    
    public boolean checkLogin(String username, String password) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select userID, fullName, status "
                        + "From tblUsers "
                        + "Where userID = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                     fullname = rs.getString("fullName");
                     role = rs.getBoolean("status");
                    //boolean role = rs.getBoolean("status");
                    //user = new TblUsersDTO(username, fullName, role);
                    return true;
                }
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    
    List<TblUsersDTO> listUser;

    public List<TblUsersDTO> getListUser() {
        return listUser;
    }
    
    public void searchFullName(String searchValue)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Select userID,password,fullName,status "
                        + "From tblUsers "
                        + "Where fullName Like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");

                rs = ps.executeQuery();
                
                while(rs.next()){
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    boolean status = rs.getBoolean("status");
                    TblUsersDTO dto = new TblUsersDTO(userID, password, fullName, status);
                    if(this.listUser == null){
                        this.listUser = new ArrayList<>();
                    }
                    this.listUser.add(dto);
                }
                
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }                 
    }
    
    public boolean deleteAccount(String username)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Delete From tblUsers "
                        + "Where userID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                
                int row = ps.executeUpdate();
                
                if(row > 0){
                    return true;
                }
            }
        }finally{
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean updateAccount(String userID, String password, boolean status)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "Update tblUsers Set "
                        + "password=?, status=? "
                        + "Where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, password);
                ps.setBoolean(2, status);
                ps.setString(3, userID);
                
                int row = ps.executeUpdate();
                
                if(row > 0){
                    return true;
                }
            }
        }finally{
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean createAcoount(String userID, String password, String fullname, boolean status)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = DBHelpers.makeConnection();
            if(con != null){
                String sql = "INSERT INTO tblUsers(userID,password,fullName,status) "
                        + "Values(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, password);
                ps.setString(3, fullname);
                ps.setBoolean(4, status);
                
                int row = ps.executeUpdate();
                
                if(row > 0){
                    return true;
                }
            }
        }finally{
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
}
