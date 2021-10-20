package rcsas;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class AdminForm extends javax.swing.JFrame {
    private String dateTime;
    private String id;
    public String selectedAdminID;
    
    public AdminForm() {
        initComponents();
    }
    
    public void initiateAdminForm(){
        //new id
        int size = RCSAS.allAdmin.size();
        int newid = size+1;
        id = "A"+String.format("%03d",newid);
        lblID.setText(id);
        
        //empty the text field
        txtUsername.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        lblUsername.setText(RCSAS.current.getUsername()); 
        cbIsHQ.setSelected(false);
        SportCenterValidation();
    }
    
    public void SportCenterValidation(){
        //combobox and checkbox
        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
        txtName.setEnabled(true);
        txtPhone.setEnabled(true);
        txtAddress.setEnabled(true);
        txtEmail.setEnabled(true);
        comboSportCenter.removeAllItems();
        ArrayList<String> firstList = new ArrayList<String>();
        ArrayList<String> secondList = new ArrayList<String>();
        for(int i=0; i<RCSAS.allSportCenter.size();i++){
            SportCenter sc = RCSAS.allSportCenter.get(i);
            if(!(sc.getName().equals("null"))){
                firstList.add(sc.getName());
            }
        }
        for(int k=0;k<RCSAS.allAdmin.size();k++){
            Admin a = RCSAS.allAdmin.get(k);
            if(!(a.getSportCenterName().equals("null"))){
                secondList.add(a.getSportCenterName());
            }
        }
        if(firstList.equals(secondList)==true){
            comboSportCenter.addItem("No Sport Center Available");
            comboSportCenter.setEnabled(false);
            txtUsername.setEnabled(false);
            txtPassword.setEnabled(false);
            txtName.setEnabled(false);
            txtPhone.setEnabled(false);
            txtAddress.setEnabled(false);
            txtEmail.setEnabled(false);
        }else{
            for(String item: firstList){
                if(secondList.contains(item)==false){
                    comboSportCenter.setEnabled(true);
                    comboSportCenter.addItem(item);
                }
            }
        }
    }
    
    public void addRowToJTable(){
        DefaultTableModel model = (DefaultTableModel) tbAdmin.getModel();
        model.setRowCount(0);
        for(int i=0; i<RCSAS.allAdmin.size();i++){
            Admin s = RCSAS.allAdmin.get(i);
            Object rowData[] = new Object[]{
                s.getID(),s.getDateCreated(),s.getDateModified(),s.getUsername(),s.getPassword(),s.getName(),
                s.getPhoneNo(),s.getAddress(),s.getEmail(),s.getSportCenterName(),s.getIsHQ()};
                model.addRow(rowData);
        }
    }
    public void UpdateAdminFile(){
        try{
            PrintWriter p = new PrintWriter("admin.txt");
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin a = RCSAS.allAdmin.get(i);
                p.println(a.getID());
                p.println(a.getDateCreated());
                p.println(a.getDateModified());
                p.println(a.getUsername());
                p.println(a.getPassword());
                p.println(a.getName());
                p.println(a.getPhoneNo());
                p.println(a.getAddress());
                p.println(a.getEmail());
                p.println(a.getSportCenterName());
                p.println(a.getIsHQ());
                p.println();
            }p.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Update failed","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAdmin = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        comboSportCenter = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        cbIsHQ = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(172, 3, 24));
        jPanel1.setPreferredSize(new java.awt.Dimension(574, 85));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Real Champions Sport Academy ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));

        lblUsername.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("1234567891234567891");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Current Login:");

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogout))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(17, 17, 17))
        );

        tbAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AdminID", "Date Created", "Date Modified", "Username", "Password", "Name", "PhoneNo", "Address", "Email", "Sport Center Name", "isHQ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAdmin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbAdmin.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbAdmin.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbAdmin);
        if (tbAdmin.getColumnModel().getColumnCount() > 0) {
            tbAdmin.getColumnModel().getColumn(1).setMinWidth(150);
            tbAdmin.getColumnModel().getColumn(2).setMinWidth(150);
            tbAdmin.getColumnModel().getColumn(5).setMinWidth(150);
            tbAdmin.getColumnModel().getColumn(7).setMinWidth(200);
            tbAdmin.getColumnModel().getColumn(9).setMinWidth(200);
        }

        btnEdit.setBackground(new java.awt.Color(172, 3, 24));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Selected");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblID.setText("A002");
        lblID.setEnabled(false);

        jLabel23.setText("Admin ID :");

        jLabel25.setText("Username :");

        jLabel26.setText("Password :");

        jLabel27.setText("Name :");

        jLabel28.setText("Address :");

        jLabel29.setText("Email :");

        jLabel30.setText("Phone :");

        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                med(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(172, 3, 24));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel32.setText("Sport Center Name:");

        cbIsHQ.setText("isHQ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel23)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel32))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSportCenter, 0, 226, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbIsHQ)
                                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblID))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(cbIsHQ)
                .addGap(9, 9, 9)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Admin");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEdit)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jScrollPane1.setViewportView(jPanel4);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void med(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_med
        // TODO add your handling code here:
    }//GEN-LAST:event_med

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        dateTime = DateTime.DateTime();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String sportCenter = comboSportCenter.getSelectedItem().toString();
        boolean isHQ = false;
        boolean registeredUN = false;
        boolean registeredName = false;
        
        //empty input
        if( (username.equals("")) || (password.equals("")) || (name.equals("")) || 
                (phone.equals("")) || (address.equals("")) || (email.equals("")) || 
                (comboSportCenter.getSelectedItem().equals("No Sport Center Available")) ){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                String Name = RCSAS.validation.CapitalizeLetter(name);
                //error validation
                if( (username.length()>20 || Name.length()>50) || 
                        (RCSAS.validation.ValidateName(Name)== false) ||
                        (RCSAS.validation.ValidatePassword(username) == false) ||
                        (RCSAS.validation.ValidatePassword(password)==false) ||
                        (RCSAS.validation.ValidatePhoneNo(phone)== false) || 
                        (RCSAS.validation.ValidateEmail(email) == false) ){
                    throw new Exception();
                }
                
                //registered name
                for(int i=0;i<RCSAS.allAdmin.size();i++){
                    Admin a = RCSAS.allAdmin.get(i);
                    if(username.equals(a.getUsername())){
                        registeredUN = true;
                        throw new Exception();
                    }else if(name.equals(a.getName())){
                        registeredName = true;
                    }
                    
                    if(cbIsHQ.isSelected()){
                        isHQ = true;
                        sportCenter = "null";
                    }else{
                        isHQ = false;    
                    }
                }
                
                //update
                Admin ad = new Admin(sportCenter,isHQ, id, dateTime, dateTime, username, password, Name, phone, address, email);
                RCSAS.allAdmin.add(ad);
                JOptionPane.showMessageDialog(null,"New admin is added");
                addRowToJTable();
                UpdateAdminFile();
                initiateAdminForm();
                
            }catch (Exception e){
                if(registeredUN){
                    JOptionPane.showMessageDialog(null,"\n*Registered username","Error Input",JOptionPane.ERROR_MESSAGE);
                }else if(registeredName){
                    JOptionPane.showMessageDialog(null,"\n*Registered name","Error Input",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                        null,
                        "Uhh-Ohh:"
                        + "\n*Username <= 20 characters"
                        + "\n*Name must <= 50 characters and not contain symbols (except '.' and '/') and digits"
                        + "\n*Username and Password must not contain spaces and symbols (except '.', '@', '/ ', '_', '*', '#', '!') )"
                        + "\n*Phone (e.g. ___-______ 012-3456789)"
                        + "\n*Email (e.g. __@__.__)",
                        "Error Input",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(tbAdmin.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a row to remove it","Error input",
                            JOptionPane.ERROR_MESSAGE);
        }else{
            int selectedRowIndex = tbAdmin.getSelectedRow();
            selectedAdminID = tbAdmin.getValueAt(selectedRowIndex,0).toString(); //row column
            this.setEnabled(false);
            RCSAS.adminEdit.setVisible(true);
            RCSAS.adminEdit.setTextField();
        }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        if(RCSAS.loginpage.isHQAdmin){
            RCSAS.adminMain.setVisible(true);
        }else if(RCSAS.loginpage.isAdmin){
            RCSAS.adminMain2.setVisible(true);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        RCSAS.mainpage.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JCheckBox cbIsHQ;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tbAdmin;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
