package rcsas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AdminCoachForm extends javax.swing.JFrame {
    private String id;
    public String selectedCoachID;
    private String dateTime;
    private String gender;

    public AdminCoachForm(){
        initComponents();
    }
    public void initiateCoachForm(){
        //button group
        ButtonGroup g = new ButtonGroup();
        g.add(rbtnID);
        g.add(rbtnName);
        g.add(rbtnHourlyRate);
        g.add(rbtnRating);
        ButtonGroup g1 = new ButtonGroup();
        g1.add(rbtnMale);
        g1.add(rbtnFemale);
        txtSearch.setText("");
        
        //new id
        int size = RCSAS.allCoach.size();
        int newid = size+1;
        id = "C"+String.format("%03d",newid);
        lblCoachID.setText(id);
        lblInitialRating.setText("0");
        lblUsername.setText(RCSAS.current.getUsername());
        
        //empty the text field
        txtUsername.setText("");
        txtPassword.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtHourlyRate.setText("");
        rbtnMale.setSelected(true);
        rbtnID.setSelected(true);
        comboSportCenter.removeAllItems();
        comboSportName.removeAllItems();
        comboSportCenter.addItem("Select Sport Center");
        
        if(RCSAS.loginpage.isHQAdmin){
            for(int i=0; i<RCSAS.allSportCenter.size();i++){
                SportCenter sc = RCSAS.allSportCenter.get(i);
                comboSportCenter.addItem(sc.getName());
            }
        }else if(RCSAS.loginpage.isAdmin){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin ab = RCSAS.allAdmin.get(i);
                if(RCSAS.current.getID().equals(ab.getID())){
                    comboSportCenter.addItem(ab.getSportCenterName());
                }
            }   
        }
    }
    
    public void addRowToJTable(){
        DefaultTableModel model = (DefaultTableModel) tbCoach.getModel();
        model.setRowCount(0);
        
        if(RCSAS.loginpage.isHQAdmin){
            for(int i=0; i<RCSAS.allCoach.size();i++){
                Coach s = RCSAS.allCoach.get(i);
                Object rowData[] = new Object[]{
                    s.getID(),s.getDateCreated(),s.getDateModified(),s.getUsername(),
                    s.getPassword(),s.getName(),s.getAge(),s.getGender(),
                    s.getPhoneNo(),s.getAddress(),s.getEmail(),s.getSportCenterName(),
                    s.getSportName(),s.getHrRate(),s.getAvgRating(),s.isIsTerminated()};
                model.addRow(rowData);
            } 
        }else if(RCSAS.loginpage.isAdmin){
            for(int i=0; i<RCSAS.allAdmin.size();i++){
                Admin ab = RCSAS.allAdmin.get(i);
                for(int z=0; z<RCSAS.allCoach.size();z++){
                    Coach c = RCSAS.allCoach.get(z);
                    if(RCSAS.current.getID().equals(ab.getID())){
                        if(c.getSportCenterName().equals(ab.getSportCenterName())){
                            Object rowData[] = new Object[]{
                                c.getID(),c.getDateCreated(),c.getDateModified(),c.getUsername(),
                                c.getPassword(),c.getName(),c.getAge(),c.getGender(),
                                c.getPhoneNo(),c.getAddress(),c.getEmail(),c.getSportCenterName(),
                                c.getSportName(),c.getHrRate(),c.getAvgRating(),c.isIsTerminated()};
                            model.addRow(rowData);
                        }
                    }
                }
            }
        }
    }
    
    public void OrderedTable(){
        ArrayList<String> nameList = new ArrayList<String>();
        HashSet<Double> hrRateList = new HashSet<Double>();
        HashSet<Double> ratingList = new HashSet<Double>();
        DefaultTableModel model = (DefaultTableModel) tbCoach.getModel();
        model.setRowCount(0);
        if(rbtnName.isSelected()){
            model.setRowCount(0);
            if(RCSAS.loginpage.isHQAdmin){
                for(int i=0;i<RCSAS.allCoach.size();i++){
                    Coach c = RCSAS.allCoach.get(i);
                    nameList.add(c.getName());
                }
            }else if(RCSAS.loginpage.isAdmin){
                for(int i=0;i<RCSAS.allAdmin.size();i++){
                    Admin a = RCSAS.allAdmin.get(i);
                    for(int z=0;z<RCSAS.allCoach.size();z++){
                        Coach c = RCSAS.allCoach.get(z);
                        if(RCSAS.current.getID().equals(a.getID())){
                            if(a.getSportCenterName().equals(c.getSportCenterName())){
                                nameList.add(c.getName());
                            }
                        }
                    }
                }
            }
            Collections.sort(nameList);
            for(String item: nameList){
                for(int i=0;i<RCSAS.allCoach.size();i++){
                    Coach c = RCSAS.allCoach.get(i);
                    if(item.equals(c.getName())){
                        Object[] orderedNameRow = new Object[]{
                            c.getID(),c.getDateCreated(),c.getDateModified(),c.getUsername(),
                            c.getPassword(),c.getName(),c.getAge(),c.getGender(),
                            c.getPhoneNo(),c.getAddress(),c.getEmail(),c.getSportCenterName(),
                            c.getSportName(),c.getHrRate(),c.getAvgRating(),c.isIsTerminated()};
                        model.addRow(orderedNameRow);
                    }
                }
            }
        }else if(rbtnHourlyRate.isSelected()){
            model.setRowCount(0);
            for(int i=0;i<RCSAS.allCoach.size();i++){
                Coach e = RCSAS.allCoach.get(i);
                hrRateList.add(e.getHrRate());
            }
            
            TreeSet<Double> list = new TreeSet<Double>(hrRateList); 
            if(RCSAS.loginpage.isHQAdmin){
                for(Double item: list){
                    for(int i=0;i<RCSAS.allCoach.size();i++){
                        Coach f = RCSAS.allCoach.get(i);
                        if(item.equals(f.getHrRate())){
                            Object[] orderedRateRow = new Object[]{
                                f.getID(),f.getDateCreated(),f.getDateModified(),f.getUsername(),
                                f.getPassword(),f.getName(),f.getAge(),f.getGender(),
                                f.getPhoneNo(),f.getAddress(),f.getEmail(),f.getSportCenterName(),
                                f.getSportName(),f.getHrRate(),f.getAvgRating(),f.isIsTerminated()};
                            model.addRow(orderedRateRow);
                        }
                    }
                }
            }else if (RCSAS.loginpage.isAdmin){
                for(Double item: list){
                    for(int i=0;i<RCSAS.allAdmin.size();i++){
                        Admin a = RCSAS.allAdmin.get(i);
                        for(int z=0;z<RCSAS.allCoach.size();z++){
                            Coach f = RCSAS.allCoach.get(z);
                            if(item.equals(f.getHrRate()) && (RCSAS.current.getID().equals(a.getID())) && 
                                (a.getSportCenterName().equals(f.getSportCenterName()))){
                                Object[] orderedRateRow = new Object[]{
                                    f.getID(),f.getDateCreated(),f.getDateModified(),f.getUsername(),
                                    f.getPassword(),f.getName(),f.getAge(),f.getGender(),
                                    f.getPhoneNo(),f.getAddress(),f.getEmail(),f.getSportCenterName(),
                                    f.getSportName(),f.getHrRate(),f.getAvgRating(),f.isIsTerminated()};
                                model.addRow(orderedRateRow);
                            }
                        }
                    }
                }
            }
        }else if(rbtnRating.isSelected()){
            model.setRowCount(0);
            for(int i=0;i<RCSAS.allCoach.size();i++){
                Coach d = RCSAS.allCoach.get(i);
                ratingList.add(d.getAvgRating());
            }
            TreeSet<Double> list2 = new TreeSet<Double>(ratingList);
            if(RCSAS.loginpage.isHQAdmin){
                for(Double item: list2){
                    for(int i=0;i<RCSAS.allCoach.size();i++){
                        Coach c = RCSAS.allCoach.get(i);
                        if(item.equals(c.getAvgRating())){
                            Object[] orderedRatingRow = new Object[]{
                                c.getID(),c.getDateCreated(),c.getDateModified(),c.getUsername(),
                                c.getPassword(),c.getName(),c.getAge(),c.getGender(),c.getPhoneNo(),
                                c.getAddress(),c.getEmail(),c.getSportCenterName(),c.getSportName(),
                                c.getHrRate(),c.getAvgRating(),c.isIsTerminated()};
                            model.addRow(orderedRatingRow);
                        }
                    }
                }
            }else if(RCSAS.loginpage.isAdmin){
                for(Double item: list2){
                    for(int i=0;i<RCSAS.allAdmin.size();i++){
                        Admin a = RCSAS.allAdmin.get(i);
                        for(int z=0;z<RCSAS.allCoach.size();z++){
                            Coach c = RCSAS.allCoach.get(z);
                            if( (item.equals(c.getAvgRating())) && (RCSAS.current.getID().equals(a.getID())) && 
                                (a.getSportCenterName().equals(c.getSportCenterName())) ){
                                Object[] orderedRatingRow = new Object[]{
                                    c.getID(),c.getDateCreated(),c.getDateModified(),c.getUsername(),
                                    c.getPassword(),c.getName(),c.getAge(),c.getGender(),c.getPhoneNo(),
                                    c.getAddress(),c.getEmail(),c.getSportCenterName(),c.getSportName(),
                                    c.getHrRate(),c.getAvgRating(),c.isIsTerminated()};
                                model.addRow(orderedRatingRow);
                            }
                        }
                    }
                }
            }
        }else if(rbtnID.isSelected()){
            addRowToJTable();
        }
    }
    
    public void UpdateCoachFile(){
        try{
            PrintWriter p = new PrintWriter("coach.txt");
            for(int i =0;i<RCSAS.allCoach.size();i++){
                Coach c = RCSAS.allCoach.get(i);
                p.println(c.getID());
                p.println(c.getDateCreated());
                p.println(c.getDateModified());
                p.println(c.getUsername());
                p.println(c.getPassword());
                p.println(c.getName());
                p.println(c.getAge());
                p.println(c.getGender());
                p.println(c.getPhoneNo());
                p.println(c.getAddress());
                p.println(c.getEmail());
                p.println(c.getSportCenterName());
                p.println(c.getSportName());
                p.println(c.getHrRate());
                p.println(c.getAvgRating());
                p.println(c.isIsTerminated());
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
        rbtnHourlyRate = new javax.swing.JRadioButton();
        lblCoach = new javax.swing.JLabel();
        rbtnRating = new javax.swing.JRadioButton();
        txtSearch = new javax.swing.JTextField();
        lblOrderBy = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        rbtnName = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbCoach = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        lblCoachID = new javax.swing.JLabel();
        lblCoach1 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        txtHourlyRate = new javax.swing.JTextField();
        comboSportCenter = new javax.swing.JComboBox<>();
        lblInitialRating = new javax.swing.JLabel();
        comboSportName = new javax.swing.JComboBox<>();
        lblGender9 = new javax.swing.JLabel();
        rbtnMale = new javax.swing.JRadioButton();
        rbtnFemale = new javax.swing.JRadioButton();
        txtPhone = new javax.swing.JTextField();
        jLabel160 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rbtnID = new javax.swing.JRadioButton();
        btnRemove = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rbtnHourlyRate.setText("Hourly Rate");
        rbtnHourlyRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnHourlyRateActionPerformed(evt);
            }
        });

        lblCoach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCoach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCoach.setText("Coach");

        rbtnRating.setText("Rating");
        rbtnRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRatingActionPerformed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        lblOrderBy.setText("Order By :");

        lblSearch.setText("Search :");

        rbtnName.setText("Name");
        rbtnName.setToolTipText("");
        rbtnName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNameActionPerformed(evt);
            }
        });

        tbCoach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date Created", "Date Modified", "Username", "Password", "Name", "Age", "Gender", "Phone No", "Address", "Email", "Sport Center Name", "Sport", "Hourly Rate", "Rating", "IsTerminated"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCoach.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCoach.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbCoach.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbCoach);
        if (tbCoach.getColumnModel().getColumnCount() > 0) {
            tbCoach.getColumnModel().getColumn(1).setMinWidth(150);
            tbCoach.getColumnModel().getColumn(2).setMinWidth(150);
            tbCoach.getColumnModel().getColumn(3).setMinWidth(150);
            tbCoach.getColumnModel().getColumn(5).setMinWidth(200);
            tbCoach.getColumnModel().getColumn(9).setMinWidth(200);
            tbCoach.getColumnModel().getColumn(11).setMinWidth(200);
            tbCoach.getColumnModel().getColumn(12).setMinWidth(200);
        }

        btnEdit.setBackground(new java.awt.Color(172, 3, 24));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit Selected");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        lblCoachID.setText("C002");
        lblCoachID.setEnabled(false);

        lblCoach1.setText("Coach ID :");

        label.setText("Username :");

        jLabel151.setText("Password :");

        jLabel152.setText("Name :");

        jLabel153.setText("Address :");

        jLabel154.setText("Email :");

        jLabel155.setText("Phone :");

        jLabel156.setText("Sport Name :");

        jLabel157.setText("Sport Center Name :");

        jLabel158.setText("Rating :");

        jLabel159.setText("Hourly Rate :");

        comboSportCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSportCenterActionPerformed(evt);
            }
        });

        lblInitialRating.setText("0");

        lblGender9.setText("Gender :");

        rbtnMale.setSelected(true);
        rbtnMale.setText("Male");

        rbtnFemale.setText("Female");

        jLabel160.setText("Age :");

        btnAdd.setBackground(new java.awt.Color(172, 3, 24));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGender9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel155)
                            .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel153, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCoach1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel151, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(lblInitialRating, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCoachID, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHourlyRate, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboSportName, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboSportCenter, javax.swing.GroupLayout.Alignment.LEADING, 0, 260, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAge, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(38, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCoach1)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lblCoachID)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel151))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel152))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel160))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnFemale)
                            .addComponent(rbtnMale)
                            .addComponent(lblGender9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel155))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel153))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel154))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSportCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel157))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboSportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel156))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel159))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInitialRating)
                            .addComponent(jLabel158))))
                .addGap(25, 25, 25)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search by Coach ID, Coach Name, Rating");

        rbtnID.setSelected(true);
        rbtnID.setText("ID");
        rbtnID.setToolTipText("");
        rbtnID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIDActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(172, 3, 24));
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("Remove Selected");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                            .addComponent(jSeparator2))))
                .addGap(40, 40, 40))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSearch)
                            .addComponent(lblOrderBy, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(rbtnID, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbtnName, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(rbtnHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(rbtnRating, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(lblCoach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblCoach)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnName)
                    .addComponent(lblOrderBy)
                    .addComponent(rbtnHourlyRate)
                    .addComponent(rbtnRating)
                    .addComponent(rbtnID))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnRemove))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jScrollPane1.setViewportView(jPanel4);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(25, 25, 25))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(btnBack)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNameActionPerformed
        OrderedTable();
    }//GEN-LAST:event_rbtnNameActionPerformed

    private void rbtnHourlyRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnHourlyRateActionPerformed
        OrderedTable();
    }//GEN-LAST:event_rbtnHourlyRateActionPerformed

    private void rbtnRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRatingActionPerformed
        OrderedTable();
    }//GEN-LAST:event_rbtnRatingActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //empty row is selected
        if(tbCoach.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, 
                    "Please select a row to remove it","Error input",
                    JOptionPane.ERROR_MESSAGE);
        }else{ 
            //To get selected Row sport ID
            int selectedRowIndex = tbCoach.getSelectedRow();
            selectedCoachID = tbCoach.getValueAt(selectedRowIndex,0).toString();
            for(int i=0;i<RCSAS.allCoach.size();i++){
                Coach c = RCSAS.allCoach.get(i);
                if(selectedCoachID.equals(c.getID())){
                    if(c.isIsTerminated()){
                        JOptionPane.showMessageDialog(
                                null,
                                "The coach account is terminated before.\nIt cannot be edited.",
                                "Error input",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        RCSAS.adminCoachEdit.setTextField();
                        RCSAS.adminCoachEdit.setVisible(true);
                        this.setEnabled(false);
                    }
                }
            }
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

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        String text = txtSearch.getText();
        String regex = "^\\d{0,3}(\\.\\d{0,2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        String regex1 = "^[a-zA-Z0-9]*$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(text);
        
        String regex2 = "^[a-zA-Z ./]*$";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(text);
        DefaultTableModel model = (DefaultTableModel) tbCoach.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        if(text.equals("")){
            tbCoach.setRowSorter(null);
            addRowToJTable();
        }else{
            if(matcher.matches()){
                tbCoach.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(text.trim(),14));
            }else if(matcher2.matches()){
                text = RCSAS.validation.CapitalizeLetter(text.trim()).trim();
                tbCoach.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(text.trim(),5));
            }else if(matcher1.matches()){
                text = RCSAS.validation.CapitalizeLetter(text.trim()).trim();
                tbCoach.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(text.trim(),0));
            }else{
                tbCoach.setRowSorter(tr);
                tr.setRowFilter(RowFilter.regexFilter(text.trim(),0,5));
            }
        }
        
        
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        dateTime = DateTime.DateTime();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String Name = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String sport = comboSportName.getSelectedItem().toString();
        String sportCenter = comboSportCenter.getSelectedItem().toString();
        double rating = Double.parseDouble(lblInitialRating.getText());
        boolean registeredUN = false;
        boolean registeredName = false;
        double hourlyRate;
        
        //validate empty string
        if ( (username.equals("")) || (password.equals("")) || (Name.equals("")) 
                || (txtAge.getText().equals("")) || (phone.equals("")) || (address.equals(""))
                || (email.equals("")) || (txtHourlyRate.getText().equals("")) ||
                (comboSportCenter.getSelectedItem()=="Select Sport Center") ||
                (comboSportName.getSelectedItem()=="Select Sport")
                ){
            JOptionPane.showMessageDialog(null,"Empty input!","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                String name = RCSAS.validation.CapitalizeLetter(Name);
                //gender
                if(rbtnMale.isSelected()){
                    gender = "M";
                }else if(rbtnFemale.isSelected()){
                    gender = "F";
                }
                //error validation
                int age = Integer.parseInt(txtAge.getText());
                Pattern pattern = Pattern.compile("^\\d{0,3}(\\.\\d{0,2})?$");
                Matcher match = pattern.matcher(txtHourlyRate.getText());
                if(match.matches()){
                    hourlyRate = Double.parseDouble(txtHourlyRate.getText());
                }else{
                    throw new Exception();
                }
                if( (username.length()>20) || (name.length()>50) || (RCSAS.validation.ValidateName(name) == false) || 
                        (RCSAS.validation.ValidatePassword(username) == false) ||
                        (RCSAS.validation.ValidatePhoneNo(phone)==false) || (RCSAS.validation.ValidatePassword(password)==false) ||
                        (RCSAS.validation.ValidateAge(age)==false) || (RCSAS.validation.ValidateEmail(email)==false) ||
                        ((hourlyRate < 100.00) || (hourlyRate > 500.00)) ){
                    throw new Exception();
                    
                }
                //validate registered username/name
                for(int i=0; i < RCSAS.allCoach.size(); i++){
                    Coach c = RCSAS.allCoach.get(i);
                    if (username.equals(c.getUsername())){
                        registeredUN = true;
                        throw new Exception();
                    }else if (name.equals(c.getName())){
                        registeredName = true;
                        throw new Exception();
                    }
                }
                boolean isTerminated = false;
                
                //update
                Coach C = new Coach(age,gender,hourlyRate,rating,sportCenter,sport,id,dateTime,dateTime,username,password,name,phone,address,email,isTerminated);
                RCSAS.allCoach.add(C);
                JOptionPane.showMessageDialog(null,"New coach is added successfully");
                initiateCoachForm();
                OrderedTable();
                UpdateCoachFile();
            }catch(Exception e){
                if(registeredUN){
                    JOptionPane.showMessageDialog(null,"Registered username","Error input",JOptionPane.ERROR_MESSAGE);
                }else if(registeredName){
                    JOptionPane.showMessageDialog(null,"Registered name","Error input",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Uhh Ohh! "
                            + "\n*Username <= 20 characters"
                            + "\n*Name must <= 50 characters and not contain symbols(except '.' and '/') and digits"
                            + "\n*Username and Password must not contain spaces and symbols (except '.', '@', '/', '_', '*', '#', '!') )"
                            + "\n*Phone (e.g. ___-______ 012-3456789)"
                            + "\n*Only accept digits for age (1-99)"
                            + "\n*Email (e.g. __@__.__)"
                            + "\n*Only accept digits for hourly rate (100-500)",
                            "Error input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }  
    }//GEN-LAST:event_btnAddActionPerformed

    private void rbtnIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIDActionPerformed
        OrderedTable();
    }//GEN-LAST:event_rbtnIDActionPerformed

    private void comboSportCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSportCenterActionPerformed
        comboSportName.removeAllItems();
        comboSportName.addItem("Select Sport");
        for(int z=0; z<RCSAS.allSport.size();z++){
            Sport sp = RCSAS.allSport.get(z);
            if(sp.getSportCenterName().equals(comboSportCenter.getSelectedItem())){
                comboSportName.addItem(sp.getSportName());
            }
        }
    }//GEN-LAST:event_comboSportCenterActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.setVisible(false);
        RCSAS.mainpage.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        //empty row is selected
        if(tbCoach.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, 
                    "Please select a row to remove it","Error input",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            //To get selected Row sport ID
            int selectedRowIndex = tbCoach.getSelectedRow();
            selectedCoachID = tbCoach.getValueAt(selectedRowIndex,0).toString();
            for(int i=0;i<RCSAS.allCoach.size();i++){
                Coach c = RCSAS.allCoach.get(i);
                if(selectedCoachID.equals(c.getID())){
                    if(c.isIsTerminated()){
                        JOptionPane.showMessageDialog(
                                null,
                                "The coach account is terminated before.\nIt cannot be removed.",
                                "Error input",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        c.setIsTerminated(true);
                        c.setDateModified(DateTime.DateTime());
                        JOptionPane.showMessageDialog(null,"The coach is terminated.");
                        UpdateCoachFile();
                        addRowToJTable();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

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
            java.util.logging.Logger.getLogger(AdminCoachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminCoachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminCoachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminCoachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminCoachForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox<String> comboSportCenter;
    private javax.swing.JComboBox<String> comboSportName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblCoach;
    private javax.swing.JLabel lblCoach1;
    private javax.swing.JLabel lblCoachID;
    private javax.swing.JLabel lblGender9;
    private javax.swing.JLabel lblInitialRating;
    private javax.swing.JLabel lblOrderBy;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JRadioButton rbtnFemale;
    private javax.swing.JRadioButton rbtnHourlyRate;
    private javax.swing.JRadioButton rbtnID;
    private javax.swing.JRadioButton rbtnMale;
    private javax.swing.JRadioButton rbtnName;
    private javax.swing.JRadioButton rbtnRating;
    private javax.swing.JTable tbCoach;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHourlyRate;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
