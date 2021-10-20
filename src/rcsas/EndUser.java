package rcsas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndUser {
    protected String ID;
    protected String dateCreated;
    protected String dateModified;
    protected String username;
    protected String password;
    protected String name;
    protected String phoneNo;
    protected String address;
    protected String email;

    public EndUser(String ID, String dateCreated, String dateModified, String username, String password, String name, String phoneNo, String address, String email) {
        this.ID = ID;
        this.name = name;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

 
    public EndUser() {
        
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
