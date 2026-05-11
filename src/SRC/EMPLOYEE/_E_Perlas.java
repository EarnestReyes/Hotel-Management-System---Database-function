/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SRC.EMPLOYEE;
import SRC.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;

/**
 *
 * @author reyes
 */
public class _E_Perlas extends javax.swing.JPanel {
   
    private SRC.NewJFrame frame;
    public _E_Perlas(SRC.NewJFrame frame) {
        this.frame = frame;
        
      
        initComponents();
        // Load all rooms
        loadRoomStatus(P1, "P - 001");
        loadRoomStatus(P2,"P - 002");
        loadRoomStatus(P3,"P - 003");
        loadRoomStatus(p4,"P - 004");
        loadRoomStatus(p5, "P - 005");
        loadRoomStatus(p6, "P - 006");
        loadRoomStatus(p7,"P - 007");
        loadRoomStatus(p8, "P - 008");
        loadRoomStatus(p9, "P - 009");
        loadRoomStatus(p10, "P - 010");
    }

    // 🔥 UPDATE OR INSERT ROOM
    public static void updateRoom(String number, String roomtype, String details, double rate, String status) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        java.sql.Connection con = java.sql.DriverManager.getConnection(
        "jdbc:sqlserver://localhost:1433;databaseName=HMS;encrypt=true;trustServerCertificate=true;",
        "sa",
        "DB123"
        );

            String sql =
                    "UPDATE rooms SET room_type=?, details=?, rate=?, status=? WHERE room_number=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, roomtype);
            ps.setString(2, details);
            ps.setDouble(3, rate);
            ps.setString(4, status);
            ps.setString(5, number);

            int updated = ps.executeUpdate();

            if (updated == 0) {

                String insert =
                        "INSERT INTO rooms (room_number, room_type, details, rate, status) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement ps2 = con.prepareStatement(insert);

                ps2.setString(1, number);
                ps2.setString(2, roomtype);
                ps2.setString(3, details);
                ps2.setDouble(4, rate);
                ps2.setString(5, status);

                ps2.executeUpdate();
                ps2.close();
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔥 ROOM CLICK → GO TO ROOM INFO
    public static String selectedRoom;

    public void openRoom(String roomNumber, JButton button) {
        
        //green
        if (button.getBackground().equals(new Color(51, 153, 0))) {

        selectedRoom = roomNumber;
        JPanel panel = (JPanel) frame.getContentPane();
        frame.setContentPane(new RoomInfo(frame, roomNumber, "Perlas", "Perlas room", 1500, panel));
        frame.revalidate();

        } else {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to open this room?",
                    "Open Room",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                // make room open again
                //red
                button.setBackground(new Color(51, 153, 0));

                updateRoom(
                        roomNumber,
                        "Perlas",
                        "Perlas Room",
                        1500.00,
                        "Open"
                );

                JOptionPane.showMessageDialog(this, "Room is now OPEN.");
            }
        }
    }

    // 🔥 LOAD ROOM COLOR FROM DB
    public void loadRoomStatus(JButton btn, String room) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        java.sql.Connection con = java.sql.DriverManager.getConnection(
        "jdbc:sqlserver://localhost:1433;databaseName=HMS;encrypt=true;trustServerCertificate=true;",
        "sa",
        "DB123"
        );

            String sql = "SELECT status FROM rooms WHERE room_number=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, room);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String status = rs.getString("status");

                if (status.equals("Booked")) {
                    btn.setBackground(Color.RED);
                } else if (status.equals("Open")) {
                    btn.setBackground(new Color(51, 153, 0));
                }

