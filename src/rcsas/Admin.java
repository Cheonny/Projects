package rcsas;

import java.time.LocalDateTime;

public class Admin extends EndUser{
    private boolean isHQ;
    private String sportCenterName;

    public Admin(String sportCenterName, boolean isHQ, String ID, String dateCreated, String dateModified, String username, String password, String name, String phoneNo, String address, String email) {
        super(ID, dateCreated, dateModified, username, password, name, phoneNo, address, email);
        this.sportCenterName = sportCenterName;
        this.isHQ = isHQ;    }

    public boolean isIsHQ() {
        return isHQ;
    }

    public void setIsHQ(boolean isHQ) {
        this.isHQ = isHQ;
    }
    
    public boolean getIsHQ() {
        return isHQ;
    }
    
    public String getSportCenterName() {
        return sportCenterName;
    }

    public void setSportCenterName(String sportCenterName) {
        this.sportCenterName = sportCenterName;
    }

    
}
