package rcsas;


public class Register {
    private String registerID;
    private String feedback;
    private int rating;
    private Schedule scheduleID;
    private Student studentID;

    public Register() {
    }   

    public Register(String registerID, String feedback, int rating, Schedule scheduleID, Student studentID) {
        this.registerID = registerID;
        this.feedback = feedback;
        this.rating = rating;
        this.scheduleID = scheduleID;
        this.studentID = studentID;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Schedule getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Schedule scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }


    
}
