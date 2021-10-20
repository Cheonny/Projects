/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class RCSAS {
    
    public static ArrayList<Student> allStudent = new ArrayList<Student>();
    public static ArrayList<Admin> allAdmin = new ArrayList<Admin>();
    public static ArrayList<Sport> allSport = new ArrayList<Sport>();
    public static ArrayList<Coach> allCoach = new ArrayList<Coach>();
    public static ArrayList<SportCenter> allSportCenter = new ArrayList<SportCenter>();
    public static ArrayList<Schedule> allSchedule = new ArrayList<Schedule>();
    public static ArrayList<Register> allRegister = new ArrayList<Register>();
    public static ArrayList<Location> allLocation = new ArrayList<Location>();
    public static ArrayList<Payment> allPayment = new ArrayList<Payment>();
    
    public static MainPageForm mainpage;
    public static LoginForm loginpage;
    public static SportDetailsForm sportdetails;
    
    public static EndUser user = new EndUser();
    public static Validation validation = new Validation();
    public static EndUser current;

    public static AdminStudentForm adminStudent;
    public static SignUpForm signup;
    public static StudentMainForm studentMain;
    public static AdminMainForm adminMain;
    public static AdminMainForm2 adminMain2;
    public static AdminScheduleForm adminSchedule;
    public static AdminEditSchedule adminEditSchedule;
    public static StudentGeneralSchedule studentGeneralSchedule;
    public static StudentRegisteredSchedule studentOwnSchedule;
    public static RegisteredStudentFeedback studentFeedback;
    public static StudentPaymentHistory studentPayment;
    public static StudentProfileForm studentProfile;
    public static AdminViewFeedback adminFeedback;
    public static AdminForm admin;
    public static AdminEditForm adminEdit;
    public static AdminSportForm adminSport;
    public static AdminSportCenter adminSportCenter;
    public static AdminSportCenterEdit adminSportCenterEdit;
    public static AdminSportLocationEdit adminSportLocationEdit;
    public static AdminCoachForm adminCoach;
    public static AdminCoachEditForm adminCoachEdit;
    public static AdminSportEditForm adminSportEdit;
    public static StudentProfileEditForm studentProfileEdit;
    public static StudentCoachFormNew studentCoachNew;
    
    public static void main(String[] args) {
        mainpage = new MainPageForm();
        loginpage = new LoginForm();
        signup = new SignUpForm();
        sportdetails = new SportDetailsForm();
        studentMain = new StudentMainForm();
        adminMain = new AdminMainForm();
        adminMain2 = new AdminMainForm2();
        adminSchedule = new AdminScheduleForm();
        adminEditSchedule = new AdminEditSchedule();
        studentGeneralSchedule = new StudentGeneralSchedule();
        studentOwnSchedule = new StudentRegisteredSchedule();
        studentFeedback = new RegisteredStudentFeedback();
        studentPayment = new StudentPaymentHistory();
        studentProfile = new StudentProfileForm();
        adminFeedback = new AdminViewFeedback();
        adminStudent = new AdminStudentForm();
        admin = new AdminForm();
        adminEdit = new AdminEditForm();
        adminSport = new AdminSportForm();
        adminSportEdit = new AdminSportEditForm();
        adminCoach = new AdminCoachForm();
        adminCoachEdit = new AdminCoachEditForm();
        adminSportLocationEdit = new AdminSportLocationEdit();
        adminSportCenter = new AdminSportCenter();
        adminSportCenterEdit = new AdminSportCenterEdit();
        studentProfileEdit = new StudentProfileEditForm();
        studentCoachNew = new StudentCoachFormNew();
        
        Schedule sch2 = null;
        Register r2 = null;
        
        try{
            Scanner s = new Scanner(new File("student.txt"));
            while(s.hasNext()){  //check inside s gt thing or not
                String ID = s.nextLine();
                String dateCreated = s.nextLine();
                String dateModified = s.nextLine();
                String username = s.nextLine();
                String password = s.nextLine();
                String name = s.nextLine();
                int age = Integer.parseInt(s.nextLine());
                String gender = s.nextLine();
                String phoneNo = s.nextLine();
                String address = s.nextLine();
                String email = s.nextLine();
                s.nextLine();
                Student st = new Student(age,gender,ID,dateCreated,dateModified,username,password,name,phoneNo,address,email);
                allStudent.add(st);   
            }
            
            s = new Scanner(new File("coach.txt"));
            while(s.hasNext()){  //check inside s gt thing or not
                String ID1 = s.nextLine();
                String dateCreated1 = s.nextLine();
                String dateModified1 = s.nextLine();
                String username1 = s.nextLine();
                String password1 = s.nextLine();
                String name1 = s.nextLine();
                int age1 = Integer.parseInt(s.nextLine());
                String gender1 = s.nextLine();
                String phoneNo1 = s.nextLine();
                String address1 = s.nextLine();
                String email1 = s.nextLine();
                String sportCenterName1 = s.nextLine();
                String sportName1 = s.nextLine();
                double hourlyRate1 = Double.parseDouble(s.nextLine());
                double rating1 = Double.parseDouble(s.nextLine());
                boolean isTerminated = Boolean.parseBoolean(s.nextLine());
                s.nextLine();
                Coach ch = new Coach(age1,gender1,hourlyRate1,rating1,sportCenterName1,sportName1,ID1,dateCreated1,dateModified1,username1,password1,name1,phoneNo1,address1,email1,isTerminated);  //Parse a,b to Customer class
                allCoach.add(ch); //add a,b in the allCustomer?
            }
            
            s = new Scanner(new File("sport.txt"));
            while(s.hasNext()){  
                String a = s.nextLine();
                String b = s.nextLine();
                String c = s.nextLine();
                String d = s.nextLine();
                double e = Double.parseDouble(s.nextLine());
                String f = s.nextLine();
                s.nextLine();
                Sport sp = new Sport(a,b,c,d,e,f);
                allSport.add(sp);
            } 
            
            s = new Scanner(new File("admin.txt"));
            while(s.hasNext()){
                
                String i = s.nextLine();
                String j = s.nextLine();
                String k = s.nextLine();
                String l = s.nextLine();
                String m = s.nextLine();
                String n = s.nextLine();
                String o = s.nextLine();
                String p = s.nextLine();
                String q = s.nextLine();
                String r = s.nextLine();
                boolean t = Boolean.parseBoolean(s.nextLine());
                s.nextLine();
                Admin ad = new Admin(r,t,i,j,k,l,m,n,o,p,q);
                allAdmin.add(ad);
            } 
            
            s = new Scanner(new File("sportCenter.txt"));
            while(s.hasNext()){
                String aa = s.nextLine();
                String bb = s.nextLine();
                String cc = s.nextLine();
                String dd = s.nextLine();
                String ee = s.nextLine();
                String ff = s.nextLine();
                String gg = s.nextLine();
                String hh = s.nextLine();
                String ii = s.nextLine();
                s.nextLine();
                SportCenter sc = new SportCenter(aa,bb,cc,dd,ee,ff,gg,hh,ii);
                allSportCenter.add(sc);
            }
            
            s = new Scanner(new File("location.txt"));
            while(s.hasNext()){
                String hhh = s.nextLine();
                String iii = s.nextLine();
                String jjj = s.nextLine();
                String kkk = s.nextLine();
                String lll = s.nextLine();
                String mmm = s.nextLine();
                s.nextLine();
                Location l = new Location(hhh,iii,jjj,kkk,lll,mmm);
                allLocation.add(l);
            }
            
            s = new Scanner(new File("schedule.txt"));
            while(s.hasNext()){
                String hh = s.nextLine();
                String ii = s.nextLine();
                String jj = s.nextLine();
                String kk = s.nextLine();
                String ll = s.nextLine();
                String mm = s.nextLine();
                String nn = s.nextLine();
                String oo = s.nextLine();
                String pp = s.nextLine();
                String qq = s.nextLine();
                int rr = Integer.parseInt(s.nextLine());
                String ss = s.nextLine();
                s.nextLine();
                Schedule sch = new Schedule(hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss);
                allSchedule.add(sch);
            }
            
            s = new Scanner(new File("register.txt"));
            while(s.hasNext()){
                String aaa = s.nextLine();
                String bbb = s.nextLine();
                int kkk = Integer.parseInt(s.nextLine());
                String ccc = s.nextLine();
                String ddd = s.nextLine();
                s.nextLine();
                for(int i=0; i<allSchedule.size();i++){
                    Schedule sch = allSchedule.get(i);
                    if(ccc.equals(sch.getID())){
                        sch2 = sch;
                    }
                }
                for(int a=0; a<allStudent.size(); a++){
                    Student x = allStudent.get(a);
                    if(ddd.equals(x.getID())){
                        Register y = new Register(aaa,bbb,kkk,sch2,x);
                        allRegister.add(y);
                        x.getMyRegister().add(y);
                    }
                }
            }
            
            s = new Scanner(new File("payment.txt"));
            while(s.hasNext()){
                String zzz = s.nextLine();
                String yyy = s.nextLine();
                double xxx = Double.parseDouble(s.nextLine());
                double www = Double.parseDouble(s.nextLine());
                double vvv = Double.parseDouble(s.nextLine());
                String uuu = s.nextLine();
                String ttt = s.nextLine();
                s.nextLine();
                
                for(int i=0; i<allRegister.size();i++){
                    Register r = allRegister.get(i);
                    if(uuu.equals(r.getRegisterID())){
                        r2 = r;
                    }
                }
                for(int a=0; a<allStudent.size();a++){
                    Student stu = allStudent.get(a);
                    if(ttt.equals(stu.getID())){
                        Payment p1 = new Payment(zzz,yyy,xxx,www,vvv,r2,stu);
                        allPayment.add(p1);
                        stu.getMyPayment().add(p1);
                    }
                }                
            }
            
            mainpage.insertSchedule();
            mainpage.setVisible(true);
            
        }catch (Throwable ex) {
            ex.printStackTrace();
        }       
    }
    
}
