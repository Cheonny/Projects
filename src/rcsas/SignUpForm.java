package rcsas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class SignUpForm extends javax.swing.JFrame {
    private String dateTime;
    private String id;
    private String gender;
    
    public SignUpForm() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){
                RCSAS.mainpage.setEnabled(true);
            }
        });
    }
    public void initializeSignUpForm(){
        int size = RCSAS.allStudent.size();
        int newid = size+1;
        id = "ST"+String.format("%05d",newid);
        lblID.setText(id);
        ButtonGroup g = new ButtonGroup();
        g.add(rbtnMale);
        g.add(rbtnFemale);
        txtUsername.setText("");
        txtPassword.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        rbtnMale.setSelected(true);
    }
    public void SignUp(){
        boolean registeredUN = false;
        boolean registeredName = false;
        dateTime = DateTime.DateTime();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String name1 = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        
        //validate empty string
        if ( (username.equals("")) || (password.equals("")) || (name1.equals("")) ||
                (txtAge.getText().equals("")) || (phone.equals("")) || 
                (address.equals("")) || (email.equals("")) ){
            JOptionPane.showMessageDialog(null, "Empty input!","Error",JOptionPane.ERROR_MESSAGE);
            
        //validate invalid condition
        }else{
            String name = RCSAS.validation.CapitalizeLetter(name1);
            System.out.println("a");
            try{
                int age = Integer.parseInt(txtAge.getText());
                if(rbtnMale.isSelected()){
                    gender = "M";
                }else if(rbtnFemale.isSelected()){
                    gender = "F";   
                }
                
                //error validation
                if( (username.length()>20 || name.length()>50) ||(RCSAS.validation.ValidatePhoneNo(phone)==false) ||
                        (RCSAS.validation.ValidatePassword(username) == false) ||
                        (RCSAS.validation.ValidateName(name)==false) || (RCSAS.validation.ValidatePassword(password)==false) ||
                        (RCSAS.validation.ValidateAge(age)==false) || (RCSAS.validation.ValidateEmail(email)==false) ){
                    System.out.println("b");
                    throw new Exception();
                }
                
                //registered name/username validation
                for(int i=0; i < RCSAS.allStudent.size(); i++){
                    Student c = RCSAS.allStudent.get(i);
                    if (txtUsername.getText().equals(c.getUsername())){
                        registeredUN = true;
                        System.out.println("c");
                        throw new Exception();
                    }else if (txtName.getText().equals(c.getName())){
                        System.out.println("d");
                        registeredName = true;
                        throw new Exception();
                    }
                }
                //update
                System.out.println("e");
                Student st = new Student(age,gender,id,dateTime,dateTime,username,password,name,phone,address,email); 
                RCSAS.allStudent.add(st);
                UpdateStudentFile();
                JOptionPane.showMessageDialog(null, "Register successfully!");
                RCSAS.mainpage.setEnabled(true);
                this.setVisible(false);
                
            }catch(Exception ex){
                if(registeredUN){
                    JOptionPane.showMessageDialog(null,"Registered username","Error Input",JOptionPane.ERROR_MESSAGE);
                }else if(registeredName){
                    JOptionPane.showMessageDialog(null,"Registered name","Error Input",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null, 
                            "Uhh Ohh!\n*Username <= 20 characters"
                            + "\n*Name must <= 50 characters and not contain symbols(except '.' and '/') and digits"
                            + "\n*Username and Password must not contain spaces and symbols (except '.', '@', '/ ', '_', '*', '#', '!') )"
                            + "\n*Only accept digits for age (1-99)"
                            + "\n*Phone (e.g. ___-______ 012-3456789)"
                            + "\n*Email (e.g. __@__.__)",
                            "Error input",
                            JOptionPane.ERROR_MESSAGE);          
                }
            }
        }
    }
    public void UpdateStudentFile(){
        try{
            PrintWriter p = new PrintWriter("student.txt");
            for(int i=0; i<RCSAS.allStudent.size();i++){
                Student b = RCSAS.allStudent.get(i);
                p.println(b.getID());
                p.println(b.getDateCreated());
                p.println(b.getDateModified());
                p.println(b.getUsername());
                p.println(b.getPassword());
                p.println(b.getName());
                p.println(b.getAge());
                p.println(b.getGender());
                p.println(b.getPhoneNo());
                p.println(b.getAddress());
                p.println(b.getEmail());
                p.println();
            }p.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Update failed.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        lbl6 = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        rbtnMale = new javax.swing.JRadioButton();
        rbtnFemale = new javax.swing.JRadioButton();
        txtPhone = new javax.swing.JTextField();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lbl9 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(450, 593));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(172, 3, 24));
        jPanel1.setPreferredSize(new java.awt.Dimension(574, 85));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Real Champions Sport Academy ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel2.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSignUp.setBackground(new java.awt.Color(172, 3, 24));
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Sign Up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lbl1.setText("Student ID :");

        lblID.setText("ST00002");
        lblID.setEnabled(false);

        lbl3.setText("Username :");

        lbl4.setText("Password :");

        lbl5.setText("Name :");

        lbl6.setText("Age :");

        lblGender.setText("Gender :");

        rbtnMale.setSelected(true);
        rbtnMale.setText("Male");

        rbtnFemale.setText("Female");

        lbl7.setText("Phone :");

        lbl8.setText("Address :");

        lbl9.setText("Email :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1)
                    .addComponent(lbl3)
                    .addComponent(lbl4)
                    .addComponent(lbl5)
                    .addComponent(lbl6)
                    .addComponent(lbl7)
                    .addComponent(lbl8)
                    .addComponent(lbl9)
                    .addComponent(lblGender))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsername)
                                .addComponent(txtPassword)
                                .addComponent(txtName)
                                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAddress)
                        .addComponent(txtEmail)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbtnMale)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnFemale)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(lblID))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl5)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl6)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnMale)
                    .addComponent(rbtnFemale)
                    .addComponent(lblGender))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl8))
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSignUp)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignUp)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        RCSAS.mainpage.setEnabled(true);
        this.setVisible(false); 
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        SignUp();
    }//GEN-LAST:event_btnSignUpActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpForm().setVisible(true);
            }
        });

        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblID;
    private javax.swing.JRadioButton rbtnFemale;
    private javax.swing.JRadioButton rbtnMale;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
