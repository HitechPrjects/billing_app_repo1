
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HiTech
 */
public class updateemp extends javax.swing.JFrame {

    /**
     * Creates new form updateemp
     */
    public updateemp() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        emcontact = new javax.swing.JTextField();
        contact = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        searched = new javax.swing.JButton();
        back = new javax.swing.JButton();
        update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel7.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Update User Details");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(110, 50, 520, 60);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee ID");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(130, 140, 110, 40);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(180, 190, 50, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contact");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(160, 230, 70, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Emergency Contact");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(80, 270, 160, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(160, 310, 70, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(180, 390, 60, 30);

        email.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(email);
        email.setBounds(240, 390, 280, 30);

        address.setColumns(20);
        address.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        address.setRows(5);
        jScrollPane1.setViewportView(address);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(240, 310, 280, 70);

        emcontact.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(emcontact);
        emcontact.setBounds(240, 270, 270, 30);

        contact.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(contact);
        contact.setBounds(240, 230, 270, 30);

        name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        getContentPane().add(name);
        name.setBounds(240, 190, 270, 30);

        id.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idMouseClicked(evt);
            }
        });
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        getContentPane().add(id);
        id.setBounds(242, 143, 270, 30);

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setLayout(null);

        searched.setBackground(new java.awt.Color(255, 51, 102));
        searched.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        searched.setForeground(new java.awt.Color(255, 255, 255));
        searched.setText("Search");
        searched.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchedActionPerformed(evt);
            }
        });
        jPanel1.add(searched);
        searched.setBounds(520, 140, 117, 31);

        back.setBackground(new java.awt.Color(255, 51, 102));
        back.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back);
        back.setBounds(510, 450, 117, 31);

        update.setBackground(new java.awt.Color(255, 51, 102));
        update.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update);
        update.setBounds(240, 450, 117, 31);

        Delete.setBackground(new java.awt.Color(255, 51, 102));
        Delete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        jPanel1.add(Delete);
        Delete.setBounds(370, 450, 117, 31);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background.jpg"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 1920, 1080);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 2000, 970);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Menu m=new Menu();
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Update empdetails set  name=?,contact=?,emcontact=?,address=?,email=? where empid=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,name.getText());
            pst.setString(2,contact.getText());
            pst.setString(3,emcontact.getText());
            pst.setString(4,address.getText());
            pst.setString(5,email.getText());
            pst.setString(6,id.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Update Successfully...");
            id.setText("");
            name.setText("");
            contact.setText("");
            emcontact.setText("");
            address.setText("");
            email.setText("");

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);

        }
    }//GEN-LAST:event_updateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Delete from empdetails where empid=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,id.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"The Record is Deleted Successfull");

            id.setText("");

            address.setText("");
            emcontact.setText("");
            email.setText("");
            contact.setText("");
            name.setText("");

            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }

    }//GEN-LAST:event_DeleteActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idMouseClicked

    }//GEN-LAST:event_idMouseClicked

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed

    }//GEN-LAST:event_idActionPerformed

    private void searchedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchedActionPerformed
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select * from empdetails where empid=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, id.getText());
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                name.setText(rs.getString("name"));
                contact.setText(rs.getString("Contact"));
                address.setText(rs.getString("address"));
                emcontact.setText(rs.getString("Emcontact"));
                email.setText(rs.getString("Email"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Record not found");

            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_searchedActionPerformed

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
            java.util.logging.Logger.getLogger(updateemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updateemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updateemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updateemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateemp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JTextArea address;
    private javax.swing.JButton back;
    private javax.swing.JTextField contact;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emcontact;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JButton searched;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
