/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblusers;

import java.io.Serializable;

/**
 *
 * @author visug
 */
public class TblUsersDTO implements Serializable{
    private String userID;
    private String password;
    private String fullName;
    private boolean status;

    public TblUsersDTO() {
    }

    public TblUsersDTO(String userID, String fullName, boolean status) {
        this.userID = userID;
        this.fullName = fullName;
        this.status = status;
    }

    public TblUsersDTO(String userID, String password, String fullName, boolean status) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
