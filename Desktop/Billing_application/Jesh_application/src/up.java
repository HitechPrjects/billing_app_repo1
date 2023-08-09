
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HTGE
 */
public class up extends javax.swing.JFrame {

    /**
     * Creates new form up
     */
    public up() {
        initComponents();
         setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
  
double x,y,z,per,xy;
 private static String input;
    private static int num;
    private static String[] units=
    {"",
     " One",
     " Two",
     " Three",
     " Four",
     " Five",
     " Six",
     " Seven",
     " Eight",
     " Nine"
    };
    private static String[] teen=
   {" Ten",
    " Eleven",
    " Twelve",
    " Thirteen",
    " Fourteen",
    " Fifteen",
    " Sixteen",
    " Seventeen",
    " Eighteen",
    " Nineteen"
   };
   private static String[] tens=
  { " Twenty",
    " Thirty",
    " Forty",
    " Fifty",
    " Sixty",
    " Seventy",
    " Eighty",
    " Ninety"
  };
  private static String[] maxs=
 {"",
  "",
  " Hundred",
  " Thousand",
  " Lakh",
  " Crore"
 };

    
    public String convertNumberToWords(double n)
    {
        input=numToString((int) n);
        String converted=""; 
        int pos=1; 
        boolean hun=false;
        while(input.length()> 0)
        {
            if(pos==1) // TENS AND UNIT POSITION
            {   if(input.length()>= 2) // TWO DIGIT NUMBERS
                {   
                 String temp=input.substring(input.length()-2,input.length());
                 input=input.substring(0,input.length()-2);
                 converted+=digits(temp);
                }
                else if(input.length()==1) // 1 DIGIT NUMBER
                {
                 converted+=digits(input); 
                 input="";
                }
                pos++;
            }
            else if(pos==2) // HUNDRED POSITION
            { 
                String temp=input.substring(input.length()-1,input.length());
                input=input.substring(0,input.length()-1);
                if(converted.length()> 0&&digits(temp)!="")
                {
                    converted=(digits(temp)+maxs[pos]+" and")+converted;
                    hun=true;
                }
                else
                {
                    if
                    (digits(temp)=="");
                    else
                    converted=(digits(temp)+maxs[pos])+converted;hun=true;
                }
                pos++;
            }
            else if(pos > 2) // REMAINING NUMBERS PAIRED BY TWO
            {
                if(input.length()>= 2) // EXTRACT 2 DIGITS
                {  
                 String temp=input.substring(input.length()-2,input.length());
                 input=input.substring(0,input.length()-2);
                   if(!hun&&converted.length()> 0)
                        converted=digits(temp)+maxs[pos]+" and"+converted;
                    else
                    {
                        if(digits(temp)=="")  ;
                        else
                        converted=digits(temp)+maxs[pos]+converted;
                    }
                 }
                 else if(input.length()==1) // EXTRACT 1 DIGIT
                 {
                   if(!hun&&converted.length()> 0)
                    converted=digits(input)+maxs[pos]+" and"+converted;
                    else
                    {
                        if(digits(input)=="")  ;
                        else
                        converted=digits(input)+maxs[pos]+converted;
                        input="";
                    }
                 }
                 pos++; 
             }
        }
        return converted;
    }
    private String digits(String temp) // TO RETURN SELECTED NUMBERS IN WORDS
    {
        String converted="";
        for(int i=temp.length()-1;i >= 0;i--)
        {   int ch=temp.charAt(i)-48;
            if(i==0&&ch>1 && temp.length()> 1)
            converted=tens[ch-2]+converted; // IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
            else if(i==0&&ch==1&&temp.length()==2) // IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS
            {
                int sum=0;
                for(int j=0;j < 2;j++)
                sum=(sum*10)+(temp.charAt(j)-48);
                return teen[sum-10];
            }
            else
            {
                if(ch > 0)
                converted=units[ch]+converted;
            } // IF SINGLE DIGIT PROVIDED
        }
        return converted;
    }
    private String numToString(int x) // CONVERT THE NUMBER TO STRING
    {
        String num="";
        while(x!=0)
        {
            num=((char)((x%10)+48))+num;
            x/=10;
        }
        return num;
    }
    private void inputNumber()
    {
        Scanner in=new Scanner(System.in);
        try
        {
          System.out.print("Please enter number to Convert into Words : ");
          num=in.nextInt();
        }
        catch(Exception e)
        {
         System.out.println("Number should be Less than 1 Arab ");
         System.exit(1);
        }
    }
  

public void dele()
{
   String sql="Delete from bill where billno=?";
try
{
 Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, bno.getText());
              pst.executeUpdate();
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null,e);
} 
}

