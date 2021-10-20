package rcsas;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sport {
    private String ID;
    private String dateCreated;
    private String dateModified;
    private String sportName;
    private double sportFee;
    private String sportCenterName;

    public Sport(String ID, String dateCreated, String dateModified, String sportName, double sportFee, String sportCenterName) {
        this.ID = ID;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.sportName = sportName;
        this.sportFee = sportFee;
        this.sportCenterName = sportCenterName;
    }
    public Sport(){
    
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public double getSportFee() {
        return sportFee;
    }

    public void setSportFee(double sportFee) {
        this.sportFee = sportFee;
    }

    public String getSportCenterName() {
        return sportCenterName;
    }

    public void setSportCenterName(String SportCenterName) {
        this.sportCenterName = SportCenterName;
    }
    
}
