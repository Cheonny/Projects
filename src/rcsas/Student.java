package rcsas;

import java.util.ArrayList;

public class Student extends EndUser{
    private int age;
    private String gender;
    private ArrayList<Payment> myPayment = new ArrayList<Payment>();
    private ArrayList<Register> myRegister = new ArrayList<Register>();

    public Student() {
    }

    public Student(int age, String gender){
        this.age = age;
        this.gender = gender;
    }

    public Student(int age, String gender, String ID, String dateCreated, String dateModified, String username, String password, String name, String phoneNo, String address, String email) {
        super(ID, dateCreated, dateModified, username, password, name, phoneNo, address, email);
        this.age = age;
        this.gender = gender;
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

    public ArrayList<Payment> getMyPayment() {
        return myPayment;
    }

    public void setMyPayment(ArrayList<Payment> myPayment) {
        this.myPayment = myPayment;
    }

    public ArrayList<Register> getMyRegister() {
        return myRegister;
    }

    public void setMyRegister(ArrayList<Register> myRegister) {
        this.myRegister = myRegister;
    }
    
    
}