public void report()  
{
  try {    
                Class.forName("com.mysql.jdbc.Driver");                
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
                String path="src\\report1.jrxml";
               HashMap a=new HashMap();
               a.put("invo", bno.getText());

                JasperReport jr;
                jr = JasperCompileManager.compileReport(path);        
                JasperPrint  jp = JasperFillManager.fillReport(jr,a,con);
                JasperViewer.viewReport(jp,false);
} catch (Exception e) 
{
            JOptionPane.showMessageDialog(null, "error "+e);
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

        jPanel1 = new javax.swing.JPanel();
        bno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        name = new javax.swing.JTextField();
        cont = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        add = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        des = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pname = new javax.swing.JTextArea();
        hsn = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        rt = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cou = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        gst = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        cgst = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sgst = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        subt = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        tax = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        tot = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setLayout(null);

        bno.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPanel1.add(bno);
        bno.setBounds(160, 80, 190, 40);

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bill number");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 80, 120, 34);

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 140, 80, 30);

        search.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel1.add(search);
        search.setBounds(370, 80, 100, 40);

        name.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(name);
        name.setBounds(160, 140, 320, 30);

        cont.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(cont);
        cont.setBounds(160, 180, 320, 30);

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contact");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 180, 80, 30);

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Date");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 220, 100, 30);

        date.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(date);
        date.setBounds(160, 220, 320, 30);

        add.setColumns(20);
        add.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        add.setRows(5);
        jScrollPane2.setViewportView(add);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(160, 270, 320, 70);

        jLabel12.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Address");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 290, 100, 30);

        jLabel16.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Destination");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 370, 120, 30);

        des.setColumns(20);
        des.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        des.setRows(5);
        jScrollPane3.setViewportView(des);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(160, 360, 320, 70);

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(" Description ");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 470, 120, 30);

        pname.setColumns(20);
        pname.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pname.setRows(5);
        jScrollPane1.setViewportView(pname);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(160, 460, 320, 70);

        hsn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(hsn);
        hsn.setBounds(680, 50, 320, 30);

        jLabel15.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("HSN/SAC");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(550, 50, 100, 30);

        jLabel34.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Rate");
        jPanel1.add(jLabel34);
        jLabel34.setBounds(550, 90, 110, 30);

        rt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        rt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtActionPerformed(evt);
            }
        });
        jPanel1.add(rt);
        rt.setBounds(680, 90, 320, 30);

        jLabel33.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Count");
        jPanel1.add(jLabel33);
        jLabel33.setBounds(550, 130, 90, 30);

        cou.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(cou);
        cou.setBounds(680, 130, 320, 30);

        jLabel17.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("GST Per");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(550, 170, 100, 30);

        gst.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        gst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gstKeyPressed(evt);
            }
        });
        jPanel1.add(gst);
        gst.setBounds(680, 170, 320, 30);

        jButton5.setBackground(new java.awt.Color(255, 51, 102));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Enter for GST Calculation");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(720, 210, 240, 40);

        cgst.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(cgst);
        cgst.setBounds(680, 260, 320, 30);

        jLabel14.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CGST");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(560, 250, 100, 30);

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("SGST");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(560, 300, 100, 30);

        sgst.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(sgst);
        sgst.setBounds(680, 300, 320, 30);

        jLabel13.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sub-Total");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(560, 340, 100, 30);

        subt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        subt.setText("0");
        subt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtActionPerformed(evt);
            }
        });
        jPanel1.add(subt);
        subt.setBounds(680, 340, 320, 30);

        jLabel36.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Tax");
        jPanel1.add(jLabel36);
        jLabel36.setBounds(560, 390, 100, 30);

        tax.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.add(tax);
        tax.setBounds(680, 380, 320, 30);

        jButton16.setBackground(new java.awt.Color(255, 51, 102));
        jButton16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton16.setText("Add");
        jButton16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16);
        jButton16.setBounds(770, 420, 110, 40);

        tot.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tot.setText("0");
        jPanel1.add(tot);
        tot.setBounds(680, 470, 320, 30);

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(560, 470, 90, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(510, 540, 140, 40);

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tab);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(510, 590, 690, 210);

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(660, 540, 130, 40);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(950, 540, 120, 40);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Remove");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(810, 540, 120, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 3860, 1410);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
      
        report();
        data();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select * from billing where bno=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, bno.getText());
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                cont.setText(rs.getString("contact"));
                name.setText(rs.getString("name"));
                date.setText(rs.getString("date"));
                add.setText(rs.getString("Address"));

            }

        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_searchActionPerformed

    private void rtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtActionPerformed

    }//GEN-LAST:event_rtActionPerformed

    private void gstKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gstKeyPressed

    }//GEN-LAST:event_gstKeyPressed
