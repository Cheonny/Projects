package rcsas;

public class SportCenter {
    private String ID;
    private String dateCreated;
    private String dateModified;
    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private String openTime; //HH:mm
    private String closeTime;

    public SportCenter(String ID, String dateCreated, String dateModified, String name, String phoneNo, String address, String email, String openTime, String closeTime) {
        this.ID = ID;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    public SportCenter(){
    
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
    
}
