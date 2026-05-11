/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SRC;

import java.sql.PreparedStatement;

/**
 *
 * @author reyes
 */
public class payment_info extends javax.swing.JPanel {
    private String roomNumber, firstname, lastname,
            phone, CID, COD, email, location, birthday, num_residence, roomType, details;
    private Double rate;
    private int age;
    private SRC.NewJFrame frame;
    
    public payment_info(SRC.NewJFrame frame, String RN,
            String firstname, String lastname, String phone, 
            String CID, String COD, String email, String location,
            int age, String birthday, String num_residence,
            String roomType, Double rate, String details) {
        
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.CID = CID;
        this.COD = COD;
        this.email = email;
        this.location = location;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
        this.num_residence = num_residence;
        this.roomType = roomType;
        this.rate = rate;
        this.details = details;
        
        this.roomNumber = RN;
        this.frame = frame;
        
        /*
        roomNumber, firstname, lastname, phone, CI, CO, email, location
        age, Birthday, residence 
        
        - get from roomIfo class
        
        - insert into database all infos 
        */
    
        initComponents();
        
        suite.setText(roomType);
        CI.setText(CID);
        CO.setText(COD);
        residence.setText(num_residence);
        total.setText(String.valueOf(rate));
        
    }
    
    //insert into transaction
    public void transaction_info() {

    try {

        java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hms",
                "root",
                ""
        );

        String sql =
        "INSERT INTO transaction ("
        + "client_firstName, "
        + "client_lastName, "
        + "client_age, "
        + "client_birthday, "
        + "client_email, "
        + "client_phoneNumber, "
        + "check_in_datetime, "
        + "check_out_datetime, "
        + "employee_firstName, "
        + "room_type, "
        + "room_number, "
        + "amount, "
        + "payment_type, "
        + "payment_date"
        + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

java.sql.PreparedStatement ps = con.prepareStatement(sql);

// CLIENT INFO
ps.setString(1, firstname);
ps.setString(2, lastname);
ps.setInt(3, age);

// DATE FIX
ps.setString(4, "August 18 2005");

ps.setString(5, email);
ps.setString(6, phone);

// CHECK IN / OUT
ps.setString(7, CID);
ps.setString(8, COD);

// EMPLOYEE INFO
ps.setString(9, SRC.Login.employee_name);

// ROOM INFO
ps.setString(10, roomType);
ps.setString(11, roomNumber);

// PAYMENT INFO
ps.setDouble(12, Double.parseDouble(amount.getText().toString()));

ps.setString(13, status.getSelectedItem().toString());

// PAYMENT DATE FIX
ps.setString(14, "08-18-2005");

ps.executeUpdate();

        ps.close();
        con.close();

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Transaction Saved!"
        );

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    //insert into reservation table
    //insert  
    public void payment_reservation_info() {

    try {

        java.sql.Connection con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hms",
                "root",
                ""
        );

        String sql =
                "INSERT INTO reservation ("
                + "room_type, "
                + "check_in_datetime, "
                + "check_out_datetime, "
                + "rates, "
                + "client_firstName, "
                + "client_lastName, "
                + "client_age, "
                + "client_birthday, "
                + "client_email, "
                + "client_phoneNumber, "
                + "location, "
                + "payment_status, "
                + "employee_firstName, "
                + "room_id"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, roomType);
        ps.setString(2, CID);
        ps.setString(3, COD);
        ps.setDouble(4, rate);

        ps.setString(5, firstname);
        ps.setString(6, lastname);
        ps.setInt(7, age);

        ps.setString(8, "Augsut 18, 2005");
        ps.setString(9, email);
        ps.setString(10, phone);

        ps.setString(11, location);

        ps.setString(12, status.getSelectedItem().toString());

        ps.setString(13, SRC.Login.employee_name);

        ps.setString(14, roomNumber);

        ps.executeUpdate();