public void data()
    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select billno,hsn,product,rate,count,cgst,sgst,gst,subtotal from bill where billno=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,bno.getText());
            ResultSet rs=pst.executeQuery();
            tab.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        x=Double.parseDouble(rt.getText());
        int qnt,gt;
        String g=gst.getText();
        qnt=Integer.parseInt(cou.getText());

        double dd;

        //   if(evt.g==KeyEvent.VK_ENTER)
        {
            y=x*qnt;
            subt.setText(String.format("%.2f", y));
            gt=Integer.parseInt(gst.getText());
            dd=y*gt/100;
            double cg;
            cg=dd/2;
            tax.setText(String.format("%.2f", dd));
            cgst.setText(String.format("%.2f", cg));
            sgst.setText(String.format("%.2f", cg));
            double tt;
            tt=y+dd;

        }
    }//GEN-LAST:event_jButton5ActionPerformed
double itotal;
    private void subtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtActionPerformed
public void clear()
{
    pname.setText("");
    rt.setText("");
    hsn.setText("");
    cou.setText("");
    gst.setText("");
}
    
    public void totalf()
{
    
   try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select sum(subtotal) from bill where billno='"+bno.getText()+"'";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                String sum=rs.getString("sum(subtotal)");
               itotal=Double.parseDouble(sum);
            }
            double dd,tt,tk;
            dd=Double.parseDouble(gst.getText());
            tt=itotal*dd/100;
            tk=tt+itotal;
            tot.setText(String.format("%.2f", tk));
           
           
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }  
}
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        adddata();
        data();
        totalf();
        clear();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cgsttot();
        sgsttot();
        sutot();
        store();
        String ffd=tot.getText();
        String ff=tax.getText();
        String amx,mx;
        double aamt,at,aa,ab,ac;
        aamt = Double.parseDouble(ffd);
        at = Double.parseDouble(ff);
        amx= convertNumberToWords(aamt)+" Rupees Only";
        mx= convertNumberToWords(at)+" Rupees Only";
        try
        {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");

            String sql="update billing set name=?,contact=?,date=?,address=?,dest=?,pname=?,count=?,cgst=?,sgst=?,hsn=?,subtotal=?,tax=?,total=?,rate=?,srno=?,irate=?,iitem=?,icount=?,icgst=?,isgst=?,ihsn=?,itax=?,isubt=?,words=?,tword=?,itax1=? where bno=?";
            PreparedStatement pst=con.prepareStatement(sql);

             pst.setString(27,bno.getText());
            pst.setString(1,name.getText());
            pst.setString(2,cont.getText());
            pst.setString(3,date.getText());
            pst.setString(4,add.getText());
            pst.setString(5,des.getText());
            
            pst.setString(6,pname.getText());
           
            pst.setString(7,rt.getText());
            pst.setString(8,cgst.getText());
            pst.setString(9,sgst.getText());
            pst.setString(10,hsn.getText()); 
            pst.setString(11,subt.getText());
            pst.setString(12,tax.getText());
            pst.setString(13,tot.getText());
           
            pst.setString(14,rt.getText());
            pst.setString(15,f10);
            pst.setString(16,f3);
            pst.setString(17,f2);
            pst.setString(18,f4);
            pst.setString(19,f5);
            pst.setString(20,f6);
            pst.setString(21,f1);
            pst.setString(22,f7);
            pst.setString(23, f8);
            pst.setString(24,amx);
             pst.setString(25, mx);
            pst.setString(26, f9);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Invoice Updated Successfully...");
            report();
            bno.setText("");
            name.setText("");
            cont.setText("");
            add.setText("");
            des.setText("");
           
            pname.setText("");
            rt.setText("");
            cgst.setText("");
            sgst.setText("");
           hsn.setText("");
           subt.setText("");
           tax.setText("");
           tot.setText("");
          rt.setText("");
          amx="";
          mx="";
            f1="";
           f2="";
           reset();

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);

        }
    }//GEN-LAST:event_jButton2ActionPerformed
String cg="",sg="",subto="",taxx="";
    public void cgsttot()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select sum(cgst) from bill where billno='"+bno.getText()+"'";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
               cgst.setText(rs.getString("sum(cgst)"));
               
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
     public void sgsttot()
{
    try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select sum(sgst) from bill where billno='"+bno.getText()+"'";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
              sgst.setText(rs.getString("sum(sgst)"));
               
            }
        }
        catch(Exception e)
        {
            
        }

}
     public void sutot()
{
    try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            String sql="Select sum(subtotal),sum(tax) from bill where billno='"+bno.getText()+"'";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
               subt.setText(rs.getString("sum(subtotal)"));
              tax.setText(rs.getString("sum(tax)"));
               
            }
        }
        catch(Exception e)
        {
            
        }
}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed
public void reset()
{
                name.setText("");
                date.setText("");
            cont.setText("");
            add.setText("");
            des.setText("");
           pname.setText("");
            rt.setText("");
            cgst.setText("");
            sgst.setText("");
           hsn.setText("");
           subt.setText("");
           tax.setText("");
           tot.setText("");
           rt.setText("");
           data();
               irate.clear();
    icount.clear();
    icgst.clear();
    isgst.clear();
    igst.clear();
    isubtot.clear();
    ihsn.clear();
    itax.clear();
    itax1.clear();
           f3="";f4="";f5="";f6="";f7="";f8="";f9="";f10="";f11="";
}
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Menu i=new  Menu();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed
ArrayList<String> iproduct = new ArrayList<>();