                btn.setOpaque(true);
                btn.setBorderPainted(false);
            }

            rs.close();
            ps.close();
            con.close();

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
        suites = new javax.swing.JButton();
        home = new javax.swing.JButton();
        P1 = new javax.swing.JButton();
        P2 = new javax.swing.JButton();
        P3 = new javax.swing.JButton();
        p4 = new javax.swing.JButton();
        p5 = new javax.swing.JButton();
        p6 = new javax.swing.JButton();
        p7 = new javax.swing.JButton();
        p8 = new javax.swing.JButton();
        p9 = new javax.swing.JButton();
        p10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        P_suites.setBackground(new java.awt.Color(102, 0, 0));

        jLabel2.setBackground(new java.awt.Color(102, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ROOMS");
        P_suites.add(jLabel2);

        add(P_suites);
        P_suites.setBounds(20, 80, 50, 20);

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

        P1.setBackground(new java.awt.Color(51, 153, 0));
        P1.setText("P - 001");
        P1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                P1StateChanged(evt);
            }
        });
        P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1ActionPerformed(evt);
            }
        });
        add(P1);
        P1.setBounds(126, 185, 90, 90);

        P2.setBackground(new java.awt.Color(51, 153, 0));
        P2.setText("P - 002");
        P2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P2ActionPerformed(evt);
            }
        });
        add(P2);
        P2.setBounds(283, 185, 90, 90);

        P3.setBackground(new java.awt.Color(51, 153, 0));
        P3.setText("P - 003");
        P3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P3ActionPerformed(evt);
            }
        });
        add(P3);
        P3.setBounds(441, 185, 90, 90);

        p4.setBackground(new java.awt.Color(51, 153, 0));
        p4.setText("P - 004");
        p4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p4ActionPerformed(evt);
            }
        });
        add(p4);
        p4.setBounds(597, 185, 90, 90);

        p5.setBackground(new java.awt.Color(51, 153, 0));
        p5.setText("P - 005");
        p5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p5ActionPerformed(evt);
            }
        });
        add(p5);
        p5.setBounds(752, 185, 90, 90);

        p6.setBackground(new java.awt.Color(51, 153, 0));
        p6.setText("P - 006");
        p6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p6ActionPerformed(evt);
            }
        });
        add(p6);
        p6.setBounds(128, 337, 90, 90);

        p7.setBackground(new java.awt.Color(51, 153, 0));
        p7.setText("P - 007");
        p7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p7ActionPerformed(evt);
            }
        });
        add(p7);
        p7.setBounds(283, 337, 90, 90);

        p8.setBackground(new java.awt.Color(51, 153, 0));
        p8.setText("P - 008");
        p8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p8ActionPerformed(evt);
            }
        });
        add(p8);
        p8.setBounds(440, 337, 90, 90);

        p9.setBackground(new java.awt.Color(51, 153, 0));
        p9.setText("P - 009");
        p9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p9ActionPerformed(evt);
            }
        });
        add(p9);
        p9.setBounds(597, 337, 90, 90);

        p10.setBackground(new java.awt.Color(51, 153, 0));
        p10.setText("P - 010");
        p10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p10ActionPerformed(evt);
            }
        });
        add(p10);
        p10.setBounds(754, 337, 90, 90);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/E_Rooms.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 960, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        frame.setContentPane(new E_Suites(frame));
        frame.revalidate();
    }//GEN-LAST:event_homeActionPerformed

    private void suitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suitesActionPerformed
       frame.setContentPane(new Suites(frame));
        frame.revalidate();
    }//GEN-LAST:event_suitesActionPerformed

    private void P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1ActionPerformed
       // openRoom("001");

            openRoom("P - 001", P1);

    }//GEN-LAST:event_P1ActionPerformed

    private void P1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_P1StateChanged
       
    }//GEN-LAST:event_P1StateChanged

    private void P2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P2ActionPerformed
        
            openRoom("P - 002", P2);

    }//GEN-LAST:event_P2ActionPerformed

    private void P3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P3ActionPerformed
      
            openRoom("P - 003", P3);

      
    }//GEN-LAST:event_P3ActionPerformed

    private void R4ActionPerformed(java.awt.event.ActionEvent evt) {
       
            openRoom("P - 004", p4);
            
    }

    private void R5ActionPerformed(java.awt.event.ActionEvent evt) {
      
            openRoom("P - 005", p5);
    }
    private void R6ActionPerformed(java.awt.event.ActionEvent evt) {
       
            openRoom("P - 006", p6);

    }

    private void R7ActionPerformed(java.awt.event.ActionEvent evt) {
        
            openRoom("P - 007", p7);

    }

    private void R8ActionPerformed(java.awt.event.ActionEvent evt) {
       
            openRoom("P - 008", p8);

    }

    private void R9ActionPerformed(java.awt.event.ActionEvent evt) {
       
            openRoom("P - 009", p9);

    }

    private void R10ActionPerformed(java.awt.event.ActionEvent evt) {
      
            openRoom("P - 010", p10);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton P1;
    private javax.swing.JButton P2;
    private javax.swing.JButton P3;
    private javax.swing.JPanel P_suites;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton p10;
    private javax.swing.JButton p4;
    private javax.swing.JButton p5;
    private javax.swing.JButton p6;
    private javax.swing.JButton p7;
    private javax.swing.JButton p8;
    private javax.swing.JButton p9;
    private javax.swing.JButton suites;
    // End of variables declaration//GEN-END:variables
} 


