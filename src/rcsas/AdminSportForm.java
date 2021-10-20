package rcsas;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AdminSportForm extends javax.swing.JFrame {
    private String dateTime;
    private String id;
    public DefaultTableModel model;
    public TableRowSorter<DefaultTableModel> sorter;// = new TableRowSorter<DefaultTableModel>(model);
    public String selectedSportID;
    private boolean registeredName = false;
    
    public AdminSportForm() {
        initComponents();
    }
    
    public void initiateSportForm(){
        //create new id
        int size = RCSAS.allSport.size();
        int newid = size+1;
        id = "SP"+String.format("%03d",newid);
        lblSportID.setText(id);
        txtSearch.setText("");
        
        //set empty text field
        txtSportName.setText("");
        txtSportFee.setText("");
        comboSportCenter.removeAllItems();
        comboSportCenter.addItem("All");
        comboSportCenter.setSelectedItem("All");
        lblUsername.setText(RCSAS.current.getUsername());
        updateCombobox2();
    }
    public void updateCombobox2(){
        comboSportCenter2.removeAllItems();
        comboSportCenter2.addItem("Select Sport Center");
        if(RCSAS.loginpage.isHQAdmin){
            
            comboSportCenter.setEnabled(true);
            for(int i=0; i<RCSAS.allSportCenter.size();i++){
                SportCenter sc = RCSAS.allSportCenter.get(i);
                if(!(sc.getName().equals("null"))){
                    comboSportCenter.addItem(sc.getName());
                    comboSportCenter2.addItem(sc.getName());
                }
            }
        }else if(RCSAS.loginpage.isAdmin){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin ab = RCSAS.allAdmin.get(i);
                if(RCSAS.current.getID().equals(ab.getID())){
                    comboSportCenter.setEnabled(false);
                    comboSportCenter2.addItem(ab.getSportCenterName());
                }
            }   
        }
    }

    //update
    public void addRowToJTable(){
        DefaultTableModel model = (DefaultTableModel) tbSport.getModel();
        model.setRowCount(0);
        if(RCSAS.loginpage.isHQAdmin){
            for(int z=0; z<RCSAS.allSport.size();z++){
                Sport sp = RCSAS.allSport.get(z);
                Object rowData[] = new Object[]{
                sp.getID(),sp.getDateCreated(),sp.getDateModified(),sp.getSportName(),sp.getSportFee(),sp.getSportCenterName()};
                model.addRow(rowData);
            }
        //SportCenterA admin & SportCenterB admin
        }else if(RCSAS.loginpage.isAdmin){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin ab = RCSAS.allAdmin.get(i);
                for(int x=0; x<RCSAS.allSport.size();x++){
                    Sport sp = RCSAS.allSport.get(x);
                    if( (RCSAS.current.getID().equals(ab.getID()))){
                        if (sp.getSportCenterName().equals(ab.getSportCenterName()) || sp.getSportCenterName().equals("null")){
                            Object rowData[] = new Object[]{
                                sp.getID(), sp.getDateCreated(),sp.getDateModified(),sp.getSportName(),
                                sp.getSportFee(),sp.getSportCenterName()};
                                model.addRow(rowData);
                        }
                    }
                }
            }
        }
    }
    public void UpdateSportFile(){
        try{
            PrintWriter p = new PrintWriter("sport.txt");
            for(int i =0;i<RCSAS.allSport.size();i++){
                Sport S = RCSAS.allSport.get(i);
                p.println(S.getID());
                p.println(S.getDateCreated());
                p.println(S.getDateModified());
                p.println(S.getSportName());
                p.println(S.getSportFee());
                p.println(S.getSportCenterName());
                p.println();
            }p.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Update failed","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSport = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        txtSportName = new javax.swing.JTextField();
        lblSportID = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        comboSportCenter2 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtSportFee = new javax.swing.JTextField();
        comboSportCenter = new javax.swing.JComboBox<>();
        lblSport = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        lblFilter = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbSport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sport ID", "Date Created", "Date Modified", "Sport Name", "Sport Fee", "Sport Center Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSport.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbSport.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSport.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbSport);
        if (tbSport.getColumnModel().getColumnCount() > 0) {
            tbSport.getColumnModel().getColumn(1).setMinWidth(150);
            tbSport.getColumnModel().getColumn(2).setMinWidth(150);
            tbSport.getColumnModel().getColumn(3).setMinWidth(200);
            tbSport.getColumnModel().getColumn(5).setMinWidth(200);
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

        lblSportID.setText("SP002");
        lblSportID.setEnabled(false);

        jLabel23.setText("Sport ID :");

        jLabel25.setText("Sport Name :");

        jLabel26.setText("Sport Center Name:");

        btnAdd.setBackground(new java.awt.Color(172, 3, 24));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        comboSportCenter2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Sport Center" }));

        jLabel28.setText("Sport Fees :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel23)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSportName)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSportID)
                    .addComponent(comboSportCenter2, 0, 162, Short.MAX_VALUE)
                    .addComponent(txtSportFee))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSportID)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSportFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSportCenter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        comboSportCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSportCenterActionPerformed(evt);
            }
        });

        lblSport.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSport.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSport.setText("Sport");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSearch.setText("Search :");

        lblFilter.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        lblFilter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFilter.setText("Search by Sport ID, Sport Name");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(63, 63, 63))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblSport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel1.setBackground(new java.awt.Color(172, 3, 24));
        jPanel1.setPreferredSize(new java.awt.Dimension(574, 85));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Real Champions Sport Academy ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcsas/images/logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 50));

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsername.setText("1234567891");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Current Login:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        dateTime = DateTime.DateTime();
        String string = txtSportName.getText();
        String SportCenter = comboSportCenter2.getSelectedItem().toString();
        Double sportFee;
        
        
        //empty input validation
        if( (string.equals("")) || (txtSportFee.getText().equals("")) || 
                (SportCenter.equals("Select Sport Center")) ){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                String sportName = RCSAS.validation.CapitalizeLetter(string);
                //check registered sport in sport center
                for(int k=0;k<RCSAS.allSport.size();k++){
                    Sport s = RCSAS.allSport.get(k);
                    if( (sportName.equals(s.getSportName())) && (SportCenter.equals(s.getSportCenterName())) ){
                        registeredName = true;
                        throw new Exception();
                    }else if(RCSAS.validation.ValidateName(s.getSportCenterName())==false){
                        throw new Exception();
                    }
                }
                //validate sport fee
                Pattern pattern = Pattern.compile("^\\d{0,3}(\\.\\d{0,2})?$");
                Matcher match = pattern.matcher(txtSportFee.getText());
                if(match.matches()){
                    sportFee = Double.parseDouble(txtSportFee.getText());
                    if(sportFee<1){
                        throw new Exception();
                    }
                }else{
                    throw new Exception();
                }

                //Update
                Sport sport = new Sport(id,dateTime,dateTime,sportName,sportFee,SportCenter);
                RCSAS.allSport.add(sport);
                UpdateSportFile();
                addRowToJTable();
                initiateSportForm();
                JOptionPane.showMessageDialog(null,"New sport is added successfully");

            }catch (Exception ex){
                if(registeredName){
                    JOptionPane.showMessageDialog(
                            null,
                            "Uhh Ohh:\n*The Sport Name has been registered in this sport center.","Error input",
                            JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Uhh Ohh:\n* Sport Center Name cannot contain symbols(except '.' and '/') and digits"
                                    + "\n*Only accept digits for sport fees (0-999)",
                            "Error input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //empty row is selected
        if(tbSport.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a row to edit it","Error input",
                            JOptionPane.ERROR_MESSAGE);
        }else{ //select a row
            int selectedRowIndex = tbSport.getSelectedRow();
            selectedSportID = tbSport.getValueAt(selectedRowIndex,0).toString(); //Selected Row sport ID
            this.setEnabled(false);
            RCSAS.adminSportEdit.setTextField();
            RCSAS.adminSportEdit.setVisible(true);

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

    private void comboSportCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSportCenterActionPerformed
        if(comboSportCenter.getSelectedItem() == null){
            return;
        }else{
            DefaultTableModel model = (DefaultTableModel) tbSport.getModel();
            model.setRowCount(0);
            for(int i=0; i<RCSAS.allSportCenter.size();i++){
                SportCenter aa = RCSAS.allSportCenter.get(i);
                if(comboSportCenter.getSelectedItem().equals("All")){
                    addRowToJTable();
                }else if(comboSportCenter.getSelectedItem().equals(aa.getName())){
                    for(int x=0; x<RCSAS.allSport.size();x++){
                        Sport sp = RCSAS.allSport.get(x);
                        if(sp.getSportCenterName().equals(aa.getName())){
                            Object rowData[] = new Object[]{
                                sp.getID(), sp.getDateCreated(),sp.getDateModified(),sp.getSportName(),
                                sp.getSportFee(),sp.getSportCenterName()};
                                model.addRow(rowData);
                        }
                    }
                }
            }
        }
       
    }//GEN-LAST:event_comboSportCenterActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        String text = txtSearch.getText();
        
        String regex1 = "^[a-zA-Z0-9]*$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(text);
        
        String regex2 = "^[a-zA-Z\\s]*$";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(text);
        DefaultTableModel model = (DefaultTableModel) tbSport.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        try{
            if(text.equals("")){
                tbSport.setRowSorter(null);
                addRowToJTable();
            }else{
                comboSportCenter.setSelectedItem("All");
                if(matcher2.matches()){
                    text = RCSAS.validation.CapitalizeLetter(text.trim()).trim();
                    tbSport.setRowSorter(tr);
                    tr.setRowFilter(RowFilter.regexFilter(text.trim(),3));
                }else if(matcher1.matches()){
                    text = text.toUpperCase();
                    tbSport.setRowSorter(tr);
                    tr.setRowFilter(RowFilter.regexFilter(text.trim(),0));
                }else{
                    tbSport.setRowSorter(tr);
                    tr.setRowFilter(RowFilter.regexFilter(text.trim(),0,3));
                }
            }
        }catch (Exception ex){
            System.out.println("Error");
        }
        

    }//GEN-LAST:event_txtSearchKeyTyped

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
            java.util.logging.Logger.getLogger(AdminSportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminSportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminSportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminSportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSportForm().setVisible(true);
            }
        }); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JComboBox<String> comboSportCenter2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSport;
    private javax.swing.JLabel lblSportID;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tbSport;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSportFee;
    private javax.swing.JTextField txtSportName;
    // End of variables declaration//GEN-END:variables
}
