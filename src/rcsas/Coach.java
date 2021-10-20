package rcsas;

import java.time.LocalDateTime;

public class Coach extends EndUser{
    private int age;
    private String gender;
    private double hrRate;
    private double avgRating;
    private String sportCenterName; 
    private String sportName;
    private boolean isTerminated;
    
    public Coach(int age, String gender, double hrRate, double avgRating, String SportCenterName, String sportName, String ID, String dateCreated, String dateModified, String username, String password, String name, String phoneNo, String address, String email, boolean isTerminated) {
        super(ID, dateCreated, dateModified, username, password, name, phoneNo, address, email);
        this.age = age;
        this.gender = gender;
        this.hrRate = hrRate;
        this.avgRating = avgRating;
        this.sportCenterName = SportCenterName;
        this.sportName = sportName;
        this.isTerminated = isTerminated;
    }
    
    
    public Coach() {
    }

    public boolean isIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHrRate() {
        return hrRate;
    }

    public void setHrRate(double hrRate) {
        this.hrRate = hrRate;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public String getSportCenterName() {
        return sportCenterName;
    }

    public void setSportCenterName(String SportCenterName) {
        this.sportCenterName = SportCenterName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String SportName) {
        this.sportName = SportName;
    }

}
