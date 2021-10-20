package rcsas;

public class Payment {
    private String ID;
    private String paymentDate;
    private double sportFees;
    private double paymentAmount;
    private double paymentBalance;
    private Register registerID;
    private Student studentID;

    public Payment() {
    }

    public Payment(String ID, String paymentDate, double sportFees, double paymentAmount, double paymentBalance, Register registerID, Student studentID) {
        this.ID = ID;
        this.paymentDate = paymentDate;
        this.sportFees = sportFees;
        this.paymentAmount = paymentAmount;
        this.paymentBalance = paymentBalance;
        this.registerID = registerID;
        this.studentID = studentID;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getSportFees() {
        return sportFees;
    }

    public void setSportFees(double sportFees) {
        this.sportFees = sportFees;
    }

    public double getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(double paymentBalance) {
        this.paymentBalance = paymentBalance;
    }

    public Register getRegisterID() {
        return registerID;
    }

    public void setRegisterID(Register registerID) {
        this.registerID = registerID;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }
    
    


    
}
