package rcsas;

import java.util.ArrayList;

public class Schedule {
    private String ID;
    private String createdDate;
    private String modifiedDate;
    private String scheduleDate;
    private String sportCenterName;
    private String sportName;
    private String sportLocation;
    private String startTime;
    private String endTime;
    private String coachName;
    private int sportPax;
    private double sportFees;
    private String availability; 

    public Schedule() {
    }

    public Schedule(String ID, String scheduleDate, String sportCenterName, String sportName, String sportLocation, String startTime, String endTime, String coachName, int sportPax, double sportFees, String availability) {
        this.ID = ID;
        this.scheduleDate = scheduleDate;
        this.sportCenterName = sportCenterName;
        this.sportName = sportName;
        this.sportLocation = sportLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coachName = coachName;
        this.sportPax = sportPax;
        this.sportFees = sportFees;
        this.availability = availability;
    }

    public Schedule(String ID, String createdDate, String modifiedDate, String scheduleDate, String sportCenterName, String sportName, String sportLocation, String startTime, String endTime, String coachName, int sportPax, String availability) {
        this.ID = ID;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.scheduleDate = scheduleDate;
        this.sportCenterName = sportCenterName;
        this.sportName = sportName;
        this.sportLocation = sportLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coachName = coachName;
        this.sportPax = sportPax;
        this.availability = availability;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    } 

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportLocation() {
        return sportLocation;
    }

    public void setSportLocation(String sportLocation) {
        this.sportLocation = sportLocation;
    }

    public String getSportCenterName() {
        return sportCenterName;
    }

    public void setSportCenterName(String sportCenterName) {
        this.sportCenterName = sportCenterName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getSportPax() {
        return sportPax;
    }

    public void setSportPax(int sportPax) {
        this.sportPax = sportPax;
    }

    public double getSportFees() {
        return sportFees;
    }

    public void setSportFees(double sportFees) {
        this.sportFees = sportFees;
    }
    
    

//    public ArrayList<Register> getRegister() {
//        return register;
//    }
//
//    public void setRegister(ArrayList<Register> register) {
//        this.register = register;
//    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