ArrayList<String> irate = new ArrayList<>();
ArrayList<String> icount = new ArrayList<>();
ArrayList<String> icgst = new ArrayList<>();
ArrayList<String> isgst = new ArrayList<>();
ArrayList<String> igst = new ArrayList<>();
ArrayList<String> isubtot = new ArrayList<>();
ArrayList<String> ihsn = new ArrayList<>();
ArrayList<String> itax = new ArrayList<>();
ArrayList<String> itax1 = new ArrayList<>();
String f1="",f2="",f3="",f4="",f5="",f6="",f7="",f8="",f9="",f10="",f11="";
int klk=0;
public void store()
{
    billda();
   int cout=ihsn.size();
   for(int kgf=0;kgf<cout;kgf++)
   {
       f1+=ihsn.get(kgf)+"\n\n";
       f2+=iproduct.get(kgf)+"\n\n";
       f3+=irate.get(kgf)+"\n\n";
       f4+=icount.get(kgf)+"\n\n";
       f5+=icgst.get(kgf)+"\n\n";
       f6+=isgst.get(kgf)+"\n\n";
       f7+=igst.get(kgf)+"\n\n";
       f8+=isubtot.get(kgf)+"\n\n";
       f9+=itax1.get(kgf)+"\n\n";
       klk+=1;
       f10+=String.valueOf(klk)+"\n\n";
       
   }
   
}

public void billda()
{
    String sql="SELECT * from bill where billno='"+bno.getText()+"'";
       try {
            
        Class.forName("com.mysql.jdbc.Driver");
            Connection conc=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
   
PreparedStatement pst=conc.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
             while(rs.next())
             {
                 
                 ihsn.add(rs.getString("hsn"));
                 iproduct.add(rs.getString("product"));
                
                 icgst.add(rs.getString("cgst"));
                 isgst.add(rs.getString("sgst"));
                 igst.add(rs.getString("gst"));
                 isubtot.add(rs.getString("subtotal"));
                 irate.add(rs.getString("rate"));
                 icount.add(rs.getString("count"));
                 itax.add(rs.getString("tax"));
                 itax1.add(rs.getString("tax")); 
                
        }
       }
             catch (ClassNotFoundException | SQLException e) {
                 JOptionPane.showMessageDialog(null, e);
        }
}




public void adddata()
{
    String ffd=tax.getText();
          String amx;
  double aamt,aa,ab,ac;
   aamt = Double.parseDouble(ffd);
    amx= convertNumberToWords(aamt)+" Rupees Only";
     try
          {
              
             Class.forName("com.mysql.jdbc.Driver");
             Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
             
             String sql="Insert into bill(billno,hsn,product,rate,count,cgst,sgst,gst,subtotal,tax,word ) values(?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,bno.getText());
             pst.setString(2, hsn.getText());
             pst.setString(3,pname.getText());          
             pst.setString(4, rt.getText());
             pst.setString(5, cou.getText());
             pst.setString(6, sgst.getText());
             pst.setString(7, cgst.getText());
             pst.setString(8, gst.getText());
             pst.setString(9, subt.getText());
             pst.setString(10,tax.getText());
      
            pst.setString(11, amx);
             pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "Added Successfully...");
          }
     catch(Exception e)
     {
         
     }
}
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
              try
        {

            int row=tab.getSelectedRow();
            String cell=tab.getModel().getValueAt(row,2).toString();
            String sql="Delete from bill where product=?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jesh","root","hitech");
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,cell);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Delete Successfull");
            data();
           
           totalf();

          
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new up().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea add;
    private javax.swing.JTextField bno;
    private javax.swing.JTextField cgst;
    private javax.swing.JTextField cont;
    private javax.swing.JTextField cou;
    private javax.swing.JTextField date;
    private javax.swing.JTextArea des;
    private javax.swing.JTextField gst;
    private javax.swing.JTextField hsn;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField name;
    private javax.swing.JTextArea pname;
    private javax.swing.JTextField rt;
    private javax.swing.JButton search;
    private javax.swing.JTextField sgst;
    private javax.swing.JTextField subt;
    private javax.swing.JTable tab;
    private javax.swing.JTextField tax;
    private javax.swing.JTextField tot;
    // End of variables declaration//GEN-END:variables
}
