/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcsas;

import java.io.PrintWriter;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class AdminSportCenter extends javax.swing.JFrame {
    
    /**
     * Creates new form AdminSportCenter
     */
    private String SCID;
    private String locationID;
    public String selectedSportCenterID;
    public String selectedSportLocationID;
    private String dateTime;
    public boolean isSportCenter = true;
    public boolean isSportLocation = false;
    private boolean registeredName = false;
    private boolean operatingHour = false;
            
    public AdminSportCenter() {
        initComponents();
    }
    public void initiateSportCenterForm1(){
        //new sc id
        int size1 = RCSAS.allSportCenter.size();
        int newid1 = size1+1;
        SCID = "SC"+String.format("%03d",newid1);
        lblSportID.setText(SCID);
        
        //new location id
        int size2 = RCSAS.allLocation.size();
        int newid2 = size2+1;
        locationID = "L"+String.format("%03d",newid2);
        lblLocationID.setText(locationID);
        
        //refresh sport center add form
        txtSportCenterName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtOpenTime.setText("");
        txtCloseTime.setText("");
        lblUsername.setText(RCSAS.current.getUsername());
        jToggleButton.setSelected(false);
    }
    public void initiateSportCenterForm2(){
        //new location id
        int size2 = RCSAS.allLocation.size();
        int newid2 = size2+1;
        locationID = "L"+String.format("%03d",newid2);
        lblLocationID.setText(locationID);
        
        //refresh location add form
        comboSportCenterName.removeAllItems();
        comboSportCenterName.addItem("Select Sport Center");
        comboSportName.removeAllItems();
        comboSportName.addItem("Select Sport");
        txtLocation.setText("");
    }
    
    public void addRowToJTable(){
        comboSportCenterName.removeAllItems();
        comboSportCenterName.addItem("Select Sport Center");
        DefaultTableModel model = (DefaultTableModel) tbSportCenter.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Sport Center ID");
        model.addColumn("Date Created");
        model.addColumn("Date Modified");
        model.addColumn("Name");
        model.addColumn("Phone No");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Open Time");
        model.addColumn("Close Time");
        for(int i=0; i<RCSAS.allSportCenter.size();i++){
            SportCenter sc = RCSAS.allSportCenter.get(i);
            Object rowData[] = new Object[]{
                sc.getID(),sc.getDateCreated(),sc.getDateModified(),sc.getName(),
                sc.getPhoneNo(),sc.getAddress(),sc.getEmail(),sc.getOpenTime(),sc.getCloseTime()};
            model.addRow(rowData);
            if(!(sc.getName().equals("null"))){
                comboSportCenterName.addItem(sc.getName());
            }
        }
    }
    public void addRowToJTable2(){
        comboSportCenterName.removeAllItems();
        comboSportCenterName.addItem("Select Sport Center");
        DefaultTableModel tbmodel = (DefaultTableModel) tbSportCenter.getModel();
        tbmodel.setRowCount(0);
        tbmodel.setColumnCount(0);
        tbmodel.addColumn("Sport Location ID");
        tbmodel.addColumn("Date Created");
        tbmodel.addColumn("Date Modified");
        tbmodel.addColumn("Sport Center Name");
        tbmodel.addColumn("Sport Location");
        tbmodel.addColumn("Sport Name");
        for(int k=0; k<RCSAS.allLocation.size();k++){
            Location l = RCSAS.allLocation.get(k);
            Object rowData[] = new Object[]{
                l.getLocationID(),l.getDateCreated(),l.getDateModified(),l.getSportCenterName(),
                l.getSportLocationName(),l.getSportName()};
            tbmodel.addRow(rowData);
        }
        for(int i=0; i<RCSAS.allSportCenter.size();i++){
            SportCenter sc = RCSAS.allSportCenter.get(i);
            if(!(sc.getName().equals("null"))){
                comboSportCenterName.addItem(sc.getName());
            }
        }
    }
    public void UpdateSportCenterFile(){
        try{
            PrintWriter p = new PrintWriter("sportCenter.txt");
            for(int i=0; i<RCSAS.allSportCenter.size();i++){
                SportCenter sc = RCSAS.allSportCenter.get(i);
                p.println(sc.getID());
                p.println(sc.getDateCreated());
                p.println(sc.getDateModified());
                p.println(sc.getName());
                p.println(sc.getPhoneNo());
                p.println(sc.getAddress());
                p.println(sc.getEmail());
                p.println(sc.getOpenTime());
                p.println(sc.getCloseTime());
                p.println();
            }p.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Update failed","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void UpdateSportLocationFile(){
        try{
            PrintWriter p = new PrintWriter("location.txt");
            for(int i=0; i<RCSAS.allLocation.size();i++){
                Location l = RCSAS.allLocation.get(i);
                p.println(l.getLocationID());
                p.println(l.getDateCreated());
                p.println(l.getDateModified());
                p.println(l.getSportCenterName());
                p.println(l.getSportLocationName());
                p.println(l.getSportName());
                p.println();
            }p.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Update failed","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSportCenter = new javax.swing.JTable();
        lblCoach = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblLocationID = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnAdd2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        comboSportCenterName = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        comboSportName = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        lblSportID = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        btnAdd1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSportCenterName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtOpenTime = new javax.swing.JTextField();
        txtCloseTime = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnEdit = new javax.swing.JButton();
        jToggleButton = new javax.swing.JToggleButton();

        jToggleButton2.setText("jToggleButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(172, 3, 24));
        jPanel1.setPreferredSize(new java.awt.Dimension(574, 85));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Real Champions Sport Academy ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Current Login:");

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
        lblUsername.setText("1234567891");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        tbSportCenter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbSportCenter.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbSportCenter.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbSportCenter);

        lblCoach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCoach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoach.setText("Sport Center/ Sport Location");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblLocationID.setText("SP002");
        lblLocationID.setEnabled(false);

        jLabel23.setText("Location ID :");

        jLabel26.setText("Sport Name :");

        btnAdd2.setBackground(new java.awt.Color(172, 3, 24));
        btnAdd2.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd2.setText("Add");
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Sport Center Name :");

        comboSportCenterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSportCenterNameActionPerformed(evt);
            }
        });

        jLabel28.setText("Location Name :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd2)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel23)
                            .addComponent(jLabel28)
                            .addComponent(jLabel26))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(txtLocation)
                                .addContainerGap())
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblLocationID)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(comboSportCenterName, 0, 180, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(comboSportName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocationID)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboSportCenterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(comboSportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnAdd2)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lblSportID.setText("SP002");
        lblSportID.setEnabled(false);

        jLabel24.setText("Sport Center ID :");

        jLabel27.setText("Sport Center Name :");

        btnAdd1.setBackground(new java.awt.Color(172, 3, 24));
        btnAdd1.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd1.setText("Add");
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Address :");

        jLabel10.setText("Email :");

        jLabel11.setText("Phone :");

        jLabel12.setText("Open Time :");

        jLabel13.setText("Close Time :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 204));
        jLabel5.setText("09:01 - 23:59");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 204));
        jLabel6.setText("07:00 - 21:00");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel24))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd1))
                    .addComponent(txtEmail)
                    .addComponent(txtOpenTime)
                    .addComponent(txtCloseTime)
                    .addComponent(txtAddress)
                    .addComponent(txtPhone)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSportID)
                            .addComponent(txtSportCenterName, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSportID)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtSportCenterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtOpenTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCloseTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd1)
                .addContainerGap())
        );

        btnEdit.setBackground(new java.awt.Color(172, 3, 24));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edited Selected");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jToggleButton.setBackground(new java.awt.Color(102, 102, 102));
        jToggleButton.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton.setText("Sport Center/Sport Location");
        jToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEdit))
                    .addComponent(lblCoach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jToggleButton)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblCoach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(20, 20, 20))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        dateTime = DateTime.DateTime();
        String sportCenter = comboSportCenterName.getSelectedItem().toString();
        String Location = txtLocation.getText();
        String sportName = comboSportName.getSelectedItem().toString();
        
        //empty input validation
        if( (sportCenter == "") || (Location == "") || (sportName == "Select Sport")){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                //validation
                String location = RCSAS.validation.CapitalizeLetter(Location);
                if(location.length()>30){
                    throw new Exception();
                }

                for(int i=0;i<RCSAS.allLocation.size();i++){
                    Location l = RCSAS.allLocation.get(i);
                    if(location.equals(l.getSportLocationName()) && sportCenter.equals(l.getSportCenterName())){
                        registeredName = true;
                        throw new Exception();
                    }   
                }
                if(RCSAS.validation.ValidateName(location)==false){
                        throw new Exception();
                }
                
                //update
                Location l = new Location(locationID,dateTime,dateTime,sportCenter,location,sportName);
                RCSAS.allLocation.add(l);
                JOptionPane.showMessageDialog(null,"New sport location is added successfully");
                UpdateSportLocationFile();
                initiateSportCenterForm2();
                addRowToJTable2();      
            }catch (Exception ex){
                if(registeredName){
                    JOptionPane.showMessageDialog(
                            null,
                            "Registered sport location name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "\n*Sport location name < 30 characters"
                            +"\n*Sport location name cannot contain symbols (except '.' and '/') and digits",
                            "Error Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        dateTime = DateTime.DateTime();
        String sportCenter = txtSportCenterName.getText();
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String openTime = txtOpenTime.getText();
        String closeTime = txtCloseTime.getText();
        
        //empty input validation
        if( (sportCenter == "") || (address == "") || (phone == "") || (email == "") || 
                (openTime == "") || (closeTime == "")){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                //registered name
                for(int i=0;i<RCSAS.allSportCenter.size();i++){
                    SportCenter sc = RCSAS.allSportCenter.get(i);
                    if(sportCenter.equals(sc.getName())){
                        registeredName = true;
                        throw new Exception();
                    }
                }

                //error input validation
                RCSAS.validation.CapitalizeLetter(sportCenter);
                if( (sportCenter.length()>50) || (RCSAS.validation.ValidateOpen(openTime) == false) || 
                        (RCSAS.validation.ValidateName(sportCenter) == false) ||
                        (RCSAS.validation.ValidateClose(closeTime) == false) ||
                        (RCSAS.validation.ValidatePhoneNo(phone) == false) ||
                        (RCSAS.validation.ValidateEmail(email) == false) ){
                    throw new Exception();
                }else{
                    //if all inputs are valid
                    LocalTime closeTime2 = LocalTime.parse(closeTime);
                    LocalTime startTime2 = LocalTime.parse(openTime);
                    LocalTime acceptableTime = startTime2.plusHours(2);
                    if(closeTime2.isAfter(acceptableTime)){
                        //update
                        SportCenter sc = new SportCenter(SCID,dateTime,dateTime,sportCenter,phone,address,email,openTime,closeTime);
                        RCSAS.allSportCenter.add(sc);
                        UpdateSportCenterFile();
                        JOptionPane.showMessageDialog(null,"New sport center is added successfully");
                        addRowToJTable();
                        initiateSportCenterForm1();
                    }else{
                        operatingHour = true;
                        throw new Exception();
                    }
                }
            }catch (Exception ex){
                if(registeredName){
                    JOptionPane.showMessageDialog(null,"Registered sport center name.","Error",JOptionPane.ERROR_MESSAGE);
                }else if(operatingHour){
                    JOptionPane.showMessageDialog(null,"The close time must be more than 2 hours from the start time.");
                }else{
                    JOptionPane.showMessageDialog(
                        null,
                        "Uhh-ohh!"
                        + "\n*Sport center name must <= 50 characters and not contain symbols(except '.' and '/') and digits"
                        + "\n*Phone (e.g. ___-______ 012-3456789)"
                        + "\n*Email (e.g. __@__.__)"
                        + "\n*Open Time & Close Time in 24HR FORMAT (e.g. HH:MM)"
                        + "\nOpen Time range from 07:00-21:00"
                        + "\nClose Time range from 09:01-23:59",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnAdd1ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if(tbSportCenter.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a row to remove it","Error input",
                            JOptionPane.ERROR_MESSAGE);
        }else{
            //sport center table
            if(isSportCenter){
                int selectedRowIndex = tbSportCenter.getSelectedRow();
                selectedSportCenterID = tbSportCenter.getValueAt(selectedRowIndex,0).toString(); //row column
                for(int i=0;i<RCSAS.allSportCenter.size();i++){
                    SportCenter sc = RCSAS.allSportCenter.get(i);
                    if(selectedSportCenterID.equals(sc.getID())){
                        if(sc.getName().equals("null")){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "The sport center is deleted.\nIt cannot be edited.",
                                    "Error input",
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            this.setEnabled(false);
                            RCSAS.adminSportCenterEdit.setVisible(true);
                            RCSAS.adminSportCenterEdit.setTextField();
                        }
                    }
                }
            //sport location table
            }else if(isSportLocation){
                int selectedRowIndex = tbSportCenter.getSelectedRow();
                selectedSportLocationID = tbSportCenter.getValueAt(selectedRowIndex,0).toString(); //row column
                for(int i=0;i<RCSAS.allLocation.size();i++){
                    Location l = RCSAS.allLocation.get(i);
                    if(selectedSportLocationID.equals(l.getLocationID())){
                        if(l.getSportLocationName().equals("null")){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "The sport location is deleted.\nIt cannot be edited.",
                                    "Error input",
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            this.setEnabled(false);
                            RCSAS.adminSportLocationEdit.setVisible(true);
                            RCSAS.adminSportLocationEdit.setTextField();
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonActionPerformed
        if(jToggleButton.isSelected()){
            isSportCenter = false;
            isSportLocation = true;
            addRowToJTable2();
        }else{
            isSportLocation = false;
            isSportCenter = true;
            addRowToJTable();
        }
    }//GEN-LAST:event_jToggleButtonActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        if(RCSAS.loginpage.isHQAdmin){
            RCSAS.adminMain.setVisible(true);
        }else if(RCSAS.loginpage.isAdmin){
            RCSAS.adminMain2.setVisible(true);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void comboSportCenterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSportCenterNameActionPerformed
        comboSportName.removeAllItems();
        comboSportName.addItem("Select Sport");
        for(int k=0; k<RCSAS.allSport.size();k++){
            Sport sp = RCSAS.allSport.get(k);
            if(sp.getSportCenterName().equals(comboSportCenterName.getSelectedItem())){
                comboSportName.addItem(sp.getSportName());
            }
        }
    }//GEN-LAST:event_comboSportCenterNameActionPerformed

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
            java.util.logging.Logger.getLogger(AdminSportCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminSportCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminSportCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminSportCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminSportCenter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> comboSportCenterName;
    private javax.swing.JComboBox<String> comboSportName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel lblCoach;
    private javax.swing.JLabel lblLocationID;
    private javax.swing.JLabel lblSportID;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tbSportCenter;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCloseTime;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtOpenTime;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSportCenterName;
    // End of variables declaration//GEN-END:variables
}