        ps.close();
        con.close();

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Reservation Saved!"
        );

        frame.setContentPane(new Ginto(frame));
        frame.revalidate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    //insert into rooms
    public void room_info(){
         try {
    
    java.sql.Connection con = java.sql.DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/hms",
            "root",
            ""
    );

    String sql =
            "UPDATE rooms SET room_type=?, details=?, rate=?, status=? WHERE room_number=?";

    java.sql.PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, roomType);
    ps.setString(2, details);
    ps.setDouble(3, rate);
    ps.setString(4, "Booked");
    ps.setString(5, roomNumber);

    int updated = ps.executeUpdate();

    ps.close();

    if (updated == 0) {

        String insert =
                "INSERT INTO rooms (room_number, room_type, details, rate, status) VALUES (?, ?, ?, ?, ?)";

        java.sql.PreparedStatement ps2 = con.prepareStatement(insert);

        ps2.setString(1, roomNumber);
        ps2.setString(2, roomType);
        ps2.setString(3, details);
        ps2.setDouble(4, rate);
        ps2.setString(5, "Booked");

        ps2.executeUpdate();
        ps2.close();
    }

    con.close();

    javax.swing.JOptionPane.showMessageDialog(this, "Room Booked!");

    frame.setContentPane(new Ginto(frame));
    frame.revalidate();

} catch (Exception e) {
    e.printStackTrace();
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

        P_suites = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        CI = new javax.swing.JTextField();
        CO = new javax.swing.JTextField();
        residence = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        suite = new javax.swing.JTextField();
        back = new javax.swing.JButton();
        proceed = new javax.swing.JButton();
        users = new javax.swing.JButton();
        guests = new javax.swing.JButton();
        transactions = new javax.swing.JButton();
        suites = new javax.swing.JButton();
        home = new javax.swing.JButton();
        register = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        P_suites.setBackground(new java.awt.Color(102, 0, 0));

        jLabel2.setBackground(new java.awt.Color(102, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SUITES");
        P_suites.add(jLabel2);

        add(P_suites);
        P_suites.setBounds(840, 20, 60, 26);

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full Payment", "Half Payment" }));
        add(status);
        status.setBounds(685, 148, 160, 22);
        add(CI);
        CI.setBounds(203, 200, 170, 22);
        add(CO);
        CO.setBounds(203, 253, 170, 22);
        add(residence);
        residence.setBounds(203, 305, 170, 22);
        add(total);
        total.setBounds(203, 357, 170, 22);

        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });
        add(amount);
        amount.setBounds(683, 201, 170, 22);
        add(suite);
        suite.setBounds(203, 148, 170, 22);

        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        add(back);
        back.setBounds(40, 460, 110, 30);

        proceed.setBorderPainted(false);
        proceed.setContentAreaFilled(false);
        proceed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedActionPerformed(evt);
            }
        });
        add(proceed);
        proceed.setBounds(150, 460, 110, 30);

        users.setBorderPainted(false);
        users.setContentAreaFilled(false);
        users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersActionPerformed(evt);
            }
        });
        add(users);
        users.setBounds(610, 20, 70, 30);

        guests.setBorderPainted(false);
        guests.setContentAreaFilled(false);
        guests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestsActionPerformed(evt);
            }
        });
        add(guests);
        guests.setBounds(680, 20, 70, 30);

        transactions.setBorderPainted(false);
        transactions.setContentAreaFilled(false);
        transactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionsActionPerformed(evt);
            }
        });
        add(transactions);
        transactions.setBounds(770, 20, 70, 30);

        suites.setBorderPainted(false);
        suites.setContentAreaFilled(false);
        suites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suitesActionPerformed(evt);
            }
        });
        add(suites);
        suites.setBounds(840, 20, 70, 30);

        home.setBorderPainted(false);
        home.setContentAreaFilled(false);
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        add(home);
        home.setBounds(900, 20, 60, 30);

        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        add(register);
        register.setBounds(540, 20, 70, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/payment.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 960, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        frame.setContentPane(new Register(frame));
        frame.revalidate();
    }//GEN-LAST:event_registerActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        frame.setContentPane(new Dashboard(frame));
        frame.revalidate();
    }//GEN-LAST:event_homeActionPerformed

    private void guestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestsActionPerformed
        frame.setContentPane(new GuestList(frame));
        frame.revalidate();
    }//GEN-LAST:event_guestsActionPerformed

    private void transactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionsActionPerformed
        frame.setContentPane(new Transactions(frame));
        frame.revalidate();
    }//GEN-LAST:event_transactionsActionPerformed

    private void suitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suitesActionPerformed
        frame.setContentPane(new Suites(frame));
        frame.revalidate();
    }//GEN-LAST:event_suitesActionPerformed

    private void usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersActionPerformed
        frame.setContentPane(new UserList(frame));
        frame.revalidate();
    }//GEN-LAST:event_usersActionPerformed

    private void proceedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedActionPerformed
       room_info();
       payment_reservation_info();
       transaction_info();
    }//GEN-LAST:event_proceedActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CI;
    private javax.swing.JTextField CO;
    private javax.swing.JPanel P_suites;
    private javax.swing.JTextField amount;
    private javax.swing.JButton back;
    private javax.swing.JButton guests;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton proceed;
    private javax.swing.JButton register;
    private javax.swing.JTextField residence;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField suite;
    private javax.swing.JButton suites;
    private javax.swing.JTextField total;
    private javax.swing.JButton transactions;
    private javax.swing.JButton users;
    // End of variables declaration//GEN-END:variables
}
