
package rcsas;

public class Location {
    private String locationID;
    private String dateCreated;
    private String dateModified;
    private String sportCenterName;
    private String sportLocationName;
    private String sportName;

    public Location(String locationID, String dateCreated, String dateModified, String sportCenterName, String sportLocationName, String sportName) {
        this.locationID = locationID;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.sportCenterName = sportCenterName;
        this.sportLocationName = sportLocationName;
        this.sportName = sportName;
    }

    public Location() {

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

    public String getSportLocationName() {
        return sportLocationName;
    }

    public void setSportLocationName(String sportLocationName) {
        this.sportLocationName = sportLocationName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String LocationID) {
        this.locationID = LocationID;
    }

    public String getSportCenterName() {
        return sportCenterName;
    }

    public void setSportCenterName(String SportCenterName) {
        this.sportCenterName = SportCenterName;
    }
}
